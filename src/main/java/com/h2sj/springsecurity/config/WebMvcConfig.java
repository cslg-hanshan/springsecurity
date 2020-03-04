package com.h2sj.springsecurity.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableCaching//事务需要开启
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.6.56:3306/springsecurity?useUnicode=true&characterEncoding=utf-8&userSSL=false&serverTimezone=UTC");
        dataSource.setUsername("hanshan");
        dataSource.setPassword("123456");
        return dataSource;
    }
}
