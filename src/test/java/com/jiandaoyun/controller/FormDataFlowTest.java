package com.jiandaoyun.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiandaoyun.shared.kernel.outbox.OutboxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 表单与数据提交流程集成测试.
 *
 * @author Codex
 *
 * @since 2026/02/28
 */
@SpringBootTest
@AutoConfigureMockMvc
class FormDataFlowTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OutboxService outboxService;

    @Test
    void shouldCreateFormAndSubmitData() throws Exception {
        String createFormBody = """
            {
              "name": "leave_form",
              "description": "leave_apply",
              "fields": [
                {"key":"name","label":"name","type":"TEXT","required":true},
                {"key":"days","label":"days","type":"NUMBER","required":true}
              ]
            }
            """;

        MvcResult formResult = mockMvc.perform(post("/api/forms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createFormBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andReturn();

        JsonNode formJson = objectMapper.readTree(formResult.getResponse().getContentAsString());
        String formId = formJson.path("data").path("id").asText();
        if (formId.isBlank()) {
            throw new IllegalStateException("formId should not be blank");
        }

        String submitBody = """
            {
              "formId": "%s",
              "data": {"name":"tom", "days": 3}
            }
            """.formatted(formId);

        mockMvc.perform(post("/api/data/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(submitBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.data.formId").value(formId));

        mockMvc.perform(get("/api/reports/forms/{formId}/summary", formId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.totalRecords").value(1));
    }

    @Test
    void shouldRejectInvalidDataType() throws Exception {
        String createFormBody = """
            {
              "name": "expense_form",
              "fields": [
                {"key":"amount","label":"amount","type":"NUMBER","required":true}
              ]
            }
            """;

        MvcResult formResult = mockMvc.perform(post("/api/forms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createFormBody))
            .andExpect(status().isOk())
            .andReturn();

        String formId = objectMapper.readTree(formResult.getResponse().getContentAsString())
            .path("data").path("id").asText();

        String invalidBody = """
            {
              "formId": "%s",
              "data": {"amount":"one_hundred"}
            }
            """.formatted(formId);

        mockMvc.perform(post("/api/data/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400));
    }

    @Test
    void shouldRejectUnknownField() throws Exception {
        String createFormBody = """
            {
              "name": "contract_form",
              "fields": [
                {"key":"title","label":"title","type":"TEXT","required":true}
              ]
            }
            """;

        MvcResult formResult = mockMvc.perform(post("/api/forms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createFormBody))
            .andExpect(status().isOk())
            .andReturn();

        String formId = objectMapper.readTree(formResult.getResponse().getContentAsString())
            .path("data").path("id").asText();

        String invalidBody = """
            {
              "formId": "%s",
              "data": {"title":"agreement", "extra":"invalid"}
            }
            """.formatted(formId);

        mockMvc.perform(post("/api/data/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.message").value("unknown field: extra"));
    }

    @Test
    void shouldAppendOutboxMessages() throws Exception {
        long before = outboxService.count();
        String createFormBody = """
            {
              "name": "event_form",
              "fields": [
                {"key":"title","label":"title","type":"TEXT","required":true}
              ]
            }
            """;

        MvcResult formResult = mockMvc.perform(post("/api/forms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createFormBody))
            .andExpect(status().isOk())
            .andReturn();

        String formId = objectMapper.readTree(formResult.getResponse().getContentAsString())
            .path("data").path("id").asText();

        String submitBody = """
            {
              "formId": "%s",
              "data": {"title":"contract"}
            }
            """.formatted(formId);

        mockMvc.perform(post("/api/data/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(submitBody))
            .andExpect(status().isOk());

        long after = outboxService.count();
        if (after < before + 2) {
            throw new IllegalStateException("outbox message count should increase by at least 2");
        }
    }
}
