package com.jiandaoyun.domain.workflow;

import com.jiandaoyun.common.enums.WorkflowStatus;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Runtime workflow instance for one data record.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowInstance {
    private String id;
    private String formId;
    private String recordId;
    private String applicant;
    private String currentTaskId;
    private WorkflowStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}