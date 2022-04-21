package com.zhj6422.dsdemo.annotation;

import com.zhj6422.dsdemo.config.DataSourceEnum;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MyDataSource {
    DataSourceEnum type() default DataSourceEnum.MASTER;
}