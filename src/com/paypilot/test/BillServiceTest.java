/*
 *  The BillServiceTest class contains unit tests for the BillService class, 
 *  which provides functionalities for managing bills.
 *  
 *  Author: Dimple, Ayush Madan
 *  Date: 20-08-2024
 */

package com.paypilot.test;

import com.paypilot.model.Bill;
import com.paypilot.repo.BillDAO;
import com.paypilot.repo.BillRepository;
import com.paypilot.service.BillService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

/**
 * Unit test class for BillService.
 * This class tests the functionalities provided by the BillService class, 
 * including adding, updating, deleting, and retrieving bills.
 */
public class BillServiceTest {

    private BillService billService;
    private BillRepository billRepository;
    private BillDAO billDAO;

    /**
     * Prepares the test environment for validating bill-related operations.
     * 
     * <p>This method initializes the {@code BillDAO}, {@code BillRepository}, and {@code BillService}
     * instances to ensure that all components are properly set up before each test execution.</p>
     * 
     * <p>The method is annotated with {@code @Before}, indicating that it is executed prior to each test case.</p>
     * 
     * <p><b>External support modules:</b></p>
     * <ul>
     *   <li>JUnit 4.x.x: Provides annotations like {@code @Before} and {@code @Test} for setting up and running unit tests.</li>
     *   <li>Java Collections Framework: Required for managing in-memory collections during testing.</li>
     * </ul>
     */
    @Before
    public void setUp() {
        // Initialize the repository and service before each test
    	billDAO = new BillDAO();
        billRepository = new BillRepository(billDAO);
        billService = new BillService(billRepository);
    }

    
    /**
     * Validates the addition of a new bill to the system using the {@code addBillService} method.
     * 
     * <p>This test ensures that a bill with specific attributes is successfully added and can be
     * retrieved from the system. The bill is then deleted to maintain test data integrity.</p>
     * 
     * <p><b>Tested Bill Attributes:</b></p>
     * <ul>
     *   <li><b>ID:</b> 5</li>
     *   <li><b>Name:</b> "Water"</li>
     *   <li><b>Category:</b> "Utilities"</li>
     *   <li><b>Due Date:</b> Current date ({@code new Date()})</li>
     *   <li><b>Amount:</b> 45.0</li>
     *   <li><b>Reminder Frequency:</b> "Monthly"</li>
     *   <li><b>Attachment:</b> Not set (null)</li>
     *   <li><b>Notes:</b> "N/A"</li>
     *   <li><b>Is Recurring:</b> true</li>
     *   <li><b>Payment Status:</b> "Upcoming"</li>
     *   <li><b>Overdue Days:</b> 0</li>
     *   <li><b>Reminder Settings:</b> Not set (null)</li>
     * </ul>
     * 
     * <p>After verifying the addition, the bill is removed to ensure the test environment remains clean.</p>
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
        assertEquals("Bill ID does not match", bill.getBillId(), retrievedBill.getBillId());
        
        // Delete the added bill using its ID
        billService.deleteBillService(bill.getBillId());
    }

    
    /**
     * Validates the updating of an existing bill using the {@code updateBillService} method.
     * 
     * <p>This test ensures that a bill's attributes can be successfully updated and that the updated values
     * are correctly reflected in the system. The bill is then deleted to maintain test data integrity.</p>
     * 
     * <p><b>Original Bill Attributes:</b></p>
     * <ul>
     *   <li><b>ID:</b> 10</li>
     *   <li><b>Name:</b> "Electricity Bill"</li>
     *   <li><b>Category:</b> "Utilities"</li>
     *   <li><b>Due Date:</b> August 15, 2024 ({@code new Date(2024 - 1900, 7, 15)})</li>
     *   <li><b>Amount:</b> 140.50</li>
     *   <li><b>Reminder Frequency:</b> "Monthly"</li>
     *   <li><b>Attachment:</b> Not set (null)</li>
     *   <li><b>Notes:</b> "Pay before due date"</li>
     *   <li><b>Is Recurring:</b> false</li>
     *   <li><b>Payment Status:</b> "Upcoming"</li>
     *   <li><b>Overdue Days:</b> 0</li>
     *   <li><b>Reminder Settings:</b> Not set (null)</li>
     * </ul>
     * 
     * <p><b>Updated Bill Attribute:</b></p>
     * <ul>
     *   <li><b>Amount:</b> 100.50</li>
     * </ul>
     * 
     * <p>After verifying the update, the bill is removed to ensure the test environment remains clean.</p>
     */
    @Test
    public void testUpdateBill() {
        // Create and add a sample Bill with specific attributes
        Bill bill = new Bill(10, "Electricity Bill", "Utilities", new Date(2024 - 1900, 7, 15), 140.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0, null);
        billService.addBillService(bill); // Ensure the bill is added before updating
        
        // Update the bill with new values
        bill.setAmount(100.50); // Update amount (for demonstration; typically you would use a different value)
        billService.updateBillService(bill);

        // Retrieve and verify the updated bill
        Bill updatedBill = billService.getBillByIdService(bill.getBillId());
        assertNotNull("Bill should be updated successfully", updatedBill);
        assertEquals("Amount did not match", 100.50, updatedBill.getAmount(), 0.01); // Tolerance for floating-point comparison
        
        // Delete the added bill using its ID
        billService.deleteBillService(bill.getBillId());
    }

    
    /**
     * Validates the deletion of a bill using the {@code deleteBillService} method.
     * 
     * <p>This test ensures that a bill is successfully added, deleted, and confirms that the deleted bill
     * cannot be retrieved afterward.</p>
     * 
     * <p><b>Bill Attributes for Addition and Deletion:</b></p>
     * <ul>
     *   <li><b>ID:</b> 5</li>
     *   <li><b>Name:</b> "Water"</li>
     *   <li><b>Category:</b> "Utilities"</li>
     *   <li><b>Due Date:</b> Current date ({@code new Date()})</li>
     *   <li><b>Amount:</b> 45.0</li>
     *   <li><b>Reminder Frequency:</b> "Monthly"</li>
     *   <li><b>Attachment:</b> Not set (null)</li>
     *   <li><b>Notes:</b> "N/A"</li>
     *   <li><b>Is Recurring:</b> true</li>
     *   <li><b>Payment Status:</b> "Upcoming"</li>
     *   <li><b>Overdue Days:</b> 0</li>
     *   <li><b>Reminder Settings:</b> Not set (null)</li>
     * </ul>
     * 
     * <p>After the deletion, an attempt to retrieve the bill should return {@code null}.</p>
     */
    @Test
    public void testDeleteBill() {
    	// Create a sample Bill with specific attributes
        Bill bill = new Bill(5, "Water", "Utilities", new Date(), 45.0, "Monthly", null, "N/A", true, "Upcoming", 0, null);
        
        // Add the bill using the service
        billService.addBillService(bill);
        
        // Delete a bill using its ID
        billService.deleteBillService(bill.getBillId());

        // Verify the bill was deleted
        Bill deletedBill = billService.getBillByIdService(2);
        assertNull("Bill should be deleted", deletedBill);
    }

    
    /**
     * Validates the retrieval of all bills using the {@code getAllBillsService} method.
     * 
     * <p>This test ensures that the total count of bills increases by one after adding a new bill to the system,
     * and confirms that the new bill can be retrieved along with the existing ones.</p>
     * 
     * <p><b>New Bill Attributes:</b></p>
     * <ul>
     *   <li><b>ID:</b> 6</li>
     *   <li><b>Name:</b> "Phone"</li>
     *   <li><b>Category:</b> "Utilities"</li>
     *   <li><b>Due Date:</b> Current date ({@code new Date()})</li>
     *   <li><b>Amount:</b> 75.0</li>
     *   <li><b>Reminder Frequency:</b> "Monthly"</li>
     *   <li><b>Attachment:</b> Not set (null)</li>
     *   <li><b>Notes:</b> "N/A"</li>
     *   <li><b>Is Recurring:</b> false</li>
     *   <li><b>Payment Status:</b> "Upcoming"</li>
     *   <li><b>Overdue Days:</b> 0</li>
     *   <li><b>Reminder Settings:</b> Not set (null)</li>
     * </ul>
     * 
     * <p>After adding the new bill, the test verifies that the size of the bill list has increased by one.</p>
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
        
        // Delete the added bill using its ID
        billService.deleteBillService(newBill.getBillId());
    }

    
    /**
     * Validates the retrieval of a bill by its ID using the {@code getBillByIdService} method.
     * 
     * <p>This test ensures that a bill can be accurately retrieved by its ID and verifies that the 
     * retrieved bill's name matches the expected value.</p>
     * 
     * <p>The test checks that the bill is found and confirms that the bill's name matches "Internet Bill".</p>
     */
    @Test
    public void testGetBillById() {
        // Retrieve a bill by ID
        Bill bill = billService.getBillByIdService(3);
        
        // Verify the bill was found and check the bill name
        assertNotNull("Bill should be found by ID", bill);
        assertEquals("Bill name does not match", "Internet Bill", bill.getBillName());
    }
}
