package com.paypilot.test;

import com.paypilot.model.Bill;
import com.paypilot.repo.BillRepository;
import com.paypilot.service.BillService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

public class BillServiceTest {

    private BillService billService;
    private BillRepository billRepository;

    @Before
    public void setUp() {
        billRepository = new BillRepository();
        billService = new BillService(billRepository);
    }

    @Test
    public void testAddBill() {
        Bill bill = new Bill(5, "Water", "Utilities", new Date(), 45.0, "Monthly", null, "N/A", true, "Upcoming", 0);
        billService.addBillService(bill);
        Bill retrievedBill = billService.getBillByIdSerivce(5);
        assertNotNull("Bill should be added successfully", retrievedBill);
        assertEquals("Bill ID not found", 5, retrievedBill.getBillId());
    }

    @Test
    public void testUpdateBill() {
        Bill bill = new Bill(1, "Electricity Bill", "Utilities", new Date(2024-1900, 7, 15), 100.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0);
        billService.updateBillService(bill);
        Bill updatedBill = billService.getBillByIdSerivce(1);
        assertNotNull("Bill should be updated successfully", updatedBill);
        assertEquals("Amount did not match", 100.50, updatedBill.getAmount(), 0.01);
    }

    @Test
    public void testDeleteBill() {
        billService.deleteBillService(2);
        Bill deletedBill = billService.getBillByIdSerivce(2);
        assertNull("Coudl not delete Bill", deletedBill);
    }

    @Test
    public void testGetAllBills() {
        int initialSize = billService.getAllBillsService().size();
        Bill newBill = new Bill(6, "Phone", "Utilities", new Date(), 75.0, "Monthly", null, "N/A", false, "Upcoming", 0);
        billService.addBillService(newBill);
        int newSize = billService.getAllBillsService().size();
        assertEquals("Size of bill list should increase by one", initialSize + 1, newSize);
    }

    @Test
    public void testGetBillById() {
        Bill bill = billService.getBillByIdSerivce(3);
        assertNotNull("Bill should be found by ID", bill);
        assertEquals("Bill name not found", "Rent", bill.getBillName());
    }
}
