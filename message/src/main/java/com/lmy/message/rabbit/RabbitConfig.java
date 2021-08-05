package com.lmy.message.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lmy
 * @data 2021-08-05 15:48
 */
@Configuration
public class RabbitConfig {

    public static final String QUEUE_SMS = "queue_sms";//短信队列名称

    public static final String QUEUE_EMAIL = "queue_email";//邮箱队列名称

    public static final String EXCHANGE_SMS_TOPIC = "exchage_sms_topic";//sms交换机名称

    public static final String EXCHANGE_EMAIL_TOPIC = "exchage_email_topic";//email交换机名称

    public static final String SMS_KEY = "sms";

    public static final String EMAIL_KEY = "email";


    @Bean(EXCHANGE_SMS_TOPIC)
    public Exchange smsExchange () {
        return ExchangeBuilder.topicExchange(EXCHANGE_SMS_TOPIC).durable(true).build();
    }

    @Bean(EXCHANGE_EMAIL_TOPIC)
    public Exchange emailExchange () {
        return ExchangeBuilder.topicExchange(EXCHANGE_EMAIL_TOPIC).durable(true).build();
    }

    @Bean(QUEUE_SMS)
    public Queue smsQueue () {
        return new Queue(QUEUE_SMS, true);
    }

    @Bean(QUEUE_EMAIL)
    public Queue emailQueue () {
        return new Queue(QUEUE_EMAIL, true);
    }

    @Bean
    public Binding smsBinding(@Qualifier(QUEUE_SMS) Queue queue, @Qualifier(EXCHANGE_SMS_TOPIC)Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(SMS_KEY).noargs();
    }

    @Bean
    public Binding emailBinding(@Qualifier(QUEUE_EMAIL) Queue queue, @Qualifier(EXCHANGE_EMAIL_TOPIC)Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(EMAIL_KEY).noargs();
    }
}
