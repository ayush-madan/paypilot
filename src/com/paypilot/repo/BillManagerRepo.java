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

    // Method to snooze a bill
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

    // Method to mark a bill as paid
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
    
    public List<Bill> getAllBills(){
    	return b.getAllBills();
    }
}
