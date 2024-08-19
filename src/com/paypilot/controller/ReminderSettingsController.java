/**
 * The {@code ReminderSettingsController} class provides methods to manage {@code ReminderSettings},
 * such as adding, updating, deleting, retrieving by ID, and listing all reminders.
 * It interacts with the {@code ReminderSettingsService} to perform these operations and provides 
 * feedback to the user via console outputs.
 *
 * <p>Author: Anshul</p>
 * <p>Date: 09-08-2024</p>
 */
package com.paypilot.controller;

import com.paypilot.model.ReminderSettings;
import com.paypilot.model.Bill;
import com.paypilot.service.ReminderSettingsService;
import com.paypilot.repo.ReminderSettingsRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ReminderSettingsController {

    private ReminderSettingsService reminderSettingsService;

    /**
     * Constructs a {@code ReminderSettingsController} with the specified {@code ReminderSettingsService}.
     *
     * @param reminderSettingsService The service layer responsible for reminder management.
     */
    public ReminderSettingsController(ReminderSettingsService reminderSettingsService) {
        this.reminderSettingsService = reminderSettingsService;
    }

    /**
     * Adds a new reminder using the {@code ReminderSettingsService} and provides feedback to the user.
     *
     * @param reminderSettings The {@code ReminderSettings} object to be added.
     */
    public void addReminder(ReminderSettings reminderSettings) {
        reminderSettingsService.addReminderService(reminderSettings);
        System.out.println("Reminder added successfully.");
    }

    /**
     * Updates an existing reminder using the {@code ReminderSettingsService} and provides feedback to the user.
     *
     * @param reminderSettings The {@code ReminderSettings} object with updated information.
     */
    public void updateReminder(ReminderSettings reminderSettings) {
        reminderSettingsService.updateReminderService(reminderSettings);
        System.out.println("Reminder updated successfully.");
    }

    /**
     * Deletes a reminder by its ID using the {@code ReminderSettingsService} and provides feedback to the user.
     *
     * @param reminderId The ID of the reminder to be deleted.
     */
    public void deleteReminder(int reminderId) {
        reminderSettingsService.deleteReminderService(reminderId);
        System.out.println("Reminder deleted successfully.");
    }

    /**
     * Retrieves a reminder by its ID using the {@code ReminderSettingsService} and prints its details.
     * If the reminder is not found, a 'not found' message is printed.
     *
     * @param reminderId The ID of the reminder to retrieve.
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
     * Retrieves and lists all reminders using the {@code ReminderSettingsService} and prints them to the console.
     */
    public void listAllReminders() {
        List<ReminderSettings> reminders = reminderSettingsService.getAllRemindersService();
        for (ReminderSettings reminder : reminders) {
            System.out.println(reminder);
        }
    }

    /**
     * The main method to simulate operations on the {@code ReminderSettingsController}.
     * Demonstrates adding, updating, retrieving, deleting, and listing reminders.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Setup repository and service for the ReminderSettingsController.
        ReminderSettingsRepository reminderSettingsRepository = new ReminderSettingsRepository();
        ReminderSettingsService reminderSettingsService = new ReminderSettingsService(reminderSettingsRepository);
        ReminderSettingsController reminderSettingsController = new ReminderSettingsController(reminderSettingsService);

        // Create a sample bill.
        Bill bill = new Bill(1, "Electricity", "Utilities", new Date(), 100.0, "Monthly", null, "N/A", false, "Upcoming", 5, null);

        // Create sample ReminderSettings objects.
        ReminderSettings reminder1 = new ReminderSettings(1, "Monthly", new Date(), "Pay on time", "Email", bill);
        ReminderSettings reminder2 = new ReminderSettings(2, "Weekly", new Date(), "Reminder for bill", "SMS", bill);

        // Add the reminders to the system.
        reminderSettingsController.addReminder(reminder1);
        reminderSettingsController.addReminder(reminder2);

        // List all reminders currently stored.
        System.out.println("Listing all reminders:");
        reminderSettingsController.listAllReminders();

        // Update reminder1's custom message and save the changes.
        reminder1.setCustomMessage("Updated reminder message");
        reminderSettingsController.updateReminder(reminder1);

        // Get and print the details of a specific reminder by its ID.
        System.out.println("Details of reminder ID 1:");
        reminderSettingsController.getReminderById(1);

        // Delete reminder2 by its ID.
        reminderSettingsController.deleteReminder(2);

        // List all reminders after the deletion of reminder2.
        System.out.println("Listing all reminders after deletion:");
        reminderSettingsController.listAllReminders();
    }
}
