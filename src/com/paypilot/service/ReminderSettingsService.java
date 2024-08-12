package com.paypilot.service;

import com.paypilot.model.ReminderSettings;
import com.paypilot.repo.ReminderSettingsRepository;

import java.util.List;
import java.util.Optional;

/**
 * ReminderSettingsService provides methods for managing reminder settings.
 * It interacts with the ReminderSettingsRepository to perform CRUD operations on reminder settings.
 * 
 * Author: Anshul
 * Date: 09-08-2024
 */
public class ReminderSettingsService {

    private ReminderSettingsRepository reminderSettingsRepository; // Repository to manage ReminderSettings

    /**
     * Constructs a ReminderSettingsService with the specified ReminderSettingsRepository.
     * 
     * @param reminderSettingsRepository The ReminderSettingsRepository used by this service.
     */
    public ReminderSettingsService(ReminderSettingsRepository reminderSettingsRepository) {
        this.reminderSettingsRepository = reminderSettingsRepository;
    }

    /**
     * Adds a new reminder setting to the repository.
     * 
     * @param reminderSettings The ReminderSettings object to be added.
     */
    public void addReminderService(ReminderSettings reminderSettings) {
        reminderSettingsRepository.addReminderToRepo(reminderSettings);
    }

    /**
     * Updates an existing reminder setting in the repository.
     * 
     * @param reminderSettings The ReminderSettings object with updated information.
     */
    public void updateReminderService(ReminderSettings reminderSettings) {
        reminderSettingsRepository.updateReminderInRepo(reminderSettings);
    }

    /**
     * Deletes a reminder setting from the repository based on its ID.
     * 
     * @param reminderId The ID of the reminder setting to be deleted.
     */
    public void deleteReminderService(int reminderId) {
        reminderSettingsRepository.deleteReminderFromRepo(reminderId);
    }

    /**
     * Retrieves a reminder setting from the repository by its ID.
     * 
     * @param reminderId The ID of the reminder setting to be retrieved.
     * @return An Optional containing the ReminderSettings object if found, or empty if not found.
     */
    public Optional<ReminderSettings> getReminderByIdService(int reminderId) {
        return reminderSettingsRepository.getReminderByIdFromRepo(reminderId);
    }

    /**
     * Retrieves all reminder settings from the repository.
     * 
     * @return A List of all ReminderSettings objects in the repository.
     */
    public List<ReminderSettings> getAllRemindersService() {
        return reminderSettingsRepository.getAllRemindersFromRepo();
    }
}
