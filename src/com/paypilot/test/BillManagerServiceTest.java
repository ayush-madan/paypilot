/*
 * This class contains unit tests for the BillManagerService class, ensure that the BillManagerService behaves
 * as expected by verifying the correctness of its methods using predefined data.
 * 
 * Author: Ayush
 * Date: 09-08-2024

 */
package com.paypilot.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import com.paypilot.model.Bill;
import com.paypilot.repo.BillManagerRepo;
import com.paypilot.service.BillManagerService;
import com.paypilot.service.BillService;

public class BillManagerServiceTest {

	private BillManagerService billManagerService;

    @Before
    public void setUp() {
        billManagerService = new BillManagerService();

        // Initialize with some demo bills
        billManagerService.addNewBillService("Electricity Bill", "Utilities", new Date(2024-1900, 7, 15), 100.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0);
        billManagerService.addNewBillService("Internet Bill", "Internet Charges", new Date(2024-1900, 7, 10), 60.00, "Monthly", null, "", true, "Paid", 0);
        billManagerService.addNewBillService("Rent", "House Rent", new Date(2024-1900, 6, 30), 1200.00, "Monthly", null, "Rent for June", false, "Overdue", 5);
        billManagerService.addNewBillService("Groceries", "Groceries", new Date(2024-1900, 7, 5), 200.00, "Weekly", null, "Weekly groceries", false, "Paid", 0);
    }
    

	@Test
	public void testGetBillsOverviewService() {
		// Testing using the first entry as per setUp() method
		String category = "Utilities";
		Date fromDate = new Date(2024-1900, 7, 14);
		Date toDate = new Date(2024-1900, 7, 16);
		String status = "Upcoming";
		
		List<Bill> billList =  billManagerService.getBillsOverviewService(category, fromDate, toDate, status);
		
		// Assert that function call does not return a null value, if the entry exists
		assertNotNull("Returned null when requesting overview of an existing bill", billList);

		
		// Check if all Bill objects in the list have Payment Status as "Upcoming" and category as "Utilities"
		for (Bill bill : billList) {
            assertEquals("Bill status is not 'Upcoming'", "Upcoming", bill.getPaymentStatus());
            assertEquals("Bill category is not 'Utilities'", "Utilities", bill.getBillCategory());
        }		
	}
	

	@Test
	public void testAddNewBillService() {
		int dbSize = billManagerService.getAllBillsService().size();
		
		// Adding new bill
		billManagerService.addNewBillService("Water", "Utilities", new Date(), 45.0, "Monthly", null, "N/A", true, "Upcoming", 0);
		
		//Check if the size of the database increased by one
		List<Bill> allBills = billManagerService.getAllBillsService();
		int newDbSize = allBills.size();
		assertEquals("Failed to add a new bill", dbSize + 1, newDbSize);
		
		//Check if the newly added bill has the correct billName
		Bill newBill = allBills.get(newDbSize - 1);
		assertEquals("Failed to add a new bill", "Water", newBill.getBillName());
	}
	

	@Test
	public void testGetOverdueBillsService() {
		// Testing using the third entry as per setUp() method
		String category = "House Rent";
		String name = "Rent";
		Date dateFrom = new Date(2024-1900, 6, 29);
		Date dateTo = new Date(2024-1900, 7, 1);
		
		List<Bill> billList =  billManagerService.getOverdueBillsService( category, name, dateTo, dateFrom);
		
		// Assert that function call does not return a null value, if the entry exists
		assertNotNull("Returned null for an existing overdue bill", billList);
		
		// Check if all Bill objects in the list have overdueDays > 0
		for (Bill bill : billList) {
			assertTrue("Not all bills have overdueDays > 0", bill.getOverdueDays() > 0);
		}		
	}
	
	
	@Test
	public void testGetUpcomingBillsService() {
		// Testing using the first entry as per setUp() method
		String category = "Utilities";
		String name = "Electricity Bill";
		Date dateFrom = new Date(2024-1900, 7, 14);
		Date dateTo = new Date(2024-1900, 7, 16);
		
		List<Bill> billList =  billManagerService.getUpcomingBillsService(category, name, dateTo, dateFrom);
		
		// Assert that function call does not return a null value, if the entry exists
		assertNotNull("Returned null when requesting overview of an existing bill", billList);

		
		// Check if all Bill objects in the list have Payment Status as "Upcoming"
		for (Bill bill : billList) {
            assertEquals("Bill status is not 'Upcoming'", "Upcoming", bill.getPaymentStatus());
        }
	}
	
	
	@Test
	public void testSnoozeBillService() {
		// Testing using the second entry as per setUp() method
		int id = 2;
		Date snoozeDate = new Date(2024-1900, 7, 12);
		billManagerService.snoozeBillService(snoozeDate, id);
		
		Bill snoozedBill = null;
		
		List<Bill> allBills = billManagerService.getAllBillsService();
		for( Bill bill : allBills) {
			if (bill.getBillId() == id) {
				snoozedBill = bill;
				break;
			}
		}		
		Date snoozedBillDueDate = snoozedBill.getDueDate();
		assertEquals("Bill due date did not change", snoozeDate, snoozedBillDueDate);
	}
	
	
	@Test
	public void testMarkBillAsPaidService() {
		// Testing using the first entry as per setUp() method
		int id = 1;
		billManagerService.markBillAsPaidService(id);
		
		Bill modBill = null;
		
		List<Bill> allBills = billManagerService.getAllBillsService();
		for( Bill bill : allBills) {
			if (bill.getBillId() == id) {
				modBill = bill;
				break;
			}
		}
		
		String modBillPaymentStatus = modBill.getPaymentStatus();
		assertEquals("Bill not marked as paid", "Paid", modBillPaymentStatus);
	}
	

}
