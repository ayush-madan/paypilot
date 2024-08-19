/*
 *  The BillServiceTest class contains unit tests for the BillService class, 
 *  which provides functionalities for managing bills.
 *  
 *  Author: Dimple
 *  Date: 09-08-2024
 */

package com.paypilot.test;

import com.paypilot.model.Bill;
import com.paypilot.repo.BillRepository;
import com.paypilot.service.BillService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

/**
 * Unit test class for BillService.
 * This class tests the functionalities provided by the BillService class, 
 * including adding, updating, deleting, and retrieving bills.
 */
public class BillServiceTest {

    private BillService billService;
    private BillRepository billRepository;

    /**
     * Sets up the test environment before each test.
     * Initializes the BillRepository and BillService instances.
     */
    @Before
    public void setUp() {
        // Initialize the repository and service before each test
        billRepository = new BillRepository();
        billService = new BillService(billRepository);
    }

    /**
     * Tests the addition of a bill using the addBillService method.
     * Verifies that the bill is successfully added and can be retrieved.
     * 
     * <p>Bill attributes:</p>
     * <ul>
     *   <li>billId: 5</li>
     *   <li>billName: "Water"</li>
     *   <li>category: "Utilities"</li>
     *   <li>dueDate: new Date() (current date)</li>
     *   <li>amount: 45.0</li>
     *   <li>frequency: "Monthly"</li>
     *   <li>reminder: null</li>
     *   <li>description: "N/A"</li>
     *   <li>paid: true</li>
     *   <li>status: "Upcoming"</li>
     *   <li>priority: 0</li>
     *   <li>reminderSettings: null</li>
     * </ul>
     */
    @Test
    public void testAddBill() {
        // Create a sample Bill with specific attributes
        Bill bill = new Bill(5, "Water", "Utilities", new Date(), 45.0, "Monthly", null, "N/A", true, "Upcoming", 0, null);
        
        // Add the bill using the service
        billService.addBillService(bill);

        // Retrieve the bill to verify it was added
        Bill retrievedBill = billService.getBillByIdService(5);
        assertNotNull("Bill should be added successfully", retrievedBill);
        assertEquals("Bill ID does not match", 5, retrievedBill.getBillId());
    }

    /**
     * Tests the updating of a bill using the updateBillService method.
     * Verifies that the bill is successfully updated and the new values are correct.
     * 
     * <p>Original Bill attributes:</p>
     * <ul>
     *   <li>billId: 1</li>
     *   <li>billName: "Electricity Bill"</li>
     *   <li>category: "Utilities"</li>
     *   <li>dueDate: new Date(2024 - 1900, 7, 15) (specific date)</li>
     *   <li>amount: 100.50</li>
     *   <li>frequency: "Monthly"</li>
     *   <li>reminder: null</li>
     *   <li>description: "Pay before due date"</li>
     *   <li>paid: false</li>
     *   <li>status: "Upcoming"</li>
     *   <li>priority: 0</li>
     *   <li>reminderSettings: null</li>
     * </ul>
     * 
     * <p>Updated Bill attributes:</p>
     * <ul>
     *   <li>amount: 100.50</li>
     * </ul>
     */
    @Test
    public void testUpdateBill() {
        // Create and add a sample Bill with specific attributes
        Bill bill = new Bill(1, "Electricity Bill", "Utilities", new Date(2024 - 1900, 7, 15), 100.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0, null);
        billService.addBillService(bill); // Ensure the bill is added before updating
        
        // Update the bill with new values
        bill.setAmount(100.50); // Update amount (for demonstration; typically you would use a different value)
        billService.updateBillService(bill);

        // Retrieve and verify the updated bill
        Bill updatedBill = billService.getBillByIdService(1);
        assertNotNull("Bill should be updated successfully", updatedBill);
        assertEquals("Amount did not match", 100.50, updatedBill.getAmount(), 0.01); // Tolerance for floating-point comparison
    }

    /**
     * Tests the deletion of a bill using the deleteBillService method.
     * Verifies that the bill is successfully deleted and cannot be retrieved.
     */
    @Test
    public void testDeleteBill() {
        // Delete a bill using its ID
        billService.deleteBillService(2);

        // Verify the bill was deleted
        Bill deletedBill = billService.getBillByIdService(2);
        assertNull("Bill should be deleted", deletedBill);
    }

    /**
     * Tests the retrieval of all bills using the getAllBillsService method.
     * Verifies that the size of the bill list increases by one after adding a new bill.
     * 
     * <p>New Bill attributes:</p>
     * <ul>
     *   <li>billId: 6</li>
     *   <li>billName: "Phone"</li>
     *   <li>category: "Utilities"</li>
     *   <li>dueDate: new Date() (current date)</li>
     *   <li>amount: 75.0</li>
     *   <li>frequency: "Monthly"</li>
     *   <li>reminder: null</li>
     *   <li>description: "N/A"</li>
     *   <li>paid: false</li>
     *   <li>status: "Upcoming"</li>
     *   <li>priority: 0</li>
     *   <li>reminderSettings: null</li>
     * </ul>
     */
    @Test
    public void testGetAllBills() {
        // Get the initial count of bills
        int initialSize = billService.getAllBillsService().size();
        
        // Add a new bill
        Bill newBill = new Bill(6, "Phone", "Utilities", new Date(), 75.0, "Monthly", null, "N/A", false, "Upcoming", 0, null);
        billService.addBillService(newBill);
        
        // Get the new count of bills and verify the size has increased by one
        int newSize = billService.getAllBillsService().size();
        assertEquals("Size of bill list should increase by one", initialSize + 1, newSize);
    }

    /**
     * Tests the retrieval of a bill by ID using the getBillByIdService method.
     * Verifies that the bill is found and the bill name matches the expected value.
     */
    @Test
    public void testGetBillById() {
        // Retrieve a bill by ID
        Bill bill = billService.getBillByIdService(3);
        
        // Verify the bill was found and check the bill name
        assertNotNull("Bill should be found by ID", bill);
        assertEquals("Bill name does not match", "Rent", bill.getBillName());
    }
}
