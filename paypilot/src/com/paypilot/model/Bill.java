package com.paypilot.model;

import java.util.Date;
import java.io.File;

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
    private ReminderSettings reminderSettings; // Relationship with ReminderSettings
    
    // Default constructor
    public Bill() {}

    // Parameterized constructor
    public Bill(int billId, String billName, String billCategory, Date dueDate, double amount,
                String reminderFrequency, File attachment, String notes, boolean isRecurring,
                String paymentStatus, int overdueDays, ReminderSettings reminderSettings) {
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
        this.reminderSettings = reminderSettings; // Initialize ReminderSettings
    }

    // Getters and Setters
    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }
    public String getBillName() { return billName; }
    public void setBillName(String billName) { this.billName = billName; }
    public String getBillCategory() { return billCategory; }
    public void setBillCategory(String billCategory) { this.billCategory = billCategory; }
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getReminderFrequency() { return reminderFrequency; }
    public void setReminderFrequency(String reminderFrequency) { this.reminderFrequency = reminderFrequency; }
    public File getAttachment() { return attachment; }
    public void setAttachment(File attachment) { this.attachment = attachment; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public boolean isRecurring() { return isRecurring; }
    public void setRecurring(boolean recurring) { isRecurring = recurring; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public int getOverdueDays() { return overdueDays; }
    public void setOverdueDays(int overdueDays) { this.overdueDays = overdueDays; }
    public ReminderSettings getReminderSettings() { return reminderSettings; }
    public void setReminderSettings(ReminderSettings reminderSettings) { this.reminderSettings = reminderSettings; }

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
                ", reminderSettings=" + reminderSettings +
                '}';
    }
}