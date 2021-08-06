package com.lmy.receive.consume;

import com.lmy.receive.utils.OrderMap;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author lmy
 * @data 2021-08-05 16:53
 */
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "queue_email", autoDelete = "true"),
        exchange = @Exchange(value = "exchage_email_topic", type = ExchangeTypes.TOPIC),
        key = "email"))
public class EmailConsume {


    @RabbitHandler
    public void emailConsume(String msg, Message message, Channel channel) throws Exception{
        if (OrderMap.getInstance().incr(msg) < 3) {
            throw new Exception(msg);
        }
        OrderMap.getInstance().remove(msg);
        System.out.println(msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
}
