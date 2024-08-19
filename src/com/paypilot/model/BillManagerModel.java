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
	
	// List to hold all Bill objects
	private List<Bill> allBils = new ArrayList<>();

	/**
     	* Retrieves the current list of all bills.
     	* 
     	* @return A list of all Bill objects
     	*/
	public List<Bill> getAllBills(){
		return this.allBils;
	}

	/**
     	* Updates the list of all bills with the provided list.
     	* 
     	* @param bills The list of Bill objects to set
     	*/
	public void setAllBills(List<Bill> b){
		this.allBils = b;
	}
}
