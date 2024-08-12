package com.paypilot.model;

import java.util.Date;

/**
 * The ReminderSettings class represents the settings for reminders associated with bills.
 * It includes details on how frequently reminders should be sent, the start date of the reminders,
 * any custom messages, notification preferences, and the associated Bill.
 * 
 * Author: Anshul
 * Date: 09-08-2024
 */
public class ReminderSettings {

    private int reminderId; // Unique identifier for the reminder
    private String reminderFrequency; // Frequency at which reminders are sent
    private Date reminderStartDate; // Date when the reminder starts
    private String customMessage; // Custom message included with the reminder
    private String notificationPref; // Notification preference (e.g., Email, SMS)
    private Bill bill; // Reference to the Bill associated with these reminder settings

    /**
     * Constructs a new ReminderSettings object with the specified parameters.
     * 
     * @param reminderId The unique identifier for the reminder.
     * @param reminderFrequency The frequency of the reminder (e.g., Daily, Weekly).
     * @param reminderStartDate The date when the reminders should start.
     * @param customMessage A custom message to be sent with the reminder.
     * @param notificationPref The notification preference (e.g., Email, SMS).
     * @param bill The Bill associated with these reminder settings.
     */
    public ReminderSettings(int reminderId, String reminderFrequency, Date reminderStartDate, String customMessage, 
                            String notificationPref, Bill bill) {
        this.reminderId = reminderId;
        this.reminderFrequency = reminderFrequency;
        this.reminderStartDate = reminderStartDate;
        this.customMessage = customMessage;
        this.notificationPref = notificationPref;
        this.bill = bill;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier for the reminder.
     * 
     * @return The reminder ID.
     */
    public int getReminderId() {
        return reminderId;
    }

    /**
     * Sets the unique identifier for the reminder.
     * 
     * @param reminderId The new reminder ID.
     */
    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    /**
     * Gets the frequency at which reminders are sent.
     * 
     * @return The reminder frequency.
     */
    public String getReminderFrequency() {
        return reminderFrequency;
    }

    /**
     * Sets the frequency at which reminders are sent.
     * 
     * @param reminderFrequency The new reminder frequency.
     */
    public void setReminderFrequency(String reminderFrequency) {
        this.reminderFrequency = reminderFrequency;
    }

    /**
     * Gets the date when the reminders should start.
     * 
     * @return The reminder start date.
     */
    public Date getReminderStartDate() {
        return reminderStartDate;
    }

    /**
     * Sets the date when the reminders should start.
     * 
     * @param reminderStartDate The new reminder start date.
     */
    public void setReminderStartDate(Date reminderStartDate) {
        this.reminderStartDate = reminderStartDate;
    }

    /**
     * Gets the custom message included with the reminder.
     * 
     * @return The custom message.
     */
    public String getCustomMessage() {
        return customMessage;
    }

    /**
     * Sets the custom message included with the reminder.
     * 
     * @param customMessage The new custom message.
     */
    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    /**
     * Gets the notification preference (e.g., Email, SMS).
     * 
     * @return The notification preference.
     */
    public String getNotificationPref() {
        return notificationPref;
    }

    /**
     * Sets the notification preference (e.g., Email, SMS).
     * 
     * @param notificationPref The new notification preference.
     */
    public void setNotificationPref(String notificationPref) {
        this.notificationPref = notificationPref;
    }

    /**
     * Gets the Bill associated with these reminder settings.
     * 
     * @return The associated Bill.
     */
    public Bill getBill() {
        return bill;
    }

    /**
     * Sets the Bill associated with these reminder settings.
     * 
     * @param bill The new associated Bill.
     */
    public void setBill(Bill bill) {
        this.bill = bill;
    }

    /**
     * Returns a string representation of the ReminderSettings object.
     * 
     * @return A string describing the ReminderSettings object.
     */
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
