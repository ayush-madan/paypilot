/*
 * BillService provides the service methods for managing bills and reminder settings.
 * It interacts with the BillRepository to perform CRUD operations on bills and to update reminder settings.
 * 
 * Author: Anshul 
 * Date: 09-08-2024
 */


package com.paypilot.service;

import com.paypilot.model.Bill;
import com.paypilot.model.ReminderSettings;
import com.paypilot.repo.BillRepository;

import java.util.Date;
import java.util.List;

public class BillService {

    private BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    // Adds a new bill
    public void addBillService(Bill bill) {
        billRepository.addBill(bill);
    }

    // Updates an existing bill
    public void updateBillService(Bill bill) {
        billRepository.updateBill(bill);
    }
    
    // Deletes a bill
    public void deleteBillService(int billId) {
        billRepository.deleteBill(billId);
    }

    // Retrieving the list of all bills
    public List<Bill> getAllBillsService() {
        return billRepository.getAllBills();
    }

    // Retrieving the bill by bill ID
    public Bill getBillByIdService(int billId) {
        return billRepository.getBillById(billId);
    }

    // Updating the reminder settings for a specific bill
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