package com.jacques.sensitive.annotation.encrypt;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 敏感字段解密/加密切面
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/02/01 09:03
 */
@Component
@Aspect
@Slf4j
public class SensitiveFieldAspect {

    /**
     * 切入数据操作层进行加密解密
     *
     * @author Jacques·Fry
     * @since 2021/02/01 15:32
     * @param proceedingJoinPoint 进行连接点
     * @return {@link Object }
     */@Around(value ="execution(* com.*.*.dao.*..*(..))")
    public Object doProcess(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 捕获方法参数列表
        List<Object> methodArgs = this.getMethodArgs(proceedingJoinPoint);
        // 循环所有参数项
        log.info("开始加密");
        for (Object item : methodArgs) {
            // 对参数项进行敏感字段加密处理
            AESUtil.handleItem(item, AESUtil.KEY, true);
        }
        Object result = proceedingJoinPoint.proceed();
        log.info("开始解密");
        // 对返回值进行敏感字段解密处理
        AESUtil.handleItem(result, AESUtil.KEY, false);
        return result;
    }

    /**
     * 获取方法的请求参数
     *
     * @author Jacques·Fry
     * @since 2021/02/01 13:41
     * @param proceedingJoinPoint 进行连接点
     * @return {@link List<Object> }
     */
    private List<Object> getMethodArgs(ProceedingJoinPoint proceedingJoinPoint) {
        List<Object> methodArgs = Lists.newArrayList();
        for (Object arg : proceedingJoinPoint.getArgs()) {
            if (null != arg) {
                methodArgs.add(arg);
            }
        }
        return methodArgs;
    }
}
