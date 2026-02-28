package com.jiandaoyun.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 绉熸埛鏍￠獙娉ㄨВ.
 *
 * @author Codex
 *
 * @since 2026/02/28
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TenantCheck {
}