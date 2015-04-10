package org.kd.singh.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.kd.singh.database.*;
import org.kd.singh.user.types.TypeFile;
import org.kd.singh.user.types.TypeDatabase;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DatabaseConfig config = new DatabaseConfig("jdbc:mysql://localhost/", "information_schema", "root", "123456");
			Database db = new Database(config, TypeDatabase.MySQL);
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
			db.ExportAllDBInfo("b:/a.pdf", TypeFile.PDF);
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (COSVisitorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
