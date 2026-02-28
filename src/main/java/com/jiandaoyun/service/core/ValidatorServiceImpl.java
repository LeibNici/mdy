package com.jiandaoyun.service.core;

import com.jiandaoyun.common.constant.FieldType;
import com.jiandaoyun.common.exception.BusinessException;
import com.jiandaoyun.domain.metadata.FieldDefinition;
import com.jiandaoyun.domain.metadata.FormDefinition;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * 提交数据校验服务实现.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
@Service
public class ValidatorServiceImpl implements ValidatorService {

    /**
     * 校验表单提交数据.
     *
     * @param formDefinition 表单定义.
     * @param data 提交数据.
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
     * 校验字段值类型.
     *
     * @param fieldType 字段类型.
     * @param key 字段编码.
     * @param value 字段值.
     * @throws BusinessException 字段类型不匹配时抛出异常.
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
