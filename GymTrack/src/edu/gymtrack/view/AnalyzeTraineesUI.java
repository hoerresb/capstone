package edu.gymtrack.view;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import edu.gymtrack.db.Factory;
import edu.gymtrack.model.PlanElement;
import edu.gymtrack.model.User;
import edu.gymtrack.model.WorkoutLog;
import edu.gymtrack.model.WorkoutPlan;

public class AnalyzeTraineesUI extends GTUI {
	private static final long serialVersionUID = 1L;
	
	final static Factory factory = new Factory();
	GymTrack gym;
	
	static ArrayList<User> users;
	static ArrayList<WorkoutPlan> plans;
	ArrayList<PlanElement> elements;
	static ArrayList<WorkoutLog> logs;
	
	JList<String> traineesList_TrkTrainees;
	
	String[] planTable_ColumnNames = {"Plan number", "Created On", "Goal", "Latest Feedback", "Percentage complete"};
	static Object[][] planTable_TableData = null;
	String[] worklogTable_ColumnNames = {"Logged on","exercise","reps/duration/distance", "% of plan complete"};
	Object[][] worklogTable_TableData = null;

	public void AnalyzeTraineesUI(GymTrack gym){
		
		this.gym = gym;
		
		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(800,400);
		gym.getContentPane().setLayout(new FlowLayout());
		
		JPanel contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(800,350));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		gym.getContentPane().add(contentPane);
		//setTitle("Track Trainees");

		// list of trainees
		
		String[] memberNames = getMemberNames(factory);
		gym.traineesList_TrkTrainees = new JList<String>(memberNames);
		gym.traineesList_TrkTrainees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gym.traineesList_TrkTrainees.setSelectedIndex(0);
		gym.traineesList_TrkTrainees.addListSelectionListener(new ListSelectionListener(){
        	public void valueChanged(ListSelectionEvent arg0) { // when new index is selected, pulls corresponding user data and displays
        		if (!arg0.getValueIsAdjusting()) {
        			refreshUserList();
        		}
        	}
        });
		
		// initial list of workout plans and logs
		
		if (gym.traineesList_TrkTrainees.getModel().getSize() != 0)
		{
			getPlanTableData(users.get(gym.traineesList_TrkTrainees.getSelectedIndex()));
			worklogTable_TableData = getWorklogTableData(users.get(gym.traineesList_TrkTrainees.getSelectedIndex()));
		}
		else
		{
			planTable_TableData = new Object[0][5];
			worklogTable_TableData = new Object[0][4];
		}
		
        JScrollPane leftScrollablePane = new JScrollPane(gym.traineesList_TrkTrainees);
        JPanel rightPanel = new JPanel();
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                   leftScrollablePane, rightPanel);
        rightPanel.setLayout(new BorderLayout(0, 0));
        rightPanel.setMinimumSize(new Dimension(500,50));
        
        JPanel topContainer = new JPanel();
        rightPanel.add(topContainer, BorderLayout.CENTER);
        
        JScrollPane topScrollablePane = new JScrollPane();
        
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);
        Dimension minimumSize = new Dimension(100, 50);
        leftScrollablePane.setMinimumSize(minimumSize);
        splitPane.setPreferredSize(new Dimension(400, 200));
		
        contentPane.add(splitPane);	
        
        gym.btnProvideFeedback_TrkTrainees = new JButton("Provide Feedback");
        gym.btnCreatNewPlan_TrkTrainees = new JButton("Create New Plan");
        gym.btnDeleteSelectedPlan_TrkTrainees = new JButton("Delete Selected Plan");
        
        JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		gym.getContentPane().add(bottomPanel);
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(530, 0));
		bottomPanel.add(separator);
		
		gym.btnBack_TrkTrainees = new JButton(new ImageIcon("images/back.png", "Back"));
		gym.btnBack_TrkTrainees.setPreferredSize(new Dimension(90, 30));
		gym.btnBack_TrkTrainees.setRolloverIcon(new ImageIcon("images/back_over.png", "Logout"));
		gym.btnBack_TrkTrainees.addActionListener(gym);
        bottomPanel.add(gym.btnBack_TrkTrainees);
        
        gym.btnLogout = new JButton(new ImageIcon("images/logout.png", "Logout"));
		gym.btnLogout.setPreferredSize(new Dimension(120, 30));
		gym.btnLogout.setRolloverIcon(new ImageIcon("images/logout_over.png", "Logout"));
		gym.btnLogout.addActionListener(gym);
		bottomPanel.add(gym.btnLogout);
        
	}
	
	//TODO calculate percentage completed
	private static Object[][] getWorklogTableData(User user){
		try {
			logs = factory.getWorkoutLogs(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object[][] data = new Object[logs.size()][4];
		for (int i = 0; i < logs.size(); i++)
		{
			data[i][0] = logs.get(i).getDate();
			data[i][1] = logs.get(i).getExerciseName();
			data[i][2] = logs.get(i).getNCompleted();
			data[i][3] = logs.get(i).getNCompleted(); // percentage
		}
		return data;
	}

	// TODO calculate completion rate on plans
	private static void getPlanTableData(User user){
		try {
			plans = factory.getWorkoutPlansForUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		planTable_TableData = new Object[plans.size()][5];
		for (int i = 0; i < plans.size(); i++)
		{
			planTable_TableData[i][0] = plans.get(i).getKey();
			planTable_TableData[i][1] = plans.get(i).getDateCreated();
			planTable_TableData[i][2] = plans.get(i).getGoals();
			planTable_TableData[i][3] = plans.get(i).getFeedback();
			planTable_TableData[i][4] = "Hi there.";
		}
		
	}
	private static String[] getMemberNames(Factory factory){
        users = new ArrayList<User>();
		try 
		{
			users = factory.getUsers();
		} catch (SQLException e) {
			// TODO handle this
			e.printStackTrace();
		}  
		String[] result = new String[users.size()];
		for (int i = 0; i < users.size(); i++) {
			result[i] = users.get(i).getUsername();
		}
		return result;
	}
	
	// method to add new workout plan or give feedback
	
	public final void newPlan(User client, User trainer, Date created, boolean isUserPlan, String goals, String feedback, int key)
	{
		plans.add(new WorkoutPlan(client, trainer, created, isUserPlan, goals, feedback, key, true));
		
		// update UI plans list
		planTable_TableData = new Object[plans.size()][5];
		for (int i = 0; i < plans.size(); i++)
		{
			// TODO calculation for completion percentage
			planTable_TableData[i][0] = plans.get(i).getKey();
			planTable_TableData[i][1] = plans.get(i).getDateCreated();
			planTable_TableData[i][2] = plans.get(i).getGoals();
			planTable_TableData[i][3] = plans.get(i).getFeedback();
			planTable_TableData[i][4] = "Hi there.";
		}
		updatePlanTable();
	}
	
	
	
	// methods to refresh UI elements
	
	public final void updatePlanTable() { // refresh UI with selected plan
		TableModel myData = new DefaultTableModel(planTable_TableData, planTable_ColumnNames);
		gym.planTable_TrkTrainees.setModel(myData);
		gym.planTable_TrkTrainees.updateUI();
	}
	
	public final void updateLogsTable() {
		TableModel myData = new DefaultTableModel(worklogTable_TableData, worklogTable_ColumnNames);
		gym.loggedProgressTable_TrkTrainees.setModel(myData);
		gym.loggedProgressTable_TrkTrainees.updateUI();
	}
	
	public void refreshUserList() {
		getPlanTableData(users.get(gym.traineesList_TrkTrainees.getSelectedIndex()));
		updatePlanTable();
		worklogTable_TableData = getWorklogTableData(users.get(gym.traineesList_TrkTrainees.getSelectedIndex()));
		updateLogsTable();
	}

	@Override
	public GTUI showUI(GymTrack gym) {
		AnalyzeTraineesUI(gym);
		return this;
	}
}
