/**
 * The {@code ReminderSettingsDAOInterface} interface defines the data access operations
 * for {@code ReminderSettings} entities. It provides methods for adding, updating,
 * deleting, and retrieving reminder settings from the data source.
 * 
 * <p>Author: Anshul</p>
 * <p>Date: 29-08-2024</p>
 */
package com.paypilot.repo;

import com.paypilot.model.ReminderSettings;
import java.util.List;
import java.util.Optional;

public interface ReminderSettingsDAOInterface {

    /**
     * Adds a new {@code ReminderSettings} object to the data source.
     * 
     * @param reminderSettings The {@code ReminderSettings} object to be added.
     * @throws IllegalArgumentException if the provided {@code ReminderSettings} object is invalid.
     */
    void addReminder(ReminderSettings reminderSettings);

    /**
     * Updates an existing {@code ReminderSettings} object in the data source.
     * 
     * @param reminderSettings The {@code ReminderSettings} object with updated information.
     * @throws IllegalArgumentException if the provided {@code ReminderSettings} object is invalid or if the ID is not found.
     */
    void updateReminder(ReminderSettings reminderSettings);

    /**
     * Deletes a {@code ReminderSettings} object from the data source based on its ID.
     * 
     * @param reminderId The ID of the {@code ReminderSettings} object to be deleted.
     * @throws IllegalArgumentException if the ID is invalid or not found.
     */
    void deleteReminder(int reminderId);

    /**
     * Retrieves a {@code ReminderSettings} object by its ID.
     * 
     * @param reminderId The ID of the {@code ReminderSettings} object to retrieve.
     * @return An {@code Optional} containing the {@code ReminderSettings} object with the specified ID,
     *         or an empty {@code Optional} if not found.
     * @throws IllegalArgumentException if the ID is invalid.
     */
    Optional<ReminderSettings> getReminderById(int reminderId);

    /**
     * Retrieves all {@code ReminderSettings} objects from the data source.
     * 
     * @return A {@code List} of all {@code ReminderSettings} objects.
     */
    List<ReminderSettings> getAllReminders();

    /**
     * Retrieves a {@code ReminderSettings} object by the associated bill's ID.
     * 
     * @param billId The ID of the bill associated with the {@code ReminderSettings} to retrieve.
     * @return An {@code Optional} containing the {@code ReminderSettings} object associated with the specified bill ID,
     *         or an empty {@code Optional} if not found.
     * @throws IllegalArgumentException if the bill ID is invalid.
     */
    Optional<ReminderSettings> getReminderByBillId(int billId);
}
