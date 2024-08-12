/*
 * The ReminderSettingsRepository class is responsible for managing a collection of ReminderSettings objects.
 * It provides methods for adding, updating, deleting, and retrieving reminder settings.
 * Additionally, this class allows for finding reminder settings by their respective bill ID.
 *
 * Author: Anshul
 * Date: 09-08-2024
 */

package com.paypilot.repo;

import com.paypilot.model.ReminderSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReminderSettingsRepository {

    private List<ReminderSettings> reminderSettingsList = new ArrayList<>();
    private int nextId = 1;

    /**
     * Adds a new reminder to the repository and assigns it a unique ID.
     * 
     * @param reminderSettings The ReminderSettings object to be added to the repository.
     */
    public void addReminderToRepo(ReminderSettings reminderSettings) {
        reminderSettings.setReminderId(nextId++);
        reminderSettingsList.add(reminderSettings);
    }

    /**
     * Updates an existing reminder in the repository. If a reminder with the same ID exists, it will be replaced.
     * 
     * @param reminderSettings The ReminderSettings object containing updated information.
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
     * Deletes a reminder from the repository based on its ID.
     * 
     * @param reminderId The ID of the reminder to be deleted.
     */
    public void deleteReminderFromRepo(int reminderId) {
        reminderSettingsList.removeIf(reminder -> reminder.getReminderId() == reminderId);
    }

    /**
     * Retrieves a reminder by its ID.
     * 
     * @param reminderId The ID of the reminder to retrieve.
     * @return An Optional containing the ReminderSettings object if found, or an empty Optional if not found.
     */
    public Optional<ReminderSettings> getReminderByIdFromRepo(int reminderId) {
        return reminderSettingsList.stream()
                .filter(reminder -> reminder.getReminderId() == reminderId)
                .findFirst();
    }

    /**
     * Retrieves all reminders in the repository.
     * 
     * @return A List of all ReminderSettings objects in the repository.
     */
    public List<ReminderSettings> getAllRemindersFromRepo() {
        return new ArrayList<>(reminderSettingsList);
    }

    /**
     * Retrieves reminder settings by bill ID.
     * 
     * @param billId The ID of the bill associated with the reminder settings.
     * @return An Optional containing the ReminderSettings object if found, or an empty Optional if not found.
     */
    public Optional<ReminderSettings> getReminderByBillId(int billId) {
        return reminderSettingsList.stream()
                .filter(reminder -> reminder.getBill() != null && reminder.getBill().getBillId() == billId)
                .findFirst();
    }
}
