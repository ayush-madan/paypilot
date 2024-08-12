package com.paypilot.controller;

import com.paypilot.model.Bill;
import com.paypilot.model.ReminderSettings;
import com.paypilot.service.BillService;
import com.paypilot.repo.BillRepository;

import java.util.Date;
import java.util.List;

/**
 * BillController manages user interactions with the BillService to handle operations related to bills.
 * It provides methods for adding, updating, deleting, listing, and retrieving bills.
 * 
 * Author: Anshul
 * Date: 09-08-2024
 */
public class BillController {

    private BillService billService; // Service to manage bill operations

    /**
     * Constructs a BillController with the specified BillService.
     * 
     * @param billService The BillService used by this controller.
     */
    public BillController(BillService billService) {
        this.billService = billService;
    }

    /**
     * Adds a new bill and prints a success message.
     * 
     * @param bill The Bill object to be added.
     */
    public void addBillController(Bill bill) {
        billService.addBill(bill);  
        System.out.println("Bill added successfully.");
    }

    /**
     * Updates an existing bill and prints a success message.
     * 
     * @param bill The Bill object with updated information.
     */
    public void updateBillController(Bill bill) {
        billService.updateBill(bill);  
        System.out.println("Bill updated successfully.");
    }

    /**
     * Deletes a bill by its ID and prints a success message.
     * 
     * @param billId The ID of the bill to be deleted.
     */
    public void deleteBillController(int billId) {
        billService.deleteBill(billId);  
        System.out.println("Bill deleted successfully.");
    }

    /**
     * Lists all bills and prints their details.
     */
    public void listAllBillsController() {
        List<Bill> bills = billService.getAllBills();  
        for (Bill bill : bills) {
            System.out.println(bill);
        }
    }

    /**
     * Retrieves and prints the details of a bill by its ID.
     * If the bill is not found, prints a "Bill not found" message.
     * 
     * @param billId The ID of the bill to be retrieved.
     */
    public void getBillDetailsController(int billId) {
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

        // Create sample reminder settings and bills
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
