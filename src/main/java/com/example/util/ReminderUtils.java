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

    public boolean isESANotificationTiming(Reminder reminder){
        DateTime date = DateTime.now();
        int min = (int)reminder.getScheduler()/600;
        int dateMin = (int)date.getMillis()/600;
        if(min == dateMin){
                return true;
        }
        return false;
    }

    public long addScheduleToInterval(Reminder reminder){
        return reminder.getScheduler()+(reminder.getInterval()*600);
    }
}
