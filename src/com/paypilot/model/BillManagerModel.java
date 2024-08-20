/*
 * BillManagerModel manages a list of bills.
 * It provides methods to get and set the list of all bills.
 * 
 * Author: Aryman Srivastava
 * <p>Date: 09-08-2024</p>
 */

package com.paypilot.model;

import java.util.ArrayList;
import java.util.List;

import com.paypilot.repo.BillManagerDAO;



public class BillManagerModel {
	
	// List to hold all Bill objects
//	private List<Bill> allBils = new ArrayList<>();
	BillManagerDAOInterface dao = new BillManagerDAO();

	/**
     	* Retrieves the current list of all bills.
     	* 
     	* @return A list of all Bill objects
     	*/
	public List<Bill> getAllBills(){
		return dao.getAllBills();
	}

	/**
     	* Updates the list of all bills with the provided list.
     	* 
     	* @param bills The list of Bill objects to set
     	*/
	public void setBill(Bill b){
		dao.addBill(b);
	}
	
	public Bill getBillbyId(int id) {
		return dao.getBillById(id);
	}
}
