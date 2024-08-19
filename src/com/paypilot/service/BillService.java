/**
 * {@code BillService} provides the service methods for managing bills and reminder settings.
 * It interacts with the {@code BillRepository} to perform CRUD operations on bills and to update reminder settings.
 * 
 * <p>Author: Anshul</p>
 * <p>Date: 09-08-2024</p>
 */
package com.paypilot.service;

import com.paypilot.model.Bill;
import com.paypilot.model.ReminderSettings;
import com.paypilot.repo.BillRepository;

import java.util.Date;
import java.util.List;

public class BillService {

    /**
     * The repository used for performing CRUD operations on bills.
     */
    private BillRepository billRepository;

    /**
     * Constructs a {@code BillService} with the specified {@code BillRepository}.
     * 
     * @param billRepository The {@code BillRepository} used by this service.
     */
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    /**
     * Adds a new bill to the repository.
     * 
     * @param bill The {@code Bill} object to be added.
     */
    public void addBillService(Bill bill) {
        billRepository.addBill(bill);
    }

    /**
     * Updates an existing bill in the repository.
     * 
     * @param bill The {@code Bill} object with updated information.
     */
    public void updateBillService(Bill bill) {
        billRepository.updateBill(bill);
    }
    
    /**
     * Deletes a bill from the repository based on its ID.
     * 
     * @param billId The ID of the {@code Bill} to be deleted.
     */
    public void deleteBillService(int billId) {
        billRepository.deleteBill(billId);
    }

    /**
     * Retrieves the list of all bills from the repository.
     * 
     * @return A list of all {@code Bill} objects.
     */
    public List<Bill> getAllBillsService() {
        return billRepository.getAllBills();
    }

    /**
     * Retrieves a bill from the repository by its ID.
     * 
     * @param billId The ID of the {@code Bill} to be retrieved.
     * @return The {@code Bill} object with the specified ID, or {@code null} if not found.
     */
    public Bill getBillByIdService(int billId) {
        return billRepository.getBillById(billId);
    }

    /**
     * Updates the reminder settings for a specific bill.
     * 
     * @param billId The ID of the {@code Bill} to update.
     * @param frequency The frequency of the reminder.
     * @param startDate The start date for the reminder.
     * @param message The custom message to be sent with the reminder.
     * @param notificationPref The preference for notification.
     * @param isRecurring Whether the reminder is recurring or not.
     */
    public void updateReminderSettings(int billId, String frequency, Date startDate, String message, String notificationPref, boolean isRecurring) {
        Bill bill = billRepository.getBillById(billId);
        if (bill != null) {
            ReminderSettings reminderSettings = new ReminderSettings(
                    bill.getReminderSettings() != null ? bill.getReminderSettings().getReminderId() : -1, // Use existing ID or -1 for new
                    frequency, startDate, message, notificationPref, bill
            );
            bill.setReminderSettings(reminderSettings);
            billRepository.updateBill(bill);
        }
    }
}
