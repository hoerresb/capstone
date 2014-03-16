package edu.gymtrack.test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import edu.gymtrack.db.GTDB;
import edu.gymtrack.db.GTMySQLDB;

public class DBTest {

	public static void main(String[] args) {
		GTDB db = new GTMySQLDB();
		try {
			ResultSet results = db.getPlansForUser("xXxAlicexXx");
			ResultSetMetaData rsmd = results.getMetaData();
			int nColumns = rsmd.getColumnCount();
			
			while(results.next()){
				for(int i = 1; i <= nColumns; ++i){
					Object field = results.getObject(i);
					if(field == null)
						System.out.println("null");
					else
						System.out.println(field.toString());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
