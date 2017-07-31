package com.example.service;

import com.example.model.Reminder;
import com.example.model.User;
import com.example.util.ReminderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by manojjha on 7/27/17.
 */
@Component
public class ReminderService {

    @Autowired
    ReminderUtils reminderUtils;

    @Autowired
    ReminderConfiguration reminderConfiguration;

    @Autowired
    UserMongoRepository userMongoRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ReminderDaoImpl reminderDao;

    public void login(User user){
//        if(user.getUserName() != null && user.getPassword() != null){
//            mongoTemplate.save(user, "User");
//        }
        reminderDao.addUser(user);

    }

    public List<Reminder> fetchReminder(User user){
        return reminderDao.fetchReminder(user.getUserName());
    }

    public List<Reminder> getActiveNotification(User user){
        List reminderList = null;
        if(user!=null){
//            Query query = new Query();
//            query.addCriteria(Criteria.where("_id").in(user.getUserName()));
//            reminder = mongoTemplate.findOne(query, Reminder.class);
            List<Reminder> list = reminderDao.fetchReminder(user.getUserName());
            if(list != null){
                reminderList = new ArrayList();
            }
            for (Reminder reminder:list) {

                if(reminder != null && reminderUtils.isNotificationTiming(reminder)){
                    reminderDao.updateReminderScheduler(reminder,reminderUtils.addScheduleToInterval(reminder));
                    reminderList.add(reminder);
                }
            }

        }
        return reminderList;
    }

    public boolean addReminder(Reminder reminder){
        if(reminder != null){
            return reminderDao.addReminder(reminder);
        }
        return false;
    }

}
