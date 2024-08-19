/**
 * The {@code BillCategory} enum represents different categories of bills.
 * It helps in classifying bills into various categories for better management and organization.
 * 
 * <p>Each enum constant represents a specific category of bill, ranging from 
 * debt payments to cell phone charges.</p>
 * 
 * <p>Author: Anshul</p>
 * <p>Date: 09-08-2024</p>
 */
package com.paypilot.model;

public enum BillCategory {

    /**
     * Represents all categories of bills.
     */
    ALL, 

    /**
     * Bills related to debt repayments.
     */
    DEBT_PAYMENTS, 

    /**
     * Bills for house rent.
     */
    HOUSE_RENT, 

    /**
     * Bills for grocery purchases.
     */
    GROCERIES, 

    /**
     * Bills for internet services.
     */
    INTERNET_CHARGES, 

    /**
     * Bills related to retirement savings.
     */
    RETIREMENT_CHARGES, 

    /**
     * Bills for cell phone usage.
     */
    CELL_PHONE_CHARGES 
}
