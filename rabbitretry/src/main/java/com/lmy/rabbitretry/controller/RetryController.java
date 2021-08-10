package com.lmy.rabbitretry.controller;

import com.lmy.rabbitretry.model.RetryDTO;
import com.lmy.rabbitretry.service.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmy
 * @data 2021-08-09 10:20
 */
@RestController
@RequestMapping(value = "/1/retry")
public class RetryController {

    private RetryService retryService;

    @Autowired
    public RetryController (RetryService retryService) {
        this.retryService = retryService;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String add(@RequestBody RetryDTO retryDTO) {
        return retryService.add(retryDTO);
    }
}
