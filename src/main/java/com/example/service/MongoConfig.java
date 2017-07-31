package com.example.service;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by manojjha on 7/29/17.
 */

@Configuration

@EnableMongoRepositories
public class MongoConfig {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.host}")
    private String host;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }


    @Bean
    public MongoTemplate mongoTemplate(){
        MongoClient mongoClient = new MongoClient(new ServerAddress(host));
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, database);
        ReminderDaoImpl.addUserAndReminder();
        return new MongoTemplate(mongoDbFactory);
    }





}
