package com.eminem.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by ysen6 on 2017/2/3.
 */
@Configuration
@EnableTransactionManagement
public class DruidDataSourceConfig implements EnvironmentAware {
    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment env) {
        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
    }

    @Bean(name="dataSource")
    public DataSource dataSource() {
        System.out.println("----------------------druid init start----------------------------");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(propertyResolver.getProperty("url"));
        datasource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
        datasource.setUsername(propertyResolver.getProperty("username"));
        datasource.setPassword(propertyResolver.getProperty("password"));
        datasource.setInitialSize(Integer.valueOf(propertyResolver.getProperty("druid.initial-size")));
        datasource.setMinIdle(Integer.valueOf(propertyResolver.getProperty("druid.minIdle")));
        datasource.setMaxWait(Long.valueOf(propertyResolver.getProperty("druid.max-wait")));
        datasource.setMaxActive(Integer.valueOf(propertyResolver.getProperty("druid.max-active")));
        datasource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver.getProperty("druid.min-evictableIdle-time-millis")));
        datasource.setTimeBetweenEvictionRunsMillis(Long.parseLong(propertyResolver.getProperty("druid.time-between-eviction-runs-millis")));
        datasource.setValidationQuery(propertyResolver.getProperty("druid.validation-query"));
        datasource.setTestWhileIdle(Boolean.parseBoolean(propertyResolver.getProperty("druid.test-whileIdle")));
        datasource.setTestOnReturn(Boolean.parseBoolean(propertyResolver.getProperty("druid.test-on-return")));
        datasource.setTestOnBorrow(Boolean.parseBoolean(propertyResolver.getProperty("druid.test-on-borrow")));
        datasource.setPoolPreparedStatements(Boolean.parseBoolean(propertyResolver.getProperty("druid.pool-prepared-statements")));
        datasource.setMaxOpenPreparedStatements(Integer.parseInt(propertyResolver.getProperty("druid.max-open-prepared-statements")));
        datasource.setRemoveAbandoned(Boolean.parseBoolean(propertyResolver.getProperty("druid.remove-abandoned")));
        datasource.setRemoveAbandonedTimeout(Integer.parseInt(propertyResolver.getProperty("druid.remove-abandoned-timeout")));
        datasource.setLogAbandoned(Boolean.parseBoolean(propertyResolver.getProperty("druid.log-abandoned")));

        try {
            datasource.setFilters(propertyResolver.getProperty("druid.filters"));
            System.out.println("----------------------druid init end----------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }


}
