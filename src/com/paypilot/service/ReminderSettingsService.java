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

    public void addReminderService(ReminderSettings reminderSettings) {
        reminderSettingsRepository.addReminderToRepo(reminderSettings);
    }

    public void updateReminderService(ReminderSettings reminderSettings) {
        reminderSettingsRepository.updateReminderInRepo(reminderSettings);
    }

    public void deleteReminderService(int reminderId) {
        reminderSettingsRepository.deleteReminderFromRepo(reminderId);
    }

    public Optional<ReminderSettings> getReminderByIdService(int reminderId) {
        return reminderSettingsRepository.getReminderByIdFromRepo(reminderId);
    }

    public List<ReminderSettings> getAllRemindersService() {
        return reminderSettingsRepository.getAllRemindersFromRepo();
    }
}
