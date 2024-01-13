package com.semicolon.africa.Todo.data.repositories;

import com.semicolon.africa.Todo.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);
}
