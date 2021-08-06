package com.lmy.receive;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.lmy")
@EnableRabbit
public class ReceiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReceiveApplication.class, args);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue("queue_email", true);
    }

    @Bean
    public Queue smsQueue() {
        return new Queue("queue_sms", true);
    }

}
