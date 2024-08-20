package com.paypilot.model;

import java.util.List;

/**
 * Interface for managing Bill-related operations in the database.
 * Provides methods to retrieve, add, and fetch bills by their ID.
 * Author : Aryman Srivastava
 *  <p>Date: 09-08-2024</p>
 *
 */
public interface BillManagerDAOInterface {

    /**
     * Retrieves a list of all bills from the database.
     * 
     * @return A List of Bill objects representing all bills in the database.
     */
    List<Bill> getAllBills();

    /**
     * Adds a new bill to the database.
     * 
     * @param bill The Bill object containing the details to be added to the database.
     */
    void addBill(Bill bill);

    /**
     * Retrieves a specific bill from the database by its ID.
     * 
     * @param id The unique identifier of the bill to retrieve.
     * @return The Bill object corresponding to the provided ID, or null if no bill is found.
     */
    Bill getBillById(int id);
}
