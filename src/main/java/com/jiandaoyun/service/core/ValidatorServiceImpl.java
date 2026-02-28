package com.jiandaoyun.service.core;

import com.jiandaoyun.common.constant.FieldType;
import com.jiandaoyun.common.exception.BusinessException;
import com.jiandaoyun.domain.metadata.FieldDefinition;
import com.jiandaoyun.domain.metadata.FormDefinition;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * Default field-level validator implementation.
 */
@Service
public class ValidatorServiceImpl implements ValidatorService {

    /**
     * {@inheritDoc}
     *
     * @throws BusinessException when required field is missing or type mismatch occurs
     */
    @Override
    public void validateSubmission(FormDefinition formDefinition, Map<String, Object> data) {
        for (FieldDefinition field : formDefinition.getFields()) {
            Object value = data.get(field.getKey());
            if (field.isRequired() && value == null) {
                throw new BusinessException("required field is missing: " + field.getKey());
            }
            if (value != null) {
                validateType(field.getType(), field.getKey(), value);
            }
        }
    }

    /**
     * Validates field value type.
     *
     * @param fieldType expected type
     * @param key field key
     * @param value field value
     */
    private void validateType(FieldType fieldType, String key, Object value) {
        boolean valid = switch (fieldType) {
            case TEXT -> value instanceof String;
            case NUMBER -> value instanceof Number;
            case BOOLEAN -> value instanceof Boolean;
            case DATE -> value instanceof String;
        };
        if (!valid) {
            throw new BusinessException("field type mismatch: " + key + " expects " + fieldType);
        }
    }
}