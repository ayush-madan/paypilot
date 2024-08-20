/**
 * The {@code BillRepository} class is responsible for managing a collection of {@code Bill} objects.
 * It provides methods for adding, updating, deleting, and retrieving bills.
 * This repository delegates database operations to {@code BillDAO}.
 * 
 * <p>Author: Anshul</p>
 * <p>Date: 09-08-2024</p>
 */

package com.paypilot.repo;

import com.paypilot.model.Bill;
import com.paypilot.model.ReminderSettings;
import java.util.List;


public class BillRepository {

    private BillDAOInterface billDAO;

    /**
     * Constructs a {@code BillRepository} with the given {@code BillDAO}.
     * 
     * @param billDAO The {@code BillDAO} object used for database operations.
     */
    public BillRepository(BillDAOInterface billDAO) {
        this.billDAO = billDAO;
    }

    /**
     * Adds a new {@code Bill} to the repository.
     * This method delegates the operation to the {@code BillDAO}.
     * 
     * @param bill The {@code Bill} object to be added.
     */
    public void addBill(Bill bill) {
        billDAO.addBill(bill);
    }

    /**
     * Updates an existing {@code Bill} in the repository.
     * This method delegates the operation to the {@code BillDAO}.
     * 
     * @param bill The {@code Bill} object with updated information.
     */
    public void updateBill(Bill bill) {
        billDAO.updateBill(bill);
    }

    /**
     * Deletes a {@code Bill} from the repository based on its ID.
     * This method delegates the operation to the {@code BillDAO}.
     * 
     * @param billId The ID of the {@code Bill} to be deleted.
     */
    public void deleteBill(int billId) {
        billDAO.deleteBill(billId);
    }

    /**
     * Retrieves all {@code Bill} objects in the repository.
     * This method delegates the retrieval to the {@code BillDAO}.
     * 
     * @return A list of all {@code Bill} objects.
     */
    public List<Bill> getAllBills() {
        return billDAO.getAllBills();
    }

    /**
     * Retrieves a {@code Bill} by its ID.
     * This method delegates the retrieval to the {@code BillDAO}.
     * 
     * @param billId The ID of the {@code Bill} to retrieve.
     * @return The {@code Bill} object with the specified ID, or {@code null} if not found.
     */
    public Bill getBillById(int billId) {
        return billDAO.getBillById(billId);
    }

    /**
     * Finds {@code Bill} objects by their category.
     * This method delegates the retrieval to the {@code BillDAO}.
     * 
     * @param category The category to search for.
     * @return A list of {@code Bill} objects that match the specified category.
     */
    public List<Bill> getBillsByCategory(String category) {
        return billDAO.getBillsByCategory(category);
    }

    /**
     * Updates the reminder settings for a {@code Bill} identified by its ID.
     * The method retrieves the bill, updates its reminder settings, and saves the changes.
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
