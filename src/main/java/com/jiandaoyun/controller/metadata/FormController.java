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
 * 表单元数据控制器。
 *
 * @author Codex
 * @since 0.1.0
 */
@RestController
@RequestMapping("/api/forms")
public class FormController {

    private final FormService formService;

    /**
     * 构造函数。
     *
     * @param formService 表单服务
     */
    public FormController(FormService formService) {
        this.formService = formService;
    }

    /**
     * 创建表单定义。
     *
     * @param request 创建请求
     * @return 创建后的表单定义
     */
    @PostMapping
    public ApiResponse<FormDefinition> create(@Valid @RequestBody CreateFormRequest request) {
        return ApiResponse.ok(formService.create(request));
    }

    /**
     * 按 ID 获取表单定义。
     *
     * @param formId 表单 ID
     * @return 表单定义
     */
    @GetMapping("/{formId}")
    public ApiResponse<FormDefinition> getById(@PathVariable String formId) {
        return ApiResponse.ok(formService.getById(formId));
    }

    /**
     * 查询全部表单定义。
     *
     * @return 表单定义列表
     */
    @GetMapping
    public ApiResponse<List<FormDefinition>> listAll() {
        return ApiResponse.ok(formService.listAll());
    }
}