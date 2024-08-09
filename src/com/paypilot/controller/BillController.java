package com.paypilot.controller;

import com.paypilot.model.Bill;
import com.paypilot.service.BillService;
import java.util.List;

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
}
