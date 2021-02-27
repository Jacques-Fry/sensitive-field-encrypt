package com.huihe.sensitive.core.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 加在敏感字段上，实现自动解密/加密
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/02/01 09:03
 * @see Annotation
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface SensitiveField {}
