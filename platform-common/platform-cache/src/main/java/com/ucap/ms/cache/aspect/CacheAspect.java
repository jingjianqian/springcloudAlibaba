package com.ucap.ms.cache.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author jjq
 * @time  20210810
 * @apiNote  AOP切入CacheAspectAnnotation注解处进行缓存相关操作
 */
@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE - 2)
public class CacheAspect {
    private final static Logger logger = LoggerFactory.getLogger(CacheAspect.class);



    /**
     * 切入点
     */
    @Pointcut("@annotation(CacheAspectAnnotation) ")
    public void access(){
        logger.info("切入点声明！！！");
    }


    @Before("access()")
    public void before(JoinPoint joinPoint){
        logger.info("before from aop");
    }
    /**
     * redis 环绕通知AOP
     * @return
     */
    @Around("access()&&@annotation(cacheAspectAnnotation)")
    public void execute(ProceedingJoinPoint proceedingJoinPoint, CacheAspectAnnotation cacheAspectAnnotation){
        logger.info("Around。。。。。");
    }

    @After("access()")
    public void after(JoinPoint joinPoint){
        logger.info("after method from aop");
    }
}
