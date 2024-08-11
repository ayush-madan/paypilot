package com.paypilot.controller;

import com.paypilot.model.Bill;
import com.paypilot.model.ReminderSettings;
import com.paypilot.service.BillService;
import com.paypilot.repo.BillRepository;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Controller class responsible for managing Bill-related operations.
 */
public class BillController {

    private BillService billService;

    /**
     * Constructs a BillController with the specified BillService.
     *
     * @param billService the BillService instance
     */
    public BillController(BillService billService) {
        this.billService = billService;
    }

    /**
     * Adds a new bill through the BillService.
     *
     * @param bill the Bill to be added
     */
    public void addBillController(Bill bill) {
        billService.addBillService(bill);  
        System.out.println("Bill added successfully.");
    }

    /**
     * Updates an existing bill through the BillService.
     *
     * @param bill the Bill to be updated
     */
    public void updateBillController(Bill bill) {
        billService.updateBillService(bill);  
        System.out.println("Bill updated successfully.");
    }

    /**
     * Deletes a bill by its ID through the BillService.
     *
     * @param billId the ID of the Bill to be deleted
     */
    public void deleteBillController(int billId) {
        billService.deleteBillService(billId);  
        System.out.println("Bill deleted successfully.");
    }

    /**
     * Lists all bills using the BillService.
     */
    public void listAllBillsController() {
        List<Bill> bills = billService.getAllBillsService();  
        for (Bill bill : bills) {
            System.out.println(bill);
        }
    }

    /**
     * Retrieves details of a specific bill by its ID.
     *
     * @param billId the ID of the Bill to be retrieved
     */
    public void getBillDetailsController(int billId) {
        Bill bill = billService.getBillByIdService(billId);  
        if (bill != null) {
            System.out.println(bill);
        } else {
            System.out.println("Bill not found.");
        }
    }

    /**
     * Creates and adds a new bill using provided details.
     *
     * @param billId the ID of the new Bill
     * @param billName the name of the Bill
     * @param billCategory the category of the Bill
     * @param dueDate the due date of the Bill
     * @param amount the amount of the Bill
     * @param reminderFrequency the reminder frequency for the Bill
     * @param attachment optional attachment for the Bill
     * @param notes additional notes for the Bill
     * @param isRecurring whether the Bill is recurring
     * @param paymentStatus the payment status of the Bill
     * @param overdueDays number of overdue days
     * @param reminderSettings the ReminderSettings associated with the Bill
     */
    public void createBillController(int billId, String billName, String billCategory, Date dueDate, double amount,
                                     String reminderFrequency, File attachment, String notes, boolean isRecurring,
                                     String paymentStatus, int overdueDays, ReminderSettings reminderSettings) {
        billService.createBillService(billId, billName, billCategory, dueDate, amount, reminderFrequency,
                                      attachment, notes, isRecurring, paymentStatus, overdueDays, reminderSettings);
        System.out.println("Bill created successfully.");
    }

    /**
     * Main method for testing the BillController functionality.
     */
    public static void main(String[] args) {
        // Setup repository and service
        BillRepository billRepository = new BillRepository(); // Create a repository instance
        BillService billService = new BillService(billRepository); // Inject repository into service
        BillController billController = new BillController(billService); // Inject service into controller

        // Create sample bills
        ReminderSettings reminderSettings = new ReminderSettings(1, "Monthly", new Date(), "Paid", "Email", null);
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

        // Create a new bill using createBillController
        System.out.println("Creating a new bill using createBillController:");
        billController.createBillController(3, "Water Bill", "Utilities", new Date(), 50.0, 
                                            "Monthly", null, "N/A", false, "Upcoming", 0, null);

        // List all bills after creation
        System.out.println("Listing all bills after creating a new bill:");
        billController.listAllBillsController();
    }
}
