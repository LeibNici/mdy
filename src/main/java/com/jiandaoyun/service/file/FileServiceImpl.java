package com.jiandaoyun.service.file;

import com.jiandaoyun.file.application.service.FileApplicationService;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * 文件服务适配实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Service
public class FileServiceImpl implements FileService {

    private final FileApplicationService fileApplicationService;

    /**
     * 构造文件服务适配实例.
     *
     * @param fileApplicationService 文件应用服务.
     */
    public FileServiceImpl(FileApplicationService fileApplicationService) {
        this.fileApplicationService = fileApplicationService;
    }

    /**
     * 获取文件模块健康状态.
     *
     * @return 健康状态信息.
     */
    @Override
    public Map<String, String> health() {
        return fileApplicationService.health();
    }
}
