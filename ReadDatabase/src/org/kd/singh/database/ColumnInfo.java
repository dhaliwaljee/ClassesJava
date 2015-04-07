package org.kd.singh.database;

public class ColumnInfo {
	private String tableName;
	private String columnName;
	private String ordinalPosition;
	private String columnDefaultValue;
	private boolean nullable;
	private String dataType;
	private String characterLength;
	private String key;
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @return the ordinalPosition
	 */
	public String getOrdinalPosition() {
		return ordinalPosition;
	}
	/**
	 * @return the columnDefaultValue
	 */
	public String getColumnDefaultValue() {
		return columnDefaultValue;
	}
	/**
	 * @return the nullable
	 */
	public boolean isNullable() {
		return nullable;
	}
	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}
	/**
	 * @return the characterLength
	 */
	public String getCharacterLength() {
		return characterLength;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @param ordinalPosition the ordinalPosition to set
	 */
	public void setOrdinalPosition(String ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}
	/**
	 * @param columnDefaultValue the columnDefaultValue to set
	 */
	public void setColumnDefaultValue(String columnDefaultValue) {
		this.columnDefaultValue = columnDefaultValue;
	}
	/**
	 * @param nullable the nullable to set
	 */
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/**
	 * @param characterLength the characterLength to set
	 */
	public void setCharacterLength(String characterLength) {
		this.characterLength = characterLength;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
}
