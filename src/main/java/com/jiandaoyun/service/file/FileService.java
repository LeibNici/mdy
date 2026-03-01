package com.jiandaoyun.service.file;

import java.util.Map;

/**
 * 文件服务接口.
 *
 * @author chenming
 *
 * @since 2026/03/01
 */
public interface FileService {

    /**
     * 获取文件模块健康状态.
     *
     * @return 健康状态信息.
     */
    Map<String, String> health();
}
