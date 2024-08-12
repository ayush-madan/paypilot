package com.paypilot.service;

import com.paypilot.model.Bill;
import com.paypilot.repo.BillRepository;
import java.util.List;

/**
 * The BillService class provides services for managing Bill objects.
 * It acts as an intermediary between the application and the BillRepository.
 * This class includes methods for adding, updating, deleting, and retrieving bills.
 * 
 * Author: Anshul
 * Date: 09-08-2024
 */
public class BillService {

    private BillRepository billRepository; // Repository to manage Bill objects

    /**
     * Constructs a BillService with the specified BillRepository.
     * 
     * @param billRepository The BillRepository used by this service.
     */
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    /**
     * Adds a new Bill to the repository.
     * 
     * @param bill The Bill object to be added.
     */
    public void addBill(Bill bill) {
        billRepository.addBill(bill);
    }

    /**
     * Updates an existing Bill in the repository.
     * 
     * @param bill The Bill object with updated information.
     */
    public void updateBill(Bill bill) {
        billRepository.updateBill(bill);
    }

    /**
     * Deletes a Bill from the repository based on its ID.
     * 
     * @param billId The ID of the Bill to be deleted.
     */
    public void deleteBill(int billId) {
        billRepository.deleteBill(billId);
    }

    /**
     * Retrieves all Bills from the repository.
     * 
     * @return A List of all Bill objects in the repository.
     */
    public List<Bill> getAllBills() {
        return billRepository.getAllBills();
    }

    /**
     * Retrieves a Bill from the repository based on its ID.
     * 
     * @param billId The ID of the Bill to be retrieved.
     * @return The Bill object with the specified ID, or null if not found.
     */
    public Bill getBillById(int billId) {
        return billRepository.getBillById(billId);
    }
}
