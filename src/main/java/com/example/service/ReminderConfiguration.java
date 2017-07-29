package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by manojjha on 7/27/17.
 */
@Configuration
@PropertySource("classpath:reminderMessage.properties")
public class ReminderConfiguration {

    @Value("${esaReminderTimingInMins}")
    private int esaNotificationTiming;

    @Value("${esaNotificationMessage}")
    private String esaNotificationMessage;

    @Value("${esaNotificationName}")
    private String esaName;

    public String getEsaNotificationMessage() {
        return esaNotificationMessage;
    }

    public int getEsaNotificationTiming() {
        return esaNotificationTiming;
    }

    public String getEsaName() {
        return esaName;
    }
}
