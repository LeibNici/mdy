package com.jiandaoyun.service.data;

import com.jiandaoyun.common.utils.IdGenerator;
import com.jiandaoyun.domain.metadata.FormDefinition;
import com.jiandaoyun.dto.request.SubmitDataRequest;
import com.jiandaoyun.service.core.ValidatorService;
import com.jiandaoyun.service.metadata.FormService;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/**
 * 数据服务实现.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
@Service
public class DataServiceImpl implements DataService {

    private final FormService formService;

    private final ValidatorService validatorService;

    private final ConcurrentHashMap<String, List<Map<String, Object>>> records = new ConcurrentHashMap<>();

    /**
     * 构造数据服务实例.
     *
     * @param formService 表单服务实例.
     * @param validatorService 校验服务实例.
     */
    public DataServiceImpl(FormService formService, ValidatorService validatorService) {
        this.formService = formService;
        this.validatorService = validatorService;
    }

    /**
     * 提交一条表单数据.
     *
     * @param request 数据提交请求.
     * @return 持久化后的记录数据.
     */
    @Override
    public Map<String, Object> submit(SubmitDataRequest request) {
        FormDefinition form = formService.getById(request.getFormId());
        validatorService.validateSubmission(form, request.getData());

        Map<String, Object> record = new HashMap<>(request.getData());
        record.put("id", IdGenerator.nextId());
        record.put("formId", request.getFormId());
        record.put("createdAt", Instant.now().toString());

        records.computeIfAbsent(request.getFormId(), key -> new ArrayList<>()).add(record);
        return record;
    }

    /**
     * 根据表单标识查询数据列表.
     *
     * @param formId 表单标识.
     * @return 表单数据列表.
     */
    @Override
    public List<Map<String, Object>> listByFormId(String formId) {
        return records.getOrDefault(formId, List.of());
    }
}
