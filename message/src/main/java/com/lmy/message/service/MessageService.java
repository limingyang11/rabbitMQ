package com.lmy.message.service;

import com.lmy.message.model.MessageDTO;
import com.lmy.message.model.ResultDTO;
import com.lmy.message.rabbit.RabbitConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author lmy
 * @data 2021-08-05 16:07
 */
@Service
public class MessageService {
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public ResultDTO sendMsg(MessageDTO messageDTO) {

        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_SMS_TOPIC, RabbitConfig.SMS_KEY, messageDTO.getTitle());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_EMAIL_TOPIC, RabbitConfig.EMAIL_KEY, messageDTO.getMsg());
        return new ResultDTO(200, "success", messageDTO);
    }


    public ResultDTO subscribe(long userId) {
        return null;
    }
}
