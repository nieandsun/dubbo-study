package com.nrsc;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo //开启基于注解的dubbo服务
@SpringBootApplication
public class NrscOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NrscOrderServiceApplication.class, args);
    }

}
