package edu.gymtrack.view;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import edu.gymtrack.db.Factory;
import edu.gymtrack.model.Activity;
import edu.gymtrack.model.PlanElement;
import edu.gymtrack.model.User;
import edu.gymtrack.model.WorkoutLog;
import edu.gymtrack.model.WorkoutPlan;

public class TrkTraineesUI extends GTUI {
	private static final long serialVersionUID = 1L;
	
	final static Factory factory = new Factory();
	GymTrack gym;
	
	//static ArrayList<User> users;
	static ArrayList<WorkoutPlan> plans;
	Map<Integer, PlanElement> elements = new HashMap<Integer, PlanElement>();  
	static ArrayList<WorkoutLog> logs;
	Map<Integer, Activity> activities = new HashMap<Integer, Activity>();
	Map<Integer, Integer> elementRequirements = new HashMap<Integer, Integer>();
	Map<Integer, Integer> completion = new HashMap<Integer, Integer>();
	User selectedUser;
	ArrayList<User> userList = new ArrayList<User>();
	
	JList<String> traineesList_TrkTrainees;
	
	String[] planTable_ColumnNames = {"Plan number", "Created On", "Goal", "Latest Feedback", "Percentage complete"};
	static Object[][] planTable_TableData = null;
	String[] worklogTable_ColumnNames = {"Logged on","exercise","reps/duration/distance", "% of plan complete"};
	Object[][] worklogTable_TableData = null;

	public void createTrkTraineesUI(GymTrack gym){
		this.gym = gym;
		try {
			userList = factory.getUsers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			selectedUser = userList.get(gym.traineesList_TrkTrainees.getSelectedIndex());
			getDatabaseData(selectedUser);
			getPlanTableData(selectedUser);
			worklogTable_TableData = getWorklogTableData(selectedUser);
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
        
        JScrollPane bottomScrollablePane = new JScrollPane();
        GroupLayout gl_topContainer = new GroupLayout(topContainer);
        gl_topContainer.setHorizontalGroup(
        	gl_topContainer.createParallelGroup(Alignment.LEADING)
        		.addComponent(topScrollablePane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        		.addComponent(bottomScrollablePane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        );
        gl_topContainer.setVerticalGroup(
        	gl_topContainer.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_topContainer.createSequentialGroup()
        			.addComponent(topScrollablePane, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(bottomScrollablePane, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
        );
        
        gym.loggedProgressTable_TrkTrainees = new JTable(worklogTable_TableData, worklogTable_ColumnNames) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
        bottomScrollablePane.setViewportView(gym.loggedProgressTable_TrkTrainees);
        gym.loggedProgressTable_TrkTrainees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gym.loggedProgressTable_TrkTrainees.setFillsViewportHeight(true);
        
        gym.planTable_TrkTrainees = new JTable(planTable_TableData, planTable_ColumnNames) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
        topScrollablePane.setViewportView(gym.planTable_TrkTrainees);
        gym.planTable_TrkTrainees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gym.planTable_TrkTrainees.setFillsViewportHeight(true);
        topContainer.setLayout(gl_topContainer);
        
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
		
		gym.btnProvideFeedback_TrkTrainees = new JButton(new ImageIcon("images/providefeedback.png", "Provide Feedback"));
		gym.btnProvideFeedback_TrkTrainees.setPreferredSize(new Dimension(190, 30));
		gym.btnProvideFeedback_TrkTrainees.setRolloverIcon(new ImageIcon("images/providefeedback_over.png", "Provide Feedback"));
		gym.btnProvideFeedback_TrkTrainees.addActionListener(gym);
        bottomPanel.add(gym.btnProvideFeedback_TrkTrainees);
        
        gym.btnCreatNewPlan_TrkTrainees = new JButton(new ImageIcon("images/createnewplan.png", "Create New Plan"));
        gym.btnCreatNewPlan_TrkTrainees.setPreferredSize(new Dimension(180, 30));
        gym.btnCreatNewPlan_TrkTrainees.setRolloverIcon(new ImageIcon("images/createnewplan_over.png", "Create New Plan"));
        gym.btnCreatNewPlan_TrkTrainees.addActionListener(gym);
        bottomPanel.add(gym.btnCreatNewPlan_TrkTrainees);
        
        gym.btnAddExercises_TrkTrainees = new JButton("Add Exercises");
        gym.btnAddExercises_TrkTrainees.setPreferredSize(new Dimension(180, 30));
        gym.btnAddExercises_TrkTrainees.addActionListener(gym);
        bottomPanel.add(gym.btnAddExercises_TrkTrainees);
        
        gym.btnDeleteSelectedPlan_TrkTrainees = new JButton(new ImageIcon("images/deleteselected.png", "Delete Selected"));
        gym.btnDeleteSelectedPlan_TrkTrainees.setPreferredSize(new Dimension(180, 30));
        gym.btnDeleteSelectedPlan_TrkTrainees.setRolloverIcon(new ImageIcon("images/deleteselected_over.png", "Delete Selected"));
        gym.btnDeleteSelectedPlan_TrkTrainees.addActionListener(gym);
        bottomPanel.add(gym.btnDeleteSelectedPlan_TrkTrainees);
        
        /*JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(180, 0));
		bottomPanel.add(separator);*/
		
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
	
	private Object[][] getWorklogTableData(User user){
//		try {
//			logs = factory.getWorkoutLogs(user);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Object[][] data = new Object[logs.size()][4];
		for (int i = 0; i < logs.size(); i++)
		{
			WorkoutLog workoutLog = logs.get(i);
			data[i][0] = workoutLog.getDate();
			data[i][1] = workoutLog.getExerciseName();
			data[i][2] = workoutLog.getNCompleted();
			data[i][3] = (double)workoutLog.getNCompleted() / elementRequirements.get(workoutLog.getElementKey()) * 100;
		}
		return data;
	}

	// TODO calculate completion rate on plans
	private void getPlanTableData(User user){
//		try {
//			plans = factory.getWorkoutPlansForUser(user);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		planTable_TableData = new Object[plans.size()][5];
		for (int i = 0; i < plans.size(); i++)
		{
			WorkoutPlan workoutPlan = plans.get(i);
			planTable_TableData[i][0] = workoutPlan.getKey();
			planTable_TableData[i][1] = workoutPlan.getDateCreated();
			planTable_TableData[i][2] = workoutPlan.getGoals();
			planTable_TableData[i][3] = workoutPlan.getFeedback();
			
			int nAssociatedElements = 0;
			double percentageAccum = 0;
			for(PlanElement e : elements.values()){
				if(e.getPlan().getKey() == workoutPlan.getKey() && completion.containsKey(e.getKey())){
					++nAssociatedElements;
					double pctComplete = (double)completion.get(e.getKey()) / e.getNRequired() * 100;
					if(pctComplete > 100)
						pctComplete = 100;
					percentageAccum += pctComplete;
				}
			}
			if(nAssociatedElements == 0)
				nAssociatedElements = 1;
			planTable_TableData[i][4] = percentageAccum / nAssociatedElements;
		}
		
	}
	
	private void getDatabaseData(User user){
		try
		{
			ArrayList<Activity> actList = factory.getActivities();
			for(Activity activity : actList){
				activities.put(activity.getKey(), activity);
			}
			
			plans = factory.getWorkoutPlansForUser(user);
			for(WorkoutPlan plan : plans){
				ArrayList<PlanElement> planElements = factory.getPlanElements(plan);
				if(planElements == null)
					continue;
				
				for(PlanElement element : planElements)
					elements.put(element.getKey(), element);
				
				for(PlanElement element : elements.values()){
					elementRequirements.put(element.getKey(), element.getNRequired());
				}
			}
			
			logs = factory.getWorkoutLogs(user);
			for(WorkoutLog log : logs){
				int elementKey = log.getElementKey();
				Integer nLogged = completion.get(elementKey);
				completion.put(elementKey, 
						nLogged == null ? log.getNCompleted() : nLogged + log.getNCompleted());
			}
		} catch (SQLException e) {
			// TODO handle this
			e.printStackTrace();
		}
	}
	
	
	private  String[] getMemberNames(Factory factory){
		userList = new ArrayList<User>();
		try 
		{
			userList = factory.getUsers();
		} catch (SQLException e) {
			// TODO handle this
			e.printStackTrace();
		}  
		String[] result = new String[userList.size()];
		for (int i = 0; i < userList.size(); i++) {
			result[i] = userList.get(i).getUsername();
		}
		return result;
	}
	
	// method to add new workout plan or give feedback
	
//	public final void newPlan(User client, User trainer, Date created, boolean isUserPlan, String goals, String feedback, int key)
//	{
//		plans.add(new WorkoutPlan(client, trainer, created, isUserPlan, goals, feedback, key, true));
//		
//		// update UI plans list
//		planTable_TableData = new Object[plans.size()][5];
//		for (int i = 0; i < plans.size(); i++)
//		{
//			// TODO calculation for completion percentage
//			planTable_TableData[i][0] = plans.get(i).getKey();
//			planTable_TableData[i][1] = plans.get(i).getDateCreated();
//			planTable_TableData[i][2] = plans.get(i).getGoals();
//			planTable_TableData[i][3] = plans.get(i).getFeedback();
//			planTable_TableData[i][4] = "Hi there.";
//		}
//		updatePlanTable();
//	}
	
	
	
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
		selectedUser = userList.get(gym.traineesList_TrkTrainees.getSelectedIndex());
		getDatabaseData(selectedUser);
		getPlanTableData(selectedUser);
		updatePlanTable();
		worklogTable_TableData = getWorklogTableData(selectedUser);
		updateLogsTable();
	}
	
	public User getSelectedUser(){
		return selectedUser;
	}
	
	public WorkoutPlan getSelectedWorkoutPlan(){
		if(plans.isEmpty())
			return null;
		
		int selectedRow = gym.planTable_TrkTrainees.getSelectedRow();
		if(selectedRow < 0)
			return null;
		
		return plans.get(selectedRow);
	}

	@Override
	public GTUI showUI(GymTrack gym) {
		createTrkTraineesUI(gym);
		return this;
	}
}
