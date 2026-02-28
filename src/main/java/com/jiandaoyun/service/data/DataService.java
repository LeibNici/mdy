package com.jiandaoyun.service.data;

import com.jiandaoyun.dto.request.SubmitDataRequest;
import java.util.List;
import java.util.Map;

public interface DataService {
    Map<String, Object> submit(SubmitDataRequest request);

    List<Map<String, Object>> listByFormId(String formId);
}