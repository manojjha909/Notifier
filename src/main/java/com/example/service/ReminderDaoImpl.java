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

import java.util.*;

/**
 * Created by manojjha on 7/29/17.
 */
@Component
public class ReminderDaoImpl {

    @Autowired
    MongoTemplate mongoTemplate;

    public static final Logger logger = Logger.getLogger(ReminderDaoImpl.class);

    public static Map<String , String>userList = new HashMap<>();
    public static Map<String , List<Reminder>>reminderList = new HashMap<String , List<Reminder>>();


    public static void addUserAndReminder(){
        userList.put("Manoj","password");
        userList.put("Khandekar","password");
//        Reminder reminder = new Reminder("Manoj","ESA","ESA Description",true,new Date(),5);
        List list= new ArrayList();
        list.add(new Reminder(1, "Manoj","ESA","ESA Description",true,new Date(),10));
        list.add(new Reminder(2, "Manoj","Water","Water Intake",true,new Date(),5));
        list.add(new Reminder(3, "Manoj","Medicine","Medicine Time",true,new Date(),20));
        reminderList.put("Manoj",list);

        List list1= new ArrayList();
        list1.add(new Reminder(1, "Khandekar","ESA","ESA Description",true,new Date(),10));
        list1.add(new Reminder(2, "Khandekar","Water","Water Intake",true,new Date(),5));
        reminderList.put("Khandekar",list1);

    }

    public void updateReminderScheduler(Reminder reminder, Date scheduledDate){
        String userName = reminder.getUserName();
        if(reminderList.get(userName) != null) {
            List<Reminder> l = reminderList.get(userName);
            for (Reminder rem:l) {
                if(rem.getReminderName().equals(reminder.getReminderName())){
                    rem.setScheduler(scheduledDate);
                }
            }
        }

//        Query query = new Query();
//        query.addCriteria(Criteria.where("userName").in(reminder.getUserName()));
//        Update update = new Update();
//        update.set("scheduler", scheduledDate);
//        mongoTemplate.updateFirst(query, update, Reminder.class);
    }

    public boolean addReminder(Reminder reminder){
        boolean response = false;
        try {
           // mongoTemplate.save(reminder,"Reminder");
            if(reminderList.get(reminder.getUserName())==null) {
                List l= new ArrayList();
                l.add(reminder);
                reminderList.put(reminder.getUserName(), l);
            }else{
                List l1 = reminderList.get(reminder.getUserName());
                l1.add(reminder);
                reminderList.put(reminder.getUserName(),l1);
            }
            response = true;
        }catch (Exception e){
            logger.error(e.getStackTrace());
        }
        return response;
    }

    public void addUser(User user){
        userList.put(user.getUserName(),user.getPassword());
    }

    public List<Reminder> fetchReminder(String userName){
        return reminderList.get(userName);
    }
}
