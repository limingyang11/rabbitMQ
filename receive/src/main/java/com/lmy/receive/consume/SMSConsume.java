package com.lmy.receive.consume;

import com.lmy.receive.utils.OrderMap;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author lmy
 * @data 2021-08-05 16:26
 */
@Component

public class SMSConsume {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "queue_sms", autoDelete = "true"),
            exchange = @Exchange(value = "exchage_sms_topic", type = ExchangeTypes.TOPIC),
            key = "sms"))
    public void smsConsume(String msg, Message message, Channel channel) {
        System.out.println(msg);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        }catch (IOException e) {

        }
    }
}
