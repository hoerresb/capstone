package edu.gymtrack.view;

import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import edu.gymtrack.db.Factory;
import edu.gymtrack.model.Activity;
import edu.gymtrack.model.Equipment;
import edu.gymtrack.model.EquipmentType;
import edu.gymtrack.model.PlanElement;
import edu.gymtrack.model.User;
import edu.gymtrack.model.WorkoutLog;
import edu.gymtrack.model.WorkoutPlan;

public class ReportsDialog extends JDialog {
	private static final long serialVersionUID = -5216236364101250887L;
	
	private final JPanel contentPanel = new JPanel();
	
	public ReportsDialog(){
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Recent Feedback");
		setBounds(100, 100, 450, 300);
		setContentPane(contentPanel);
		setResizable(true);
		
		setLayout(new BoxLayout(contentPanel, DISPOSE_ON_CLOSE));
		JTextArea report = new JTextArea();
		report.setEditable(false);
		JScrollPane scroll = new JScrollPane(report);
		add(scroll);
		
		report.setText(getReport());
	}
	
	private String getReport(){
		Factory f = new Factory();
		StringBuilder sb = new StringBuilder();
		
		try{
			ArrayList<User> users = f.getUsers();
			for(User u : users){
				appendLine(sb, "User: " + u.toString());
				if(u.isClient()){
					ArrayList<WorkoutPlan> uPlans = f.getWorkoutPlansForUser(u);
					appendLine(sb, '\t' + "Plans{");
					for(WorkoutPlan uPlan : uPlans){
						appendLine(sb, "\t\t" + uPlan.toString());
						
						appendLine(sb, "\t\t" + "Elements{");
						ArrayList<PlanElement> elements = f.getPlanElements(uPlan);
						for(PlanElement e : elements){
							appendLine(sb, "\t\t\t" + e.toString());
						}
						appendLine(sb, "\t\t" + "}");
						
						appendLine(sb, "\t\t" + "Logs{");
						ArrayList<WorkoutLog> logs = f.getWorkoutLogs(u);
						for(WorkoutLog l : logs){
							appendLine(sb, "\t\t\t" + l.toString());
						}
						appendLine(sb, "\t\t" + "}");
					}
					appendLine(sb, '\t' + "}");
				}
			}
			
			ArrayList<Activity> activities = f.getActivities();
			appendLine(sb, "Activities{");
			for(Activity a : activities){
				appendLine(sb, '\t' + a.toString());
			}
			appendLine(sb, "}");
			
			ArrayList<EquipmentType> types = f.getEquipmentTypes();
			appendLine(sb, "Equipment Types{");
			for(EquipmentType type : types)
				appendLine(sb, '\t' + type.toString());
			appendLine(sb, '\t' + "}");
			
			ArrayList<Equipment> equipment = f.getEquipment();
			appendLine(sb, "Equipment{");
			for(Equipment e : equipment)
				appendLine(sb, '\t' + e.toString());
			appendLine(sb, '\t' + "}");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	private void appendLine(StringBuilder sb, String line){
		sb.append(line);
		sb.append("\n");
	}
}
