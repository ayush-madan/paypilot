/**
 * The {@code ReminderSettingsRepository} class is responsible for managing {@code ReminderSettings} objects.
 * It delegates data management tasks to the {@code ReminderSettingsDAO} for database operations.
 * 
 * <p>Author: Anshul</p>
 * <p>Date: 09-08-2024</p>
 */
package com.paypilot.repo;

import com.paypilot.model.ReminderSettings;

import java.util.List;
import java.util.Optional;


public class ReminderSettingsRepository {

    private ReminderSettingsDAOInterface reminderSettingsDAO; // DAO instance for database operations

    /**
     * Constructs a {@code ReminderSettingsRepository} with the specified {@code ReminderSettingsDAO}.
     * 
     * @param reminderSettingsDAO The {@code ReminderSettingsDAO} instance used for database operations.
     */
    public ReminderSettingsRepository(ReminderSettingsDAOInterface reminderSettingsDAO) {
        this.reminderSettingsDAO = reminderSettingsDAO;
    }

    /**
     * Adds a new {@code ReminderSettings} to the repository.
     * 
     * @param reminderSettings The {@code ReminderSettings} object to be added.
     */
    public void addReminder(ReminderSettings reminderSettings) {
        reminderSettingsDAO.addReminder(reminderSettings);
    }

    /**
     * Updates an existing {@code ReminderSettings} in the repository.
     * 
     * @param reminderSettings The {@code ReminderSettings} object with updated information.
     */
    public void updateReminder(ReminderSettings reminderSettings) {
        reminderSettingsDAO.updateReminder(reminderSettings);
    }

    /**
     * Deletes a {@code ReminderSettings} from the repository based on its ID.
     * 
     * @param reminderId The ID of the {@code ReminderSettings} to be deleted.
     */
    public void deleteReminder(int reminderId) {
        reminderSettingsDAO.deleteReminder(reminderId);
    }

    /**
     * Retrieves a {@code ReminderSettings} by its ID.
     * 
     * @param reminderId The ID of the {@code ReminderSettings} to retrieve.
     * @return An {@code Optional} containing the {@code ReminderSettings} object if found, or {@code Optional.empty()} if not found.
     */
    public Optional<ReminderSettings> getReminderById(int reminderId) {
        return reminderSettingsDAO.getReminderById(reminderId);
    }

    /**
     * Retrieves all {@code ReminderSettings} from the repository.
     * 
     * @return A {@code List} of all {@code ReminderSettings} objects.
     */
    public List<ReminderSettings> getAllReminders() {
        return reminderSettingsDAO.getAllReminders();
    }

    /**
     * Retrieves {@code ReminderSettings} by the associated bill's ID.
     * 
     * @param billId The ID of the bill for which to retrieve reminder settings.
     * @return An {@code Optional} containing the {@code ReminderSettings} object if found, or {@code Optional.empty()} if not found.
     */
    public Optional<ReminderSettings> getReminderByBillId(int billId) {
        return reminderSettingsDAO.getReminderByBillId(billId);
    }
}
