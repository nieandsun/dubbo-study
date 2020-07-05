package com.nrsc;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class NrscOrderWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(NrscOrderWebApplication.class, args);
    }

}
