package com.jiandaoyun.controller.metadata;

import com.jiandaoyun.common.model.ApiResponse;
import com.jiandaoyun.domain.metadata.FormDefinition;
import com.jiandaoyun.dto.request.CreateFormRequest;
import com.jiandaoyun.service.metadata.FormService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 鐞涖劌宕熼崗鍐╂殶閹诡喗甯堕崚璺烘珤閵? *
 *
 * @author Codex
 *
 * @since 2026/02/28
 */
@RestController
@RequestMapping("/api/forms")

public class FormController {

    private final FormService formService;

    /**
     * 閺嬪嫰鈧姴鍤遍弫鑸偓?     *
 *
 * @param formService 鐞涖劌宕熼張宥呭
     */
    public FormController(FormService formService) {
        this.formService = formService;
    }

    /**
     * 閸掓稑缂撶悰銊ュ礋鐎规矮绠熼妴?     *
 *
 * @param request 閸掓稑缂撶拠閿嬬湴
 *
 * @return 閸掓稑缂撻崥搴ｆ畱鐞涖劌宕熺€规矮绠?
     */
    @PostMapping
    public ApiResponse<FormDefinition> create(@Valid @RequestBody CreateFormRequest request) {
        return ApiResponse.ok(formService.create(request));
    }

    /**
     * 閹?ID 閼惧嘲褰囩悰銊ュ礋鐎规矮绠熼妴?     *
 *
 * @param formId 鐞涖劌宕?ID
 *
 * @return 鐞涖劌宕熺€规矮绠?
     */
    @GetMapping("/{formId}")
    public ApiResponse<FormDefinition> getById(@PathVariable String formId) {
        return ApiResponse.ok(formService.getById(formId));
    }

    /**
     * 閺屻儴顕楅崗銊╁劥鐞涖劌宕熺€规矮绠熼妴?     *
 *
 * @return 鐞涖劌宕熺€规矮绠熼崚妤勩€?
     */
    @GetMapping
    public ApiResponse<List<FormDefinition>> listAll() {
        return ApiResponse.ok(formService.listAll());
    }
}