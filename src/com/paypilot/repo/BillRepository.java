package com.paypilot.repo;

import com.paypilot.model.Bill;
import com.paypilot.model.ReminderSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.File;

/**
 * Repository class for managing bills.
 * Provides methods to add, update, delete, and retrieve bills.
 */
public class BillRepository {

    private List<Bill> bills = new ArrayList<>();

    /**
     * Initializes the repository with some demo bills.
     */
    public BillRepository() {
        bills.add(new Bill(1, "Electricity Bill", "Utilities", new Date(2024-1900, 7, 15), 100.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0, null));
        bills.add(new Bill(2, "Internet Bill", "Internet Charges", new Date(2024-1900, 7, 10), 60.00, "Monthly", null, "", true, "Paid", 0, null));
        bills.add(new Bill(3, "Rent", "House Rent", new Date(2024-1900, 6, 30), 1200.00, "Monthly", null, "Rent for June", false, "Pending", 5, null));
        bills.add(new Bill(4, "Groceries", "Groceries", new Date(2024-1900, 7, 5), 200.00, "Weekly", null, "Weekly groceries", false, "Paid", 0, null));
    }

    /**
     * Adds a new bill to the repository.
     * 
     * @param bill The bill to be added
     */
    public void addBill(Bill bill) {
        bills.add(bill);
    }

    /**
     * Updates an existing bill in the repository.
     * 
     * @param bill The bill with updated information
     */
    public void updateBill(Bill bill) {
        for (int i = 0; i < bills.size(); i++) {
            if (bills.get(i).getBillId() == bill.getBillId()) {
                bills.set(i, bill);
                return;
            }
        }
    }

    /**
     * Deletes a bill from the repository by its ID.
     * 
     * @param billId The ID of the bill to be deleted
     */
    public void deleteBill(int billId) {
        bills.removeIf(bill -> bill.getBillId() == billId);
    }

    /**
     * Retrieves all bills in the repository.
     * 
     * @return A list of all bills
     */
    public List<Bill> getAllBills() {
        return new ArrayList<>(bills);
    }

    /**
     * Retrieves a bill by its ID.
     * 
     * @param billId The ID of the bill to retrieve
     * @return The bill with the specified ID, or null if not found
     */
    public Bill getBillById(int billId) {
        for (Bill bill : bills) {
            if (bill.getBillId() == billId) {
                return bill;
            }
        }
        return null;
    }

    /**
     * Finds bills by their category.
     * 
     * @param category The category to search for
     * @return A list of bills in the specified category
     */
    public List<Bill> getBillsByCategory(String category) {
        List<Bill> result = new ArrayList<>();
        for (Bill bill : bills) {
            if (bill.getBillCategory().equalsIgnoreCase(category)) {
                result.add(bill);
            }
        }
        return result;
    }

    /**
     * Updates the reminder settings for a specific bill.
     * 
     * @param billId            The ID of the bill
     * @param reminderSettings The new reminder settings
     */
    public void updateBillReminderSettings(int billId, ReminderSettings reminderSettings) {
        Bill bill = getBillById(billId);
        if (bill != null) {
            bill.setReminderSettings(reminderSettings);
            updateBill(bill);
        }
    }

    /**
     * Creates and adds a new bill with detailed attributes.
     * 
     * @param billId            Unique ID for the bill
     * @param billName          Name of the bill
     * @param billCategory      Category of the bill
     * @param dueDate           Due date of the bill
     * @param amount            Amount of the bill
     * @param reminderFrequency Reminder frequency
     * @param attachment        Optional file attachment
     * @param notes             Additional notes
     * @param isRecurring       Whether the bill is recurring
     * @param paymentStatus     Payment status
     * @param overdueDays       Number of overdue days
     * @param reminderSettings  Reminder settings
     */
    public void createBill(int billId, String billName, String billCategory, Date dueDate, double amount,
            String reminderFrequency, File attachment, String notes, boolean isRecurring,
            String paymentStatus, int overdueDays, ReminderSettings reminderSettings) {
        Bill newBill = new Bill(billId, billName, billCategory, dueDate, amount, reminderFrequency,
                                attachment, notes, isRecurring, paymentStatus, overdueDays, reminderSettings);
        addBill(newBill);
    }
}
