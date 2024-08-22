/*
 * The ReminderSettingsTest class contains unit tests for the ReminderSettingsService class, 
 * which manages the ReminderSettings objects.
 * 
 * Author: Dimple, Ayush Madan
 * Date: 20-08-2024
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
     * Configures the test environment prior to each test execution.
     * 
     * <p>This method is responsible for setting up the necessary components, including
     * {@code ReminderSettingsDAO}, {@code ReminderSettingsRepository}, and {@code ReminderSettingsService},
     * ensuring that the test environment is properly initialized before each test runs.</p>
     * 
     * <p><b>External Support Modules:</b></p>
     * <ul>
     *   <li><b>JUnit 4:</b> Utilized for annotations such as {@code @Before} and {@code @Test} to manage test setup and execution.</li>
     *   <li><b>Java Collections Framework:</b> Used internally within the repository and service to manage collections of reminder settings.</li>
     * </ul>
     */
    @Before
    public void setUp() {
        // Initialize the repository and service before each test
    	reminderSettingsDAO = new ReminderSettingsDAO();
        reminderSettingsRepository = new ReminderSettingsRepository(reminderSettingsDAO);
        reminderSettingsService = new ReminderSettingsService(reminderSettingsRepository);
    }

    
    /**
     * Validates the addition of a reminder using the {@code addReminderService} method.
     * 
     * <p>This test ensures that a reminder is successfully added to the system and can be retrieved 
     * based on its ID.</p>
     * 
     * <p><b>Bill Attributes:</b></p>
     * <ul>
     *   <li><b>ID:</b> 1</li>
     *   <li><b>Name:</b> "Electricity Bill"</li>
     *   <li><b>Category:</b> "Utilities"</li>
     *   <li><b>Due Date:</b> Current date ({@code new Date()})</li>
     *   <li><b>Amount:</b> 100.50</li>
     *   <li><b>Reminder Frequency:</b> "Monthly"</li>
     *   <li><b>Attachment:</b> Not set (null)</li>
     *   <li><b>Notes:</b> "Pay before due date"</li>
     *   <li><b>Is Recurring:</b> false</li>
     *   <li><b>Payment Status:</b> "Upcoming"</li>
     *   <li><b>Priority:</b> 0</li>
     *   <li><b>Reminder Settings:</b> Not set (null)</li>
     * </ul>
     * 
     * <p><b>ReminderSettings Attributes:</b></p>
     * <ul>
     *   <li><b>ID:</b> 4</li>
     *   <li><b>Reminder Frequency:</b> "Monthly"</li>
     *   <li><b>Reminder Start Date:</b> Current date ({@code new Date()})</li>
     *   <li><b>Custom Message:</b> "Reminder Message"</li>
     *   <li><b>Notification Type:</b> "Email"</li>
     *   <li><b>Associated Bill:</b> The {@code Bill} object associated with this reminder</li>
     * </ul>
     * 
     * <p>The test checks that the reminder is added successfully and can be retrieved by its ID. 
     * It also verifies that the ID of the retrieved reminder matches the expected value. After verification,
     * the added reminder is deleted to maintain test isolation.</p>
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
     * Validates the updating of a reminder using the {@code updateReminderService} method.
     * 
     * <p>This test ensures that a reminder can be successfully updated and that the updated values are 
     * correctly reflected in the system.</p>
     * 
     * <p><b>Original ReminderSettings Attributes:</b></p>
     * <ul>
     *   <li><b>ID:</b> 4</li>
     *   <li><b>Reminder Frequency:</b> "Monthly"</li>
     *   <li><b>Reminder Date:</b> Current date ({@code new Date()})</li>
     *   <li><b>Custom Message:</b> "Reminder Message"</li>
     *   <li><b>Notification Type:</b> "Email"</li>
     *   <li><b>Associated Bill:</b> The {@code Bill} object associated with this reminder</li>
     * </ul>
     * 
     * <p><b>Updated ReminderSettings Attributes:</b></p>
     * <ul>
     *   <li><b>ID:</b> 4</li>
     *   <li><b>Reminder Frequency:</b> "Weekly"</li>
     *   <li><b>Reminder Date:</b> Current date ({@code new Date()})</li>
     *   <li><b>Custom Message:</b> "Updated Message"</li>
     *   <li><b>Notification Type:</b> "SMS"</li>
     *   <li><b>Associated Bill:</b> The same {@code Bill} object as before</li>
     * </ul>
     * 
     * <p>The test updates the reminder settings and verifies that the new values are correctly applied by 
     * retrieving the updated reminder from the system and checking its attributes. After verification, 
     * the reminder is deleted to maintain test isolation.</p>
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
     * Validates the deletion of a reminder using the {@code deleteReminderService} method.
     * 
     * <p>This test ensures that a reminder can be successfully deleted from the system and that it cannot
     * be retrieved afterward.</p>
     * 
     * <p><b>ReminderSettings Attributes for Deletion:</b></p>
     * <ul>
     *   <li><b>ID:</b> 4</li>
     *   <li><b>Reminder Frequency:</b> "Monthly"</li>
     *   <li><b>Reminder Date:</b> Current date ({@code new Date()})</li>
     *   <li><b>Custom Message:</b> "Reminder Message"</li>
     *   <li><b>Notification Type:</b> "Email"</li>
     *   <li><b>Associated Bill:</b> The {@code Bill} object associated with this reminder</li>
     * </ul>
     * 
     * <p>The test first adds a reminder to the system using the {@code addReminderService} method. It then
     * deletes the reminder using the {@code deleteReminderService} method and verifies that the reminder 
     * is no longer present in the system by attempting to retrieve it with {@code getReminderByIdService}. 
     * The absence of the reminder is confirmed by checking that the retrieval method returns {@code Optional.empty()}.</p>
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
     * Validates the retrieval of a reminder by ID using the {@code getReminderByIdService} method.
     * 
     * <p>This test ensures that a reminder can be successfully retrieved from the system by its ID and that
     * the retrieved reminder's attributes match the expected values.</p>
     * 
     * <p><b>ReminderSettings Attributes for Retrieval:</b></p>
     * <ul>
     *   <li><b>ID:</b> 4</li>
     *   <li><b>Reminder Frequency:</b> "Monthly"</li>
     *   <li><b>Reminder Date:</b> Current date ({@code new Date()})</li>
     *   <li><b>Custom Message:</b> "Reminder Message"</li>
     *   <li><b>Notification Type:</b> "Email"</li>
     *   <li><b>Associated Bill:</b> The {@code Bill} object associated with this reminder</li>
     * </ul>
     * 
     * <p>The test first creates and adds a reminder with the specified attributes using the {@code addReminderService}
     * method. It then retrieves the reminder using its ID with {@code getReminderByIdService}. The test verifies that
     * the retrieved reminder is present and that the reminder frequency matches the expected value. Finally, the
     * test removes the added reminder to maintain a clean test environment.</p>
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
     * Validates the retrieval of all reminders using the {@code getAllRemindersService} method.
     * 
     * <p>This test ensures that reminders are correctly added and that the total count of reminders
     * increases by the expected amount after adding a new reminder.</p>
     * 
     * <p><b>Sample ReminderSettings Attributes for Addition:</b></p>
     * <ul>
     *   <li><b>ID:</b> 4</li>
     *   <li><b>Reminder Frequency:</b> "Monthly"</li>
     *   <li><b>Reminder Date:</b> Current date ({@code new Date()})</li>
     *   <li><b>Custom Message:</b> "Reminder Message 1"</li>
     *   <li><b>Notification Type:</b> "Email"</li>
     *   <li><b>Associated Bill:</b> {@code Bill} object with ID 1</li>
     * </ul>
     * 
     * <p>The test ensures that the reminder was successfully added and that the reminder list accurately reflects
     * the total number of reminders. Finally, the test removes the added reminder to maintain a clean test environment.</p>
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
