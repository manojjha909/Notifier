package com.example.util;

import com.example.model.Reminder;
import com.example.service.ReminderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
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

    public boolean isESANotificationTiming(Reminder reminder){
        Date date = new Date();
        if(reminder.getScheduler() == date){
                return true;
        }
        return false;
    }

    public Date addScheduleToInterval(Reminder reminder){
        Date schedule = reminder.getScheduler();
        Long milliSecond = schedule.getSeconds()*10L;
        return new Date(milliSecond+reminder.getInterval()*10);
    }
}
