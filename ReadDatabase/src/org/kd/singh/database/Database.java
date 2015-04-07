package org.kd.singh.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private String mUrl;
	private String mDatabaseName;
	private String mUserName;
	private String mPassword;
	
	private Connection mConnection;
	
	public Database() throws SQLException {
		initilaize();
		startConnection();
	}
	
	private void initilaize(){
		mDatabaseName = "test";
		mUrl = "jdbc:mysql://localhost/" + mDatabaseName;
		mUserName = "root";
		mPassword = "123456";
	}
	
	private void startConnection() throws SQLException{
		mConnection = DriverManager.getConnection(mUrl, mUserName, mPassword);
	}
	
	
}
