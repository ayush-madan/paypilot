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

    public void addBillService(Bill bill) {
        billRepository.addBill(bill);
    }

    public void updateBillService(Bill bill) {
        billRepository.updateBill(bill);
    }

    public void deleteBillService(int billId) {
        billRepository.deleteBill(billId);
    }

    public List<Bill> getAllBillsService() {
        return billRepository.getAllBills();
    }

    public Bill getBillByIdService(int billId) {
        return billRepository.getBillById(billId);
    }

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