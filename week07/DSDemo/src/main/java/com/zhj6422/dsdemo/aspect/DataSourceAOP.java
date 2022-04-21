package com.zhj6422.dsdemo.aspect;

import com.zhj6422.dsdemo.annotation.MyDataSource;
import com.zhj6422.dsdemo.config.DataSourceEnum;
import com.zhj6422.dsdemo.config.DatabaseContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAOP {

    @Pointcut("@annotation(com.zhj6422.dsdemo.annotation.Read1)")
    public void read1Pointcut() {}

    @Pointcut("@annotation(com.zhj6422.dsdemo.annotation.Read2)")
    public void read2Pointcut() {}

    @Pointcut("@annotation(com.zhj6422.dsdemo.annotation.Write)")
    public void writePointcut() {}

    @Before("read1Pointcut()")
    public void read1Advise() {
        DatabaseContextHolder.slave1();
    }

    @Before("read2Pointcut()")
    public void read2Advise() {
        DatabaseContextHolder.slave2();
    }

    @Before("writePointcut()")
    public void writeAdvise() {
        DatabaseContextHolder.master();
    }
}