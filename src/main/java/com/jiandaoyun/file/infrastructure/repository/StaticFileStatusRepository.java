package com.jiandaoyun.file.infrastructure.repository;

import com.jiandaoyun.file.domain.repository.FileStatusRepository;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * 静态文件模块状态仓储实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Repository
public class StaticFileStatusRepository implements FileStatusRepository {

    /**
     * 获取文件模块状态.
     *
     * @return 状态信息.
     */
    @Override
    public Map<String, String> getStatus() {
        return Map.of("status", "ok");
    }
}
