/*
 * ReminderSettingsService provides the service methods for managing reminder settings.
 * It interacts with the ReminderSettingsRepository to perform CRUD operations on reminders.
 * 
 * Author: Anshul
 * Date: 09-08-2024
 */

package com.paypilot.service;

import com.paypilot.model.ReminderSettings;
import com.paypilot.repo.ReminderSettingsRepository;

import java.util.List;
import java.util.Optional;

public class ReminderSettingsService {

    private ReminderSettingsRepository reminderSettingsRepository;

    public ReminderSettingsService(ReminderSettingsRepository reminderSettingsRepository) {
        this.reminderSettingsRepository = reminderSettingsRepository;
    }

    // Adds a new reminder setting 
    public void addReminderService(ReminderSettings reminderSettings) {
        reminderSettingsRepository.addReminderToRepo(reminderSettings);
    }

    // Updates an existing reminder setting 
    public void updateReminderService(ReminderSettings reminderSettings) {
        reminderSettingsRepository.updateReminderInRepo(reminderSettings);
    }

    // Deletes a reminder setting
    public void deleteReminderService(int reminderId) {
        reminderSettingsRepository.deleteReminderFromRepo(reminderId);
    }
    
    /*
     * Retrieves a reminder setting by its ID.
     * Returns an 'Optional' containing the ReminderSettings object if found, or empty if not.
     */ 
    public Optional<ReminderSettings> getReminderByIdService(int reminderId) {
        return reminderSettingsRepository.getReminderByIdFromRepo(reminderId);
    }
    
    //List of all the reminder settings
    public List<ReminderSettings> getAllRemindersService() {
        return reminderSettingsRepository.getAllRemindersFromRepo();
    }
}