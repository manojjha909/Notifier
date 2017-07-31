package com.example.controller;

import com.example.model.User;
import com.example.service.ReminderService;
import com.example.model.Reminder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by manojjha on 7/27/17.
 */

@RestController
@RequestMapping("/reminder")
public class ReminderController {

    @Autowired
    ReminderService reminderService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestBody User user) {
        if (user != null) {
            reminderService.login(user);
        }
        return user;

    }

    @RequestMapping(value = "/addreminder", method = RequestMethod.POST)
    public boolean addReminder(@RequestBody Reminder newReminder) {
        return reminderService.addReminder(newReminder);
    }


    @RequestMapping(value = "/fetchAllReminder", method = RequestMethod.POST)
    public List<Reminder> fetchReminder(@RequestBody User user){
        return reminderService.fetchReminder(user);
    }


    @RequestMapping(value = "/getActiveNotification", method = RequestMethod.POST)
    public List<Reminder> getActiveNotification(@RequestBody User user){
        System.out.println("*******/fillESATimesheet");
        List<Reminder> reminder = reminderService.getActiveNotification(user);
        return reminder;
    }

    @RequestMapping(value = "/fetchReminders", method = RequestMethod.POST)
    public List<Reminder> fetchAllReminders(@RequestBody User user) {
        return reminderService.getReminders(user);
    }
}
