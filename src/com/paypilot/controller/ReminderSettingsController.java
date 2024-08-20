package com.paypilot.controller;

import com.paypilot.model.ReminderSettings;
import com.paypilot.model.Bill;
import com.paypilot.service.ReminderSettingsService;
import com.paypilot.repo.ReminderSettingsDAOInterface;
import com.paypilot.repo.ReminderSettingsRepository;
import com.paypilot.repo.ReminderSettingsDAO;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The {@code ReminderSettingsController} class manages the interaction between the user interface
 * and the {@code ReminderSettingsService} to handle various operations related to {@code ReminderSettings} objects.
 * It provides methods for adding, updating, deleting, and retrieving reminder settings, as well as
 * listing all reminders stored in the system.
 *
 * <p>This class is responsible for controlling the flow of data to and from the
 * {@code ReminderSettingsService} and outputs messages to the user based on the outcome of these operations.</p>
 *
 * <p>Author: Anshul</p>
 * <p>Date: 09-08-2024</p>
 */
public class ReminderSettingsController {

    /**
     * Dependency injected {@code ReminderSettingsService} instance to manage reminder settings.
     */
    private ReminderSettingsService reminderSettingsService;

    /**
     * Constructs a {@code ReminderSettingsController} with the specified {@code ReminderSettingsService}.
     *
     * @param reminderSettingsService The service layer responsible for reminder settings management operations.
     */
    public ReminderSettingsController(ReminderSettingsService reminderSettingsService) {
        this.reminderSettingsService = reminderSettingsService;
    }

    /**
     * Handles adding a new {@code ReminderSettings} via the {@code ReminderSettingsService} and outputs a success message.
     *
     * @param reminderSettings The {@code ReminderSettings} object to be added.
     */
    public void addReminder(ReminderSettings reminderSettings) {
        reminderSettingsService.addReminderService(reminderSettings);
        System.out.println("Reminder added successfully.");
    }

    /**
     * Handles updating an existing {@code ReminderSettings} via the {@code ReminderSettingsService} and outputs a success message.
     *
     * @param reminderSettings The {@code ReminderSettings} object with updated information.
     */
    public void updateReminder(ReminderSettings reminderSettings) {
        reminderSettingsService.updateReminderService(reminderSettings);
        System.out.println("Reminder updated successfully.");
    }

    /**
     * Handles deleting a {@code ReminderSettings} by its ID via the {@code ReminderSettingsService} and outputs a success message.
     *
     * @param reminderId The ID of the {@code ReminderSettings} to be deleted.
     */
    public void deleteReminder(int reminderId) {
        reminderSettingsService.deleteReminderService(reminderId);
        System.out.println("Reminder deleted successfully.");
    }

    /**
     * Retrieves and lists all {@code ReminderSettings} objects via the {@code ReminderSettingsService} and prints them to the console.
     */
    public void listAllReminders() {
        List<ReminderSettings> reminders = reminderSettingsService.getAllRemindersService();
        for (ReminderSettings reminder : reminders) {
            System.out.println(reminder);
        }
    }

    /**
     * Retrieves a {@code ReminderSettings} by its ID via the {@code ReminderSettingsService} and prints its details.
     * If the {@code ReminderSettings} is not found, it outputs a 'not found' message.
     *
     * @param reminderId The ID of the {@code ReminderSettings} to retrieve.
     */
    public void getReminderDetails(int reminderId) {
        Optional<ReminderSettings> reminder = reminderSettingsService.getReminderByIdService(reminderId);
        if (reminder.isPresent()) {
            System.out.println(reminder.get());
        } else {
            System.out.println("Reminder not found.");
        }
    }

    /**
     * Main method to simulate operations on the {@code ReminderSettingsController}.
     * Demonstrates adding, updating, retrieving, deleting, and listing {@code ReminderSettings} objects.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Setup DAO and Repository
        ReminderSettingsDAOInterface reminderSettingsDAO = new ReminderSettingsDAO(); // Ensure proper instantiation
        ReminderSettingsRepository reminderSettingsRepository = new ReminderSettingsRepository(reminderSettingsDAO);

        // Setup Service and Controller
        ReminderSettingsService reminderSettingsService = new ReminderSettingsService(reminderSettingsRepository);
        ReminderSettingsController reminderSettingsController = new ReminderSettingsController(reminderSettingsService);

        // Create a sample Bill object.
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
        reminderSettingsController.getReminderDetails(1);

        // Delete reminder2 by its ID.
        reminderSettingsController.deleteReminder(2);

        // List all reminders after the deletion of reminder2.
        System.out.println("Listing all reminders after deletion:");
        reminderSettingsController.listAllReminders();
    }
}
