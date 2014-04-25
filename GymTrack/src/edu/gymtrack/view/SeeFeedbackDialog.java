package edu.gymtrack.view;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import edu.gymtrack.db.Factory;
import edu.gymtrack.model.User;
import edu.gymtrack.model.WorkoutPlan;

public class SeeFeedbackDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ArrayList<WorkoutPlan> plans = null;
	private ArrayList<JTextArea> jGoalTexts = new ArrayList<JTextArea>();
	private ArrayList<JTextArea> jFBTexts = new ArrayList<JTextArea>();

	public SeeFeedbackDialog(GymTrack gym, User.UserType editorType){
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Recent Feedback");
		setBounds(100, 100, 450, 300);
		setContentPane(contentPanel);
		
		Factory f = new Factory();
		try {
			plans = f.getWorkoutPlansForUser(gym.loggedIn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dispose();
		}
		
		setLayout(new GridLayout(0, 1));
		
		for(WorkoutPlan plan : plans){
			JLabel planLabel = new JLabel("Plan Created: " + plan.getDateCreated());
			add(planLabel);
			
			JLabel goalLabel = new JLabel("Goal:");
			add(goalLabel);
			
			JTextArea jGoalTxt = new JTextArea();
			jGoalTxt.setText(plan.getGoals());
			jGoalTexts.add(jGoalTxt);
			add(jGoalTxt);
			
			JLabel fbLabel = new JLabel("Feedback:");
			add(fbLabel);
			
			JTextArea jFBTxt = new JTextArea();
			jFBTxt.setText(plan.getFeedback());
			if(editorType == User.UserType.CLIENT);
				jFBTxt.setEditable(false);
			jFBTexts.add(jFBTxt);
			add(jFBTxt);
			
			add(new JLabel()); // spacer
		}
		
		JPanel controlPanel = new JPanel(new GridLayout());
		
		gym.btnUpdate_Feedback = new JButton("Update");
		gym.btnUpdate_Feedback.addActionListener(gym);
		controlPanel.add(gym.btnUpdate_Feedback, null);
		
		gym.btnCancel_Feedback = new JButton("Cancel");
		gym.btnCancel_Feedback.addActionListener(gym);
		controlPanel.add(gym.btnCancel_Feedback, null);
		
		add(controlPanel);
	}
	
	public ArrayList<WorkoutPlan> getUpdatedPlans(){
		ArrayList<WorkoutPlan> results = new ArrayList<WorkoutPlan>();
		
		for(int i = 0; i < plans.size(); ++i){
			WorkoutPlan plan = plans.get(i);
			
			if(!plan.getFeedback().equals(jFBTexts.get(i).getText())){
				plan.setEdited(true);
				plan.setFeedback(jFBTexts.get(i).getText());
			}
			
			if(!plan.getGoals().equals(jGoalTexts.get(i).getText())){
				plan.setEdited(true);
				plan.setGoals(jGoalTexts.get(i).getText());
			}
			
			if(plan.isEdited())
				results.add(plan);
		}
		
		return results;
	}
}
