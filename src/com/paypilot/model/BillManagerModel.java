/*
 * BillManagerModel manages a list of bills and interacts with the data 
 * access layer to perform operations such as retrieving, updating, and 
 * fetching bills by their ID. It serves as the model in the MVC architecture, 
 * encapsulating the data and business logic related to bills.
 * 
 * Author: Aryman Srivastava
 * <p>Date: 09-08-2024</p>
 */

package com.paypilot.model;

import java.util.List;

import com.paypilot.repo.BillManagerDAO;

public class BillManagerModel {

    // Data Access Object for performing bill-related database operations
    private BillManagerDAOInterface dao = new BillManagerDAO();

    /**
     * Retrieves the current list of all bills from the database.
     * This method leverages the DAO layer to fetch the complete list of 
     * bills stored in the database, ensuring that the business logic has 
     * access to up-to-date data. It provides a direct link between the 
     * application's model and the persistence layer.
     * 
     * @return A List of all Bill objects currently stored in the database.
     */
    public List<Bill> getAllBills() {
        return dao.getAllBills();
    }

    /**
     * Adds a new bill to the database through the DAO.
     * This method accepts a Bill object and delegates the task of 
     * adding this bill to the database to the DAO layer. It ensures 
     * that the new bill is correctly persisted, allowing the application 
     * to maintain an accurate and up-to-date list of all bills.
     * 
     * @param bill The Bill object to be added to the database.
     */
    public void setBill(Bill b) {
        dao.addBill(b);
    }

    /**
     * Retrieves a specific bill by its ID from the database.
     * This method interacts with the DAO layer to fetch a single bill 
     * based on its unique identifier. It is crucial for scenarios where 
     * the application needs to display or process details of a particular bill.
     * 
     * @param id The unique identifier of the bill to retrieve.
     * @return The Bill object corresponding to the provided ID, or null if not found.
     */
    public Bill getBillbyId(int id) {
        return dao.getBillById(id);
    }
}
