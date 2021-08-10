package com.lmy.rabbitretry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.lmy")
public class RabbitretryApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitretryApplication.class, args);
    }

}
