package cn.cyberwater.customer.aop;

import cn.cyberwater.customer.common.LogRecordUtil;
import cn.cyberwater.customer.common.anno.BusinessLog;
import cn.cyberwater.customer.service.OptionLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 肖明亮
 * @date 2020/8/11 19:48
 * @Description: 业务日志记录
 */
@Aspect
@Component
@Slf4j
public class BusinessLogAspect {

    private final String pointcut = "@annotation(cn.cyberwater.customer.common.anno.BusinessLog)";

    private Map<String, OptionLogService> optionLogServiceMap = new HashMap<>();

    @Lazy
    @Autowired
    private LogRecordUtil logRecordUtil;

    @Autowired
    private List<OptionLogService> optionLogServices;

    @PostConstruct
    private void init() {
        for (OptionLogService optionLogService : optionLogServices) {
            optionLogServiceMap.put(optionLogService.businessType(), optionLogService);
        }
    }

    @Before(pointcut)
    public void doBefore(JoinPoint joinPoint) throws Exception {
        //获取入参
        Object[] params = joinPoint.getArgs();
        //获取方法注解
        MethodSignature methodName = (MethodSignature) joinPoint.getSignature();
        Method method = methodName.getMethod();
        BusinessLog annotation = method.getAnnotation(BusinessLog.class);
        //业务类型
        String businessType = annotation.businessType();
        //业务类型(详细)
        String businessDetail = annotation.businessDetail();
        OptionLogService optionLogService = optionLogServiceMap.get(businessType + "-" + businessDetail);
        if (optionLogService == null) {
            log.info("该[{}]业务分类不存在!", businessType);
            return;
        }
        //方法执行前前要做的事情
        optionLogService.doBefore(params);
    }

    @Around(pointcut)
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //执行方法,并获取方法返回值
        Object result = joinPoint.proceed();

        //获取入参
        Object[] params = joinPoint.getArgs();

        //获取方法注解
        MethodSignature methodName = (MethodSignature) joinPoint.getSignature();
        Method method = methodName.getMethod();
        BusinessLog annotation = method.getAnnotation(BusinessLog.class);

        //写入日志
        logRecordUtil.writeLog(params, result, annotation, optionLogServiceMap);
        return result;
    }

    @Before(pointcut)
    public void beforePrintLog(JoinPoint joinPoint) {
        //获取入参
        try {
            Object[] params = joinPoint.getArgs();
            //获取方法注解
            MethodSignature methodName = (MethodSignature) joinPoint.getSignature();
            Method method = methodName.getMethod();
            BusinessLog annotation = method.getAnnotation(BusinessLog.class);
            //业务类型
            String businessType = annotation.businessType();
            //业务类型(详细)
            String businessDetail = annotation.businessDetail();
            //获取相应备注拼接实现类
            OptionLogService optionLogService = optionLogServiceMap.get(businessType + "-" + businessDetail);
            optionLogService.setOldEntity(params);
            log.info("执行操作日志前置方法！");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
