/**
 * {@code ReminderSettingsService} provides the service methods for managing reminder settings.
 * It interacts with the {@code ReminderSettingsRepository} to perform CRUD operations on reminders.
 * 
 * <p>Author: Anshul</p>
 * <p>Date: 09-08-2024</p>
 */
package com.paypilot.service;

import com.paypilot.model.ReminderSettings;
import com.paypilot.repo.ReminderSettingsRepository;

import java.util.List;
import java.util.Optional;

public class ReminderSettingsService {

    /**
     * The repository used for performing CRUD operations on reminder settings.
     */
    private ReminderSettingsRepository reminderSettingsRepository;

    /**
     * Constructs a {@code ReminderSettingsService} with the specified {@code ReminderSettingsRepository}.
     * 
     * @param reminderSettingsRepository The {@code ReminderSettingsRepository} used by this service.
     */
    public ReminderSettingsService(ReminderSettingsRepository reminderSettingsRepository) {
        this.reminderSettingsRepository = reminderSettingsRepository;
    }

    /**
     * Adds a new reminder setting to the repository.
     * 
     * @param reminderSettings The {@code ReminderSettings} object to be added.
     */
    public void addReminderService(ReminderSettings reminderSettings) {
        reminderSettingsRepository.addReminderToRepo(reminderSettings);
    }

    /**
     * Updates an existing reminder setting in the repository.
     * 
     * @param reminderSettings The {@code ReminderSettings} object with updated information.
     */
    public void updateReminderService(ReminderSettings reminderSettings) {
        reminderSettingsRepository.updateReminderInRepo(reminderSettings);
    }

    /**
     * Deletes a reminder setting from the repository based on its ID.
     * 
     * @param reminderId The ID of the {@code ReminderSettings} to be deleted.
     */
    public void deleteReminderService(int reminderId) {
        reminderSettingsRepository.deleteReminderFromRepo(reminderId);
    }
    
    /**
     * Retrieves a reminder setting by its ID.
     * 
     * @param reminderId The ID of the reminder setting to be retrieved.
     * @return An {@code Optional} containing the {@code ReminderSettings} object if found, or empty if not.
     */
    public Optional<ReminderSettings> getReminderByIdService(int reminderId) {
        return reminderSettingsRepository.getReminderByIdFromRepo(reminderId);
    }
    
    /**
     * Retrieves the list of all reminder settings from the repository.
     * 
     * @return A list of all {@code ReminderSettings} objects.
     */
    public List<ReminderSettings> getAllRemindersService() {
        return reminderSettingsRepository.getAllRemindersFromRepo();
    }
}
