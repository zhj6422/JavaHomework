package com.zhj6422.dsdemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Configuration
public class DataSourceConfig {
    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);
    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave1")
    public DataSource slave1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave2")
    public DataSource slave2DataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    public DataSource targetDataSource(@Qualifier("masterDataSource") DataSource masterDataSoure,
                                       @Qualifier("slave1DataSource") DataSource slave1DataSource,
                                       @Qualifier("slave2DataSource") DataSource slave2DataSource) {
        // ???????????????????????????????????????
        Map<Object, Object> targetDataSource = new HashMap<>();

        // ???map?????????????????????
        targetDataSource.put(DataSourceEnum.MASTER,masterDataSoure);
        targetDataSource.put(DataSourceEnum.SLAVE1,slave1DataSource);
        targetDataSource.put(DataSourceEnum.SLAVE2,slave2DataSource);

        // ?????? routtingDataSource ????????????????????????
        RoutingDataSource routtingDataSource = new RoutingDataSource();

        // ????????????????????????
        routtingDataSource.setTargetDataSources(targetDataSource);

        // ????????????????????????
        routtingDataSource.setDefaultTargetDataSource(masterDataSoure);

        return routtingDataSource;
    }
}
