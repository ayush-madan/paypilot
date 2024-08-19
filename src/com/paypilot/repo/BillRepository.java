/**
 * The {@code BillRepository} class is responsible for managing a collection of {@code Bill} objects.
 * It provides methods for adding, updating, deleting, and retrieving bills. 
 * Additionally, it allows for searching bills by category and updating reminder settings for a specific bill.
 * This repository is initialized with some demo data for testing purposes.
 * 
 * <p>Author: Anshul</p>
 * <p>Date: 09-08-2024</p>
 */
package com.paypilot.repo;

import com.paypilot.model.Bill;
import com.paypilot.model.ReminderSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.File;

public class BillRepository {

    /**
     * The list of bills managed by this repository.
     */
    private List<Bill> bills = new ArrayList<>();

    /**
     * Constructs a {@code BillRepository} and initializes it with some demo bills.
     */
    public BillRepository() {
        // Initialize with some demo bills
        bills.add(new Bill(1, "Electricity Bill", "Utilities", new Date(2024-1900, 7, 15), 100.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0, null));
        bills.add(new Bill(2, "Internet Bill", "Internet Charges", new Date(2024-1900, 7, 10), 60.00, "Monthly", null, "", true, "Paid", 0, null));
        bills.add(new Bill(3, "Rent", "House Rent", new Date(2024-1900, 6, 30), 1200.00, "Monthly", null, "Rent for June", false, "Overdue", 5, null));
        bills.add(new Bill(4, "Groceries", "Groceries", new Date(2024-1900, 7, 5), 200.00, "Weekly", null, "Weekly groceries", false, "Paid", 0, null));
    }

    /**
     * Adds a new {@code Bill} to the repository.
     * 
     * @param bill The {@code Bill} object to be added.
     */
    public void addBill(Bill bill) {
        bills.add(bill);
    }

    /**
     * Updates an existing {@code Bill} in the repository.
     * 
     * @param bill The {@code Bill} object with updated information.
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
     * Deletes a {@code Bill} from the repository based on its ID.
     * 
     * @param billId The ID of the {@code Bill} to be deleted.
     */
    public void deleteBill(int billId) {
        bills.removeIf(bill -> bill.getBillId() == billId);
    }

    /**
     * Retrieves all {@code Bill} objects in the repository.
     * 
     * @return A list of all {@code Bill} objects.
     */
    public List<Bill> getAllBills() {
        return new ArrayList<>(bills);
    }

    /**
     * Retrieves a {@code Bill} by its ID.
     * 
     * @param billId The ID of the {@code Bill} to retrieve.
     * @return The {@code Bill} object with the specified ID, or {@code null} if not found.
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
     * Finds {@code Bill} objects by their category.
     * 
     * @param category The category to search for.
     * @return A list of {@code Bill} objects that match the specified category.
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
     * Updates the reminder settings for a {@code Bill} identified by its ID.
     * 
     * @param billId The ID of the {@code Bill} to update.
     * @param reminderSettings The new {@code ReminderSettings} to set for the bill.
     */
    public void updateBillReminderSettings(int billId, ReminderSettings reminderSettings) {
        Bill bill = getBillById(billId);
        if (bill != null) {
            bill.setReminderSettings(reminderSettings);
            updateBill(bill);
        }
    }
}
