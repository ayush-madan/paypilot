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

    public ReminderSettingsController(ReminderSettingsService reminderSettingsService) {
        this.reminderSettingsService = reminderSettingsService;
    }

    public void addReminder(ReminderSettings reminderSettings) {
        reminderSettingsService.addReminderService(reminderSettings);
        System.out.println("Reminder added successfully.");
    }

    public void updateReminder(ReminderSettings reminderSettings) {
        reminderSettingsService.updateReminderService(reminderSettings);
        System.out.println("Reminder updated successfully.");
    }

    public void deleteReminder(int reminderId) {
        reminderSettingsService.deleteReminderService(reminderId);
        System.out.println("Reminder deleted successfully.");
    }

    public void getReminderById(int reminderId) {
        Optional<ReminderSettings> reminder = reminderSettingsService.getReminderByIdService(reminderId);
        if (reminder.isPresent()) {
            System.out.println(reminder.get());
        } else {
            System.out.println("Reminder not found.");
        }
    }

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
