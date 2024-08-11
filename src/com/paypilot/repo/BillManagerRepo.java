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
	
	// Method to get an overview of bills
	BillManagerModel b = new BillManagerModel();
	
	public Bill createNewBill(int billId, String name, String Category, Date dueDate, double amount, String reminderFrequency, File attachement, String note,boolean isRecurring, String paymentStatus, int overDueDays, ReminderSettings rs) {
		return new Bill(billId, name, Category, dueDate, amount, reminderFrequency, attachement, note, isRecurring, paymentStatus, overDueDays, rs);
	}
	
	
	// Retrieves a list of bills that match the specified category, date range, and status
    public List<Bill> getBillsOverview(String category, Date fromDate, Date toDate, String status) {
        // Implementation goes here
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

    // Method to add a new bill
    public void addNewBill(Bill bill) {
        // Implementation goes here
    	List<Bill> allBills = b.getAllBills();
    	allBills.add(bill);
    	b.setAllBills(allBills);
    }

    // Method to get overdue bills
    public List<Bill> getOverdueBills() {
        // Implementation goes here
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

    // Method to get upcoming bills
    public List<Bill> getUpcomingBills() {
        // Implementation goes here
    	List<Bill> upcomingBills = new ArrayList<>();
    	List<Bill> allBills = b.getAllBills();
    	for(Bill bills: allBills) {
    		if(bills.getPaymentStatus().equalsIgnoreCase("upcoming")) {
    			upcomingBills.add(bills);
    		}
    	}
        return upcomingBills;
    }

    // Snoozes a bill by updating its due date to a new date
    public void snoozeBill(Bill bill, Date snoozeDate) {
        // Implementation goes here
    	List<Bill> allBills = b.getAllBills();
    	for(Bill bills: allBills) {
    		if(bills.getBillId() == bill.getBillId()) {
    			bills.setDueDate(snoozeDate);
    			break;
    		}
    	}
    	b.setAllBills(allBills);
    }

    // Marks a bill as paid by updating its payment status
    public void markBillAsPaid(Bill bill) {
        // Implementation goes here
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
