package com.app;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends ReactiveMongoRepository<User, Integer> {
}
