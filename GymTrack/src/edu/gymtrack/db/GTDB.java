package edu.gymtrack.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface GTDB {
	
	public Map<String, String> getAuthPairs() throws SQLException;
	
	public ResultSet getPlansForUser(String username) throws SQLException;
	
	public ResultSet getUsers() throws SQLException;
	
	public ResultSet getActivities() throws SQLException;
}
