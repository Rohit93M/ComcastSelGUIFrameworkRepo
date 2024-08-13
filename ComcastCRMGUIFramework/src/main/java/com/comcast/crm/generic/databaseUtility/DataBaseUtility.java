package com.comcast.crm.generic.databaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility {

	Connection conn; // conn object is declared globally so that in another method i can close the
						// connection

	public void getDbConnection(String url, String username, String password) throws SQLException {

		try { // what if connection doesn't happen, in such situations we should be able to
				// handle the exception
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {

		}
	}
	
	public void getDbConnection() throws SQLException {
		
		try { // what if connection doesn't happen, in such situations we should be able to
			// handle the exception
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/Projects", "root@%", "root");
			//directly connecting to d/b which is available for the project
		} catch (Exception e) {
			
		}
	}

	public void closeDbConnection() throws SQLException {
		try {
			conn.close();
		} catch (Exception e) {

		}
	}

	public ResultSet executeSelectQuery(String Query) throws SQLException {
		ResultSet result = null;
		try {
			Statement state = conn.createStatement();
			result = state.executeQuery(Query);
		} catch (Exception e) {

		}
		return result;
	}

	public int executeNonSelectQuery(String Query) throws SQLException {
		int result = 0;
		try {
			Statement state = conn.createStatement();
			result = state.executeUpdate(Query);
		} catch (Exception e) {

		}
		return result;
	}

}
