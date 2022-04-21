package com.zhj6422.dsdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseContextHolder {
    private static ThreadLocal<DataSourceEnum> threadLocal = new ThreadLocal<>();

    private static final Logger log =  LoggerFactory.getLogger(DatabaseContextHolder.class);

    public static void setDatabaseType(DataSourceEnum dataSourceEnum) {
        threadLocal.set(dataSourceEnum);
        log.info("切换数据源:" + dataSourceEnum);
    }

    public static void master(){
        setDatabaseType(DataSourceEnum.MASTER);
    }

    public static void slave1(){
        setDatabaseType(DataSourceEnum.SLAVE1);
    }

    public static void slave2(){
        setDatabaseType(DataSourceEnum.SLAVE2);
    }


    public static DataSourceEnum getDatabaseType() {
        return threadLocal.get();
    }

    public static void clearDatabaseType() {
        threadLocal.remove();
    }
}
