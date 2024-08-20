package com.paypilot.repo;

import com.paypilot.model.Bill;
import java.util.List;

/**
 * The {@code BillDAO} interface defines methods for managing {@code Bill} objects
 * in the database. It provides operations for adding, updating, deleting, 
 * retrieving, and finding bills based on their attributes.
 * 
 * <p>Author: Anshul</p>
 * <p>Date: 20-08-2024</p>
 */
public interface BillDAOInterface {
    
    /**
     * Adds a new {@code Bill} to the database.
     * 
     * @param bill The {@code Bill} object to be added.
     */
    void addBill(Bill bill);
    
    /**
     * Updates an existing {@code Bill} in the database.
     * 
     * @param bill The {@code Bill} object with updated information.
     */
    void updateBill(Bill bill);
    
    /**
     * Deletes a {@code Bill} from the database based on its ID.
     * 
     * @param billId The ID of the {@code Bill} to be deleted.
     */
    void deleteBill(int billId);
    
    /**
     * Retrieves a {@code Bill} by its ID.
     * 
     * @param billId The ID of the {@code Bill} to retrieve.
     * @return The {@code Bill} object with the specified ID, or {@code null} if not found.
     */
    Bill getBillById(int billId);
    
    /**
     * Retrieves all {@code Bill} objects from the database.
     * 
     * @return A list of all {@code Bill} objects.
     */
    List<Bill> getAllBills();
    
    /**
     * Finds {@code Bill} objects by their category.
     * 
     * @param category The category to search for.
     * @return A list of {@code Bill} objects that match the specified category.
     */
    List<Bill> getBillsByCategory(String category);
}
