package com.batch.businessbatch.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SQSListener {

    private final Logger logger = LoggerFactory.getLogger(SQSListener.class);

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @SqsListener
    public void loadmessage(String message){
        logger.info(message);
    }
}
