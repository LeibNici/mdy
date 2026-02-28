package com.jiandaoyun.domain.workflow;

import com.jiandaoyun.common.enums.WorkflowStatus;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 閸楁洘娼稉姘閺佺増宓佺€电懓绨查惃鍕ウ缁嬪鐤勬笟瀣ㄢ偓? *
 *
 * @author Codex
 *
 * @since 2026/02/28
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