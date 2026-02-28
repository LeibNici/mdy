package com.jiandaoyun.service.data;

import com.jiandaoyun.dto.request.SubmitDataRequest;
import java.util.List;
import java.util.Map;

/**
 * 閸斻劍鈧焦鏆熼幑顔芥箛閸斺剝甯撮崣锝冣偓? *
 *
 * @author Codex
 *
 * @since 2026/02/28
 */

public interface DataService {

    /**
     * 閹绘劒姘︽稉鈧弶陇顔囪ぐ鏇樷偓?     *
 *
 * @param request 閹绘劒姘︾拠閿嬬湴
 *
 * @return 娣囨繂鐡ㄩ崥搴ｆ畱鐠佹澘缍?
     */
    Map<String, Object> submit(SubmitDataRequest request);

    /**
     * 閹稿銆冮崡?ID 閺屻儴顕楃拋鏉跨秿閵?     *
 *
 * @param formId 鐞涖劌宕?ID
 *
 * @return 鐠佹澘缍嶉崚妤勩€?
     */
    List<Map<String, Object>> listByFormId(String formId);
}