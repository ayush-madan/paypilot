/**
 * The {@code BillDAOInterface} interface defines methods for managing {@code Bill} objects
 * in the database. It provides operations for adding, updating, deleting, 
 * retrieving, and finding bills based on their attributes.
 * 
 * <p>Author: Anshul</p>
 * <p>Date: 20-08-2024</p>
 */
package com.paypilot.repo;

import com.paypilot.model.Bill;
import java.util.List;

public interface BillDAOInterface {
    
    /**
     * Adds a new {@code Bill} to the database.
     * 
     * <p>Business Logic:</p>
     * This method is used to insert a new bill record into the database. It ensures that a 
     * new bill with all the required details is stored in the {@code Bills} table.
     * 
     * @param bill The {@code Bill} object to be added.
     */
    void addBill(Bill bill);
    
    /**
     * Updates an existing {@code Bill} in the database.
     * 
     * <p>Business Logic:</p>
     * This method updates the details of an existing bill in the database. It modifies the
     * record in the {@code Bills} table with the new information provided in the {@code Bill} object.
     * 
     * @param bill The {@code Bill} object with updated information.
     */
    void updateBill(Bill bill);
    
    /**
     * Deletes a {@code Bill} from the database based on its ID.
     * 
     * <p>Business Logic:</p>
     * This method removes a bill record from the {@code Bills} table using the specified bill ID.
     * It ensures that the bill with the given ID is deleted from the database.
     * 
     * @param billId The ID of the {@code Bill} to be deleted.
     */
    void deleteBill(int billId);
    
    /**
     * Retrieves a {@code Bill} by its ID.
     * 
     * <p>Business Logic:</p>
     * This method fetches a bill record from the {@code Bills} table using the specified bill ID.
     * It retrieves the details of the bill if it exists, otherwise, it returns {@code null}.
     * 
     * @param billId The ID of the {@code Bill} to retrieve.
     * @return The {@code Bill} object with the specified ID, or {@code null} if not found.
     */
    Bill getBillById(int billId);
    
    /**
     * Retrieves all {@code Bill} objects from the database.
     * 
     * <p>Business Logic:</p>
     * This method returns a list of all bill records from the {@code Bills} table. It provides
     * a complete overview of all bills currently stored in the database.
     * 
     * @return A list of all {@code Bill} objects.
     */
    List<Bill> getAllBills();
    
    /**
     * Finds {@code Bill} objects by their category.
     * 
     * <p>Business Logic:</p>
     * This method retrieves all bill records from the {@code Bills} table that match the specified category.
     * It allows for filtering bills based on their category attribute.
     * 
     * @param category The category to search for.
     * @return A list of {@code Bill} objects that match the specified category.
     */
    List<Bill> getBillsByCategory(String category);
}
