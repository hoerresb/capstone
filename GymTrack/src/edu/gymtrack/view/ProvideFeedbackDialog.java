package edu.gymtrack.view;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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

public class ProvideFeedbackDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ArrayList<WorkoutPlan> plans = null;
	private ArrayList<JTextArea> jFBTexts = new ArrayList<JTextArea>();
	private ArrayList<JTextArea> jGoalTexts = new ArrayList<JTextArea>();

	public ProvideFeedbackDialog(GymTrack gym, User.UserType editorType){
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Provide Feedback on Plans");
		setBounds(100, 100, 450, 300);
		setContentPane(contentPanel);
		setResizable(false);
		
		Factory f = new Factory();
		try {
			ArrayList<User> users = f.getUsers();
			plans = f.getWorkoutPlansForUser(users.get(gym.traineesList_TrkTrainees.getSelectedIndex()));
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
			jGoalTxt.setEditable(false);
			add(jGoalTxt);
			
			JLabel fbLabel = new JLabel("Feedback:");
			add(fbLabel);
			
			JTextArea jFBTxt = new JTextArea();
			jFBTxt.setText(plan.getFeedback());
			if(editorType == User.UserType.CLIENT);
			jFBTexts.add(jFBTxt);
			add(jFBTxt);
			
			add(new JLabel()); // spacer
		}
		
		JPanel controlPanel = new JPanel(new FlowLayout());
		controlPanel.setPreferredSize(new Dimension(150,30));
		
		gym.btnUpdate_PFeedback = new JButton(new ImageIcon("images/dialog_update.png", "Update"));
		gym.btnUpdate_PFeedback.setPreferredSize(new Dimension(70,25));
		gym.btnUpdate_PFeedback.setRolloverIcon(new ImageIcon("images/dialog_update_over.png", "Update"));
		gym.btnUpdate_PFeedback.addActionListener(gym);
		controlPanel.add(gym.btnUpdate_PFeedback, null);
		
		gym.btnCancel_PFeedback = new JButton(new ImageIcon("images/dialog_cancel.png", "Cancel"));
		gym.btnCancel_PFeedback.setPreferredSize(new Dimension(70,25));
		gym.btnCancel_PFeedback.setRolloverIcon(new ImageIcon("images/dialog_cancel_over.png", "Cancel"));
		gym.btnCancel_PFeedback.addActionListener(gym);
		controlPanel.add(gym.btnCancel_PFeedback, null);
		
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
