package com.example.service;

import com.example.model.Reminder;
import com.example.model.User;
import com.example.util.ReminderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

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

    private Date today;

    public void login(User user){
        if(user.getUserName() != null && user.getPassword() != null){
            mongoTemplate.save(user, "User");
        }

    }

    public Reminder getESANotification(User user){
        Reminder reminder = null;
        if(user!=null){
            Query query = new Query();
            query.addCriteria(Criteria.where("userName").in(user.getUserName()));
            List<Reminder> reminderList = mongoTemplate.find(query, Reminder.class);
            reminder = reminderList.get(0);
            if(reminder != null && reminderUtils.isESANotificationTiming(reminder)){
                reminderDao.updateReminderScheduler(reminder,reminderUtils.addScheduleToInterval(reminder));
            }
        }
        return reminder;
    }

    public List<Reminder> getReminders(User user){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(user.getUserName()));
        List<Reminder> reminderList = mongoTemplate.find(query, Reminder.class);

        reminderList.add(new Reminder(1, "msnishan@gmail.com", "Test Reminder 1", "Test Reminder Description 1", true, null, 15));
        reminderList.add(new Reminder(2, "msnishan@gmail.com", "Test Reminder 2", "Test Reminder Description 2", true, null, 10));
        reminderList.add(new Reminder(3, "msnishan@gmail.com", "Test Reminder 3", "Test Reminder Description 3", false, null, 20));
        return reminderList;
    }

    public boolean addReminder(Reminder reminder){
        if(reminder != null){
            return reminderDao.addReminder(reminder);
        }
        return false;
    }

}
