package com.paypilot.repo;

import com.paypilot.model.ReminderSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReminderSettingsRepository {

    private List<ReminderSettings> reminderSettingsList = new ArrayList<>();
    private int nextId = 1;

    public void addReminderToRepo(ReminderSettings reminderSettings) {
        reminderSettings.setReminderId(nextId++);
        reminderSettingsList.add(reminderSettings);
    }

    public void updateReminderInRepo(ReminderSettings reminderSettings) {
        for (int i = 0; i < reminderSettingsList.size(); i++) {
            if (reminderSettingsList.get(i).getReminderId() == reminderSettings.getReminderId()) {
                reminderSettingsList.set(i, reminderSettings);
                return;
            }
        }
    }

    public void deleteReminderFromRepo(int reminderId) {
        reminderSettingsList.removeIf(reminder -> reminder.getReminderId() == reminderId);
    }

    public Optional<ReminderSettings> getReminderByIdFromRepo(int reminderId) {
        return reminderSettingsList.stream()
                .filter(reminder -> reminder.getReminderId() == reminderId)
                .findFirst();
    }

    public List<ReminderSettings> getAllRemindersFromRepo() {
        return new ArrayList<>(reminderSettingsList);
    }

    // Method to get reminder settings by bill id
    public Optional<ReminderSettings> getReminderByBillId(int billId) {
        return reminderSettingsList.stream()
                .filter(reminder -> reminder.getBill() != null && reminder.getBill().getBillId() == billId)
                .findFirst();
    }
}