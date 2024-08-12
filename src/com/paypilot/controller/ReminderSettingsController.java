package com.paypilot.controller;

import com.paypilot.model.ReminderSettings;
import com.paypilot.model.Bill;
import com.paypilot.service.ReminderSettingsService;
import com.paypilot.repo.ReminderSettingsRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * ReminderSettingsController provides methods for managing reminder settings, interacting with the ReminderSettingsService.
 * It prints feedback messages to the console for user interaction.
 * 
 * Author: Anshul
 * Date: 09-08-2024
 */
public class ReminderSettingsController {

    private ReminderSettingsService reminderSettingsService; // Service to manage reminder settings

    /**
     * Constructs a ReminderSettingsController with the specified ReminderSettingsService.
     * 
     * @param reminderSettingsService The ReminderSettingsService used by this controller.
     */
    public ReminderSettingsController(ReminderSettingsService reminderSettingsService) {
        this.reminderSettingsService = reminderSettingsService;
    }

    /**
     * Adds a new reminder and prints a success message.
     * 
     * @param reminderSettings The ReminderSettings object to be added.
     */
    public void addReminder(ReminderSettings reminderSettings) {
        reminderSettingsService.addReminderService(reminderSettings);
        System.out.println("Reminder added successfully.");
    }

    /**
     * Updates an existing reminder and prints a success message.
     * 
     * @param reminderSettings The ReminderSettings object with updated information.
     */
    public void updateReminder(ReminderSettings reminderSettings) {
        reminderSettingsService.updateReminderService(reminderSettings);
        System.out.println("Reminder updated successfully.");
    }

    /**
     * Deletes a reminder by its ID and prints a success message.
     * 
     * @param reminderId The ID of the reminder to be deleted.
     */
    public void deleteReminder(int reminderId) {
        reminderSettingsService.deleteReminderService(reminderId);
        System.out.println("Reminder deleted successfully.");
    }

    /**
     * Retrieves and prints the details of a reminder by its ID.
     * If the reminder is not found, prints a "Reminder not found" message.
     * 
     * @param reminderId The ID of the reminder to be retrieved.
     */
    public void getReminderById(int reminderId) {
        Optional<ReminderSettings> reminder = reminderSettingsService.getReminderByIdService(reminderId);
        if (reminder.isPresent()) {
            System.out.println(reminder.get());
        } else {
            System.out.println("Reminder not found.");
        }
    }

    /**
     * Retrieves and prints the details of all reminders.
     */
    public void listAllReminders() {
        List<ReminderSettings> reminders = reminderSettingsService.getAllRemindersService();
        for (ReminderSettings reminder : reminders) {
            System.out.println(reminder);
        }
    }

    public static void main(String[] args) {
        // Setup repository and service
        ReminderSettingsRepository reminderSettingsRepository = new ReminderSettingsRepository();
        ReminderSettingsService reminderSettingsService = new ReminderSettingsService(reminderSettingsRepository);
        ReminderSettingsController reminderSettingsController = new ReminderSettingsController(reminderSettingsService);

        // Create a sample bill
        Bill bill = new Bill(1, "Electricity", "Utilities", new Date(), 100.0, "Monthly", null, "N/A", false, "Upcoming", 5, null);

        // Create sample reminders
        ReminderSettings reminder1 = new ReminderSettings(1, "Monthly", new Date(), "Pay on time", "Email", bill);
        ReminderSettings reminder2 = new ReminderSettings(2, "Weekly", new Date(), "Reminder for bill", "SMS", bill);

        // Add reminders
        reminderSettingsController.addReminder(reminder1);
        reminderSettingsController.addReminder(reminder2);

        // List all reminders
        System.out.println("Listing all reminders:");
        reminderSettingsController.listAllReminders();

        // Update a reminder
        reminder1.setCustomMessage("Updated reminder message");
        reminderSettingsController.updateReminder(reminder1);

        // Get details of a specific reminder
        System.out.println("Details of reminder ID 1:");
        reminderSettingsController.getReminderById(1);

        // Delete a reminder
        reminderSettingsController.deleteReminder(2);

        // List all reminders again
        System.out.println("Listing all reminders after deletion:");
        reminderSettingsController.listAllReminders();
    }
}
