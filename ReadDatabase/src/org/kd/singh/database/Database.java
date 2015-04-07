package org.kd.singh.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class Database {
	private String mUrl;
	private String mDatabaseName;
	private String mUserName;
	private String mPassword;
	
	private Connection mConnection;
	
	public Database() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
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
	
	public ArrayList<ColumnProperties> getColumnsInfo(String tableName) throws SQLException{
		ArrayList<ColumnProperties> list = new ArrayList<ColumnProperties>();
		PreparedStatement statement = (PreparedStatement) mConnection.prepareStatement("Select * from information_schema.COLUMNS where TABLE_NAME = ?  AND table_schema=?");
		statement.setString(1, tableName);
		statement.setString(2, mDatabaseName);
		
		if(statement.execute()){
			ResultSet rs = statement.getResultSet();
			while(rs.next()){
				ColumnProperties properties = new ColumnProperties();
				properties.setTableName(tableName);
				properties.setColumnName(rs.getString("column_name"));
				properties.setOrdinalPosition(rs.getString("ordinal_position"));
				properties.setColumnDefaultValue(rs.getString("column_default"));
				properties.setNullable(rs.getString("is_nullable").equalsIgnoreCase("yes")?true:false);
				properties.setDataType(rs.getString("data_type"));
				properties.setCharacterLength(rs.getString("character_maximum_length"));
				properties.setKey(rs.getString("column_key"));
				list.add(properties);
			}
		}
		return list;
	}

	public ArrayList<TableProperties> getTablesInfo() throws SQLException{
		ArrayList<TableProperties> list = new ArrayList<TableProperties>();
		PreparedStatement statement = (PreparedStatement) mConnection.prepareStatement("Select * from information_schema.tables where table_schema=?");
		statement.setString(1, mDatabaseName);
		
		if(statement.execute()){
			ResultSet rs = statement.getResultSet();
			while(rs.next()){
				TableProperties properties = new TableProperties();
				properties.setDatabaseName(mDatabaseName);
				properties.setTableName(rs.getString("table_name"));
				properties.setEngine(rs.getString("engine"));
				properties.setTableRows(rs.getInt("table_rows"));
				properties.setCreatedDate(rs.getDate("create_time"));
				properties.setUpdatedDate(rs.getDate("update_time"));
				properties.setTableCollation(rs.getString("table_collation"));
				properties.setTableComments(rs.getString("table_comment"));
				list.add(properties);
			}
		}
		return list;
	}

	
	
}
