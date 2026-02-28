package com.jiandaoyun.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 鏁版嵁鏉冮檺娉ㄨВ.
 *
 * @author Codex
 *
 * @since 2026/02/28
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataPermission {
}