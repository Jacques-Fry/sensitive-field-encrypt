package com.huihe.sensitive.core.annotation;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 功能扫描注释
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/2/27 9:49
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface Sensitive {
}
