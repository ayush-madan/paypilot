/**
 * Test class for BillManagerService.
 * <p>
 * This class contains unit tests for the BillManagerService class, which manages
 * various operations related to bills, including adding, retrieving, snoozing, 
 * and marking bills as paid. Each test ensures that the methods in BillManagerService
 * perform as expected under different scenarios.
 * </p>
 * 
 * Author: Esha Thakur, Ayush Madan
 * Date: 20-08-2024
 */

package com.paypilot.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.paypilot.model.Bill;
import com.paypilot.repo.BillManagerRepo;
import com.paypilot.service.BillManagerService;

public class BillManagerServiceTest {

    private BillManagerService billManagerService;
    private BillManagerRepo billManagerRepo;

    /**
     * Sets up the test environment before each test case.
     * <p>
     * This method initializes a mock implementation of BillManagerRepo that 
     * stores bills in-memory. It also initializes the BillManagerService with
     * this mock repository. This setup allows us to test the service methods
     * without relying on a real database.
     * </p>
     * <p>
     * External support modules:
     * <ul>
     *   <li><b>JUnit 5:</b> Provides annotations like @BeforeEach and @Test for setting up and running unit tests.</li>
     *   <li><b>Java Collections Framework:</b> Used to manage the list of bills in-memory.</li>
     * </ul>
     * </p>
     */
    @BeforeEach
    public void setUp() {
        // Initialize BillManagerRepo with an in-memory implementation for testing
        billManagerRepo = new BillManagerRepo() {
            private final List<Bill> bills = new ArrayList<>();

            @Override
            public List<Bill> getAllBills() {
                // Returns a copy of the current list of bills
                return new ArrayList<>(bills);
            }

            @Override
            public void addNewBill(Bill bill) {
                // Adds a new bill to the in-memory list
                bills.add(bill);
            }

            @Override
            public List<Bill> getBillsOverview(String category, Date fromDate, Date toDate, String status) {
                // Filters bills based on category and status for testing
                List<Bill> result = new ArrayList<>();
                for (Bill b : bills) {
                    if ((category == null || b.getBillCategory().equalsIgnoreCase(category)) &&
                        (status == null || b.getPaymentStatus().equalsIgnoreCase(status))) {
                        result.add(b);
                    }
                }
                return result;
            }

            @Override
            public List<Bill> getOverdueBills() {
                // Returns all bills as a placeholder for actual overdue bill logic
                return new ArrayList<>(bills);
            }

            @Override
            public List<Bill> getUpcomingBills() {
                // Returns all bills as a placeholder for actual upcoming bill logic
                return new ArrayList<>(bills);
            }

            @Override
            public void snoozeBill(Bill bill, Date snoozeDate) {
                // Updates the bill's due date to the snooze date
                bill.setDueDate(snoozeDate);
            }

            @Override
            public void markBillAsPaid(Bill bill) {
                // Sets the bill's payment status to "Paid"
                bill.setPaymentStatus("Paid");
            }

            @Override
            public Bill createNewBill(int billId, String name, String category, Date dueDate, double amount, String reminderFrequency, File attachment, String note, boolean isRecurring, String paymentStatus, int overDueDays, ReminderSettings rs) {
                // Creates a new Bill object with the provided parameters
                return new Bill(billId, name, category, dueDate, amount, reminderFrequency, attachment, note, isRecurring, paymentStatus, overDueDays, rs);
            }
        };

        // Initialize BillManagerService with the mock repository
        billManagerService = new BillManagerService();
        billManagerService.br = billManagerRepo; // Inject the mock repo into the service
    }

    /**
     * Tests the addNewBillService method of BillManagerService.
     * <p>
     * This test case verifies that a new bill can be added to the service. It checks
     * whether the bill is correctly added to the in-memory repository by validating
     * the bill's attributes after addition.
     * </p>
     */
    @Test
    public void testAddNewBillService() {
        // Arrange: Prepare test data
        String name = "Test Bill";
        String category = "Utilities";
        Date dueDate = new Date();
        double amount = 100.50;
        String reminderFrequency = "Monthly";
        File attachment = new File("path/to/attachment");
        String note = "This is a test bill.";
        boolean isRecurring = true;
        String paymentStatus = "Pending";
        int overDueDays = 5;
        
        // Act: Call the service method to add a new bill
        billManagerService.addNewBillService(name, category, dueDate, amount, reminderFrequency, attachment, note, isRecurring, paymentStatus, overDueDays);
        
        // Assert: Verify that the bill has been added correctly
        List<Bill> bills = billManagerRepo.getAllBills();
        assertEquals(1, bills.size(), "The bill should be added to the repository.");
        Bill bill = bills.get(0);
        assertEquals(name, bill.getBillName(), "The bill name should match.");
        assertEquals(category, bill.getBillCategory(), "The bill category should match.");
        assertEquals(amount, bill.getAmount(), "The bill amount should match.");
    }

    /**
     * Tests the getBillsOverviewService method of BillManagerService.
     * <p>
     * This test case checks whether bills are retrieved correctly based on the provided
     * category and status. It validates that the service correctly filters the bills
     * from the repository based on these parameters.
     * </p>
     */
    @Test
    public void testGetBillsOverviewService() {
        // Arrange: Add a test bill to the repository
        Date now = new Date();
        Bill bill = new Bill(1, "Test Bill", "Utilities", now, 100.50, "Monthly", null, "Test note", false, "Pending", 0, null);
        billManagerRepo.addNewBill(bill);
        
        // Act: Call the service method to get bills overview
        List<Bill> result = billManagerService.getBillsOverviewService("Utilities", null, null, "Pending");
        
        // Assert: Verify that the correct bill is retrieved
        assertNotNull(result, "The result should not be null.");
        assertEquals(1, result.size(), "There should be one bill in the result.");
        assertEquals("Test Bill", result.get(0).getBillName(), "The bill name should match.");
    }

    /**
     * Tests the getOverdueBillsService method of BillManagerService.
     * <p>
     * This test case verifies that overdue bills are correctly retrieved. It ensures
     * that bills marked as overdue are included in the result, based on the implementation
     * of the getOverdueBills method in the mock repository.
     * </p>
     */
    @Test
    public void testGetOverdueBillsService() {
        // Arrange: Add an overdue bill to the repository
        Date now = new Date();
        Bill bill = new Bill(1, "Overdue Bill", "Utilities", now, 100.50, "Monthly", null, "Overdue", false, "Pending", 1, null);
        billManagerRepo.addNewBill(bill);
        
        // Act: Call the service method to get overdue bills
        List<Bill> result = billManagerService.getOverdueBillsService(null, null, now, now);
        
        // Assert: Verify that the overdue bill is included in the result
        assertNotNull(result, "The result should not be null.");
        assertEquals(1, result.size(), "There should be one overdue bill in the result.");
        assertEquals("Overdue Bill", result.get(0).getBillName(), "The bill name should match.");
    }

    /**
     * Tests the getUpcomingBillsService method of BillManagerService.
     * <p>
     * This test case ensures that upcoming bills are correctly retrieved from the repository.
     * It verifies that bills which are not overdue are included in the result based on
     * the implementation of the getUpcomingBills method in the mock repository.
     * </p>
     */
    @Test
    public void testGetUpcomingBillsService() {
        // Arrange: Add an upcoming bill to the repository
        Date now = new Date();
        Bill bill = new Bill(1, "Upcoming Bill", "Utilities", now, 100.50, "Monthly", null, "Upcoming", false, "Pending", 0, null);
        billManagerRepo.addNewBill(bill);
        
        // Act: Call the service method to get upcoming bills
        List<Bill> result = billManagerService.getUpcomingBillsService(null, null, now, now);
        
        // Assert: Verify that the upcoming bill is included in the result
        assertNotNull(result, "The result should not be null.");
        assertEquals(1, result.size(), "There should be one upcoming bill in the result.");
        assertEquals("Upcoming Bill", result.get(0).getBillName(), "The bill name should match.");
    }

    /**
     * Tests the snoozeBillService method of BillManagerService.
     * <p>
     * This test case checks that the due date of a bill can be successfully updated
     * to a new snooze date. It verifies that the service correctly updates the bill's
     * due date when the snooze operation is performed.
     * </p>
     */
    @Test
    public void testSnoozeBillService() {
        // Arrange: Add a bill to the repository and set a snooze date
        Date now = new Date();
        Date snoozeDate = new Date(now.getTime() + 1000000000L); // Some future date
        Bill bill = new Bill(1, "Bill to Snooze", "Utilities", now, 100.50, "Monthly", null, "Snooze Test", false, "Pending", 0, null);
        billManagerRepo.addNewBill(bill);
        
        // Act: Call the service method to snooze the bill
        billManagerService.snoozeBillService(snoozeDate, 1);
        
        // Assert: Verify that the bill's due date has been updated
        Bill updatedBill = billManagerRepo.getAllBills().get(0);
        assertEquals(snoozeDate, updatedBill.getDueDate(), "The due date should be updated to the snooze date.");
    }

    /**
     * Tests the markBillAsPaidService method of BillManagerService.
     * <p>
     * This test case ensures that a bill's payment status is correctly updated to "Paid".
     * It verifies that the service method correctly modifies the payment status of the
     * specified bill.
     * </p>
     */
    @Test
    public void testMarkBillAsPaidService() {
        // Arrange: Add a bill to the repository with a "Pending" status
        Date now = new Date();
        Bill bill = new Bill(1, "Bill to Mark Paid", "Utilities", now, 100.50, "Monthly", null, "Paid Test", false, "Pending", 0, null);
        billManagerRepo.addNewBill(bill);
        
        // Act: Call the service method to mark the bill as paid
        billManagerService.markBillAsPaidService(1);
        
        // Assert: Verify that the bill's payment status has been updated to "Paid"
        Bill updatedBill = billManagerRepo.getAllBills().get(0);
        assertEquals("Paid", updatedBill.getPaymentStatus(), "The payment status should be updated to 'Paid'.");
    }

    /**
     * Tests the getAllBillsService method of BillManagerService.
     * <p>
     * This test case verifies that all bills are correctly retrieved from the repository.
     * It checks that the service returns all bills currently stored in the repository.
     * </p>
     */
    @Test
    public void testGetAllBillsService() {
        // Arrange: Add a test bill to the repository
        Bill bill = new Bill(1, "Test Bill", "Utilities", new Date(), 100.50, "Monthly", null, "Test note", false, "Pending", 0, null);
        billManagerRepo.addNewBill(bill);
        
        // Act: Call the service method to retrieve all bills
        List<Bill> result = billManagerService.getAllBillsService();
        
        // Assert: Verify that the bill is included in the result
        assertNotNull(result, "The result should not be null.");
        assertEquals(1, result.size(), "There should be one bill in the result.");
        assertEquals("Test Bill", result.get(0).getBillName(), "The bill name should match.");
    }
}