package com.lmy.rabbitretry.service;

import com.lmy.rabbitretry.model.RetryDTO;
import com.lmy.rabbitretry.rabbit.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lmy
 * @data 2021-08-09 10:23
 */
@Service
public class RetryService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public String add(RetryDTO retryDTO) {
        rabbitTemplate.convertAndSend(RabbitConfig.NORMAL_EXCHANGE, RabbitConfig.NORMAL_KEY, retryDTO.toString());
        return "success";
    }



}
