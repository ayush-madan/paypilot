package com.paypilot.service;

import com.paypilot.model.Bill;
import com.paypilot.model.ReminderSettings;
import com.paypilot.repo.BillRepository;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Service class for managing bills.
 * Provides methods for adding, updating, deleting, and retrieving bills, as well as managing reminder settings.
 */
public class BillService {

    private BillRepository billRepository;

    /**
     * Constructs a BillService instance with the specified BillRepository.
     * 
     * @param billRepository The repository used to manage bills
     */
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    /**
     * Adds a new bill to the repository.
     * 
     * @param bill The bill to be added
     */
    public void addBillService(Bill bill) {
        billRepository.addBill(bill);
    }

    /**
     * Updates an existing bill in the repository.
     * 
     * @param bill The bill with updated information
     */
    public void updateBillService(Bill bill) {
        billRepository.updateBill(bill);
    }

    /**
     * Deletes a bill from the repository by its ID.
     * 
     * @param billId The ID of the bill to be deleted
     */
    public void deleteBillService(int billId) {
        billRepository.deleteBill(billId);
    }

    /**
     * Retrieves all bills from the repository.
     * 
     * @return A list of all bills
     */
    public List<Bill> getAllBillsService() {
        return billRepository.getAllBills();
    }

    /**
     * Retrieves a bill by its ID.
     * 
     * @param billId The ID of the bill to retrieve
     * @return The bill with the specified ID, or null if not found
     */
    public Bill getBillByIdService(int billId) {
        return billRepository.getBillById(billId);
    }

    /**
     * Updates the reminder settings for a specified bill.
     * If the bill already has reminder settings, they are updated; otherwise, new settings are created.
     * 
     * @param billId             The ID of the bill to update
     * @param frequency          The reminder frequency
     * @param startDate          The start date of the reminder
     * @param message            The custom message for the reminder
     * @param notificationPref   The notification preference (e.g., email, SMS)
     * @param isRecurring        Whether the reminder is recurring
     */
    public void updateReminderSettings(int billId, String frequency, Date startDate, String message, 
                                        String notificationPref, boolean isRecurring) {
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

    /**
     * Creates and adds a new bill to the repository.
     * 
     * @param billId             The ID of the new bill
     * @param billName           The name of the bill
     * @param billCategory       The category of the bill
     * @param dueDate            The due date of the bill
     * @param amount             The amount of the bill
     * @param reminderFrequency  The frequency of the reminder
     * @param attachment         An optional attachment for the bill
     * @param notes              Additional notes related to the bill
     * @param isRecurring        Whether the bill is recurring
     * @param paymentStatus      The payment status of the bill
     * @param overdueDays        The number of overdue days
     * @param reminderSettings   The reminder settings associated with the bill
     */
    public void createBillService(int billId, String billName, String billCategory, Date dueDate, double amount,
                                  String reminderFrequency, File attachment, String notes, boolean isRecurring,
                                  String paymentStatus, int overdueDays, ReminderSettings reminderSettings) {
        billRepository.createBill(billId, billName, billCategory, dueDate, amount, reminderFrequency,
                                  attachment, notes, isRecurring, paymentStatus, overdueDays, reminderSettings);
    }
}
