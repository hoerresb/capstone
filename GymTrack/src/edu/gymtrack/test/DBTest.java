package edu.gymtrack.test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.gymtrack.db.Factory;
import edu.gymtrack.db.GTDB;
import edu.gymtrack.db.GTMySQLDB;
import edu.gymtrack.model.User;
import edu.gymtrack.model.WorkoutPlan;

public class DBTest {

	public static void main(String[] args) {
		try {
			Factory f = new Factory();
			ArrayList<User> users = f.getUsers();
			for(User u : users){
				System.out.println("User: " + u.toString());
				if(u.isClient()){
					ArrayList<WorkoutPlan> uPlans = f.getWorkoutPlansForUser(u); // plans for chubbs not fetched
					System.out.println('\t' + "Plans{");
					for(WorkoutPlan uPlan : uPlans)
						System.out.println("\t\t" + uPlan.toString());
					System.out.println('\t' + "}");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
