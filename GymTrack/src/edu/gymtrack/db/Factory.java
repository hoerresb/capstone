package edu.gymtrack.db;

import java.sql.SQLException;
import java.util.Map;

import edu.gymtrack.controller.Authentication;

public class Factory {
	public static Authentication createAuthentication() throws SQLException{
		GTDB db = new GTMySQLDB();
		Map<String, String> pairs = db.getAuthPairs();
		return new Authentication(pairs);
	}
}
