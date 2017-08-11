package com.wayne.sparrow.app.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

/**
 * Created by Wayne on 2017/8/9.
 * 数据库的配置
 */
@Data
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.wayne.sparrow.app.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class DatabaseConfig {

    @Value("${spring.datasource.config-location}")
    private String configLocation;
    @Value("${spring.datasource.mapper-path}")
    private String mapperPath;

    //注册dataSource
    @Bean(initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties("spring.datasource")
    public DruidDataSource dataSource() throws SQLException {
        return new DruidDataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(configLocation));
        PathMatchingResourcePatternResolver pathPattern = new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
        sqlSessionFactoryBean.setMapperLocations(pathPattern.getResources(packageSearchPath));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory(), ExecutorType.SIMPLE);
    }
}
