package com.paypilot.paypilot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.paypilot.billmngr.Bill;
import com.paypilot.billmngr.repo.BillManagerRepo;

public class BillManagerService {
	BillManagerRepo br = new BillManagerRepo();
	
	public List<Bill> getBillsOverview(String category, Date fromDate, Date toDate, String status) {
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
//			if(b.getCategory().equalsIgnoreCase("Debt")) {
//			    debtBill++;
//			} else if(b.getCategory().equalsIgnoreCase("Payment")) {
//			    paymentBill++;
//			} else if(b.getCategory().equalsIgnoreCase("HouseRent")) {
//			    houseRentBill++;
//			} else if(b.getCategory().equalsIgnoreCase("Groceries")) {
//			    groceriesBill++;
//			} else if(b.getCategory().equalsIgnoreCase("InternetCharges")) {
//			    internetChargesBill++;
//			} else if(b.getCategory().equalsIgnoreCase("RetirementCharges")) {
//			    retirementChargesBill++;
//			} else if(b.getCategory().equalsIgnoreCase("CellPhoneCharges")) {
//			    cellPhoneChargesBill++;
//			}
//		}
//		
		
		return br.getBillsOverview(category, fromDate, toDate, status);
    }

    // Method to add a new bill
    public void addNewBill(String name, String Category, Date dueDate, double amount, String reminderFrequency, String attachement, String note,boolean isRecurring, String paymentStatus, int overDueDays) {
        br.addNewBill(br.createNewBill(name, Category, dueDate, amount, 
        			reminderFrequency, attachement, note, isRecurring, 
        			paymentStatus, overDueDays));
        }

    // Method to get overdue bills
    public List<Bill> getOverdueBills(String Category, String name, Date dateTo, Date dateFrom) {
        // Implementation goes here
    	List<Bill> allBills = br.getOverdueBills();
    	List<Bill> temp1;
    	List<Bill> temp2;
    	List<Bill> temp3;

    	// Filter by Category
    	if (Category != null) {
    		temp1 = new ArrayList<>();
    	    for (Bill b : allBills) {
    	        if (b.getCategory().equalsIgnoreCase(Category)) {
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
    	        if (b.getName().equalsIgnoreCase(name)) {
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
    public List<Bill> getUpcomingBills(String Category, String name, Date dateTo, Date dateFrom) {
        // Implementation goes here
    	List<Bill> allBills = br.getUpcomingBills();
    	List<Bill> temp1;
    	List<Bill> temp2;
    	List<Bill> temp3;

    	// Filter by Category
    	if (Category != null) {
    		temp1 = new ArrayList<>();
    	    for (Bill b : allBills) {
    	        if (b.getCategory().equalsIgnoreCase(Category)) {
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
    	        if (b.getName().equalsIgnoreCase(name)) {
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
    public void snoozeBill(String Category, String name, Date SnoozeDate) {
        // Implementation goes here
    	List<Bill> allBills = br.getUpcomingBills();
    	List<Bill> temp1;
    	List<Bill> temp2;

    	// Filter by Category
    	if (Category != null) {
    		temp1 = new ArrayList<>();
    	    for (Bill b : allBills) {
    	        if (b.getCategory().equalsIgnoreCase(Category)) {
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
    	        if (b.getName().equalsIgnoreCase(name)) {
    	            temp2.add(b);
    	        }
    	    }
    	} else {
    	    temp2 = temp1;
    	}
    
    	for(Bill b:temp2) {
    		br.snoozeBill(b, SnoozeDate);
    	}
    }

    // Method to mark a bill as paid
    public void markBillAsPaid(String Category, String name) {
    	List<Bill> allBills = br.getUpcomingBills();
    	List<Bill> temp1;
    	List<Bill> temp2;

    	// Filter by Category
    	if (Category != null) {
    		temp1 = new ArrayList<>();
    	    for (Bill b : allBills) {
    	        if (b.getCategory().equalsIgnoreCase(Category)) {
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
    	        if (b.getName().equalsIgnoreCase(name)) {
    	            temp2.add(b);
    	        }
    	    }
    	} else {
    	    temp2 = temp1;
    	}
    
    	for(Bill b:temp2) {
    		br.markBillAsPaid(b);;
    	}
    }
}
