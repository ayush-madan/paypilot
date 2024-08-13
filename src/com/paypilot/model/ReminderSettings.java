/*
 * The ReminderSettings class provides the settings for reminders related to bills.
 * It includes information on how often to remind, when to start, and notification preferences.
 * It also references the Bill for which the reminders are set.
 * 
 * Author: Anshul
 * Date: 09-08-2024
 */


package com.paypilot.model;

import java.util.Date;

public class ReminderSettings {

    private int reminderId;
    private String reminderFrequency; // Frequency of the reminder
    private Date reminderStartDate;
    private String customMessage; // Custom message to be sent with the reminder
    private String notificationPref; // Preference for notification
    private Bill bill;  // Reference to a Bill

    // Constructor
    public ReminderSettings(int reminderId, String reminderFrequency, Date reminderStartDate, String customMessage, 
                            String notificationPref, Bill bill) {
        this.reminderId = reminderId;
        this.reminderFrequency = reminderFrequency;
        this.reminderStartDate = reminderStartDate;
        this.customMessage = customMessage;
        this.notificationPref = notificationPref;
        this.bill = bill;
    }

    // Getters and setters
    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public String getReminderFrequency() {
        return reminderFrequency;
    }

    public void setReminderFrequency(String reminderFrequency) {
        this.reminderFrequency = reminderFrequency;
    }

    public Date getReminderStartDate() {
        return reminderStartDate;
    }

    public void setReminderStartDate(Date reminderStartDate) {
        this.reminderStartDate = reminderStartDate;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    public String getNotificationPref() {
        return notificationPref;
    }

    public void setNotificationPref(String notificationPref) {
        this.notificationPref = notificationPref;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "ReminderSettings{" +
                "reminderId=" + reminderId +
                ", reminderFrequency='" + reminderFrequency + '\'' +
                ", reminderStartDate=" + reminderStartDate +
                ", customMessage='" + customMessage + '\'' +
                ", notificationPref='" + notificationPref + '\'' +
                ", bill=" + bill +
                '}';
    }
}