package com.jiandaoyun.service.metadata;

import com.jiandaoyun.domain.metadata.FormDefinition;
import com.jiandaoyun.dto.request.CreateFormRequest;
import java.util.List;

/**
 * 鐞涖劌宕熼崗鍐╂殶閹诡喗婀囬崝鈩冨复閸欙絻鈧? *
 *
 * @author Codex
 *
 * @since 2026/02/28
 */

public interface FormService {

    /**
     * 閸掓稑缂撻獮璺哄絺鐢啳銆冮崡鏇炵暰娑斿鈧?     *
 *
 * @param request 閸掓稑缂撶拠閿嬬湴
 *
 * @return 閸掓稑缂撻崥搴ｆ畱鐞涖劌宕熺€规矮绠?
     */
    FormDefinition create(CreateFormRequest request);

    /**
     * 閹?ID 閺屻儴顕楃悰銊ュ礋鐎规矮绠熼妴?     *
 *
 * @param formId 鐞涖劌宕?ID
 *
 * @return 鐞涖劌宕熺€规矮绠?
     */
    FormDefinition getById(String formId);

    /**
     * 閺屻儴顕楅崗銊╁劥鐞涖劌宕熺€规矮绠熼妴?     *
 *
 * @return 鐞涖劌宕熺€规矮绠熼崚妤勩€?
     */
    List<FormDefinition> listAll();
}