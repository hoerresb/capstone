package edu.gymtrack.view;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.border.EtchedBorder;

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
		setResizable(false);
		
		Factory f = new Factory();
		try {
			plans = f.getWorkoutPlansForUser(gym.loggedIn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dispose();
		}
		
		setLayout(new FlowLayout());
		
		JPanel plansPane = new JPanel(new GridLayout(0,1));
		JScrollPane scroll = new JScrollPane(plansPane);
		scroll.setPreferredSize(new Dimension(435,228));
		for(WorkoutPlan plan : plans){
			JLabel planLabel = new JLabel("Plan Created: " + plan.getDateCreated());
			plansPane.add(planLabel);
			
			JLabel goalLabel = new JLabel("Goal:");
			plansPane.add(goalLabel);
			
			JTextArea jGoalTxt = new JTextArea();
			jGoalTxt.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			jGoalTxt.setText(plan.getGoals());
			jGoalTexts.add(jGoalTxt);
			plansPane.add(jGoalTxt);
			
			JLabel fbLabel = new JLabel("Feedback:");
			plansPane.add(fbLabel);
			
			JTextArea jFBTxt = new JTextArea();
			jFBTxt.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			jFBTxt.setText(plan.getFeedback());
			if(editorType == User.UserType.CLIENT);
				jFBTxt.setEditable(false);
			jFBTexts.add(jFBTxt);
			plansPane.add(jFBTxt);
			
			plansPane.add(new JLabel()); // spacer
		} 
		add(scroll);
		//scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMinimum());
		
		JPanel controlPanel = new JPanel(new FlowLayout((FlowLayout.RIGHT)));
		controlPanel.setPreferredSize(new Dimension(443,30));
		
		gym.btnUpdate_Feedback = new JButton(new ImageIcon("images/dialog_update.png", "Update"));
		gym.btnUpdate_Feedback.setPreferredSize(new Dimension(70,25));
		gym.btnUpdate_Feedback.setRolloverIcon(new ImageIcon("images/dialog_update_over.png", "Update"));
		gym.btnUpdate_Feedback.addActionListener(gym);
		controlPanel.add(gym.btnUpdate_Feedback, null);
		
		gym.btnCancel_Feedback = new JButton(new ImageIcon("images/dialog_cancel.png", "Cancel"));
		gym.btnCancel_Feedback.setPreferredSize(new Dimension(70,25));
		gym.btnCancel_Feedback.setRolloverIcon(new ImageIcon("images/dialog_cancel_over.png", "Cancel"));
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
