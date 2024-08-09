package com.paypilot.controller;

import com.paypilot.model.Bill;
import com.paypilot.service.BillService;
import com.paypilot.repo.BillRepository;  // Assuming BillRepository exists
import java.util.Date;

public class BillController {

    private BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    public void addBill(Bill bill) {
        billService.addBill(bill);
        System.out.println("Bill added successfully.");
    }

    public void updateBill(Bill bill) {
        billService.updateBill(bill);
        System.out.println("Bill updated successfully.");
    }

    public void deleteBill(int billId) {
        billService.deleteBill(billId);
        System.out.println("Bill deleted successfully.");
    }

    public void listAllBills() {
        List<Bill> bills = billService.getAllBills();
        for (Bill bill : bills) {
            System.out.println(bill);
        }
    }

    public void getBillDetails(int billId) {
        Bill bill = billService.getBillById(billId);
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

        // Create sample bills
        Bill bill1 = new Bill(1, "Electricity", "Utilities", new Date(), 100.0, "Monthly", null, "N/A", false, "Upcoming", 5);
        Bill bill2 = new Bill(2, "Internet", "Utilities", new Date(), 60.0, "Monthly", null, "N/A", true, "Pending", 2);

        // Add bills
        billController.addBill(bill1);
        billController.addBill(bill2);

        // List all bills
        System.out.println("Listing all bills:");
        billController.listAllBills();

        // Update a bill
        bill1.setAmount(120.0);
        billController.updateBill(bill1);

        // Get details of a specific bill
        System.out.println("Details of bill ID 1:");
        billController.getBillDetails(1);

        // Delete a bill
        billController.deleteBill(2);

        // List all bills again
        System.out.println("Listing all bills after deletion:");
        billController.listAllBills();
    }
}
