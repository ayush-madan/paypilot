package com.paypilot.repo;

import com.paypilot.model.Bill;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.File;

public class BillRepository {

    private List<Bill> bills = new ArrayList<>();

    // Constructor to initialize demo data
    public BillRepository() {
        // Initialize with some demo bills
        bills.add(new Bill(1, "Electricity Bill", "Utilities", new Date(2024-1900, 7, 15), 100.50, "Monthly", null, "Pay before due date", false, "Upcoming", 0));
        bills.add(new Bill(2, "Internet Bill", "Internet Charges", new Date(2024-1900, 7, 10), 60.00, "Monthly", null, "", true, "Paid", 0));
        bills.add(new Bill(3, "Rent", "House Rent", new Date(2024-1900, 6, 30), 1200.00, "Monthly", null, "Rent for June", false, "Overdue", 5));
        bills.add(new Bill(4, "Groceries", "Groceries", new Date(2024-1900, 7, 5), 200.00, "Weekly", null, "Weekly groceries", false, "Paid", 0));
    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }

    public void updateBill(Bill bill) {
        for (int i = 0; i < bills.size(); i++) {
            if (bills.get(i).getBillId() == bill.getBillId()) {
                bills.set(i, bill);
                return;
            }
        }
    }

    public void deleteBill(int billId) {
        bills.removeIf(bill -> bill.getBillId() == billId);
    }

    public List<Bill> getAllBills() {
        return new ArrayList<>(bills);
    }

    public Bill getBillById(int billId) {
        for (Bill bill : bills) {
            if (bill.getBillId() == billId) {
                return bill;
            }
        }
        return null;
    }
}
