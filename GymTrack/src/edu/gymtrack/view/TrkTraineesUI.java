package edu.gymtrack.view;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
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

public class TrkTraineesUI extends GTUI {
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

	public void createTrkTraineesUI(GymTrack gym){
		
		this.gym = gym;
		
		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(800,400);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		gym.setContentPane(contentPane);
		//setTitle("Track Trainees");

		// list of trainees
		
		String[] memberNames = getMemberNames(factory);
		traineesList_TrkTrainees = new JList<String>(memberNames);
		traineesList_TrkTrainees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		traineesList_TrkTrainees.setSelectedIndex(0);
		traineesList_TrkTrainees.addListSelectionListener(new ListSelectionListener(){
        	public void valueChanged(ListSelectionEvent arg0) { // when new index is selected, pulls corresponding user data and displays
        		if (!arg0.getValueIsAdjusting()) {
        			getPlanTableData(users.get(traineesList_TrkTrainees.getSelectedIndex()));
        			updatePlanTable();
        		}
        	}
        });
		
		// initial list of workout plans and logs
		
		if (traineesList_TrkTrainees.getModel().getSize() != 0)
		{
			getPlanTableData(users.get(traineesList_TrkTrainees.getSelectedIndex()));
			worklogTable_TableData = getWorklogTableData(users.get(traineesList_TrkTrainees.getSelectedIndex()));
		}
		else
		{
			planTable_TableData = new Object[0][5];
			worklogTable_TableData = new Object[0][4];
		}
		
        JScrollPane leftScrollablePane = new JScrollPane(traineesList_TrkTrainees);
        JPanel rightPanel = new JPanel();
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                   leftScrollablePane, rightPanel);
        rightPanel.setLayout(new BorderLayout(0, 0));
        
        JPanel bottomContainer = new JPanel();
        rightPanel.add(bottomContainer, BorderLayout.SOUTH);
        
        gym.btnBack_TrkTrainees = new JButton("Back");
        gym.btnBack_TrkTrainees.addActionListener(gym);
        gym.btnProvideFeedback_TrkTrainees = new JButton("Provide Feedback");
        gym.btnCreatNewPlan_TrkTrainees = new JButton("Create New Plan");
        gym.btnDeleteSelectedPlan_TrkTrainees = new JButton("Delete Selected Plan");
        
        GroupLayout gl_bottomContainer = new GroupLayout(bottomContainer);
        gl_bottomContainer.setHorizontalGroup(
        	gl_bottomContainer.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_bottomContainer.createSequentialGroup()
        			.addComponent(gym.btnBack_TrkTrainees)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(gym.btnProvideFeedback_TrkTrainees)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(gym.btnCreatNewPlan_TrkTrainees)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(gym.btnDeleteSelectedPlan_TrkTrainees)
        			.addContainerGap(236, Short.MAX_VALUE))
        );
        gl_bottomContainer.setVerticalGroup(
        	gl_bottomContainer.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_bottomContainer.createSequentialGroup()
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGroup(gl_bottomContainer.createParallelGroup(Alignment.BASELINE)
        				.addComponent(gym.btnBack_TrkTrainees)
        				.addComponent(gym.btnProvideFeedback_TrkTrainees)
        				.addComponent(gym.btnCreatNewPlan_TrkTrainees)
        				.addComponent(gym.btnDeleteSelectedPlan_TrkTrainees)))
        );
        bottomContainer.setLayout(gl_bottomContainer);
        
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
        
        gym.loggedProgressTable_TrkTrainees = new JTable(worklogTable_TableData, worklogTable_ColumnNames);
        bottomScrollablePane.setViewportView(gym.loggedProgressTable_TrkTrainees);
        gym.loggedProgressTable_TrkTrainees.setFillsViewportHeight(true);
        
        gym.planTable_TrkTrainees = new JTable(planTable_TableData, planTable_ColumnNames);
        topScrollablePane.setViewportView(gym.planTable_TrkTrainees);
        gym.planTable_TrkTrainees.setFillsViewportHeight(true);
        topContainer.setLayout(gl_topContainer);
        
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);
        Dimension minimumSize = new Dimension(100, 50);
        leftScrollablePane.setMinimumSize(minimumSize);
        splitPane.setPreferredSize(new Dimension(400, 200));
		
        contentPane.add(splitPane);	
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
	
	// methods to refresh UI elements
	
	public final void updatePlanTable() { // refresh UI with selected plan
		TableModel myData = new DefaultTableModel(planTable_TableData, planTable_ColumnNames);
		gym.planTable_TrkTrainees.setModel(myData);
		gym.planTable_TrkTrainees.updateUI();
	}

	@Override
	public GTUI showUI(GymTrack gym) {
		createTrkTraineesUI(gym);
		return this;
	}
}
