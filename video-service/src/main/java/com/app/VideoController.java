package com.app;

import com.app.service.FileService;
import com.app.service.FileUploadService;
import com.app.service.VideoService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import io.swagger.v3.oas.annotations.media.Content;

import java.util.List;


// TODO https://paulcwarren.github.io/spring-content/spring-content-rest-docs/
// https://stackoverflow.com/questions/49564006/how-to-upload-an-image-or-a-video-to-a-persistant-folder-in-class-path-with-spri


@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {
    private final static String IMG_ROOT = System.getProperty("user.dir") ;
    private final VideoService videoService;

    private final FileService fileService;
    private final FileUploadService fileUploadService;

    @GetMapping
    private String getStatus() {
        System.out.println(IMG_ROOT);
        return "OK VIDEO SERVICE";
    }

    @GetMapping("/send")
    private String sendVideo(){
        videoService.saveVideo();
        return "Send";
    }


    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<FileResponseDto> uploadFile(@RequestPart(name = "file") Mono<FilePart> file){
        System.out.println("controller");
        System.out.println(file.toString());
        return fileService.uploadFile(file);
    }

    @PostMapping(value = "/upload-mono", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<List<String>> upload(@RequestPart("file") Mono<FilePart> filePartMono) {
        System.out.println(filePartMono);
        /*
          To see the response beautifully we are returning strings as Mono List
          of String. We could have returned Flux<String> from here.
          If you are curious enough then just return Flux<String> from here and
          see the response on Postman
         */
        return fileUploadService.getLines(filePartMono).collectList();
    }
}
