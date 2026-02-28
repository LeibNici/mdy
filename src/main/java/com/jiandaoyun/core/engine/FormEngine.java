package com.jiandaoyun.core.engine;

import com.jiandaoyun.common.enums.FormStatus;
import com.jiandaoyun.common.exception.BusinessException;
import com.jiandaoyun.domain.metadata.FieldDefinition;
import com.jiandaoyun.domain.metadata.FormDefinition;
import com.jiandaoyun.dto.request.CreateFormRequest;
import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 * 表单引擎.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
@Component
public class FormEngine {

    /**
     * 构建表单定义对象.
     *
     * @param formId 表单标识.
     * @param request 创建表单请求.
     * @param now 当前时间.
     * @return 表单定义对象.
     */
    public FormDefinition buildForm(String formId, CreateFormRequest request, Instant now) {
        List<FieldDefinition> fields = buildFields(request.getFields());
        return FormDefinition.builder()
            .id(formId)
            .name(request.getName())
            .description(request.getDescription())
            .status(FormStatus.PUBLISHED)
            .version(1)
            .fields(fields)
            .createdAt(now)
            .updatedAt(now)
            .build();
    }

    /**
     * 规范化提交数据并校验未知字段.
     *
     * @param formDefinition 表单定义.
     * @param rawData 原始提交数据.
     * @return 规范化后的提交数据.
     * @throws BusinessException 提交数据包含未知字段时抛出异常.
     */
    public Map<String, Object> normalizeSubmission(FormDefinition formDefinition, Map<String, Object> rawData) {
        Map<String, Object> normalized = new LinkedHashMap<>();
        Set<String> allowedKeys = new HashSet<>();
        for (FieldDefinition field : formDefinition.getFields()) {
            String key = field.getKey();
            allowedKeys.add(key);
            normalized.put(key, rawData.get(key));
        }
        for (String key : rawData.keySet()) {
            if (!allowedKeys.contains(key)) {
                throw new BusinessException("unknown field: " + key);
            }
        }
        return normalized;
    }

    /**
     * 构建字段定义列表并校验字段键唯一性.
     *
     * @param fieldRequests 字段请求列表.
     * @return 字段定义列表.
     */
    private List<FieldDefinition> buildFields(List<CreateFormRequest.FieldRequest> fieldRequests) {
        Set<String> keySet = new HashSet<>();
        return fieldRequests.stream()
            .map(field -> {
                String key = field.getKey();
                if (!keySet.add(key)) {
                    throw new BusinessException("duplicate field key: " + key);
                }
                return FieldDefinition.builder()
                    .key(key)
                    .label(field.getLabel())
                    .type(field.getType())
                    .required(field.isRequired())
                    .build();
            })
            .toList();
    }
}
