package com.paypilot.service;

import com.paypilot.model.Bill;
import com.paypilot.repo.BillRepository;
import java.util.List;

public class BillService {

    private BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public void addBillService(Bill bill) {
        billRepository.addBill(bill);
    }

    public void updateBillService(Bill bill) {
        billRepository.updateBill(bill);
    }

    public void deleteBillService(int billId) {
        billRepository.deleteBill(billId);
    }

    public List<Bill> getAllBillsService() {
        return billRepository.getAllBills();
    }

    public Bill getBillByIdService(int billId) {
        return billRepository.getBillById(billId);
    }
}
