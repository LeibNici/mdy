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
 * REST controller for form data operations.
 */
@RestController
@RequestMapping("/api/data")
public class DataController {

    private final DataService dataService;

    /**
     * Creates controller instance.
     *
     * @param dataService data service
     */
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    /**
     * Submits one form data record.
     *
     * @param request submit request
     * @return submitted record
     */
    @PostMapping("/submit")
    public ApiResponse<Map<String, Object>> submit(@Valid @RequestBody SubmitDataRequest request) {
        return ApiResponse.ok(dataService.submit(request));
    }

    /**
     * Lists records by form ID.
     *
     * @param formId form ID
     * @return record list
     */
    @GetMapping("/{formId}")
    public ApiResponse<List<Map<String, Object>>> listByFormId(@PathVariable String formId) {
        return ApiResponse.ok(dataService.listByFormId(formId));
    }
}