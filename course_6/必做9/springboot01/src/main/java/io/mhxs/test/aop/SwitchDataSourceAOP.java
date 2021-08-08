package io.mhxs.test.aop;

import io.mhxs.test.config.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Lazy(false)
@Order(0)
public class SwitchDataSourceAOP {
    @Before("execution(* io.mhxs.test.service.*.*(..))")
    public void process(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        if (methodName.startsWith("get") || methodName.startsWith("count") || methodName.startsWith("find")
                || methodName.startsWith("list") || methodName.startsWith("select") || methodName.startsWith("check")) {
            // 读
            DataSourceContextHolder.setDbType("selectDataSource");
        } else {
            // 切换dataSource
            DataSourceContextHolder.setDbType("updateDataSource");
        }
    }
}