package com.paypilot.repo;

import java.io.File;
import java.sql.*;
import java.util.*;
import java.util.Date;

import com.paypilot.model.Bill;
import com.paypilot.model.BillManagerDAOInterface;



public class BillManagerDAO implements BillManagerDAOInterface{
	
	public List<Bill> getAllBills(){
		
		List<Bill> b = new ArrayList<>();
		
		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM BILLS";
		Bill bill = null;
		try {
			con = DBConnection.getConnection();
			smt = con.createStatement();
			rs = smt.executeQuery(query);
			
			while (rs.next()) {
				String id = rs.getString(1);
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
//				String userId = rs.getString(12);
				
				bill = new Bill(Integer.parseInt(id), name, category, dueDate, amount, reminderFrequency, attachment, notes, isRecurring, paymentStatus, overdueDays, null);
				b.add(bill);
			}
		}
		catch(Exception e)
		{e.printStackTrace();}
		finally {
			 try {
			        if (rs != null) {
			            rs.close();
			        }
			        if (smt != null) {
			            smt.close();
			        }
			        if (con != null) {
			            con.close();
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
		}
		return b;
	}
	
	public void addBill(Bill bill) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    String query = "INSERT INTO BILLS (bill_name, bill_category, due_date, amount, reminder_frequency, attachment, notes, is_recurring, payment_status, overdue_days, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
//	        pstmt.setString(11, bill.getUserId());
	        pstmt.setString(11, null);

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
//	            String userId = rs.getString("user_id");

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
