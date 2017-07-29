package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


/**
 * Created by manojjha on 7/27/17.
 */
@Document
public class Reminder {

    @Id
    private String userName;
    String reminderName;
    String description;
    boolean enableNotificaton;
    Date scheduler;
    int interval;

    public Reminder(){
    }

    public Reminder(String userName, String reminderName, String description, boolean enableNotificaton,
                    Date scheduler, int interval){
        this.userName = userName;
        this.reminderName = reminderName;
        this.description = description;
        this.enableNotificaton = enableNotificaton;
        this.scheduler = scheduler;
        this.interval = interval;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReminderName() {
        return reminderName;
    }

    public void setReminderName(String reminderName) {
        this.reminderName = reminderName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnableNotificaton() {
        return enableNotificaton;
    }

    public void setEnableNotificaton(boolean enableNotificaton) {
        this.enableNotificaton = enableNotificaton;
    }

    public Date getScheduler() {
        return scheduler;
    }

    public void setScheduler(Date scheduler) {
        this.scheduler = scheduler;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }












}
