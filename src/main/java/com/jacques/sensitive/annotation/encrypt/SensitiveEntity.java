package com.jacques.sensitive.annotation.encrypt;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 敏感的实体类，优化加解密切面执行性能
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/2/1 13:29
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface SensitiveEntity {
}
