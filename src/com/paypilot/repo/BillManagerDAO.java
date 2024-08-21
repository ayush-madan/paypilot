/**
 * Implementation of the BillManagerDAOInterface that interacts with the database
 * to perform operations related to bills, such as retrieving, adding, and fetching bills by ID.
 * Author: Aryman Srivastava
 * Date: 20-08-2024
 */
package com.paypilot.repo;

import java.io.File;
import java.sql.*;
import java.util.*;
import java.util.Date;

import com.paypilot.model.Bill;
import com.paypilot.model.BillManagerDAOInterface;

public class BillManagerDAO implements BillManagerDAOInterface {

    /**
     * Retrieves a list of all bills from the database.
     * 
     * @return A List of Bill objects representing all bills in the database.
     */
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM BILLS";

        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Integer id = rs.getInt(1);
                String name = rs.getString(2);
                String category = rs.getString(3);
                Date dueDate = rs.getDate(4);
                Float amount = rs.getFloat(5);
                String reminderFrequency = rs.getString(6);
                File attachment = rs.getString(7) != null ? new File(rs.getString(7)) : null;
                String notes = rs.getString(8);
                Boolean isRecurring = rs.getBoolean(9);
                String paymentStatus = rs.getString(10);
                int overdueDays = rs.getInt(11);

                Bill bill = new Bill(id, name, category, dueDate, amount, reminderFrequency, attachment, notes, isRecurring, paymentStatus, overdueDays, null);
                bills.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bills;
    }

    /**
     * Adds a new bill to the database.
     * 
     * @param bill The Bill object containing the details to be added to the database.
     */
    public void addBill(Bill bill) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = "INSERT INTO BILLS (bill_id, bill_name, bill_category, due_date, amount, reminder_frequency, attachment, notes, is_recurring, payment_status, overdue_days, user_id) VALUES (bill_id_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            con = DBConnection.getConnection();
            pstmt = con.prepareStatement(query);

            // Set parameters based on the Bill object
            pstmt.setString(1, bill.getBillName());
            pstmt.setString(2, bill.getBillCategory());
            pstmt.setDate(3, new java.sql.Date(bill.getDueDate().getTime()));  // Converting java.util.Date to java.sql.Date
            pstmt.setFloat(4, (float) bill.getAmount());
            pstmt.setString(5, bill.getReminderFrequency());
            pstmt.setString(6, bill.getAttachment() != null ? bill.getAttachment().getPath() : null);
            pstmt.setString(7, bill.getNotes());
            pstmt.setBoolean(8, bill.isRecurring());
            pstmt.setString(9, bill.getPaymentStatus());
            pstmt.setInt(10, bill.getOverdueDays());
            pstmt.setInt(11, (Integer) null);  // Assuming userId is null for now

            // Execute the insert operation
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retrieves a specific bill from the database by its ID.
     * 
     * @param id The unique identifier of the bill to retrieve.
     * @return The Bill object corresponding to the provided ID, or null if no bill is found.
     */
    public Bill getBillById(int id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Bill bill = null;
        String query = "SELECT * FROM BILLS WHERE id = ?";

        try {
            con = DBConnection.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("bill_name");
                String category = rs.getString("bill_category");
                Date dueDate = rs.getDate("due_date");
                Float amount = rs.getFloat("amount");
                String reminderFrequency = rs.getString("reminder_frequency");
                File attachment = rs.getString("attachment") != null ? new File(rs.getString("attachment")) : null;
                String notes = rs.getString("notes");
                Boolean isRecurring = rs.getBoolean("is_recurring");
                String paymentStatus = rs.getString("payment_status");
                int overdueDays = rs.getInt("overdue_days");

                bill = new Bill(id, name, category, dueDate, amount, reminderFrequency, attachment, notes, isRecurring, paymentStatus, overdueDays, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bill;
    }
}

