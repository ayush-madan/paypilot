package com.paypilot.model;

import java.util.Date;
import java.io.File;

/**
 * Represents a Bill with various attributes such as bill ID, name, category, due date, amount, etc.
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
    private ReminderSettings reminderSettings;

    /**
     * Default constructor.
     */
    public Bill() {}

    /**
     * Constructs a Bill with the specified details.
     *
     * @param billId             the unique identifier for the bill
     * @param billName           the name of the bill
     * @param billCategory       the category to which the bill belongs
     * @param dueDate            the due date of the bill
     * @param amount             the amount to be paid for the bill
     * @param reminderFrequency  the frequency at which reminders should be sent
     * @param attachment         an optional attachment related to the bill
     * @param notes              additional notes regarding the bill
     * @param isRecurring        indicates if the bill is recurring
     * @param paymentStatus      the current payment status of the bill
     * @param overdueDays        the number of days the bill is overdue, if applicable
     * @param reminderSettings   the reminder settings associated with the bill
     */
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
        this.reminderSettings = reminderSettings;
    }

    // Getters and setters

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillCategory() {
        return billCategory;
    }

    public void setBillCategory(String billCategory) {
        this.billCategory = billCategory;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReminderFrequency() {
        return reminderFrequency;
    }

    public void setReminderFrequency(String reminderFrequency) {
        this.reminderFrequency = reminderFrequency;
    }

    public File getAttachment() {
        return attachment;
    }

    public void setAttachment(File attachment) {
        this.attachment = attachment;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean isRecurring) {
        this.isRecurring = isRecurring;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(int overdueDays) {
        this.overdueDays = overdueDays;
    }

    public ReminderSettings getReminderSettings() {
        return reminderSettings;
    }

    public void setReminderSettings(ReminderSettings reminderSettings) {
        this.reminderSettings = reminderSettings;
    }

    /**
     * Returns a string representation of the bill.
     *
     * @return a string containing the details of the bill
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
                ", reminderSettings=" + reminderSettings +
                '}';
    }
}
