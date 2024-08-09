package com.paypilot.service;

import com.paypilot.model.Bill;
import com.paypilot.repo.BillRepository;
import java.util.List;

public class BillService {

    private BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public void addBill(Bill bill) {
        billRepository.addBill(bill);
    }

    public void updateBill(Bill bill) {
        billRepository.updateBill(bill);
    }

    public void deleteBill(int billId) {
        billRepository.deleteBill(billId);
    }

    public List<Bill> getAllBills() {
        return billRepository.getAllBills();
    }

    public Bill getBillById(int billId) {
        return billRepository.getBillById(billId);
    }
}
