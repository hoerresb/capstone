package edu.gymtrack.test;

import java.net.Authenticator;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.gymtrack.controller.Authentication;
import edu.gymtrack.db.Factory;
import edu.gymtrack.model.Activity;
import edu.gymtrack.model.Equipment;
import edu.gymtrack.model.EquipmentType;
import edu.gymtrack.model.PlanElement;
import edu.gymtrack.model.User;
import edu.gymtrack.model.WorkoutLog;
import edu.gymtrack.model.WorkoutPlan;
import edu.gymtrack.model.User.UserType;

public class DBTest {

	public static void main(String[] args) {
		try {
			Factory f = new Factory();
			
//			Authentication auth = Factory.createAuthentication();
//			String pw = auth.getHashForPassword("test");
			
//			Authentication auth = f.createAuthentication();
//			auth.addUser("client", "client");
//			auth.addUser("trainer", "trainer");
//			auth.addUser("owner", "owner");
//			
//			ArrayList<User> newData = new ArrayList<>();
//			User user = new User("client", UserType.CLIENT, 0, true);
//			newData.add(user);
//			user = new User("trainer", UserType.TRAINER, 0, true);
//			newData.add(user);
//			user = new User("owner", UserType.OWNER, 0, true);
//			newData.add(user);
//			f.updateUsers(newData, auth);
			
			ArrayList<User> users = f.getUsers();
			for(User u : users){
				System.out.println("User: " + u.toString());
				if(u.isClient()){
					ArrayList<WorkoutPlan> uPlans = f.getWorkoutPlansForUser(u);
					System.out.println('\t' + "Plans{");
					for(WorkoutPlan uPlan : uPlans){
						System.out.println("\t\t" + uPlan.toString());
						
						System.out.println("\t\t" + "Elements{");
						ArrayList<PlanElement> elements = f.getPlanElements(uPlan);
						for(PlanElement e : elements){
							System.out.println("\t\t\t" + e.toString());
						}
						System.out.println("\t\t" + "}");
						
						System.out.println("\t\t" + "Logs{");
						ArrayList<WorkoutLog> logs = f.getWorkoutLogs(u);
						for(WorkoutLog l : logs){
							System.out.println("\t\t\t" + l.toString());
						}
						System.out.println("\t\t" + "}");
					}
					System.out.println('\t' + "}");
				}
			}
			
			ArrayList<Activity> activities = f.getActivities();
			System.out.println("Activities{");
			for(Activity a : activities){
				System.out.println('\t' + a.toString());
			}
			System.out.println("}");
			
			ArrayList<EquipmentType> types = f.getEquipmentTypes();
			System.out.println("Equipment Types{");
			for(EquipmentType type : types)
				System.out.println('\t' + type.toString());
			System.out.println('\t' + "}");
			
			ArrayList<Equipment> equipment = f.getEquipment();
			System.out.println("Equipment{");
			for(Equipment e : equipment)
				System.out.println('\t' + e.toString());
			System.out.println('\t' + "}");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
