package com.jiandaoyun.controller.file;

import com.jiandaoyun.common.model.ApiResponse;
import com.jiandaoyun.service.file.FileService;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件控制器.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@RestController
@RequestMapping("/api/file")
public class FileController {

    private final FileService fileService;

    /**
     * 构造文件控制器实例.
     *
     * @param fileService 文件服务实例.
     */
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 获取文件模块健康状态.
     *
     * @return 健康状态信息.
     */
    @GetMapping("/health")
    public ApiResponse<Map<String, String>> health() {
        return ApiResponse.ok(fileService.health());
    }
}
