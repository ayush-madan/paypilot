/*
 * The BillManagerRepo class is responsible for managing the storage, retrieval, and updating of Bill objects.
 * It provides various methods to create new bills, get an overview of bills, and manage the state of bills (e.g., marking them as paid, snoozing them, etc.).
 * This class interacts with the BillManagerModel to handle the underlying data structure that stores all bills.
 * 
 * Author: Aryman Srivastava
 * Date: 09-08-2024
 */

package com.paypilot.repo;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.paypilot.model.*;

public class BillManagerRepo {
	
	// Instance of BillManagerModel to interact with the underlying data structure
	BillManagerModel b = new BillManagerModel();

	 /**
     * Creates a new Bill object with the specified details.
     * 
     * @param billId Unique identifier for the bill
     * @param name Name of the bill
     * @param Category Category of the bill (e.g., Utilities, Rent, etc.)
     * @param dueDate Due date of the bill
     * @param amount Amount to be paid
     * @param reminderFrequency Frequency of reminders (e.g., Weekly, Monthly)
     * @param attachment Optional file attachment (e.g., invoice)
     * @param note Optional note associated with the bill
     * @param isRecurring Indicates whether the bill is recurring
     * @param paymentStatus Status of the payment (e.g., Pending, Paid)
     * @param overDueDays Number of days overdue, if applicable
     * @param rs Reminder settings associated with the bill
     * @return The newly created Bill object
     */
	public Bill createNewBill(int billId, String name, String Category, Date dueDate, double amount, String reminderFrequency, File attachement, String note,boolean isRecurring, String paymentStatus, int overDueDays, ReminderSettings rs) {
		return new Bill(billId, name, Category, dueDate, amount, reminderFrequency, attachement, note, isRecurring, paymentStatus, overDueDays, rs);
	}
	
	
	/**
     * Retrieves a list of bills that match the specified category, date range, and status.
     * 
     * @param category The category of the bills to retrieve
     * @param fromDate The start date for filtering bills
     * @param toDate The end date for filtering bills
     * @param status The payment status of the bills to retrieve
     * @return A list of bills matching the specified criteria
     */
    public List<Bill> getBillsOverview(String category, Date fromDate, Date toDate, String status) {
	// Retrieve all bills and filter based on provided criteria
    	List<Bill> allBills = b.getAllBills();
    	List<Bill> wantedBills = new ArrayList<>();
    	for(Bill bills: allBills) {
    		if((bills.getBillCategory().equals(category) || category.equalsIgnoreCase("All")) && 
    		   bills.getDueDate().before(toDate) && bills.getDueDate().after(fromDate)&&
    			bills.getPaymentStatus().equals(status)) {
    			wantedBills.add(bills);
    		}
    	}
       
		return wantedBills;
    }

    /**
     * Adds a new bill to the list of all bills.
     * 
     * @param bill The bill to add
     */
    public void addNewBill(Bill bill) {
	 // Retrieve the current list of all bills, add the new bill, and update the list
    	List<Bill> allBills = b.getAllBills();
    	allBills.add(bill);
    	b.setAllBills(allBills);
    }

      /**
     * Retrieves a list of overdue bills.
     * 
     * @return A list of bills that are overdue
     */
    public List<Bill> getOverdueBills() {
	// Filter bills to find those that are overdue
    	List<Bill> pendingBills = new ArrayList<>();
    	List<Bill> allBills = b.getAllBills();
    	for(Bill bills: allBills) {
    		if(bills.getPaymentStatus().equalsIgnoreCase("pending")||
    				bills.getDueDate().before(new java.util.Date())) {
    			pendingBills.add(bills);
    		}
    	}
        return pendingBills;
    }

    /**
     * Retrieves a list of upcoming bills.
     * 
     * @return A list of bills with a status of 'upcoming'
     */
    public List<Bill> getUpcomingBills() {
	// Filter bills to find those that are marked as upcoming
    	List<Bill> upcomingBills = new ArrayList<>();
    	List<Bill> allBills = b.getAllBills();
    	for(Bill bills: allBills) {
    		if(bills.getPaymentStatus().equalsIgnoreCase("upcoming")) {
    			upcomingBills.add(bills);
    		}
    	}
        return upcomingBills;
    }

    /**
     * Snoozes a bill by updating its due date to a new date.
     * 
     * @param bill The bill to snooze
     * @param snoozeDate The new due date for the bill
     */
    public void snoozeBill(Bill bill, Date snoozeDate) {
	// Update the due date of the specified bill
    	List<Bill> allBills = b.getAllBills();
    	for(Bill bills: allBills) {
    		if(bills.getBillId() == bill.getBillId()) {
    			bills.setDueDate(snoozeDate);
    			break;
    		}
    	}
    	b.setAllBills(allBills);
    }

     /**
     * Marks a bill as paid by updating its payment status and setting the paid date.
     * 
     * @param bill The bill to mark as paid
     */
    public void markBillAsPaid(Bill bill) {
        // Update the payment status of the specified bill to 'Paid' and set the paid date
    	List<Bill> allBills = b.getAllBills();
    	for(Bill bills: allBills) {
    		if(bills.getBillId() == bill.getBillId()) {
    			bills.setPaymentStatus("Paid");
    			bills.setDueDate(null);
    			break;
    		}
    	}
    	b.setAllBills(allBills);
    }
    
    //Retrieves a list of all bills
    public List<Bill> getAllBills(){
    	return b.getAllBills();
    }
}
