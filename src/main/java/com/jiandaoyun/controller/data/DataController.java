package com.jiandaoyun.controller.data;

import com.jiandaoyun.common.model.ApiResponse;
import com.jiandaoyun.dto.request.SubmitDataRequest;
import com.jiandaoyun.service.data.DataService;
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
 * 动态数据控制器。
 *
 * @author Codex
 * @since 0.1.0
 */
@RestController
@RequestMapping("/api/data")
public class DataController {

    private final DataService dataService;

    /**
     * 构造函数。
     *
     * @param dataService 数据服务
     */
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    /**
     * 提交一条表单数据。
     *
     * @param request 提交请求
     * @return 保存后的记录
     */
    @PostMapping("/submit")
    public ApiResponse<Map<String, Object>> submit(@Valid @RequestBody SubmitDataRequest request) {
        return ApiResponse.ok(dataService.submit(request));
    }

    /**
     * 按表单 ID 查询数据列表。
     *
     * @param formId 表单 ID
     * @return 记录列表
     */
    @GetMapping("/{formId}")
    public ApiResponse<List<Map<String, Object>>> listByFormId(@PathVariable String formId) {
        return ApiResponse.ok(dataService.listByFormId(formId));
    }
}