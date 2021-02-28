package com.app;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping("/status")
    private String getStatus() {
        return "OK USER SERVICE";
    }

    @GetMapping("/all")
    private Flux<User> get() {
        System.out.println("HELLO HERE");
        return userService.get();
    }

    @PostMapping
    public Mono<User> add(@RequestBody User user) {
        return userService.add(user);
    }

    @GetMapping("/send")
    private String sendUser() {
        userService.saveUser();
        return "USER SEND";
    }
}
