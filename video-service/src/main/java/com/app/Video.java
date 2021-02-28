package com.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.io.InputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "videos")
public class Video {
    @Id
    private String id;
    private String title;
    private InputStream stream;
}
