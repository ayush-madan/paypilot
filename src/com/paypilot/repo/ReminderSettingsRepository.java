package com.paypilot.repo;

import com.paypilot.model.ReminderSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing reminder settings.
 * Provides methods to add, update, delete, and retrieve reminder settings.
 */
public class ReminderSettingsRepository {

    private List<ReminderSettings> reminderSettingsList = new ArrayList<>();
    private int nextId = 1; // ID generator for new reminder settings

    /**
     * Adds a new reminder settings object to the repository.
     * 
     * @param reminderSettings The reminder settings to be added
     */
    public void addReminderToRepo(ReminderSettings reminderSettings) {
        reminderSettings.setReminderId(nextId++);
        reminderSettingsList.add(reminderSettings);
    }

    /**
     * Updates an existing reminder settings object in the repository.
     * 
     * @param reminderSettings The updated reminder settings
     */
    public void updateReminderInRepo(ReminderSettings reminderSettings) {
        for (int i = 0; i < reminderSettingsList.size(); i++) {
            if (reminderSettingsList.get(i).getReminderId() == reminderSettings.getReminderId()) {
                reminderSettingsList.set(i, reminderSettings);
                return;
            }
        }
    }

    /**
     * Deletes a reminder settings object from the repository by its ID.
     * 
     * @param reminderId The ID of the reminder settings to be deleted
     */
    public void deleteReminderFromRepo(int reminderId) {
        reminderSettingsList.removeIf(reminder -> reminder.getReminderId() == reminderId);
    }

    /**
     * Retrieves a reminder settings object by its ID.
     * 
     * @param reminderId The ID of the reminder settings to retrieve
     * @return An Optional containing the reminder settings if found, otherwise empty
     */
    public Optional<ReminderSettings> getReminderByIdFromRepo(int reminderId) {
        return reminderSettingsList.stream()
                .filter(reminder -> reminder.getReminderId() == reminderId)
                .findFirst();
    }

    /**
     * Retrieves all reminder settings objects in the repository.
     * 
     * @return A list of all reminder settings
     */
    public List<ReminderSettings> getAllRemindersFromRepo() {
        return new ArrayList<>(reminderSettingsList);
    }

    /**
     * Retrieves a reminder settings object associated with a specific bill ID.
     * 
     * @param billId The ID of the bill
     * @return An Optional containing the reminder settings if found, otherwise empty
     */
    public Optional<ReminderSettings> getReminderByBillId(int billId) {
        return reminderSettingsList.stream()
                .filter(reminder -> reminder.getBill() != null && reminder.getBill().getBillId() == billId)
                .findFirst();
    }
}
