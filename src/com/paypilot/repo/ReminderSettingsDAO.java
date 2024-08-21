/**
 * The {@code ReminderSettingsDAO} class implements the {@code ReminderSettingsDAOInterface} interface
 * and provides methods for managing {@code ReminderSettings} entities in the data source.
 * It uses a {@code Connection} for database operations and an optional {@code BillDAO} for fetching {@code Bill} objects.
 * 
 * <p>Author: Anshul</p>
 * <p>Date: 20-08-2024</p>
 */
package com.paypilot.repo;

import com.paypilot.model.Bill;
import com.paypilot.model.ReminderSettings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReminderSettingsDAO implements ReminderSettingsDAOInterface {

    private Connection connection;
    private BillDAOInterface billDAO; // Instance of BillDAO for retrieving Bill objects
    
    /**
     * Default constructor.
     * 
     * <p>Business Logic:</p>
     * Initializes a new instance of {@code ReminderSettingsDAO}. The connection and billDAO 
     * must be set using setter methods or through constructors before using this instance.
     */
    public ReminderSettingsDAO() {
    }

    /**
     * Constructs a {@code ReminderSettingsDAO} with the specified {@code BillDAO}.
     * 
     * <p>Business Logic:</p>
     * Initializes a new instance of {@code ReminderSettingsDAO} with a {@code BillDAO} instance.
     * This allows the DAO to use the {@code BillDAO} for operations related to {@code Bill} objects.
     * 
     * @param billDAO The {@code BillDAO} instance used to fetch {@code Bill} objects.
     */
    public ReminderSettingsDAO(BillDAOInterface billDAO) {
        this.billDAO = billDAO;
    }

    /**
     * Constructs a {@code ReminderSettingsDAO} with the specified {@code Connection}.
     * 
     * <p>Business Logic:</p>
     * Initializes a new instance of {@code ReminderSettingsDAO} with a {@code Connection} object.
     * This allows the DAO to perform database operations using the provided connection.
     * 
     * @param connection The {@code Connection} object used for database operations.
     */
    public ReminderSettingsDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds a new {@code ReminderSettings} object to the data source.
     * 
     * <p>Business Logic:</p>
     * This method inserts a new reminder settings record into the {@code ReminderSettings} table.
     * It uses the provided {@code ReminderSettings} object to populate the table's columns and retrieves
     * the generated ID for the new record.
     * 
     * @param reminderSettings The {@code ReminderSettings} object to be added.
     */
    @Override
    public void addReminder(ReminderSettings reminderSettings) {
        String sql = "INSERT INTO ReminderSettings (reminderFrequency, reminderStartDate, customMessage, notificationPref, billId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, reminderSettings.getReminderFrequency());
            statement.setDate(2, new java.sql.Date(reminderSettings.getReminderStartDate().getTime()));
            statement.setString(3, reminderSettings.getCustomMessage());
            statement.setString(4, reminderSettings.getNotificationPref());
            statement.setInt(5, reminderSettings.getBill().getBillId());
            statement.executeUpdate();

            // Retrieve the generated key for the new reminder
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                reminderSettings.setReminderId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing {@code ReminderSettings} object in the data source.
     * 
     * <p>Business Logic:</p>
     * This method updates an existing reminder settings record in the {@code ReminderSettings} table
     * with new information provided in the {@code ReminderSettings} object. It uses the reminder ID
     * to locate the record to be updated.
     * 
     * @param reminderSettings The {@code ReminderSettings} object with updated information.
     */
    @Override
    public void updateReminder(ReminderSettings reminderSettings) {
        String sql = "UPDATE ReminderSettings SET reminderFrequency = ?, reminderStartDate = ?, customMessage = ?, notificationPref = ?, billId = ? WHERE reminderId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, reminderSettings.getReminderFrequency());
            statement.setDate(2, new java.sql.Date(reminderSettings.getReminderStartDate().getTime()));
            statement.setString(3, reminderSettings.getCustomMessage());
            statement.setString(4, reminderSettings.getNotificationPref());
            statement.setInt(5, reminderSettings.getBill().getBillId());
            statement.setInt(6, reminderSettings.getReminderId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a {@code ReminderSettings} object from the data source based on its ID.
     * 
     * <p>Business Logic:</p>
     * This method removes a reminder settings record from the {@code ReminderSettings} table using
     * the specified reminder ID. It ensures that the record with the given ID is deleted.
     * 
     * @param reminderId The ID of the {@code ReminderSettings} object to be deleted.
     */
    @Override
    public void deleteReminder(int reminderId) {
        String sql = "DELETE FROM ReminderSettings WHERE reminderId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reminderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a {@code ReminderSettings} object by its ID.
     * 
     * <p>Business Logic:</p>
     * This method fetches a reminder settings record from the {@code ReminderSettings} table using
     * the specified reminder ID. It returns an {@code Optional} containing the retrieved object or
     * an empty {@code Optional} if the record is not found.
     * 
     * @param reminderId The ID of the {@code ReminderSettings} object to retrieve.
     * @return An {@code Optional} containing the {@code ReminderSettings} object with the specified ID,
     *         or an empty {@code Optional} if not found.
     */
    @Override
    public Optional<ReminderSettings> getReminderById(int reminderId) {
        String sql = "SELECT * FROM ReminderSettings WHERE reminderId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reminderId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ReminderSettings reminderSettings = mapRowToReminderSettings(resultSet);
                return Optional.of(reminderSettings);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Retrieves all {@code ReminderSettings} objects from the data source.
     * 
     * <p>Business Logic:</p>
     * This method retrieves all reminder settings records from the {@code ReminderSettings} table.
     * It provides a complete list of all reminders currently stored in the database.
     * 
     * @return A {@code List} of all {@code ReminderSettings} objects.
     */
    @Override
    public List<ReminderSettings> getAllReminders() {
        List<ReminderSettings> reminders = new ArrayList<>();
        String sql = "SELECT * FROM ReminderSettings";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                reminders.add(mapRowToReminderSettings(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reminders;
    }

    /**
     * Retrieves a {@code ReminderSettings} object by the associated bill's ID.
     * 
     * <p>Business Logic:</p>
     * This method fetches a reminder settings record from the {@code ReminderSettings} table that
     * is associated with the specified bill ID. It returns an {@code Optional} containing the
     * retrieved object or an empty {@code Optional} if the record is not found.
     * 
     * @param billId The ID of the bill associated with the {@code ReminderSettings} to retrieve.
     * @return An {@code Optional} containing the {@code ReminderSettings} object associated with the specified bill ID,
     *         or an empty {@code Optional} if not found.
     */
    @Override
    public Optional<ReminderSettings> getReminderByBillId(int billId) {
        String sql = "SELECT * FROM ReminderSettings WHERE billId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, billId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ReminderSettings reminderSettings = mapRowToReminderSettings(resultSet);
                return Optional.of(reminderSettings);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Maps a {@code ResultSet} row to a {@code ReminderSettings} object.
     * 
     * <p>Business Logic:</p>
     * This method converts a row from the {@code ResultSet} into a {@code ReminderSettings} object.
     * It retrieves data from the result set and constructs a {@code ReminderSettings} object.
     * It also uses the {@code BillDAO} to fetch the associated {@code Bill} object based on the bill ID.
     * 
     * @param resultSet The {@code ResultSet} containing the data for the {@code ReminderSettings}.
     * @return A {@code ReminderSettings} object populated with data from the {@code ResultSet}.
     * @throws SQLException If an SQL error occurs while mapping the data.
     */
    private ReminderSettings mapRowToReminderSettings(ResultSet resultSet) throws SQLException {
        int reminderId = resultSet.getInt("reminderId");
        String reminderFrequency = resultSet.getString("reminderFrequency");
        Date reminderStartDate = resultSet.getDate("reminderStartDate");
        String customMessage = resultSet.getString("customMessage");
        String notificationPref = resultSet.getString("notificationPref");
        int billId = resultSet.getInt("billId");

        // Use the instance of BillDAO to get the Bill object
        Bill bill = billDAO.getBillById(billId);

        return new ReminderSettings(reminderId, reminderFrequency, reminderStartDate, customMessage, notificationPref, bill);
    }
}
