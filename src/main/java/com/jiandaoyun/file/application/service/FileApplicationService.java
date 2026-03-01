package com.jiandaoyun.file.application.service;

import com.jiandaoyun.file.domain.repository.FileStatusRepository;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * 文件应用服务.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Service
public class FileApplicationService {

    private final FileStatusRepository fileStatusRepository;

    /**
     * 构造文件应用服务实例.
     *
     * @param fileStatusRepository 文件状态仓储.
     */
    public FileApplicationService(FileStatusRepository fileStatusRepository) {
        this.fileStatusRepository = fileStatusRepository;
    }

    /**
     * 获取文件模块健康状态.
     *
     * @return 健康状态信息.
     */
    public Map<String, String> health() {
        return fileStatusRepository.getStatus();
    }
}
