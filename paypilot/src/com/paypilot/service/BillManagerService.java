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
	
	public List<Bill> getBillsOverviewService(String category, Date fromDate, Date toDate, String status) {
        // Implementation goes here
		
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

    // Method to add a new bill
    public void addNewBillService(String name, String Category, Date dueDate, double amount, String reminderFrequency, File attachement, String note,boolean isRecurring, String paymentStatus, int overDueDays) {
        List<Bill> allBills = br.getAllBills();
    	int billId = allBills.size()+1;
    	br.addNewBill(br.createNewBill(billId, name, Category, dueDate, amount, 
        			reminderFrequency, attachement, note, isRecurring, 
        			paymentStatus, overDueDays, null));
        }

    // Method to get overdue bills
    public List<Bill> getOverdueBillsService(String Category, String name, Date dateTo, Date dateFrom) {
        // Implementation goes here
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

    // Method to get upcoming bills
    public List<Bill> getUpcomingBillsService(String Category, String name, Date dateTo, Date dateFrom) {
        // Implementation goes here
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

    // Method to snooze a bill
    public void snoozeBillService(String Category, String name, Date SnoozeDate) {
        // Implementation goes here
    	List<Bill> allBills = br.getUpcomingBills();
    	List<Bill> temp1;
    	List<Bill> temp2;

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
    
    	for(Bill b:temp2) {
    		br.snoozeBill(b, SnoozeDate);
		break;
    	}
    }

    // Method to mark a bill as paid
    public void markBillAsPaidService(String Category, String name) {
    	List<Bill> allBills = br.getUpcomingBills();
    	List<Bill> temp1;
    	List<Bill> temp2;

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
    
    	for(Bill b:temp2) {
    		br.markBillAsPaid(b);
		break;
    	}
    }
}
