package com.wayne.Sparrow.app.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

/**
 * Created by Wayne on 2017/8/9.
 * 数据库的配置
 */

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    //注册dataSource
    @Bean(initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource dataSource() throws SQLException {
        return new DruidDataSource();
    }
}
