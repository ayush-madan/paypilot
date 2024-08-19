/*
 * The BillManagerService class provides service methods for managing bills.
 * This class interacts with the BillManagerRepo to perform operations such as 
 * creating, retrieving, updating, and deleting bills. It includes methods for 
 * handling overdue and upcoming bills, as well as marking bills as paid/snoozed.
 * 
 * Author: Aryman Srivastava
 * Date: 09-08-2024
 */

package com.paypilot.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.paypilot.model.Bill;
import com.paypilot.model.ReminderSettings;
import com.paypilot.repo.BillManagerRepo;

public class BillManagerService {
	BillManagerRepo br = new BillManagerRepo();
	
	
	/**
     * Retrieves a list of bills based on the specified category, date range, and status.
     * 
     * @param category The category of the bills to retrieve.
     * @param fromDate The start date of the date range.
     * @param toDate The end date of the date range.
     * @param status The status of the bills to retrieve (e.g., "Upcoming").
     * @return A list of bills that match the specified criteria.
     */
	public List<Bill> getBillsOverviewService(String category, Date fromDate, Date toDate, String status) {
		
//		Type of Bills Debt, Payment, HouseRent, Groceries, InternetCharges, RetirementCharges, CellPhoneCharges
//		List<Bill> allBill = br.getBillsOverview(category, fromDate, toDate, status);
//		int debtBill = 0;
//		int paymentBill = 0;
//		int houseRentBill = 0;
//		int groceriesBill = 0;
//		int internetChargesBill = 0;
//		int retirementChargesBill = 0;
//		int cellPhoneChargesBill = 0;
//		for(Bill b:allBill) {
//			if(b.getBillCategory().equalsIgnoreCase("Debt")) {
//			    debtBill++;
//			} else if(b.getBillCategory().equalsIgnoreCase("Payment")) {
//			    paymentBill++;
//			} else if(b.getBillCategory().equalsIgnoreCase("HouseRent")) {
//			    houseRentBill++;
//			} else if(b.getBillCategory().equalsIgnoreCase("Groceries")) {
//			    groceriesBill++;
//			} else if(b.getBillCategory().equalsIgnoreCase("InternetCharges")) {
//			    internetChargesBill++;
//			} else if(b.getBillCategory().equalsIgnoreCase("RetirementCharges")) {
//			    retirementChargesBill++;
//			} else if(b.getBillCategory().equalsIgnoreCase("CellPhoneCharges")) {
//			    cellPhoneChargesBill++;
//			}
//		}
//		
		
		return br.getBillsOverview(category, fromDate, toDate, status);
    }

     /**
     * Adds a new bill to the bill manager service.
     * 
     * @param name The name of the bill.
     * @param Category The category of the bill.
     * @param dueDate The due date of the bill.
     * @param amount The amount of the bill.
     * @param reminderFrequency The reminder frequency for the bill.
     * @param attachment An optional file attachment associated with the bill.
     * @param note An optional note for the bill.
     * @param isRecurring Indicates if the bill is recurring.
     * @param paymentStatus The payment status of the bill (e.g., "Paid", "Upcoming").
     * @param overDueDays The number of overdue days, if applicable.
     */
    public void addNewBillService(String name, String Category, Date dueDate, double amount, String reminderFrequency, File attachement, String note,boolean isRecurring, String paymentStatus, int overDueDays) {
        List<Bill> allBills = br.getAllBills();
    	int billId = allBills.size()+1;
    	br.addNewBill(br.createNewBill(billId, name, Category, dueDate, amount, 
        			reminderFrequency, attachement, note, isRecurring, 
        			paymentStatus, overDueDays, null));
        }

    /**
     * Retrieves a list of overdue bills based on the specified criteria.
     * 
     * @param Category The category of the bills to retrieve.
     * @param name The name of the bills to retrieve.
     * @param dateTo The end date of the date range.
     * @param dateFrom The start date of the date range.
     * @return A list of overdue bills that match the specified criteria.
     */
    public List<Bill> getOverdueBillsService(String Category, String name, Date dateTo, Date dateFrom) {
    	List<Bill> allBills = br.getOverdueBills();
    	List<Bill> temp1;
    	List<Bill> temp2;
    	List<Bill> temp3;

    	// Filter by Category
    	if (Category != null) {
    		temp1 = new ArrayList<>();
    	    for (Bill b : allBills) {
    	        if (b.getBillCategory().equalsIgnoreCase(Category)) {
    	            temp1.add(b);
    	        }
    	    }
    	} else {
    	    temp1 = allBills;
    	}

    	// Filter by Name
    	if (name != null) {
    	    temp2 = new ArrayList<>();
    	    for (Bill b : temp1) {
    	        if (b.getBillName().equalsIgnoreCase(name)) {
    	            temp2.add(b);
    	        }
    	    }
    	} else {
    	    temp2 = temp1;
    	}

    	// Filter by Date Range (dateFrom to dateTo)
    	if (dateFrom != null && dateTo != null) {
    	    temp3 = new ArrayList<>();
    	    for (Bill b : temp2) {
    	        Date dueDate = b.getDueDate();
    	        if ((dueDate.equals(dateFrom) || dueDate.after(dateFrom)) &&
    	            (dueDate.equals(dateTo) || dueDate.before(dateTo))) {
    	            temp3.add(b);
    	        }
    	    }
    	} else {
    	    temp3 = temp2;
    	}

    	// Final result will be in temp3
    	return temp3;
    }

    /**
     * Retrieves a list of upcoming bills based on the specified criteria.
     * 
     * @param Category The category of the bills to retrieve.
     * @param name The name of the bills to retrieve.
     * @param dateTo The end date of the date range.
     * @param dateFrom The start date of the date range.
     * @return A list of upcoming bills that match the specified criteria.
     */// Method to get upcoming bills
    public List<Bill> getUpcomingBillsService(String Category, String name, Date dateTo, Date dateFrom) {
    	List<Bill> allBills = br.getUpcomingBills();
    	List<Bill> temp1;
    	List<Bill> temp2;
    	List<Bill> temp3;

    	// Filter by Category
    	if (Category != null) {
    		temp1 = new ArrayList<>();
    	    for (Bill b : allBills) {
    	        if (b.getBillCategory().equalsIgnoreCase(Category)) {
    	            temp1.add(b);
    	        }
    	    }
    	} else {
    	    temp1 = allBills;
    	}

    	// Filter by Name
    	if (name != null) {
    	    temp2 = new ArrayList<>();
    	    for (Bill b : temp1) {
    	        if (b.getBillName().equalsIgnoreCase(name)) {
    	            temp2.add(b);
    	        }
    	    }
    	} else {
    	    temp2 = temp1;
    	}

    	// Filter by Date Range (dateFrom to dateTo)
    	if (dateFrom != null && dateTo != null) {
    	    temp3 = new ArrayList<>();
    	    for (Bill b : temp2) {
    	        Date dueDate = b.getDueDate();
    	        if ((dueDate.equals(dateFrom) || dueDate.after(dateFrom)) &&
    	            (dueDate.equals(dateTo) || dueDate.before(dateTo))) {
    	            temp3.add(b);
    	        }
    	    }
    	} else {
    	    temp3 = temp2;
    	}

    	// Final result will be in temp3
    	return temp3;
    }

     /**
     * Snoozes a bill by updating its due date to a specified snooze date.
     * 
     * @param snoozeDate The new due date for the bill.
     * @param id The ID of the bill to snooze.
     */
    public void snoozeBillService(Date snoozeDate, int id) {
    	List<Bill> allBills = br.getAllBills();

		for(Bill b:allBills){
			if(b.getBillId() == id){
				br.snoozeBill(b,snoozeDate);
			}
		}
    }

       /**
     * Marks a bill as paid by updating its payment status.
     * 
     * @param id The ID of the bill to mark as paid.
     */
    public void markBillAsPaidService(int id) {
    	List<Bill> allBills = br.getAllBills();

		for(Bill b:allBills){
			if(b.getBillId() == id){
				br.markBillAsPaid(b);
			}
		}
    }
    
    public List<Bill> getAllBillsService(){
    	return br.getAllBills();
    }
}
