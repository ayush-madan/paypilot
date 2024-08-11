/*
 * BillManagerModel manages a list of bills.
 * It provides methods to get and set the list of all bills.
 * 
 * Author: Aryman Srivastava
 * Date: 09-08-2024
 */

package com.paypilot.model;

import java.util.ArrayList;
import java.util.List;



public class BillManagerModel {
	
	// List to hold all bills
	private List<Bill> allBils = new ArrayList<>();
	
	public List<Bill> getAllBills(){
		return this.allBils;
	}
	
	public void setAllBills(List<Bill> b){
		this.allBils = b;
	}

}
