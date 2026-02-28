package com.jiandaoyun.service.core;

import com.jiandaoyun.common.constant.FieldType;
import com.jiandaoyun.common.exception.BusinessException;
import com.jiandaoyun.domain.metadata.FieldDefinition;
import com.jiandaoyun.domain.metadata.FormDefinition;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    @Override
    public void validateSubmission(FormDefinition formDefinition, Map<String, Object> data) {
        for (FieldDefinition field : formDefinition.getFields()) {
            Object value = data.get(field.getKey());
            if (field.isRequired() && value == null) {
                throw new BusinessException("必填字段缺失: " + field.getKey());
            }
            if (value != null) {
                validateType(field.getType(), field.getKey(), value);
            }
        }
    }

    private void validateType(FieldType fieldType, String key, Object value) {
        boolean valid = switch (fieldType) {
            case TEXT -> value instanceof String;
            case NUMBER -> value instanceof Number;
            case BOOLEAN -> value instanceof Boolean;
            case DATE -> value instanceof String;
        };
        if (!valid) {
            throw new BusinessException("字段类型错误: " + key + " 应为 " + fieldType);
        }
    }
}