package com.lmy.message.controller;

import com.lmy.message.model.MessageDTO;
import com.lmy.message.model.ResultDTO;
import com.lmy.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lmy
 * @data 2021-08-05 16:06
 */
@RestController
@RequestMapping(value = "/1/msg")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController (MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ResultDTO sendMsg(@RequestBody MessageDTO messageDTO) {
        return messageService.sendMsg(messageDTO);
    }

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ResultDTO subscribe(@RequestParam(value = "user_id", required = true) long userId) {
        return messageService.subscribe(userId);
    }
}
