package com.huihe.sensitive.core.aspect;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.huihe.sensitive.core.annotation.Sensitive;
import com.huihe.sensitive.core.utils.SensitiveAesUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
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
public class SensitiveFieldAspect {

    private final Logger log = LoggerFactory.getLogger(SensitiveFieldAspect.class);

    @Value("${sensitive.key}")
    public String key;

    @PostConstruct
    public void print(){
        log.info("\n\n" +
                "███████╗███████╗███╗   ██╗███████╗██╗████████╗██╗██╗   ██╗███████╗\n" +
                "██╔════╝██╔════╝████╗  ██║██╔════╝██║╚══██╔══╝██║██║   ██║██╔════╝\n" +
                "███████╗█████╗  ██╔██╗ ██║███████╗██║   ██║   ██║██║   ██║█████╗  \n" +
                "╚════██║██╔══╝  ██║╚██╗██║╚════██║██║   ██║   ██║╚██╗ ██╔╝██╔══╝  \n" +
                "███████║███████╗██║ ╚████║███████║██║   ██║   ██║ ╚████╔╝ ███████╗\n" +
                "╚══════╝╚══════╝╚═╝  ╚═══╝╚══════╝╚═╝   ╚═╝   ╚═╝  ╚═══╝  ╚══════╝" +
                "\n");
    }

    /**
     * 切入数据操作层进行加密解密
     *
     * @param proceedingJoinPoint 进行连接点
     * @param sensitive           加解密标识
     * @return {@link Object }
     * @author Jacques·Fry
     * @since 2021/02/01 15:32
     */
    @Around(value = "@within(sensitive)")
    public Object doProcess(ProceedingJoinPoint proceedingJoinPoint, Sensitive sensitive) throws Throwable {

        // 捕获方法参数列表
        List<Object> methodArgs = this.getMethodArgs(proceedingJoinPoint);
        // log.info("开始加密");

        // 循环所有参数项
        for (Object item : methodArgs) {

            if (item == null) {
                continue;
            }
            // 对参数项进行敏感字段加密处理
            // 如果是列表数据
            if (item instanceof List) {
                for (Object data : (List) item) {
                    encode(data);
                }
            }
            // 如果是单数据
            else {
                encode(item);
            }
        }

        Object result = proceedingJoinPoint.proceed();

        if (result == null) {
            return result;
        }

        // log.info("开始解密");

        // 对返回值进行敏感字段解密处理
        // 如果是列表数据
        List<?> resultList = null;
        if (result instanceof List) {
            // 普通列表
            resultList = (List<?>) result;
        } else if (result instanceof Page) {
            // JPA分页
            resultList = ((Page<?>) result).getContent();
        } else if (result instanceof IPage) {
            // mybatis-plus 分页
            resultList = ((IPage<?>) result).getRecords();
        }

        if (resultList != null && resultList.size() > 0) {
            for (Object item : resultList) {
                decode(item);
            }
        } else {
            // 如果是单数据
            decode(result);
        }

        return result;
    }


    /**
     * 获取方法的请求参数
     *
     * @param proceedingJoinPoint 进行连接点
     * @return {@link List<Object> }
     * @author Jacques·Fry
     * @since 2021/02/01 13:41
     */
    private List<Object> getMethodArgs(ProceedingJoinPoint proceedingJoinPoint) {
        List<Object> methodArgs = new ArrayList<>();
        for (Object arg : proceedingJoinPoint.getArgs()) {
            if (null != arg) {
                methodArgs.add(arg);
            }
        }
        return methodArgs;
    }

    /**
     * 加密
     *
     * @author Jacques·Fry
     * @since 2021/02/01 16:58
     */
    public void encode(Object data) {
        /// log.info("入参类型: " + data.getClass().getName());
        try {
            SensitiveAesUtil.processOne(data, key, true);
        } catch (IllegalAccessException e) {
            log.info("加密失败", e);
        }
    }

    /**
     * 解密
     *
     * @author Jacques·Fry
     * @since 2021/02/01 16:58
     */
    public void decode(Object data) {
        /// log.info("入参类型: " + data.getClass().getName());
        try {
            SensitiveAesUtil.processOne(data, key, false);
        } catch (IllegalAccessException e) {
            log.info("解密失败", e);
        }
    }


}
