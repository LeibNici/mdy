package com.jiandaoyun.file.domain.repository;

import java.util.Map;

/**
 * 文件模块状态仓储端口.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public interface FileStatusRepository {

    /**
     * 获取文件模块状态.
     *
     * @return 状态信息.
     */
    Map<String, String> getStatus();
}
