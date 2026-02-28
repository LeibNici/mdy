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

@RestController
@RequestMapping("/api/forms")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @PostMapping
    public ApiResponse<FormDefinition> create(@Valid @RequestBody CreateFormRequest request) {
        return ApiResponse.ok(formService.create(request));
    }

    @GetMapping("/{formId}")
    public ApiResponse<FormDefinition> getById(@PathVariable String formId) {
        return ApiResponse.ok(formService.getById(formId));
    }

    @GetMapping
    public ApiResponse<List<FormDefinition>> listAll() {
        return ApiResponse.ok(formService.listAll());
    }
}