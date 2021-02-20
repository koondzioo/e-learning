package com.app;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RabbitTemplate rabbitTemplate;
    private final UsersRepository usersRepository;

    public void saveUser() {
        rabbitTemplate.convertAndSend("x.user", "", "NEW USER");
        System.out.println("NEW USER SENT");
    }

    public Mono<User> add(User user) {
        System.out.println("saving to DB");
        return usersRepository.save(user);
    }

    public Flux<User> get() {
        return usersRepository.findAll();
    }

    @RabbitListener(queues = "q.video")
    public void getVideo(@Payload String json) {
        try {
            System.out.println("CONSUME VIDEO -> " + json);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

}
