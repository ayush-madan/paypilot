/**
 * Interface for managing Bill-related operations in the database.
 * Provides methods to retrieve, add, and fetch bills by their ID.
 * This interface is intended to be implemented by classes that interact with 
 * the database layer, ensuring that all bill-related operations are handled 
 * consistently and according to the application's business rules.
 * 
 * <p>Author : Aryman Srivastava</p>
 * <p>Date: 09-08-2024</p>
 *
 */

package com.paypilot.model;

import java.util.List;

public interface BillManagerDAOInterface {

    /**
     * Retrieves a list of all bills from the database.
     * This method is expected to query the database and return a complete list 
     * of all bills, ensuring that the business layer has access to all the 
     * relevant data. The list should be ordered according to the business 
     * requirements, such as by the most recent bill date or by ID.
     * 
     * @return A List of Bill objects representing all bills in the database.
     */
    List<Bill> getAllBills();

    /**
     * Adds a new bill to the database.
     * This method takes a Bill object that encapsulates all the necessary 
     * details of a bill (such as the amount, due date, etc.) and inserts it 
     * into the database. The implementation should handle any required 
     * validation, such as checking for duplicate entries or ensuring that 
     * mandatory fields are populated, before the bill is persisted.
     * 
     * @param bill The Bill object containing the details to be added to the database.
     */
    void addBill(Bill bill);

    /**
     * Retrieves a specific bill from the database by its ID.
     * This method should be used to fetch a single bill based on its unique 
     * identifier. The implementation must ensure that the ID provided is valid 
     * and that the corresponding bill is returned. If no bill matches the given ID, 
     * the method should return null, allowing the business logic to handle this 
     * case appropriately, such as by displaying an error message or logging the incident.
     * 
     * @param id The unique identifier of the bill to retrieve.
     * @return The Bill object corresponding to the provided ID, or null if no bill is found.
     */
    Bill getBillById(int id);
}
