package com.jiandaoyun.common.enums;

/**
 * 表单状态枚举定义.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
public enum FormStatus {
    /**
     * 草稿状态，表单尚未发布.
     */
    DRAFT,

    /**
     * 已发布状态，表单可用于数据填报.
     */
    PUBLISHED,

    /**
     * 已归档状态，表单停止使用.
     */
    ARCHIVED
}
