package com.jiandaoyun.controller.data;

import com.jiandaoyun.common.model.ApiResponse;
import com.jiandaoyun.data.application.service.DataApplicationService;
import com.jiandaoyun.dto.request.SubmitDataRequest;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据控制器.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@RestController
@RequestMapping("/api/data")
public class DataController {

    private final DataApplicationService dataApplicationService;

    /**
     * 构造数据控制器实例.
     *
     * @param dataApplicationService 数据应用服务实例.
     */
    public DataController(DataApplicationService dataApplicationService) {
        this.dataApplicationService = dataApplicationService;
    }

    /**
     * 提交表单数据.
     *
     * @param request 数据提交请求.
     * @return 提交后的记录数据.
     */
    @PostMapping("/submit")
    public ApiResponse<Map<String, Object>> submit(@Valid @RequestBody SubmitDataRequest request) {
        return ApiResponse.ok(dataApplicationService.submit(request));
    }

    /**
     * 按表单标识查询数据列表.
     *
     * @param formId 表单标识.
     * @return 表单数据列表.
     */
    @GetMapping("/{formId}")
    public ApiResponse<List<Map<String, Object>>> listByFormId(@PathVariable String formId) {
        return ApiResponse.ok(dataApplicationService.listByFormId(formId));
    }
}
