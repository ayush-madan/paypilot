/**
 * The {@code ReminderSettingsRepository} class is responsible for managing a collection of {@code ReminderSettings} objects.
 * It provides methods for adding, updating, deleting, and retrieving reminder settings. 
 * Additionally, it allows for finding reminder settings by their respective bill ID.
 * 
 * <p>Author: Anshul</p>
 * <p>Date: 09-08-2024</p>
 */
package com.paypilot.repo;

import com.paypilot.model.ReminderSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReminderSettingsRepository {

    /**
     * The list of reminder settings managed by this repository.
     */
    private List<ReminderSettings> reminderSettingsList = new ArrayList<>();

    /**
     * The next unique ID to be assigned to a new reminder setting.
     */
    private int nextId = 1;

    /**
     * Adds a new {@code ReminderSettings} to the repository and assigns it a unique ID.
     * 
     * @param reminderSettings The {@code ReminderSettings} object to be added.
     */
    public void addReminderToRepo(ReminderSettings reminderSettings) {
        reminderSettings.setReminderId(nextId++);
        reminderSettingsList.add(reminderSettings);
    }

    /**
     * Updates an existing {@code ReminderSettings} in the repository.
     * 
     * @param reminderSettings The {@code ReminderSettings} object with updated information.
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
     * Deletes a {@code ReminderSettings} from the repository based on its ID.
     * 
     * @param reminderId The ID of the {@code ReminderSettings} to be deleted.
     */
    public void deleteReminderFromRepo(int reminderId) {
        reminderSettingsList.removeIf(reminder -> reminder.getReminderId() == reminderId);
    }

    /**
     * Retrieves a {@code ReminderSettings} by its ID.
     * 
     * @param reminderId The ID of the {@code ReminderSettings} to retrieve.
     * @return An {@code Optional} containing the {@code ReminderSettings} object if found, or {@code Optional.empty()} if not found.
     */
    public Optional<ReminderSettings> getReminderByIdFromRepo(int reminderId) {
        return reminderSettingsList.stream()
                .filter(reminder -> reminder.getReminderId() == reminderId)
                .findFirst();
    }

    /**
     * Retrieves all {@code ReminderSettings} in the repository.
     * 
     * @return A list of all {@code ReminderSettings} objects.
     */
    public List<ReminderSettings> getAllRemindersFromRepo() {
        return new ArrayList<>(reminderSettingsList);
    }

    /**
     * Retrieves {@code ReminderSettings} by bill ID.
     * 
     * @param billId The ID of the bill for which to retrieve reminder settings.
     * @return An {@code Optional} containing the {@code ReminderSettings} object if found, or {@code Optional.empty()} if not found.
     */
    public Optional<ReminderSettings> getReminderByBillId(int billId) {
        return reminderSettingsList.stream()
                .filter(reminder -> reminder.getBill() != null && reminder.getBill().getBillId() == billId)
                .findFirst();
    }
}
