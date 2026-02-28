package com.jiandaoyun.controller.data;

import com.jiandaoyun.common.model.ApiResponse;
import com.jiandaoyun.dto.request.SubmitDataRequest;
import com.jiandaoyun.service.data.DataService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 閸斻劍鈧焦鏆熼幑顔藉付閸掕泛娅掗妴? *
 *
 * @author Codex
 *
 * @since 2026/02/28
 */
@RestController
@RequestMapping("/api/data")

public class DataController {

    private final DataService dataService;

    /**
     * 閺嬪嫰鈧姴鍤遍弫鑸偓?     *
 *
 * @param dataService 閺佺増宓侀張宥呭
     */
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    /**
     * 閹绘劒姘︽稉鈧弶陇銆冮崡鏇熸殶閹诡喓鈧?     *
 *
 * @param request 閹绘劒姘︾拠閿嬬湴
 *
 * @return 娣囨繂鐡ㄩ崥搴ｆ畱鐠佹澘缍?
     */
    @PostMapping("/submit")
    public ApiResponse<Map<String, Object>> submit(@Valid @RequestBody SubmitDataRequest request) {
        return ApiResponse.ok(dataService.submit(request));
    }

    /**
     * 閹稿銆冮崡?ID 閺屻儴顕楅弫鐗堝祦閸掓銆冮妴?     *
 *
 * @param formId 鐞涖劌宕?ID
 *
 * @return 鐠佹澘缍嶉崚妤勩€?
     */
    @GetMapping("/{formId}")
    public ApiResponse<List<Map<String, Object>>> listByFormId(@PathVariable String formId) {
        return ApiResponse.ok(dataService.listByFormId(formId));
    }
}