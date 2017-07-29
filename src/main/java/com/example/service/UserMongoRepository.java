package com.example.service;

import com.example.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by manojjha on 7/29/17.
 */
public interface UserMongoRepository extends MongoRepository<User, String> {

}
