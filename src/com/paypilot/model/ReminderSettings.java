/**
 * The {@code ReminderSettings} class provides the settings for reminders related to bills.
 * It includes information on how often to remind, the start date of reminders, 
 * custom messages, notification preferences, and a reference to the bill the reminder is related to.
 * 
 * <p>This class helps in managing the reminder configurations for each bill,
 * ensuring that users are notified based on their preferences.</p>
 * 
 * <p>Author: Anshul</p>
 * <p>Date: 09-08-2024</p>
 */
package com.paypilot.model;

import java.util.Date;

public class ReminderSettings {

    /**
     * The unique identifier for the reminder.
     */
    private int reminderId;
    
    /**
     * Frequency of the reminder (e.g., Daily, Weekly, Monthly).
     */
    private String reminderFrequency; 
    
    /**
     * The start date for when the reminder notifications should begin.
     */
    private Date reminderStartDate;
    
    /**
     * A custom message to be sent with the reminder notification.
     */
    private String customMessage; 
    
    /**
     * The user's preferred notification method (e.g., Email, SMS).
     */
    private String notificationPref; 
    
    /**
     * The bill associated with the reminder.
     */
    private Bill bill;  

    /**
     * Constructs a new {@code ReminderSettings} object with the specified details.
     * 
     * @param reminderId         The unique identifier for the reminder.
     * @param reminderFrequency  The frequency of the reminder (e.g., Daily, Weekly, Monthly).
     * @param reminderStartDate  The start date for the reminder notifications.
     * @param customMessage      The custom message for the reminder.
     * @param notificationPref   The user's preferred notification method.
     * @param bill               The bill associated with this reminder.
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
     * @param reminderId The reminder ID.
     */
    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    /**
     * Gets the frequency of the reminder.
     * 
     * @return The reminder frequency.
     */
    public String getReminderFrequency() {
        return reminderFrequency;
    }

    /**
     * Sets the frequency of the reminder.
     * 
     * @param reminderFrequency The reminder frequency.
     */
    public void setReminderFrequency(String reminderFrequency) {
        this.reminderFrequency = reminderFrequency;
    }

    /**
     * Gets the start date for the reminder notifications.
     * 
     * @return The start date of the reminder.
     */
    public Date getReminderStartDate() {
        return reminderStartDate;
    }

    /**
     * Sets the start date for the reminder notifications.
     * 
     * @param reminderStartDate The start date of the reminder.
     */
    public void setReminderStartDate(Date reminderStartDate) {
        this.reminderStartDate = reminderStartDate;
    }

    /**
     * Gets the custom message associated with the reminder.
     * 
     * @return The custom message.
     */
    public String getCustomMessage() {
        return customMessage;
    }

    /**
     * Sets the custom message for the reminder.
     * 
     * @param customMessage The custom message for the reminder.
     */
    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    /**
     * Gets the user's preferred notification method.
     * 
     * @return The notification preference.
     */
    public String getNotificationPref() {
        return notificationPref;
    }

    /**
     * Sets the user's preferred notification method.
     * 
     * @param notificationPref The notification preference.
     */
    public void setNotificationPref(String notificationPref) {
        this.notificationPref = notificationPref;
    }

    /**
     * Gets the bill associated with the reminder.
     * 
     * @return The bill associated with this reminder.
     */
    public Bill getBill() {
        return bill;
    }

    /**
     * Sets the bill associated with the reminder.
     * 
     * @param bill The bill to associate with this reminder.
     */
    public void setBill(Bill bill) {
        this.bill = bill;
    }

    /**
     * Returns a string representation of the {@code ReminderSettings} object,
     * including all its fields.
     * 
     * @return A string representation of the reminder settings.
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
