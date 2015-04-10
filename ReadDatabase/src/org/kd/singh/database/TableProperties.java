package org.kd.singh.database;

import java.sql.Date;

public class TableProperties {
	private String databaseName;
	private String tableName;
	private String engine;
	private int tableRows;
	private Date createdDate;
	private Date updatedDate;
	private String tableCollation;
	private String tableComments;
	
	/**
	 * @return the databaseName
	 */
	public String getDatabaseName() {
		return databaseName;
	}
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @return the engine
	 */
	public String getEngine() {
		return engine;
	}
	/**
	 * @return the tableRows
	 */
	public Integer getTableRows() {
		return tableRows;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @return the tableCollation
	 */
	public String getTableCollation() {
		return tableCollation;
	}
	/**
	 * @return the tableComments
	 */
	public String getTableComments() {
		return tableComments;
	}
	/**
	 * @param databaseName the databaseName to set
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @param engine the engine to set
	 */
	public void setEngine(String engine) {
		this.engine = engine;
	}
	/**
	 * @param tableRows the tableRows to set
	 */
	public void setTableRows(int tableRows) {
		this.tableRows = tableRows;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	/**
	 * @param tableCollation the tableCollation to set
	 */
	public void setTableCollation(String tableCollation) {
		this.tableCollation = tableCollation;
	}
	/**
	 * @param tableComments the tableComments to set
	 */
	public void setTableComments(String tableComments) {
		this.tableComments = tableComments;
	}
	
}
