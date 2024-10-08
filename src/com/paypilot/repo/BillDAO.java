/**
 * The {@code BillDAO} class implements the {@code BillDAOInterface} interface.
 * It provides methods for performing CRUD operations on the {@code Bills} table in the database.
 * This includes adding, updating, deleting, and retrieving bills.
 * 
 * <p>Author: Anshul</p>
 * <p>Date: 20-08-2024</p>
 */
package com.paypilot.repo;

import com.paypilot.model.Bill;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO implements BillDAOInterface {

    /**
     * Adds a new bill to the database.
     * 
     * <p>Business Logic:</p>
     * This method inserts a new record into the {@code Bills} table. It ensures that a bill
     * with the specified attributes is added to the database. Proper handling of attachment and
     * due date is implemented to ensure correct data storage.
     * 
     * @param bill The {@code Bill} object to be added to the database.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver class is not found.
     */
    @Override
    public void addBill(Bill bill) {
        String sql = "INSERT INTO Bills (bill_id, bill_name, bill_category, due_date, amount, reminder_frequency, attachment, notes, is_recurring, payment_status, overdue_days, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, bill.getBillId());
            ps.setString(2, bill.getBillName());
            ps.setString(3, bill.getBillCategory());
            ps.setDate(4, new java.sql.Date(bill.getDueDate().getTime()));
            ps.setDouble(5, bill.getAmount());
            ps.setString(6, bill.getReminderFrequency());
            ps.setString(7, bill.getAttachment() != null ? bill.getAttachment().getPath() : null);
            ps.setString(8, bill.getNotes());
            ps.setBoolean(9, bill.isRecurring());
            ps.setString(10, bill.getPaymentStatus());
            ps.setInt(11, bill.getOverdueDays());
            ps.setString(12, null);
            // ps.setString(12, bill.getUserId()); // Uncomment and adjust if `Bill` has a `getUserId()` method

            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing bill in the database.
     * 
     * <p>Business Logic:</p>
     * This method updates an existing record in the {@code Bills} table. It modifies the bill
     * attributes as specified. This ensures that the latest information is reflected in the database,
     * including handling changes in the attachment and due date.
     * 
     * @param bill The {@code Bill} object containing updated information.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver class is not found.
     */
    @Override
    public void updateBill(Bill bill) {
        String sql = "UPDATE Bills SET bill_name = ?, bill_category = ?, due_date = ?, amount = ?, reminder_frequency = ?, attachment = ?, notes = ?, is_recurring = ?, payment_status = ?, overdue_days = ?, user_id = ? WHERE bill_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bill.getBillName());
            ps.setString(2, bill.getBillCategory());
            ps.setDate(3, new java.sql.Date(bill.getDueDate().getTime()));
            ps.setDouble(4, bill.getAmount());
            ps.setString(5, bill.getReminderFrequency());
            ps.setString(6, bill.getAttachment() != null ? bill.getAttachment().getPath() : null);
            ps.setString(7, bill.getNotes());
            ps.setBoolean(8, bill.isRecurring());
            ps.setString(9, bill.getPaymentStatus());
            ps.setInt(10, bill.getOverdueDays());
            ps.setString(11, null);
            // ps.setString(11, bill.getUserId()); // Uncomment and adjust if `Bill` has a `getUserId()` method
            ps.setInt(12, bill.getBillId());

            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a bill from the database based on its ID.
     * 
     * <p>Business Logic:</p>
     * This method removes a bill record from the {@code Bills} table using the provided bill ID.
     * It ensures that no orphan records are left in the database. The bill ID must be valid.
     * 
     * @param billId The ID of the bill to be deleted.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver class is not found.
     */
    @Override
    public void deleteBill(int billId) {
        String sql = "DELETE FROM Bills WHERE bill_id = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, billId);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a bill by its ID.
     * 
     * <p>Business Logic:</p>
     * This method fetches a bill record from the {@code Bills} table using the specified bill ID.
     * It ensures that the correct bill information is retrieved. If the bill is not found, it returns {@code null}.
     * 
     * @param billId The ID of the bill to retrieve.
     * @return The {@code Bill} object with the specified ID, or {@code null} if not found.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver class is not found.
     */
    @Override
    public Bill getBillById(int billId) {
        String sql = "SELECT * FROM Bills WHERE bill_id = ?";
        Bill bill = null;
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, billId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bill = new Bill(
                    rs.getInt("bill_id"),
                    rs.getString("bill_name"),
                    rs.getString("bill_category"),
                    rs.getDate("due_date"),
                    rs.getDouble("amount"),
                    rs.getString("reminder_frequency"),
                    rs.getBlob("attachment") != null ? new File(rs.getString("attachment")) : null,
                    rs.getString("notes"),
                    rs.getBoolean("is_recurring"),
                    rs.getString("payment_status"),
                    rs.getInt("overdue_days"),
                    null // Set this based on your `ReminderSettings` data, if applicable
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bill;
    }

    /**
     * Retrieves all bills from the database.
     * 
     * <p>Business Logic:</p>
     * This method retrieves all records from the {@code Bills} table. It returns a list of all bill
     * objects currently stored in the database. This is useful for displaying or processing all bills.
     * 
     * @return A list of all {@code Bill} objects.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver class is not found.
     */
    @Override
    public List<Bill> getAllBills() {
        String sql = "SELECT * FROM Bills";
        List<Bill> bills = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                bills.add(new Bill(
                    rs.getInt("bill_id"),
                    rs.getString("bill_name"),
                    rs.getString("bill_category"),
                    rs.getDate("due_date"),
                    rs.getDouble("amount"),
                    rs.getString("reminder_frequency"),
                    rs.getBlob("attachment") != null ? new File(rs.getString("attachment")) : null,
                    rs.getString("notes"),
                    rs.getBoolean("is_recurring"),
                    rs.getString("payment_status"),
                    rs.getInt("overdue_days"),
                    null // Set this based on your `ReminderSettings` data, if applicable
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bills;
    }

    /**
     * Retrieves bills by their category.
     * 
     * <p>Business Logic:</p>
     * This method fetches bill records from the {@code Bills} table based on the specified category.
     * It ensures that only bills matching the given category are returned. This is useful for filtering
     * bills by their category.
     * 
     * @param category The category to search for.
     * @return A list of {@code Bill} objects that match the specified category.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver class is not found.
     */
    @Override
    public List<Bill> getBillsByCategory(String category) {
        String sql = "SELECT * FROM Bills WHERE bill_category = ?";
        List<Bill> bills = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bills.add(new Bill(
                    rs.getInt("bill_id"),
                    rs.getString("bill_name"),
                    rs.getString("bill_category"),
                    rs.getDate("due_date"),
                    rs.getDouble("amount"),
                    rs.getString("reminder_frequency"),
                    rs.getBlob("attachment") != null ? new File(rs.getString("attachment")) : null,
                    rs.getString("notes"),
                    rs.getBoolean("is_recurring"),
                    rs.getString("payment_status"),
                    rs.getInt("overdue_days"),
                    null // Set this based on your `ReminderSettings` data, if applicable
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bills;
    }
}
