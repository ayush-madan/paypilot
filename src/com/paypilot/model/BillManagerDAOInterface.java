package com.paypilot.model;

import java.util.List;

public interface BillManagerDAOInterface {
	List<Bill> getAllBills();
	void addBill(Bill bill);
	Bill getBillById(int id);
}