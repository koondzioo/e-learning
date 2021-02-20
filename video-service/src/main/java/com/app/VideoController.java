package com.app;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping
    private String getStatus() {
        return "OK VIDEO SERVICE";
    }

    @GetMapping("/send")
    private String sendVideo(){
        videoService.saveVideo();
        return "Send";
    }
}
