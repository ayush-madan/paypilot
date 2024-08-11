/*
 * The ReminderSettingsRepository class responsible for managing a collection of ReminderSettings objects.
 * It provides methods for adding, updating, deleting, and retrieving reminder settings.
 * Also, this class allows for finding reminder settings by their respective bill ID.
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

    //Adds a new reminder to the repository and assigns it a unique ID
    public void addReminderToRepo(ReminderSettings reminderSettings) {
        reminderSettings.setReminderId(nextId++);
        reminderSettingsList.add(reminderSettings);
    }

    //Updates an existing reminder in the repository
    public void updateReminderInRepo(ReminderSettings reminderSettings) {
        for (int i = 0; i < reminderSettingsList.size(); i++) {
            if (reminderSettingsList.get(i).getReminderId() == reminderSettings.getReminderId()) {
                reminderSettingsList.set(i, reminderSettings);
                return;
            }
        }
    }

    //Deletes a reminder from the repository based on its ID
    public void deleteReminderFromRepo(int reminderId) {
        reminderSettingsList.removeIf(reminder -> reminder.getReminderId() == reminderId);
    }

    /*
	    Retrieves a reminder by its ID. 
	    Returns an 'Optional' containing the ReminderSettings object if found, or empty if not found.
    */
    public Optional<ReminderSettings> getReminderByIdFromRepo(int reminderId) {
        return reminderSettingsList.stream()
                .filter(reminder -> reminder.getReminderId() == reminderId)
                .findFirst();
    }

    //Retrieves list all reminders in the repository.
    public List<ReminderSettings> getAllRemindersFromRepo() {
        return new ArrayList<>(reminderSettingsList);
    }

    /* 
    	Retrieves reminder settings by bill id, returns an 'Optional' containing the ReminderSettings object 
    	if found, or empty if not found.
    */
    public Optional<ReminderSettings> getReminderByBillId(int billId) {
        return reminderSettingsList.stream()
                .filter(reminder -> reminder.getBill() != null && reminder.getBill().getBillId() == billId)
                .findFirst();
    }
}