package com.paypilot.model;

import java.util.Date;
import java.io.File;

/**
 * The Bill class represents a bill with various attributes such as ID, name, category, due date, amount, etc.
 * It includes constructors, getters, setters, and a method to represent the Bill object as a string.
 * 
 * Author: Anshul
 * Date: 09-08-2024
 */
public class Bill {
    private int billId;
    private String billName;
    private String billCategory;
    private Date dueDate;
    private double amount;
    private String reminderFrequency;
    private File attachment;
    private String notes;
    private boolean isRecurring;
    private String paymentStatus;
    private int overdueDays;

    /**
     * Default constructor for the Bill class.
     * Initializes a new Bill object with default values.
     */
    public Bill() {
        // Default constructor
    }

    /**
     * Parameterized constructor to initialize a Bill object with specified values.
     * 
     * @param billId The unique identifier for the bill.
     * @param billName The name of the bill.
     * @param billCategory The category of the bill.
     * @param dueDate The due date for the bill.
     * @param amount The amount of the bill.
     * @param reminderFrequency The frequency of reminders for the bill.
     * @param attachment An optional file attachment related to the bill.
     * @param notes Additional notes about the bill.
     * @param isRecurring Indicates whether the bill is recurring.
     * @param paymentStatus The current payment status of the bill.
     * @param overdueDays The number of days the bill is overdue.
     */
    public Bill(int billId, String billName, String billCategory, Date dueDate, double amount,
                String reminderFrequency, File attachment, String notes, boolean isRecurring,
                String paymentStatus, int overdueDays) {
        this.billId = billId;
        this.billName = billName;
        this.billCategory = billCategory;
        this.dueDate = dueDate;
        this.amount = amount;
        this.reminderFrequency = reminderFrequency;
        this.attachment = attachment;
        this.notes = notes;
        this.isRecurring = isRecurring;
        this.paymentStatus = paymentStatus;
        this.overdueDays = overdueDays;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier for the bill.
     * 
     * @return The bill ID.
     */
    public int getBillId() { return billId; }

    /**
     * Sets the unique identifier for the bill.
     * 
     * @param billId The new bill ID.
     */
    public void setBillId(int billId) { this.billId = billId; }

    /**
     * Gets the name of the bill.
     * 
     * @return The bill name.
     */
    public String getBillName() { return billName; }

    /**
     * Sets the name of the bill.
     * 
     * @param billName The new bill name.
     */
    public void setBillName(String billName) { this.billName = billName; }

    /**
     * Gets the category of the bill.
     * 
     * @return The bill category.
     */
    public String getBillCategory() { return billCategory; }

    /**
     * Sets the category of the bill.
     * 
     * @param billCategory The new bill category.
     */
    public void setBillCategory(String billCategory) { this.billCategory = billCategory; }

    /**
     * Gets the due date for the bill.
     * 
     * @return The due date.
     */
    public Date getDueDate() { return dueDate; }

    /**
     * Sets the due date for the bill.
     * 
     * @param dueDate The new due date.
     */
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    /**
     * Gets the amount of the bill.
     * 
     * @return The bill amount.
     */
    public double getAmount() { return amount; }

    /**
     * Sets the amount of the bill.
     * 
     * @param amount The new bill amount.
     */
    public void setAmount(double amount) { this.amount = amount; }

    /**
     * Gets the frequency of reminders for the bill.
     * 
     * @return The reminder frequency.
     */
    public String getReminderFrequency() { return reminderFrequency; }

    /**
     * Sets the frequency of reminders for the bill.
     * 
     * @param reminderFrequency The new reminder frequency.
     */
    public void setReminderFrequency(String reminderFrequency) { this.reminderFrequency = reminderFrequency; }

    /**
     * Gets the file attachment related to the bill.
     * 
     * @return The attachment file.
     */
    public File getAttachment() { return attachment; }

    /**
     * Sets the file attachment related to the bill.
     * 
     * @param attachment The new attachment file.
     */
    public void setAttachment(File attachment) { this.attachment = attachment; }

    /**
     * Gets additional notes about the bill.
     * 
     * @return The bill notes.
     */
    public String getNotes() { return notes; }

    /**
     * Sets additional notes about the bill.
     * 
     * @param notes The new bill notes.
     */
    public void setNotes(String notes) { this.notes = notes; }

    /**
     * Checks if the bill is recurring.
     * 
     * @return True if the bill is recurring, false otherwise.
     */
    public boolean isRecurring() { return isRecurring; }

    /**
     * Sets whether the bill is recurring.
     * 
     * @param recurring True if the bill is recurring, false otherwise.
     */
    public void setRecurring(boolean recurring) { isRecurring = recurring; }

    /**
     * Gets the current payment status of the bill.
     * 
     * @return The payment status.
     */
    public String getPaymentStatus() { return paymentStatus; }

    /**
     * Sets the current payment status of the bill.
     * 
     * @param paymentStatus The new payment status.
     */
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    /**
     * Gets the number of days the bill is overdue.
     * 
     * @return The number of overdue days.
     */
    public int getOverdueDays() { return overdueDays; }

    /**
     * Sets the number of days the bill is overdue.
     * 
     * @param overdueDays The new number of overdue days.
     */
    public void setOverdueDays(int overdueDays) { this.overdueDays = overdueDays; }

    /**
     * Returns a string representation of the Bill object.
     * 
     * @return A string describing the Bill object.
     */
    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", billName='" + billName + '\'' +
                ", billCategory='" + billCategory + '\'' +
                ", dueDate=" + dueDate +
                ", amount=" + amount +
                ", reminderFrequency='" + reminderFrequency + '\'' +
                ", attachment=" + attachment +
                ", notes='" + notes + '\'' +
                ", isRecurring=" + isRecurring +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", overdueDays=" + overdueDays +
                '}';
    }
}
