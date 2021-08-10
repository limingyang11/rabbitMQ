package com.lmy.rabbitretry.service;

import com.lmy.rabbitretry.rabbit.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author lmy
 * @data 2021-08-09 10:27
 */
@Component
public class ReceiveMsg {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveMsg.class);

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.NORMAL_QUEUE)
    public void getMsg (String msg, Message message, Channel channel) throws Exception{
        int a = 0;
        long count = getCount(message);
        LOGGER.info("第{}次 ：{}", count, msg);
        int b = 1/a;
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        LOGGER.info("消费成功{}", msg);
    }


    @RabbitListener(queues = RabbitConfig.DEAD_QUEUE)
    public void deadMsg(String msg, Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        LOGGER.info("进入死信队列，消费成功{}", msg);
    }


    private long getCount(Message message) {
        long retryCount = 0L;
        Map map = message.getMessageProperties().getHeaders();
        if (map != null && map.containsKey("x-death")) {
            List<Map<String,Object>> deaths = (List<Map<String,Object>>)map.get("x-death");
            if (deaths.size() > 0) {
                retryCount = (Long)deaths.get(0).get("count");
            }
        }
        return retryCount;
    }
}
