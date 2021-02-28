package com.app;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideosRepository extends ReactiveMongoRepository<Video, Integer> {
}
