package com.example.util;

import com.example.model.Reminder;
import com.example.service.ReminderConfiguration;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by manojjha on 7/27/17.
 */

@Component
public class ReminderUtils {

    @Autowired
    ReminderConfiguration configuration;

    public boolean isNotificationTiming(Reminder reminder){
        Date date = new Date();
        if(date.getSeconds()%5==0 && reminder.getReminderName().equals("ESA")){
            return true;
        }

        if(date.getSeconds()%6==0 && reminder.getReminderName().equals("Water")){
            return true;
        }

        if(date.getSeconds()%8==0 && reminder.getReminderName().equals("Medicine")){
            return true;
        }

//        if(date.getHours() == reminder.getScheduler().getHours()){
//            if(date.getMinutes()==reminder.getScheduler().getMinutes()){
//                return true;
//            }
//        }
        return false;
    }

    public Date addScheduleToInterval(Reminder reminder){
        Date date = reminder.getScheduler();
        int sec= reminder.getScheduler().getSeconds()+(reminder.getInterval()*60);
        return new Date(sec*10);
    }
}
