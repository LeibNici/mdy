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

@Service
public class DataServiceImpl implements DataService {

    private final FormService formService;
    private final ValidatorService validatorService;
    private final ConcurrentHashMap<String, List<Map<String, Object>>> records = new ConcurrentHashMap<>();

    public DataServiceImpl(FormService formService, ValidatorService validatorService) {
        this.formService = formService;
        this.validatorService = validatorService;
    }

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

    @Override
    public List<Map<String, Object>> listByFormId(String formId) {
        return records.getOrDefault(formId, List.of());
    }
}