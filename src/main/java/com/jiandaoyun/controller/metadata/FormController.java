package com.jiandaoyun.controller.metadata;

import com.jiandaoyun.common.model.ApiResponse;
import com.jiandaoyun.domain.metadata.FormDefinition;
import com.jiandaoyun.dto.request.CreateFormRequest;
import com.jiandaoyun.metadata.application.service.FormApplicationService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 表单控制器.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@RestController
@RequestMapping("/api/forms")
public class FormController {

    private final FormApplicationService formApplicationService;

    /**
     * 构造表单控制器实例.
     *
     * @param formApplicationService 表单应用服务实例.
     */
    public FormController(FormApplicationService formApplicationService) {
        this.formApplicationService = formApplicationService;
    }

    /**
     * 创建表单定义.
     *
     * @param request 创建表单请求.
     * @return 新建的表单定义.
     */
    @PostMapping
    public ApiResponse<FormDefinition> create(@Valid @RequestBody CreateFormRequest request) {
        return ApiResponse.ok(formApplicationService.create(request));
    }

    /**
     * 根据标识查询表单定义.
     *
     * @param formId 表单标识.
     * @return 表单定义.
     */
    @GetMapping("/{formId}")
    public ApiResponse<FormDefinition> getById(@PathVariable String formId) {
        return ApiResponse.ok(formApplicationService.getById(formId));
    }

    /**
     * 查询全部表单定义.
     *
     * @return 表单定义列表.
     */
    @GetMapping
    public ApiResponse<List<FormDefinition>> listAll() {
        return ApiResponse.ok(formApplicationService.listAll());
    }
}
