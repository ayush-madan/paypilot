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
import com.paypilot.repo.ReminderSettingsDAO;
import com.paypilot.repo.ReminderSettingsRepository;
import com.paypilot.service.ReminderSettingsService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Unit test class for ReminderSettingsService.
 * This class tests the functionalities provided by the ReminderSettingsService class, 
 * including adding, updating, deleting, and retrieving reminder settings.
 */
public class ReminderSettingsTest {
	
	private ReminderSettingsDAO reminderSettingsDAO;
    private ReminderSettingsService reminderSettingsService;
    private ReminderSettingsRepository reminderSettingsRepository;

    /**
     * Sets up the test environment before each test.
     * Initializes the ReminderSettingsRepository and ReminderSettingsService instances.
     */
    @Before
    public void setUp() {
        // Initialize the repository and service before each test
    	reminderSettingsDAO = new ReminderSettingsDAO();
        reminderSettingsRepository = new ReminderSettingsRepository(reminderSettingsDAO);
        reminderSettingsService = new ReminderSettingsService(reminderSettingsRepository);
    }

    
    /**
     * Tests the addition of a reminder using the addReminderService method.
     * Verifies that the reminder is successfully added and can be retrieved.
     * 
     * <p>Bill attributes:</p>
     * <ul>
     *   <li>billId: 1</li>
     *   <li>billName: "Electricity Bill"</li>
     *   <li>category: "Utilities"</li>
     *   <li>dueDate: new Date() (current date)</li>
     *   <li>amount: 100.50</li>
     *   <li>frequency: "Monthly"</li>
     *   <li>reminder: null</li>
     *   <li>description: "Pay before due date"</li>
     *   <li>paid: false</li>
     *   <li>status: "Upcoming"</li>
     *   <li>priority: 0</li>
     *   <li>reminderSettings: null</li>
     * </ul>
     * 
     * <p>ReminderSettings attributes:</p>
     * <ul>
     *   <li>reminderId: 1</li>
     *   <li>reminderFrequency: "Monthly"</li>
     *   <li>reminderDate: new Date() (current date)</li>
     *   <li>customMessage: "Reminder Message"</li>
     *   <li>notificationType: "Email"</li>
     *   <li>associatedBill: bill - the Bill object associated with this reminder</li>
     * </ul>
     */
    @Test
    public void testAddReminder() {
        // Create a sample Bill
        Bill bill = new Bill(1, "Electricity Bill", "Utilities", new Date(), 100.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0, null);
        
        // Create a sample ReminderSettings
        ReminderSettings reminderSettings = new ReminderSettings(4, "Monthly", new Date(), "Reminder Message", "Email", bill);
        
        // Add the reminder using the service
        reminderSettingsService.addReminderService(reminderSettings);

        // Retrieve the reminder to verify it was added
        Optional<ReminderSettings> retrievedReminder = reminderSettingsService.getReminderByIdService(reminderSettings.getReminderId());
        assertTrue("Reminder was not added correctly", retrievedReminder.isPresent());
        assertEquals("Reminder ID does not match", reminderSettings.getReminderId(), retrievedReminder.get().getReminderId());
        
        //Remove the added ReminderSettings
        reminderSettingsService.deleteReminderService(reminderSettings.getReminderId());
    }

    
    /**
     * Tests the updating of a reminder using the updateReminderService method.
     * Verifies that the reminder is successfully updated and the new values are correct.
     * 
     * <p>Original ReminderSettings attributes:</p>
     * <ul>
     *   <li>reminderId: 1</li>
     *   <li>reminderFrequency: "Monthly"</li>
     *   <li>reminderDate: new Date() (current date)</li>
     *   <li>customMessage: "Reminder Message"</li>
     *   <li>notificationType: "Email"</li>
     *   <li>associatedBill: bill - the Bill object associated with this reminder</li>
     * </ul>
     * 
     * <p>Updated ReminderSettings attributes:</p>
     * <ul>
     *   <li>reminderId: 1</li>
     *   <li>reminderFrequency: "Weekly"</li>
     *   <li>reminderDate: new Date() (current date)</li>
     *   <li>customMessage: "Updated Message"</li>
     *   <li>notificationType: "SMS"</li>
     *   <li>associatedBill: bill - the same Bill object</li>
     * </ul>
     */
    @Test
    public void testUpdateReminder() {
        // Create a sample Bill
        Bill bill = new Bill(1, "Electricity Bill", "Utilities", new Date(), 100.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0, null);
        
        // Create and add a sample ReminderSettings
        ReminderSettings reminderSettings = new ReminderSettings(4, "Monthly", new Date(), "Reminder Message", "Email", bill);
        reminderSettingsService.addReminderService(reminderSettings);

        // Create and update the reminder with new values
        ReminderSettings updatedReminderSettings = new ReminderSettings(4, "Weekly", new Date(), "Updated Message", "SMS", bill);
        reminderSettingsService.updateReminderService(updatedReminderSettings);

        // Retrieve and verify the updated reminder
        Optional<ReminderSettings> retrievedReminder = reminderSettingsService.getReminderByIdService(4);
        assertTrue("Reminder was not found after update", retrievedReminder.isPresent());
        assertEquals("Reminder frequency did not update correctly", "Weekly", retrievedReminder.get().getReminderFrequency());
        assertEquals("Custom message did not update correctly", "Updated Message", retrievedReminder.get().getCustomMessage());
        
        //Remove the added ReminderSettings
        reminderSettingsService.deleteReminderService(reminderSettings.getReminderId());
    }

    
    /**
     * Tests the deletion of a reminder using the deleteReminderService method.
     * Verifies that the reminder is successfully deleted and cannot be retrieved.
     */
    @Test
    public void testDeleteReminder() {
        // Create a sample Bill
        Bill bill = new Bill(1, "Electricity Bill", "Utilities", new Date(), 100.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0, null);
        
        // Create and add a sample ReminderSettings
        ReminderSettings reminderSettings = new ReminderSettings(4, "Monthly", new Date(), "Reminder Message", "Email", bill);
        reminderSettingsService.addReminderService(reminderSettings);

        // Delete the reminder
        reminderSettingsService.deleteReminderService(reminderSettings.getReminderId());

        // Verify the reminder was deleted
        Optional<ReminderSettings> retrievedReminder = reminderSettingsService.getReminderByIdService(reminderSettings.getReminderId());
        assertFalse("Reminder was not deleted", retrievedReminder.isPresent());
    }

    
    /**
     * Tests the retrieval of a reminder by ID using the getReminderByIdService method.
     * Verifies that the reminder is found and the reminder frequency matches the expected value.
     */
    @Test
    public void testGetReminderById() {
        // Create a sample Bill
        Bill bill = new Bill(1, "Electricity Bill", "Utilities", new Date(), 100.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0, null);
        
        // Create and add a sample ReminderSettings
        ReminderSettings reminderSettings = new ReminderSettings(4, "Monthly", new Date(), "Reminder Message", "Email", bill);
        reminderSettingsService.addReminderService(reminderSettings);

        // Retrieve the reminder by ID
        Optional<ReminderSettings> retrievedReminder = reminderSettingsService.getReminderByIdService(reminderSettings.getReminderId());
        assertTrue("Reminder was not found by ID", retrievedReminder.isPresent());
        assertEquals("Reminder frequency does not match", "Monthly", retrievedReminder.get().getReminderFrequency());
        
        //Remove the added ReminderSettings
        reminderSettingsService.deleteReminderService(reminderSettings.getReminderId());
    }

    
    /**
     * Tests the retrieval of all reminders using the getAllRemindersService method.
     * Verifies that the size of the reminder list increases by two after adding two new reminders.
     * 
     * <p>Bill 1 attributes:</p>
     * <ul>
     *   <li>billId: 1</li>
     *   <li>billName: "Electricity Bill"</li>
     *   <li>category: "Utilities"</li>
     *   <li>dueDate: new Date() (current date)</li>
     *   <li>amount: 100.50</li>
     *   <li>frequency: "Monthly"</li>
     *   <li>reminder: null</li>
     *   <li>description: "Pay before due date"</li>
     *   <li>paid: false</li>
     *   <li>status: "Upcoming"</li>
     *   <li>priority: 0</li>
     *   <li>reminderSettings: null</li>
     * </ul>
     * 
     * <p>ReminderSettings 1 attributes:</p>
     * <ul>
     *   <li>reminderId: 1</li>
     *   <li>reminderFrequency: "Monthly"</li>
     *   <li>reminderDate: new Date() (current date)</li>
     *   <li>customMessage: "Reminder Message 1"</li>
     *   <li>notificationType: "Email"</li>
     *   <li>associatedBill: bill1 - the Bill object associated with this reminder</li>
     * </ul>
     * 
     * <p>Bill 2 attributes:</p>
     * <ul>
     *   <li>billId: 2</li>
     *   <li>billName: "Internet Bill"</li>
     *   <li>category: "Internet"</li>
     *   <li>dueDate: new Date() (current date)</li>
     *   <li>amount: 60.00</li>
     *   <li>frequency: "Monthly"</li>
     *   <li>reminder: null</li>
     *   <li>description: "Reminder for Internet Bill"</li>
     *   <li>paid: true</li>
     *   <li>status: "Upcoming"</li>
     *   <li>priority: 0</li>
     *   <li>reminderSettings: null</li>
     * </ul>
     * 
     * <p>ReminderSettings 2 attributes:</p>
     * <ul>
     *   <li>reminderId: 2</li>
     *   <li>reminderFrequency: "Weekly"</li>
     *   <li>reminderDate: new Date() (current date)</li>
     *   <li>customMessage: "Reminder Message 2"</li>
     *   <li>notificationType: "SMS"</li>
     *   <li>associatedBill: bill2 - the Bill object associated with this reminder</li>
     * </ul>
     */
    
    @Test
    public void testGetAllReminders() {
    	int oldSize = reminderSettingsService.getAllRemindersService().size();
    	
        // Create sample Bills
        Bill bill1 = new Bill(1, "Electricity Bill", "Utilities", new Date(), 100.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0, null);

        // Create and add multiple ReminderSettings
        ReminderSettings reminderSettings = new ReminderSettings(4, "Monthly", new Date(), "Reminder Message 1", "Email", bill1);

        reminderSettingsService.addReminderService(reminderSettings);
    	
        // Retrieve all reminders and verify the count
        int newSize = reminderSettingsService.getAllRemindersService().size();
        
        assertEquals("Unable to fetch all reminders", oldSize+1, newSize);
        
        //Remove the added ReminderSettings
        reminderSettingsService.deleteReminderService(reminderSettings.getReminderId());
    }
}
