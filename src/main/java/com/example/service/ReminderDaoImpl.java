package com.example.service;

import com.example.model.Reminder;
import com.example.model.User;
import com.mongodb.DBCollection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by manojjha on 7/29/17.
 */
@Component
public class ReminderDaoImpl {

    @Autowired
    MongoTemplate mongoTemplate;

    public static final Logger logger = Logger.getLogger(ReminderDaoImpl.class);

    public void updateReminderScheduler(Reminder reminder, Date scheduledDate){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").in(reminder.getUserName()));
        Update update = new Update();
        update.set("scheduler", scheduledDate);
        mongoTemplate.updateFirst(query, update, Reminder.class);
    }

    public boolean addReminder(Reminder reminder){
        boolean response = false;
        try {
            mongoTemplate.save(reminder,"Reminder");
            response = true;
        }catch (Exception e){
            logger.error(e.getStackTrace());
        }
        return response;
    }
}
