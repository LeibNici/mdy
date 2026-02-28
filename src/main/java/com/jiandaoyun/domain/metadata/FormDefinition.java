package com.jiandaoyun.domain.metadata;

import com.jiandaoyun.common.enums.FormStatus;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormDefinition {
    private String id;
    private String name;
    private String description;
    private FormStatus status;
    private int version;
    private List<FieldDefinition> fields;
    private Instant createdAt;
    private Instant updatedAt;
}