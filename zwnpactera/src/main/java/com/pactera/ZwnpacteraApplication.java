package com.pactera;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration//开启自动初始化
 //@EnableRabbit//开启mq
 //@EnableScheduling//开始定时任务注解
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
 //@MapperScan(basePackages = {"com.pactera.mapper"})//扫码mapper
public class ZwnpacteraApplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

    public static void main(String[] args) {
        SpringApplication.run(ZwnpacteraApplication.class, args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        //指定项目名称
       // container.setContextPath("/pactera");
	   System.out.print("hahhahaha");
        //指定端口地址
        container.setPort(8090);
    }

    }
