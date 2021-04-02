package com.app.controller;

import com.app.model.FileStorage;
import com.app.model.Info;
import com.app.service.AES;
import com.app.service.AmazonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final AmazonService amazonService;


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Info uploadFile(@RequestParam MultipartFile file) {
        System.out.println("HERE WE ARE");
        return Info.builder().message(amazonService.uploadFile(file)).build();
    }


    @GetMapping
    private String getStatus() {
        final String secretKey = "ssshhhhhhhhhhh!!!!";

        String originalString = "howtodoinjava.com";
        String encryptedString = AES.encrypt(originalString, secretKey) ;
        String decryptedString = AES.decrypt(encryptedString, secretKey) ;

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
        return "OK VIDEO SERVICE";
    }
}




