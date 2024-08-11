package com.paypilot.service;

import com.paypilot.model.ReminderSettings;
import com.paypilot.repo.ReminderSettingsRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing reminder settings.
 */
public class ReminderSettingsService {

    private ReminderSettingsRepository reminderSettingsRepository;

    /**
     * Constructs a ReminderSettingsService with the specified repository.
     * @param reminderSettingsRepository The repository to interact with.
     */
    public ReminderSettingsService(ReminderSettingsRepository reminderSettingsRepository) {
        this.reminderSettingsRepository = reminderSettingsRepository;
    }

    /**
     * Adds a new reminder settings entry.
     * @param reminderSettings The reminder settings to add.
     */
    public void addReminderService(ReminderSettings reminderSettings) {
        reminderSettingsRepository.addReminderToRepo(reminderSettings);
    }

    /**
     * Updates an existing reminder settings entry.
     * @param reminderSettings The updated reminder settings.
     */
    public void updateReminderService(ReminderSettings reminderSettings) {
        reminderSettingsRepository.updateReminderInRepo(reminderSettings);
    }

    /**
     * Deletes a reminder settings entry by its ID.
     * @param reminderId The ID of the reminder settings to delete.
     */
    public void deleteReminderService(int reminderId) {
        reminderSettingsRepository.deleteReminderFromRepo(reminderId);
    }

    /**
     * Retrieves a reminder settings entry by its ID.
     * @param reminderId The ID of the reminder settings to retrieve.
     * @return An Optional containing the reminder settings if found, otherwise empty.
     */
    public Optional<ReminderSettings> getReminderByIdService(int reminderId) {
        return reminderSettingsRepository.getReminderByIdFromRepo(reminderId);
    }

    /**
     * Retrieves all reminder settings entries.
     * @return A list of all reminder settings.
     */
    public List<ReminderSettings> getAllRemindersService() {
        return reminderSettingsRepository.getAllRemindersFromRepo();
    }
}
