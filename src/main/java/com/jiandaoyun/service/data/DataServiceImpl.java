package com.jiandaoyun.service.data;

import com.jiandaoyun.data.application.service.DataApplicationService;
import com.jiandaoyun.dto.request.SubmitDataRequest;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * 数据服务适配实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Service
public class DataServiceImpl implements DataService {

    private final DataApplicationService dataApplicationService;

    /**
     * 构造数据服务适配实例.
     *
     * @param dataApplicationService 数据应用服务.
     */
    public DataServiceImpl(DataApplicationService dataApplicationService) {
        this.dataApplicationService = dataApplicationService;
    }

    /**
     * 提交表单数据.
     *
     * @param request 数据提交请求.
     * @return 持久化后的记录.
     */
    @Override
    public Map<String, Object> submit(SubmitDataRequest request) {
        return dataApplicationService.submit(request);
    }

    /**
     * 按表单标识查询数据列表.
     *
     * @param formId 表单标识.
     * @return 数据记录列表.
     */
    @Override
    public List<Map<String, Object>> listByFormId(String formId) {
        return dataApplicationService.listByFormId(formId);
    }
}
