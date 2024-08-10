package com.paypilot.billmngr.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.paypilot.billmngr.*;

public class BillManagerRepo {
	
	// Method to get an overview of bills
	BillManagerModel b = new BillManagerModel();
	
	public Bill createNewBill(String name, String Category, Date dueDate, double amount, String reminderFrequency, String attachement, String note,boolean isRecurring, String paymentStatus, int overDueDays) {
		return new Bill(name, Category, dueDate, amount, reminderFrequency, attachement, note, isRecurring, paymentStatus, overDueDays);
	}
	
    public List<Bill> getBillsOverview(String category, Date fromDate, Date toDate, String status) {
        // Implementation goes here
    	List<Bill> allBills = b.getAllBills();
    	List<Bill> wantedBills = new ArrayList<>();
    	for(Bill bills: allBills) {
    		if((bills.getCategory().equals(category) || category.equalsIgnoreCase("All")) && 
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
    				bills.getDueDate().after(new java.util.Date())) {
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
