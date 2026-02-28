package com.jiandaoyun.service.core;

import com.jiandaoyun.common.constant.FieldType;
import com.jiandaoyun.common.exception.BusinessException;
import com.jiandaoyun.domain.metadata.FieldDefinition;
import com.jiandaoyun.domain.metadata.FormDefinition;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * 默认字段校验实现。
 *
 * @author Codex
 * @since 0.1.0
 */
@Service
public class ValidatorServiceImpl implements ValidatorService {

    /**
     * {@inheritDoc}
     *
     * @throws BusinessException 必填项缺失或类型不匹配时抛出
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
     * 校验字段值类型。
     *
     * @param fieldType 预期类型
     * @param key 字段键
     * @param value 字段值
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