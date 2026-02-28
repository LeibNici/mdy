package com.jiandaoyun.service.data;

import com.jiandaoyun.dto.request.SubmitDataRequest;
import java.util.List;
import java.util.Map;

/**
 * 数据服务接口.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */

public interface DataService {

    /**
     * .
     *
     * @param request 请求参数.
     * @return 处理结果.
     */
    Map<String, Object> submit(SubmitDataRequest request);

    /**
     * 查询.
     *
     * @param formId 表单标识.
     * @return 处理结果.
     */
    List<Map<String, Object>> listByFormId(String formId);
}
