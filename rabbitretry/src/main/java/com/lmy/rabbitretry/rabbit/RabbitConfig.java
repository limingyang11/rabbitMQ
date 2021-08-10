package com.lmy.rabbitretry.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lmy
 * @data 2021-08-09 9:32
 */
@Configuration
public class RabbitConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConfig.class);

    public static final String DEAD_QUEUE = "dead_queue";

    public static final String DEAD_EXCHANGE = "dead_exchange";

    public static final String DEAD_KEY = "dead_key";

    public static final String NORMAL_QUEUE = "normal_queue";

    public static final String NORMAL_EXCHANGE = "normal_exchange";

    public static final String NORMAL_KEY = "normal_key";

    @Bean(DEAD_QUEUE)
    public Queue deadQueue() {
        return new Queue(DEAD_QUEUE, true);
    }

    @Bean(NORMAL_QUEUE)
    public Queue normalQueue() {
        Map map = new HashMap();
        map.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        map.put("x-dead-letter-routing-key", DEAD_KEY);
        return QueueBuilder.durable(NORMAL_QUEUE).withArguments(map)
                .build();
    }

    @Bean(DEAD_EXCHANGE)
    public TopicExchange deadExchange() {
        return new TopicExchange(DEAD_EXCHANGE, true, false);
    }

    @Bean(NORMAL_EXCHANGE)
    public Exchange normalExchange() {
        return ExchangeBuilder.topicExchange(NORMAL_EXCHANGE).durable(true).build();
    }

    @Bean
    public Binding deadBinding(@Qualifier(DEAD_QUEUE) Queue queue, @Qualifier(DEAD_EXCHANGE) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_KEY);
    }

    @Bean
    public Binding normalBinding(@Qualifier(NORMAL_QUEUE) Queue queue, @Qualifier(NORMAL_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(NORMAL_KEY).noargs();
    }

}
