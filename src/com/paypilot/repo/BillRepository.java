/*
 * The BillRepository class is responsible for managing a collection of Bill objects.
 * It provides methods for adding, updating, deleting, and retrieving bills.
 * Additionally, this class allows for searching bills by category and updating reminder settings for a specific bill. 
 * This repository is initialized with some demo data for testing purposes. 
 * 
 * Author: Anshul
 * Date: 09-08-2024 
 */

package com.paypilot.repo;

import com.paypilot.model.Bill;
import com.paypilot.model.ReminderSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.File;

public class BillRepository {

    private List<Bill> bills = new ArrayList<>();

    /**
     * Constructor to initialize the repository with demo bills.
     * Adds a set of predefined bills to the repository for testing purposes.
     */
    public BillRepository() {
        // Initialize with some demo bills
        bills.add(new Bill(1, "Electricity Bill", "Utilities", new Date(2024-1900, 7, 15), 100.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0, null));
        bills.add(new Bill(2, "Internet Bill", "Internet Charges", new Date(2024-1900, 7, 10), 60.00, "Monthly", null, "", true, "Paid", 0, null));
        bills.add(new Bill(3, "Rent", "House Rent", new Date(2024-1900, 6, 30), 1200.00, "Monthly", null, "Rent for June", false, "Overdue", 5, null));
        bills.add(new Bill(4, "Groceries", "Groceries", new Date(2024-1900, 7, 5), 200.00, "Weekly", null, "Weekly groceries", false, "Paid", 0, null));
    }

    /**
     * Adds a new bill to the repository.
     * 
     * @param bill The Bill object to be added to the repository.
     */
    public void addBill(Bill bill) {
        bills.add(bill);
    }

    /**
     * Updates an existing bill in the repository. If a bill with the same ID exists, it will be replaced.
     * 
     * @param bill The Bill object containing updated information.
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
     * Deletes a bill from the repository based on its ID.
     * 
     * @param billId The ID of the bill to be deleted.
     */
    public void deleteBill(int billId) {
        bills.removeIf(bill -> bill.getBillId() == billId);
    }

    /**
     * Retrieves all bills from the repository.
     * 
     * @return A List of all Bill objects in the repository.
     */
    public List<Bill> getAllBills() {
        return new ArrayList<>(bills);
    }

    /**
     * Retrieves a bill by its ID.
     * 
     * @param billId The ID of the bill to retrieve.
     * @return The Bill object with the specified ID, or null if not found.
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
     * @param category The category of the bills to find.
     * @return A List of Bill objects that match the specified category.
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
     * @param billId The ID of the bill for which to update the reminder settings.
     * @param reminderSettings The new ReminderSettings to be set for the bill.
     */
    public void updateBillReminderSettings(int billId, ReminderSettings reminderSettings) {
        Bill bill = getBillById(billId);
        if (bill != null) {
            bill.setReminderSettings(reminderSettings);
            updateBill(bill);
        }
    }
}
