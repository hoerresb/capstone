package edu.gymtrack.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GTDB {
	public ResultSet getPlansForUser(String username) throws SQLException;
}
