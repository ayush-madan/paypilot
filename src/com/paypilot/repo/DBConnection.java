/**
 * The {@code DBConnection} class provides a utility method to establish a connection
 * to the Oracle database using credentials specified in a properties file.
 * 
 * <p>Business Logic:</p>
 * This class encapsulates the logic for connecting to the database, ensuring the 
 * proper JDBC driver is loaded and connection parameters are fetched securely.
 * 
 * <p>Author: Ayush Madan</p>
 * <p>Date: 20-08-2024</p>
 */

package com.paypilot.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
  * Establishes and returns a connection to the database.
  * 
  * <p>Business Logic:</p>
  * This method loads the Oracle JDBC driver and retrieves connection details 
  * from the properties file. It ensures that a valid connection is returned 
  * for interacting with the database.
  * 
  * @return A {@code Connection} object for interacting with the database.
  * @throws ClassNotFoundException If the JDBC driver class is not found.
  * @throws SQLException If a database access error occurs.
  */          
   
public class DBConnection {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {    
		
        // Load the resource bundle to retrieve the database configuration details
        ResourceBundle rb = ResourceBundle.getBundle("oracle");
		
        // Retrieve the database URL, username, and password from the properties file
	String url = rb.getString("db.url");
        String username = rb.getString("db.username");
        String password = rb.getString("db.password");
        Connection con = null;
		
        // Load the Oracle JDBC driver class
        Class.forName("oracle.jdbc.driver.OracleDriver");
		
	// Establish and return the connection to the database using the credentials
        con = DriverManager.getConnection(url, username, password);
        return con;
    }
}
