package com.jiandaoyun.service.data;

import com.jiandaoyun.dto.request.SubmitDataRequest;
import java.util.List;
import java.util.Map;

/**
 * 动态数据服务接口。
 *
 * @author Codex
 * @since 0.1.0
 */
public interface DataService {

    /**
     * 提交一条记录。
     *
     * @param request 提交请求
     * @return 保存后的记录
     */
    Map<String, Object> submit(SubmitDataRequest request);

    /**
     * 按表单 ID 查询记录。
     *
     * @param formId 表单 ID
     * @return 记录列表
     */
    List<Map<String, Object>> listByFormId(String formId);
}