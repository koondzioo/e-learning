package com.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VideoService {

    private final RabbitTemplate rabbitTemplate;



    public void saveVideo() {
        rabbitTemplate.convertAndSend("x.video", "", "NEW VIDEO");
        System.out.println("NEW VIDEO SENT");
    }

    @RabbitListener(queues = "q.user")
    public void getUser(@Payload String json) {
        try {
            System.out.println("CONSUME USER -> " + json);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }


}
