package com.jiandaoyun.service.workflow;

import com.jiandaoyun.domain.workflow.WorkflowInstance;
import com.jiandaoyun.dto.request.ApproveTaskRequest;
import com.jiandaoyun.dto.request.StartWorkflowRequest;

/**
 * 瀹搞儰缍斿ù浣虹椽閹烘帗婀囬崝鈩冨复閸欙絻鈧? *
 *
 * @author Codex
 *
 * @since 2026/02/28
 */

public interface WorkflowService {

    /**
     * 閸氼垰濮╁ù浣衡柤鐎圭偘绶ラ妴?     *
 *
 * @param request 閸氼垰濮╃拠閿嬬湴
 *
 * @return 濞翠胶鈻肩€圭偘绶?
     */
    WorkflowInstance start(StartWorkflowRequest request);

    /**
     * 鐎光剝澹掗幋鏍攺閸ョ偞绁︾粙瀣╂崲閸斅扳偓?     *
 *
 * @param request 鐎光剝澹掔拠閿嬬湴
 *
 * @return 閺囧瓨鏌婇崥搴ｆ畱濞翠胶鈻肩€圭偘绶?
     */
    WorkflowInstance approve(ApproveTaskRequest request);

    /**
     * 閹?ID 閺屻儴顕楀ù浣衡柤鐎圭偘绶ラ妴?     *
 *
 * @param instanceId 濞翠胶鈻肩€圭偘绶?ID
 *
 * @return 濞翠胶鈻肩€圭偘绶?
     */
    WorkflowInstance getById(String instanceId);
}