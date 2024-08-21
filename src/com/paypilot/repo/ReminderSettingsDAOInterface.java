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
     * <p>Business Logic:</p>
     * This method inserts a new record into the {@code ReminderSettings} table.
     * The provided {@code ReminderSettings} object must contain valid data for the insertion to succeed.
     * 
     * @param reminderSettings The {@code ReminderSettings} object to be added.
     * @throws IllegalArgumentException if the provided {@code ReminderSettings} object is invalid.
     */
    void addReminder(ReminderSettings reminderSettings);

    /**
     * Updates an existing {@code ReminderSettings} object in the data source.
     * 
     * <p>Business Logic:</p>
     * This method updates an existing reminder settings record in the {@code ReminderSettings} table
     * with the new information provided in the {@code ReminderSettings} object.
     * The object must have a valid ID corresponding to an existing record in the database.
     * 
     * @param reminderSettings The {@code ReminderSettings} object with updated information.
     * @throws IllegalArgumentException if the provided {@code ReminderSettings} object is invalid or if the ID is not found.
     */
    void updateReminder(ReminderSettings reminderSettings);

    /**
     * Deletes a {@code ReminderSettings} object from the data source based on its ID.
     * 
     * <p>Business Logic:</p>
     * This method removes a record from the {@code ReminderSettings} table using the specified reminder ID.
     * The ID must correspond to an existing record for the deletion to be successful.
     * 
     * @param reminderId The ID of the {@code ReminderSettings} object to be deleted.
     * @throws IllegalArgumentException if the ID is invalid or not found.
     */
    void deleteReminder(int reminderId);

    /**
     * Retrieves a {@code ReminderSettings} object by its ID.
     * 
     * <p>Business Logic:</p>
     * This method fetches a reminder settings record from the {@code ReminderSettings} table
     * using the specified reminder ID. If the record exists, it returns an {@code Optional} containing the object;
     * otherwise, it returns an empty {@code Optional}.
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
     * <p>Business Logic:</p>
     * This method retrieves all reminder settings records from the {@code ReminderSettings} table.
     * It returns a {@code List} of all objects currently stored in the database.
     * 
     * @return A {@code List} of all {@code ReminderSettings} objects.
     */
    List<ReminderSettings> getAllReminders();

    /**
     * Retrieves a {@code ReminderSettings} object by the associated bill's ID.
     * 
     * <p>Business Logic:</p>
     * This method fetches a reminder settings record associated with the specified bill ID.
     * It returns an {@code Optional} containing the {@code ReminderSettings} object if found,
     * or an empty {@code Optional} if no record is associated with the given bill ID.
     * 
     * @param billId The ID of the bill associated with the {@code ReminderSettings} to retrieve.
     * @return An {@code Optional} containing the {@code ReminderSettings} object associated with the specified bill ID,
     *         or an empty {@code Optional} if not found.
     * @throws IllegalArgumentException if the bill ID is invalid.
     */
    Optional<ReminderSettings> getReminderByBillId(int billId);
}
