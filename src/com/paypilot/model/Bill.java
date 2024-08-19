/**
 * The {@code Bill} class represents a bill in the system. It contains details such as
 * the bill's ID, name, category, due date, amount, and other relevant information.
 * It also includes a relationship with {@code ReminderSettings} for managing reminders.
 * 
 * <p>This class is used to store and retrieve details about bills and is
 * associated with reminder settings to help manage notifications for bill payments.</p>
 * 
 * <p>Author: Anshul</p>
 * <p>Date: 09-08-2024</p>
 */
package com.paypilot.model;

import java.util.Date;
import java.io.File;

public class Bill {

    /**
     * Unique identifier for the bill.
     */
    private int billId;

    /**
     * Name or title of the bill (e.g., "Electricity").
     */
    private String billName;

    /**
     * Category of the bill (e.g., "Utilities").
     */
    private String billCategory;

    /**
     * Due date for the bill payment.
     */
    private Date dueDate;

    /**
     * Amount to be paid for the bill.
     */
    private double amount;

    /**
     * Frequency for reminders related to the bill (e.g., "Monthly").
     */
    private String reminderFrequency;

    /**
     * Optional file attachment related to the bill.
     */
    private File attachment;

    /**
     * Additional notes related to the bill.
     */
    private String notes;

    /**
     * Indicates whether the bill is recurring.
     */
    private boolean isRecurring;

    /**
     * Status of the bill payment (e.g., "Paid", "Pending").
     */
    private String paymentStatus;

    /**
     * Number of days the bill is overdue.
     */
    private int overdueDays;

    /**
     * Associated reminder settings for the bill.
     */
    private ReminderSettings reminderSettings;

    /**
     * Default constructor.
     */
    public Bill() {}

    /**
     * Parameterized constructor to initialize a {@code Bill} object with all fields.
     *
     * @param billId Unique identifier for the bill.
     * @param billName Name or title of the bill.
     * @param billCategory Category of the bill.
     * @param dueDate Due date for the bill payment.
     * @param amount Amount to be paid for the bill.
     * @param reminderFrequency Frequency for reminders related to the bill.
     * @param attachment Optional file attachment related to the bill.
     * @param notes Additional notes related to the bill.
     * @param isRecurring Indicates whether the bill is recurring.
     * @param paymentStatus Status of the bill payment.
     * @param overdueDays Number of days the bill is overdue.
     * @param reminderSettings Associated reminder settings for the bill.
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

    // Getters and Setters

    /**
     * Returns the unique identifier for the bill.
     *
     * @return the bill's ID.
     */
    public int getBillId() { return billId; }

    /**
     * Sets the unique identifier for the bill.
     *
     * @param billId the bill's ID to set.
     */
    public void setBillId(int billId) { this.billId = billId; }

    /**
     * Returns the name or title of the bill.
     *
     * @return the bill's name.
     */
    public String getBillName() { return billName; }

    /**
     * Sets the name or title of the bill.
     *
     * @param billName the bill's name to set.
     */
    public void setBillName(String billName) { this.billName = billName; }

    /**
     * Returns the category of the bill.
     *
     * @return the bill's category.
     */
    public String getBillCategory() { return billCategory; }

    /**
     * Sets the category of the bill.
     *
     * @param billCategory the bill's category to set.
     */
    public void setBillCategory(String billCategory) { this.billCategory = billCategory; }

    /**
     * Returns the due date for the bill payment.
     *
     * @return the due date.
     */
    public Date getDueDate() { return dueDate; }

    /**
     * Sets the due date for the bill payment.
     *
     * @param dueDate the due date to set.
     */
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    /**
     * Returns the amount to be paid for the bill.
     *
     * @return the amount.
     */
    public double getAmount() { return amount; }

    /**
     * Sets the amount to be paid for the bill.
     *
     * @param amount the amount to set.
     */
    public void setAmount(double amount) { this.amount = amount; }

    /**
     * Returns the frequency for reminders related to the bill.
     *
     * @return the reminder frequency.
     */
    public String getReminderFrequency() { return reminderFrequency; }

    /**
     * Sets the frequency for reminders related to the bill.
     *
     * @param reminderFrequency the reminder frequency to set.
     */
    public void setReminderFrequency(String reminderFrequency) { this.reminderFrequency = reminderFrequency; }

    /**
     * Returns the optional file attachment related to the bill.
     *
     * @return the file attachment.
     */
    public File getAttachment() { return attachment; }

    /**
     * Sets the optional file attachment related to the bill.
     *
     * @param attachment the file attachment to set.
     */
    public void setAttachment(File attachment) { this.attachment = attachment; }

    /**
     * Returns the additional notes related to the bill.
     *
     * @return the notes.
     */
    public String getNotes() { return notes; }

    /**
     * Sets the additional notes related to the bill.
     *
     * @param notes the notes to set.
     */
    public void setNotes(String notes) { this.notes = notes; }

    /**
     * Returns whether the bill is recurring.
     *
     * @return {@code true} if the bill is recurring; {@code false} otherwise.
     */
    public boolean isRecurring() { return isRecurring; }

    /**
     * Sets whether the bill is recurring.
     *
     * @param recurring {@code true} if the bill is recurring; {@code false} otherwise.
     */
    public void setRecurring(boolean recurring) { isRecurring = recurring; }

    /**
     * Returns the status of the bill payment.
     *
     * @return the payment status.
     */
    public String getPaymentStatus() { return paymentStatus; }

    /**
     * Sets the status of the bill payment.
     *
     * @param paymentStatus the payment status to set.
     */
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    /**
     * Returns the number of days the bill is overdue.
     *
     * @return the overdue days.
     */
    public int getOverdueDays() { return overdueDays; }

    /**
     * Sets the number of days the bill is overdue.
     *
     * @param overdueDays the overdue days to set.
     */
    public void setOverdueDays(int overdueDays) { this.overdueDays = overdueDays; }

    /**
     * Returns the associated reminder settings for the bill.
     *
     * @return the reminder settings.
     */
    public ReminderSettings getReminderSettings() { return reminderSettings; }

    /**
     * Sets the associated reminder settings for the bill.
     *
     * @param reminderSettings the reminder settings to set.
     */
    public void setReminderSettings(ReminderSettings reminderSettings) { this.reminderSettings = reminderSettings; }

    /**
     * Returns a string representation of the {@code Bill} object, including all its fields.
     *
     * @return a string representation of the bill.
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
