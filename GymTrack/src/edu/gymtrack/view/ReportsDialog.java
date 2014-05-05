package edu.gymtrack.view;

import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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

public class ReportsDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = -5216236364101250887L;
	
	private final JPanel contentPanel = new JPanel();
	private JButton btnPrint;
	private JButton btnSave;
	JTextArea report;
	
	public ReportsDialog(){
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Recent Feedback");
		setBounds(100, 100, 450, 300);
		setContentPane(contentPanel);
		setResizable(true);
		
		setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		report = new JTextArea();
		report.setEditable(false);
		JScrollPane scroll = new JScrollPane(report);
		add(scroll);
		
		JPanel ctlPanel = new JPanel();
		ctlPanel.setLayout(new FlowLayout());
		
		btnPrint = new JButton("Print");
		btnPrint.addActionListener(this);
		ctlPanel.add(btnPrint);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(this);
		ctlPanel.add(btnSave);
		
		add(ctlPanel);
		
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
							
							appendLine(sb, "\t\t\t" + "Logs{");
							ArrayList<WorkoutLog> logs = f.getWorkoutLogs(e.getKey());
							for(WorkoutLog l : logs){
								appendLine(sb, "\t\t\t\t" + l.toString());
							}
							appendLine(sb, "\t\t\t" + "}");
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnPrint){
			try {
				report.print();
			} catch (PrinterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(arg0.getSource() == btnSave){
			FileDialog fDialog = new FileDialog(this, "Save", FileDialog.SAVE);
	        fDialog.setVisible(true);
	        String path = fDialog.getDirectory() + fDialog.getFile();
	        File f = new File(path);
	        BufferedWriter writer;
            try {
            	writer = new BufferedWriter(new FileWriter(f));
				writer.write(report.getText());
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
