package com.paypilot.model;
import java.util.ArrayList;
import java.util.List;



public class BillManagerModel {
	
	private List<Bill> allBils = new ArrayList<>();
	
	public List<Bill> getAllBills(){
		return this.allBils;
	}
	
	public void setAllBills(List<Bill> b){
		this.allBils = b;
	}

}
