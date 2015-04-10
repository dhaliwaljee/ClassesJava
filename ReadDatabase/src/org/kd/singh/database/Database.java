package org.kd.singh.database;
/**
 * @author Karandeep Singh Dhaliwal
 * 
 * This class is under development and not dynamically generate data, its only for test purpose, if you are going to use this class,
 * then must notify me about the changes or modification you did with it.
 *  
 */

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.kd.singh.user.types.TypeFile;
import org.kd.singh.user.types.TypeDatabase;

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
	
	public Database(DatabaseConfig config, TypeDatabase typeDatabase) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		this(config.getDatabaseName(), config.getUrl(), config.getUserName(), config.getPassword(), typeDatabase);
	}
	
	public Database(String databaseName, String url, String userName, String password, TypeDatabase typeDatabase) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		if(typeDatabase == TypeDatabase.MySQL){
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		mDatabaseName = databaseName;
		mUrl = url + mDatabaseName;
		mUserName = userName;
		mPassword = password;
		this.startConnection();
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

	/**
	 * Generate all database info and export as a file
	 * @param url document file url
	 * @param file type of file
	 * @throws COSVisitorException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void ExportAllDBInfo(String url, TypeFile file) throws COSVisitorException, IOException, SQLException{
		if(file==TypeFile.PDF){
			//Create PDF document
			PDDocument document = new PDDocument();
			
			//Create PDF Page
			PDPage page = new PDPage();
			PDPage page2 = new PDPage();
			PDPage aboutPage = new PDPage();
			
			//Create the text
			PDFont font = PDType1Font.TIMES_ROMAN;
			PDPageContentStream contentStream = new PDPageContentStream(document, page, true, true);
			PDPageContentStream contentStreamPage2 = new PDPageContentStream(document, page2, true, true);
			
			contentStream.beginText();
			contentStream.setFont(font, 12);
			contentStream.moveTextPositionByAmount(10, 750);
			contentStream.drawString("Table Information");
			contentStream.endText();
			
			// fetching data from database
			ArrayList<TableProperties> list1 = this.getTablesInfo();
			int totalRowsPerPage = 32;
			int totalRows = list1.size()+1;
			int totalRowsPage1 = totalRows;	// first page table rows
			int totalRowsPage2 = totalRows; // second page table rows
			
			//counting total rows to be displayed on page
			if(totalRows>totalRowsPerPage){
				totalRowsPage1 = totalRowsPerPage;
				totalRows -= totalRowsPerPage;
			}else{
				totalRows=0;
			}
			
			totalRowsPage2= (totalRows>totalRowsPerPage) ? totalRowsPerPage: totalRows+1;
				
			String[][] tableInfo_Page1 = new String[totalRowsPage1][];
			String[][] tableInfo_Page2 = new String[totalRowsPage2][];
			
			final String[] columnNames = new String[]{
					"Database Name",
					"Engine",
					"Table Name",
					"Total Rows",
					"Created On",
					"Updated On",
				};
			
			tableInfo_Page1[0] = columnNames;
			tableInfo_Page2[0] = columnNames;
			
			// fetching data for table columns
			for(int i=0, page1_rows=1, page2_rows=1;i<list1.size();i++){
				TableProperties col = list1.get(i);
				//System.out.println(i);
				if(i<=30){
					tableInfo_Page1[page1_rows++] = new String[]{
								col.getDatabaseName(),
								col.getEngine(),
								col.getTableName(),
								col.getTableRows().toString(),
								col.getCreatedDate().toString(),
								col.getUpdatedDate()!=null?col.getUpdatedDate().toString():""
							};
				}else{
					tableInfo_Page2[page2_rows++] = new String[]{
							col.getDatabaseName(),
							col.getEngine(),
							col.getTableName(),
							col.getTableRows().toString(),
							col.getCreatedDate().toString(),
							col.getUpdatedDate()!=null?col.getUpdatedDate().toString():""
						};
				}
			}
			
			// drawing a table and inserting data into table
			System.out.println(tableInfo_Page1==null);
			PDFMethods.drawTable(page, contentStream, 740, 5, tableInfo_Page1);
			if(tableInfo_Page2.length>1){
				PDFMethods.drawTable(page2, contentStreamPage2, 740, 5, tableInfo_Page2);
			}
			
			//closing content stream
			contentStream.close();
			contentStreamPage2.close();
			
			//adding pages to the document
			document.addPage(page);
			document.addPage(page2);
			
			//saving pdf
			document.save(url);
			
			//closing document
			document.close();
		}//pdf file generation if closed
	}
	
}
