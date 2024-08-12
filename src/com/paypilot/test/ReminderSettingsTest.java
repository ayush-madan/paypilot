/*
 * The ReminderSettingsTest class contains unit tests for the ReminderSettingsService class, 
 * which manages the ReminderSettings objects.
 * 
 * Author: Dimple
 * Date: 09-08-2024
 */
package com.paypilot.test;

import com.paypilot.model.Bill;
import com.paypilot.model.ReminderSettings;
import com.paypilot.repo.ReminderSettingsRepository;
import com.paypilot.service.ReminderSettingsService;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ReminderSettingsTest {

    private ReminderSettingsService reminderSettingsService;
    private ReminderSettingsRepository reminderSettingsRepository;

    @Before
    public void setUp() {
        // Initialize the repository and service before each test
        reminderSettingsRepository = new ReminderSettingsRepository();
        reminderSettingsService = new ReminderSettingsService(reminderSettingsRepository);
    }

    @Test
    public void testAddReminder() {
        // Create a sample Bill
        // Arguments:
        // billId: 1
        // billName: "Electricity Bill"
        // category: "Utilities"
        // dueDate: new Date() - current date
        // amount: 100.50
        // frequency: "Monthly"
        // reminder: null
        // description: "Pay before due date"
        // paid: false
        // status: "Upcoming"
        // priority: 0
        // reminderSettings: null
        Bill bill = new Bill(1, "Electricity Bill", "Utilities", new Date(), 100.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0, null);
        
        // Create a sample ReminderSettings
        // Arguments:
        // reminderId: 1
        // reminderFrequency: "Monthly"
        // reminderDate: new Date() - current date
        // customMessage: "Reminder Message"
        // notificationType: "Email"
        // associatedBill: bill - the Bill object associated with this reminder
        ReminderSettings reminderSettings = new ReminderSettings(1, "Monthly", new Date(), "Reminder Message", "Email", bill);
        
        // Add the reminder using the service
        reminderSettingsService.addReminderService(reminderSettings);

        // Retrieve the reminder to verify it was added
        Optional<ReminderSettings> retrievedReminder = reminderSettingsService.getReminderByIdService(1);
        assertTrue("Reminder was not added correctly", retrievedReminder.isPresent());
        assertEquals("Reminder ID does not match", 1, retrievedReminder.get().getReminderId());
    }

    @Test
    public void testUpdateReminder() {
        // Create a sample Bill
        Bill bill = new Bill(1, "Electricity Bill", "Utilities", new Date(), 100.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0, null);
        
        // Create and add a sample ReminderSettings
        // Arguments:
        // reminderId: 1
        // reminderFrequency: "Monthly"
        // reminderDate: new Date() - current date
        // customMessage: "Reminder Message"
        // notificationType: "Email"
        // associatedBill: bill - the Bill object associated with this reminder
        ReminderSettings reminderSettings = new ReminderSettings(1, "Monthly", new Date(), "Reminder Message", "Email", bill);
        reminderSettingsService.addReminderService(reminderSettings);

        // Create and update the reminder with new values
        // Arguments for updated ReminderSettings:
        // reminderId: 1
        // reminderFrequency: "Weekly"
        // reminderDate: new Date() - current date
        // customMessage: "Updated Message"
        // notificationType: "SMS"
        // associatedBill: bill - the same Bill object
        ReminderSettings updatedReminderSettings = new ReminderSettings(1, "Weekly", new Date(), "Updated Message", "SMS", bill);
        reminderSettingsService.updateReminderService(updatedReminderSettings);

        // Retrieve and verify the updated reminder
        Optional<ReminderSettings> retrievedReminder = reminderSettingsService.getReminderByIdService(1);
        assertTrue("Reminder was not found after update", retrievedReminder.isPresent());
        assertEquals("Reminder frequency did not update correctly", "Weekly", retrievedReminder.get().getReminderFrequency());
        assertEquals("Custom message did not update correctly", "Updated Message", retrievedReminder.get().getCustomMessage());
    }

    @Test
    public void testDeleteReminder() {
        // Create a sample Bill
        Bill bill = new Bill(1, "Electricity Bill", "Utilities", new Date(), 100.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0, null);
        
        // Create and add a sample ReminderSettings
        ReminderSettings reminderSettings = new ReminderSettings(1, "Monthly", new Date(), "Reminder Message", "Email", bill);
        reminderSettingsService.addReminderService(reminderSettings);

        // Delete the reminder
        reminderSettingsService.deleteReminderService(1);

        // Verify the reminder was deleted
        Optional<ReminderSettings> retrievedReminder = reminderSettingsService.getReminderByIdService(1);
        assertFalse("Reminder was not deleted", retrievedReminder.isPresent());
    }

    @Test
    public void testGetReminderById() {
        // Create a sample Bill
        Bill bill = new Bill(1, "Electricity Bill", "Utilities", new Date(), 100.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0, null);
        
        // Create and add a sample ReminderSettings
        ReminderSettings reminderSettings = new ReminderSettings(1, "Monthly", new Date(), "Reminder Message", "Email", bill);
        reminderSettingsService.addReminderService(reminderSettings);

        // Retrieve the reminder by ID
        Optional<ReminderSettings> retrievedReminder = reminderSettingsService.getReminderByIdService(1);
        assertTrue("Reminder was not found by ID", retrievedReminder.isPresent());
        assertEquals("Reminder frequency does not match", "Monthly", retrievedReminder.get().getReminderFrequency());
    }

    @Test
    public void testGetAllReminders() {
        // Create sample Bills
        Bill bill1 = new Bill(1, "Electricity Bill", "Utilities", new Date(), 100.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0, null);
        Bill bill2 = new Bill(2, "Internet Bill", "Internet", new Date(), 60.00, "Monthly", null, "Reminder for Internet Bill", true, "Upcoming", 0, null);

        // Create and add multiple ReminderSettings
        ReminderSettings reminderSettings1 = new ReminderSettings(1, "Monthly", new Date(), "Reminder Message 1", "Email", bill1);
        ReminderSettings reminderSettings2 = new ReminderSettings(2, "Weekly", new Date(), "Reminder Message 2", "SMS", bill2);

        reminderSettingsService.addReminderService(reminderSettings1);
        reminderSettingsService.addReminderService(reminderSettings2);

        // Retrieve all reminders and verify the count
        List<ReminderSettings> allReminders = reminderSettingsService.getAllRemindersService();
        assertEquals("The number of reminders should be 2", 2, allReminders.size());
    }
}
