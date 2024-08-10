package com.paypilot.controller;

import com.paypilot.model.Bill;
import com.paypilot.model.ReminderSettings;
import com.paypilot.service.BillService;
import com.paypilot.repo.BillRepository;
import java.util.Date;
import java.util.List;

public class BillController {

    private BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    
    public void addBillController(Bill bill) {
        billService.addBillService(bill);  
        System.out.println("Bill added successfully.");
    }

    public void updateBillController(Bill bill) {
        billService.updateBillService(bill);  
        System.out.println("Bill updated successfully.");
    }

    public void deleteBillController(int billId) {
        billService.deleteBillService(billId);  
        System.out.println("Bill deleted successfully.");
    }

    public void listAllBillsController() {
        List<Bill> bills = billService.getAllBillsService();  
        for (Bill bill : bills) {
            System.out.println(bill);
        }
    }

    public void getBillDetailsController(int billId) {
        Bill bill = billService.getBillByIdService(billId);  
        if (bill != null) {
            System.out.println(bill);
        } else {
            System.out.println("Bill not found.");
        }
    }

    public static void main(String[] args) {
        // Setup repository and service
        BillRepository billRepository = new BillRepository(); // Create a repository instance
        BillService billService = new BillService(billRepository); // Inject repository into service
        BillController billController = new BillController(billService); // Inject service into controller

        // Create a sample bill
        ReminderSettings reminderSettings = new ReminderSettings(1, "Monthly", new Date(), "Pay on time", "Email", null);
        Bill bill1 = new Bill(1, "Electricity", "Utilities", new Date(), 100.0, "Monthly", null, "N/A", false, "Upcoming", 5, reminderSettings);
        Bill bill2 = new Bill(2, "Internet", "Utilities", new Date(), 60.0, "Monthly", null, "N/A", true, "Pending", 2, null);

        // Add bills
        billController.addBillController(bill1);
        billController.addBillController(bill2);

        // List all bills
        System.out.println("Listing all bills:");
        billController.listAllBillsController();

        // Update a bill
        bill1.setAmount(120.0);
        billController.updateBillController(bill1);

        // Get details of a specific bill
        System.out.println("Details of bill ID 1:");
        billController.getBillDetailsController(1);

        // Delete a bill
        billController.deleteBillController(2);

        // List all bills again
        System.out.println("Listing all bills after deletion:");
        billController.listAllBillsController();
    }
}
