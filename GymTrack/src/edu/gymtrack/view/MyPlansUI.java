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
import edu.gymtrack.model.Activity;
import edu.gymtrack.model.DBMutable;
import edu.gymtrack.model.Equipment;
import edu.gymtrack.model.PlanElement;
import edu.gymtrack.model.User;
import edu.gymtrack.model.WorkoutLog;
import edu.gymtrack.model.WorkoutPlan;

public class MyPlansUI extends GTUI {
	private static final long serialVersionUID = 1L;
	
	final Factory factory = new Factory();
	GymTrack gym;
	JList<String> planList_MyPlans;
	
	ArrayList<WorkoutPlan> plans;
	ArrayList<PlanElement> elements;
	ArrayList<WorkoutLog> logs;
	
	String[] planTable_ColumnNames = {"Exercise", "Rep/Duration(mins)", "How Often"};
    Object[][] planTable_TableData = null;
    String[] worklogTable_ColumnNames = {"Logged on","exercise","reps/duration(mins)/distance(miles)", "% of plan complete"};
	Object[][] worklogTable_TableData = null;
	
	public void createMyPlansUI(GymTrack gym){
		this.gym = gym;
		
		JPanel contentPane = new JPanel();
		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(800,400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		gym.setContentPane(contentPane);
		//setTitle("My Plans");
		
		// List of plans at left
		
		planList_MyPlans = new JList<String>(getMyPlans(factory, gym));
        planList_MyPlans.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        planList_MyPlans.setSelectedIndex(0);
        planList_MyPlans.addListSelectionListener(new ListSelectionListener(){
        	public void valueChanged(ListSelectionEvent arg0) { // when new index is selected, pulls corresponding plan data and displays
        		if (!arg0.getValueIsAdjusting()) {
        			getPlanTableData(factory, plans.get(planList_MyPlans.getMinSelectionIndex()));
        			updatePlanDetailsTable();
        			System.out.println("Switch!");
        		}
        	}
        });
        
        if (planList_MyPlans.getModel().getSize() != 0) // if user has no plans, info is not retrieved
        {
        	getPlanTableData(factory, plans.get(planList_MyPlans.getMinSelectionIndex()));
        }
        
        // List of workout logs at bottom
		
		getWorklogTableData(factory, gym);
        
        JScrollPane leftScrollablePane = new JScrollPane(planList_MyPlans);
        
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout(0, 0));
        JPanel bottomContainer = new JPanel();
        rightPanel.add(bottomContainer, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                   leftScrollablePane, rightPanel);

        gym.btnBack_MyPlans = new JButton("Back");
        gym.btnBack_MyPlans.setFont(new Font("Calibri", Font.PLAIN, 13));
        gym.btnBack_MyPlans.setPreferredSize(new Dimension(90, 30));
        gym.btnBack_MyPlans.addActionListener(gym);
        gym.btnLogout = new JButton("Logout");
		gym.btnLogout.setFont(new Font("Calibri", Font.PLAIN, 13));
		gym.btnLogout.setPreferredSize(new Dimension(90, 30));
		gym.btnLogout.addActionListener(gym);
        gym.btnLogWork_MyPlans = new JButton("Log Work");
        gym.btnLogWork_MyPlans.addActionListener(gym);
        gym.btnLogWork_MyPlans.setPreferredSize(new Dimension(90, 30));
        gym.btnLogWork_MyPlans.setFont(new Font("Calibri", Font.PLAIN, 13));
        gym.btnSeeFeedback_MyPlans = new JButton("See Feedback");
        gym.btnSeeFeedback_MyPlans.addActionListener(gym);
        gym.btnSeeFeedback_MyPlans.setPreferredSize(new Dimension(90, 30));
        gym.btnSeeFeedback_MyPlans.setFont(new Font("Calibri", Font.PLAIN, 13));
        
        GroupLayout gl_bottomContainer = new GroupLayout(bottomContainer);
        gl_bottomContainer.setHorizontalGroup(
        	gl_bottomContainer.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_bottomContainer.createSequentialGroup()
        			.addComponent(gym.btnBack_MyPlans)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(gym.btnLogout)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(gym.btnLogWork_MyPlans)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(gym.btnSeeFeedback_MyPlans)
        			.addContainerGap(385, Short.MAX_VALUE))
        );
        gl_bottomContainer.setVerticalGroup(
        	gl_bottomContainer.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_bottomContainer.createSequentialGroup()
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGroup(gl_bottomContainer.createParallelGroup(Alignment.BASELINE)
        				.addComponent(gym.btnLogWork_MyPlans)
        				.addComponent(gym.btnSeeFeedback_MyPlans)
        				.addComponent(gym.btnBack_MyPlans)
        				.addComponent(gym.btnLogout)))
        );
        bottomContainer.setLayout(gl_bottomContainer);
        
        JPanel topContainer = new JPanel();
        JPanel planDetailsContainer = new JPanel();
        JPanel loggedWorkContainer = new JPanel();
        
        rightPanel.add(topContainer, BorderLayout.CENTER);

        GroupLayout gl_topContainer = new GroupLayout(topContainer);
        gl_topContainer.setHorizontalGroup(
        	gl_topContainer.createParallelGroup(Alignment.LEADING)
        		.addComponent(planDetailsContainer, GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        		.addComponent(loggedWorkContainer, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        );
        gl_topContainer.setVerticalGroup(
        	gl_topContainer.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_topContainer.createSequentialGroup()
        			.addComponent(planDetailsContainer, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(loggedWorkContainer, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
        );
        loggedWorkContainer.setLayout(new BorderLayout(0, 0));
        
        JPanel loggedWorkTitleContainer = new JPanel();
        loggedWorkContainer.add(loggedWorkTitleContainer, BorderLayout.NORTH);
        
        JLabel lblLoggedWork = new JLabel("Logged Work");
        GroupLayout gl_loggedWorkTitleContainer = new GroupLayout(loggedWorkTitleContainer);
        gl_loggedWorkTitleContainer.setHorizontalGroup(
        	gl_loggedWorkTitleContainer.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_loggedWorkTitleContainer.createSequentialGroup()
        			.addComponent(lblLoggedWork)
        			.addContainerGap(572, Short.MAX_VALUE))
        );
        gl_loggedWorkTitleContainer.setVerticalGroup(
        	gl_loggedWorkTitleContainer.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_loggedWorkTitleContainer.createSequentialGroup()
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addComponent(lblLoggedWork))
        );
        loggedWorkTitleContainer.setLayout(gl_loggedWorkTitleContainer);
        
        JScrollPane bottomScrollablePane = new JScrollPane();
        loggedWorkContainer.add(bottomScrollablePane, BorderLayout.CENTER);
        
        gym.loggedWorkTable_MyPlans = new JTable(worklogTable_TableData, worklogTable_ColumnNames);
        bottomScrollablePane.setViewportView(gym.loggedWorkTable_MyPlans);
        gym.loggedWorkTable_MyPlans.setFillsViewportHeight(true);
        planDetailsContainer.setLayout(new BorderLayout(0, 0));
        
        JPanel planDetailsTitleContainer = new JPanel();
        planDetailsContainer.add(planDetailsTitleContainer, BorderLayout.NORTH);
        
        JLabel lblPlanDetails = new JLabel("Plan Details");
        GroupLayout gl_planDetailsTitleContainer = new GroupLayout(planDetailsTitleContainer);
        gl_planDetailsTitleContainer.setHorizontalGroup(
        	gl_planDetailsTitleContainer.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_planDetailsTitleContainer.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblPlanDetails)
        			.addContainerGap(584, Short.MAX_VALUE))
        );
        gl_planDetailsTitleContainer.setVerticalGroup(
        	gl_planDetailsTitleContainer.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_planDetailsTitleContainer.createSequentialGroup()
        			.addComponent(lblPlanDetails)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        planDetailsTitleContainer.setLayout(gl_planDetailsTitleContainer);
        
        JScrollPane topScrollablePane = new JScrollPane();
        planDetailsContainer.add(topScrollablePane, BorderLayout.CENTER);
        
        gym.planDetailsTable_MyPlans = new JTable(planTable_TableData,planTable_ColumnNames);
        topScrollablePane.setViewportView(gym.planDetailsTable_MyPlans);
        gym.planDetailsTable_MyPlans.setFillsViewportHeight(true);
        topContainer.setLayout(gl_topContainer);

        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);
        Dimension minimumSize = new Dimension(100, 50);
        leftScrollablePane.setMinimumSize(minimumSize);
        splitPane.setPreferredSize(new Dimension(400, 200));
		
        contentPane.add(splitPane);
	}
	
	private void getPlanTableData(Factory factory, WorkoutPlan plan){
		// populates array with the PlanElements for the chosen WorkoutPlan.
		// TODO Figure out how the columns are meant to correspond to the model
		ArrayList<PlanElement> elements = new ArrayList<PlanElement>();
		try
		{
			elements = factory.getPlanElements(plan);
		} catch (SQLException e) {
			// TODO handle this
			e.printStackTrace();
		}  
		
		planTable_TableData = new Object[elements.size()][3];
		
		for (int i = 0; i < elements.size(); i++)
		{
			planTable_TableData[i][0] = elements.get(i).getActivityName();
			planTable_TableData[i][1] = elements.get(i).getNRequired();
			planTable_TableData[i][2] = null; // what's the third element? compare to schema
		}
		
	}
	
	//TODO implement
	private void getWorklogTableData(Factory factory, GymTrack gym){
		try
		{
			logs = factory.getWorkoutLogs(gym.loggedIn);
		} catch (SQLException e) {
			// TODO handle this
			e.printStackTrace();
		}   
		
		worklogTable_TableData = new Object[logs.size()][4];
		
		for (int i = 0; i < logs.size(); i++)
		{
			worklogTable_TableData[i][0] = logs.get(i).getDate();
			worklogTable_TableData[i][1] = logs.get(i).getElementKey();
			worklogTable_TableData[i][2] = logs.get(i).getNCompleted();
			worklogTable_TableData[i][3] = logs.get(i).getNCompleted();
		}
		
		/*Object[][] data = {
			{"1-15-2013","running", "1 mile", "5%"},
			{"1-25-2013","bench press", "20 reps", "15%"},
			{"1-30-2013","running", "2 mile", "25%"},
			{"2-05-2013","sit ups", "100", "30%"},
			{"2-20-2013","running", "1 mile", "50%"},
			{"2-25-2013","pull ups", "25", "65%"}
		};*/
	}
	

	private String[] getMyPlans(Factory factory, GymTrack gym){
		
		// populates list of plans; returns list of plan names used to create a JList
		// a given index of JList should correspond with the correct workoutPlan in the plans list
		
		plans = new ArrayList<WorkoutPlan>();
		try
		{
			plans = factory.getWorkoutPlansForUser(gym.loggedIn);
		} catch (SQLException e) {
			// TODO handle this
			e.printStackTrace();
		}  
		
		String[] myPlans = new String[plans.size()];
		
		for (int i = 0; i < plans.size(); i++)
		{
			myPlans[i] = plans.get(i).getDateCreated().toString();
		}
		
		return myPlans;
	}

	@Override
	public GTUI showUI(GymTrack gym) {
		createMyPlansUI(gym);
		return this;
	}
	
	public final void updatePlanDetailsTable() { // refresh UI with selected plan
		TableModel myData = new DefaultTableModel(planTable_TableData, planTable_ColumnNames);
		gym.planDetailsTable_MyPlans.setModel(myData);
		gym.planDetailsTable_MyPlans.updateUI();
	}
	
	// methods to create objects
	
	public final void newPlan(User client, User trainer, Date created, boolean isUserPlan, String goals, String feedback, int key)
	{
		plans.add(new WorkoutPlan(client, trainer, created, isUserPlan, goals, feedback, key, true));
		
		// update UI plans list
		String[] myPlans = new String[plans.size()];
		for (int i = 0; i < plans.size(); i++)
		{
			myPlans[i] = plans.get(i).getDateCreated().toString();
		}
		planList_MyPlans.setListData(myPlans);
		planList_MyPlans.updateUI();
	}
	
	public final void newElement(Activity activity, Equipment equipment, WorkoutPlan plan, int nRequired, int key)
	{
		elements.add(new PlanElement(activity, equipment, plan, nRequired, key, null, true));
		
		// update UI elements list
		for (int i = 0; i < elements.size(); i++)
		{
			planTable_TableData[i][0] = elements.get(i).getActivityName();
			planTable_TableData[i][1] = elements.get(i).getNRequired();
			planTable_TableData[i][2] = null; // what's the third element? compare to schema
		}
		TableModel myData = new DefaultTableModel(planTable_TableData, planTable_ColumnNames);
		gym.planDetailsTable_MyPlans.setModel(myData);
		gym.planDetailsTable_MyPlans.updateUI();
	}
	
	public final void newLog(int key, int elementKey, Date date, int completed)
	{
		logs.add(new WorkoutLog(key, elementKey, date, completed, true));
		
		// update UI logs list
		worklogTable_TableData = new Object[logs.size()][4];
		
		for (int i = 0; i < logs.size(); i++)
		{
			worklogTable_TableData[i][0] = logs.get(i).getDate();
			worklogTable_TableData[i][1] = logs.get(i).getElementKey();
			worklogTable_TableData[i][2] = logs.get(i).getNCompleted();
			worklogTable_TableData[i][3] = logs.get(i).getNCompleted();
		}
		TableModel myData = new DefaultTableModel(worklogTable_TableData, worklogTable_ColumnNames);
		gym.loggedWorkTable_MyPlans.setModel(myData);
		gym.loggedWorkTable_MyPlans.updateUI();
	}
	
	// methods to delete objects
	
	public final void delete(DBMutable object) {
		object.setDelete(true);
	}
	
	// methods to finalize changes
	
	public final void commitPlanChanges() throws SQLException {
		factory.updateWorkoutPlans(plans);
	}
	
	public final void commitElementChanges() throws SQLException {
		factory.updatePlanElements(elements);
	}
	
	public final void commitLogChanges() throws SQLException {
		factory.updateWorkoutLogs(logs);
	}
}
