/**
 * The {@code BillController} class manages the interaction between the user interface
 * and the {@code BillService} to handle various operations related to {@code Bill} objects.
 * It provides methods for adding, updating, deleting, and retrieving bills, as well as
 * listing all bills stored in the system.
 *
 * <p>This class is responsible for controlling the flow of data to and from the
 * {@code BillService} and outputs messages to the user based on the outcome of these operations.</p>
 *
 * <p>Author: Anshul</p>
 * <p>Date: 09-08-2024</p>
 */
package com.paypilot.controller;

import com.paypilot.model.Bill;
import com.paypilot.model.ReminderSettings;
import com.paypilot.service.BillService;
import com.paypilot.repo.BillRepository;
import java.util.Date;
import java.util.List;

public class BillController {

    /**
     * Dependency injected {@code BillService} instance to manage bills.
     */
    private BillService billService;

    /**
     * Constructs a {@code BillController} with the specified {@code BillService}.
     *
     * @param billService The service layer responsible for bill management operations.
     */
    public BillController(BillService billService) {
        this.billService = billService;
    }

    /**
     * Handles adding a new {@code Bill} via the {@code BillService} and outputs a success message.
     *
     * @param bill The {@code Bill} object to be added.
     */
    public void addBillController(Bill bill) {
        billService.addBillService(bill);
        System.out.println("Bill added successfully.");
    }

    /**
     * Handles updating an existing {@code Bill} via the {@code BillService} and outputs a success message.
     *
     * @param bill The {@code Bill} object with updated information.
     */
    public void updateBillController(Bill bill) {
        billService.updateBillService(bill);
        System.out.println("Bill updated successfully.");
    }

    /**
     * Handles deleting a {@code Bill} by its ID via the {@code BillService} and outputs a success message.
     *
     * @param billId The ID of the {@code Bill} to be deleted.
     */
    public void deleteBillController(int billId) {
        billService.deleteBillService(billId);
        System.out.println("Bill deleted successfully.");
    }

    /**
     * Retrieves and lists all {@code Bill} objects via the {@code BillService} and prints them to the console.
     */
    public void listAllBillsController() {
        List<Bill> bills = billService.getAllBillsService();
        for (Bill bill : bills) {
            System.out.println(bill);
        }
    }

    /**
     * Retrieves a {@code Bill} by its ID via the {@code BillService} and prints its details.
     * If the {@code Bill} is not found, it outputs a 'not found' message.
     *
     * @param billId The ID of the {@code Bill} to retrieve.
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
     * Main method to simulate operations on the {@code BillController}.
     * Demonstrates adding, updating, retrieving, deleting, and listing {@code Bill} objects.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Setup repository and service for the BillController.
        BillRepository billRepository = new BillRepository();
        BillService billService = new BillService(billRepository);
        BillController billController = new BillController(billService);

        // Create sample ReminderSettings and Bill objects.
        ReminderSettings reminderSettings = new ReminderSettings(1, "Monthly", new Date(), "Pay on time", "Email", null);
        Bill bill1 = new Bill(1, "Electricity", "Utilities", new Date(), 100.0, "Monthly", null, "N/A", false, "Upcoming", 5, reminderSettings);
        Bill bill2 = new Bill(2, "Internet", "Utilities", new Date(), 60.0, "Monthly", null, "N/A", true, "Pending", 2, null);

        // Add the bills to the system.
        billController.addBillController(bill1);
        billController.addBillController(bill2);

        // List all bills currently stored.
        System.out.println("Listing all bills:");
        billController.listAllBillsController();

        // Update the amount of bill1 and save the changes.
        bill1.setAmount(120.0);
        billController.updateBillController(bill1);

        // Get and print the details of a specific bill by its ID.
        System.out.println("Details of bill ID 1:");
        billController.getBillDetailsController(1);

        // Delete bill2 by its ID.
        billController.deleteBillController(2);

        // List all bills after the deletion of bill2.
        System.out.println("Listing all bills after deletion:");
        billController.listAllBillsController();
    }
}
