package org.kd.singh.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.kd.singh.database.*;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Database db = new Database();
			ArrayList<ColumnProperties> list = db.getColumnsInfo("userinfo");
			if(list.size()<=0){
				System.out.println("No such Table");
			}
			for(ColumnProperties col: list){
				System.out.println(col.getColumnName());
			}

			ArrayList<TableProperties> list1 = db.getTablesInfo();
			for(TableProperties col: list1){
				System.out.println(col.getTableName() + col.getCreatedDate());
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
