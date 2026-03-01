package com.jiandaoyun.auth.infrastructure.repository;

import com.jiandaoyun.auth.domain.repository.AuthStatusRepository;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * 静态认证状态仓储实现.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
@Repository
public class StaticAuthStatusRepository implements AuthStatusRepository {

    /**
     * 获取认证模块状态.
     *
     * @return 状态信息.
     */
    @Override
    public Map<String, String> getStatus() {
        return Map.of("status", "ok");
    }
}
