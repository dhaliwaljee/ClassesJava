package org.kd.singh.database;

public class DatabaseConfig {
	private String url;
	private String databaseName;
	private String userName;
	private String password;
	
	public DatabaseConfig() {
		// TODO Auto-generated constructor stub
	}
	public DatabaseConfig(String url, String databaseName, String userName,
			String password) {
		super();
		this.url = url;
		this.databaseName = databaseName;
		this.userName = userName;
		this.password = password;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @return the databaseName
	 */
	public String getDatabaseName() {
		return databaseName;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @param databaseName the databaseName to set
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}	
}
