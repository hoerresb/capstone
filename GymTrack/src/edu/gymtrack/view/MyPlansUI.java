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
	Map<Integer, PlanElement> elements = new HashMap<Integer, PlanElement>();  
	ArrayList<WorkoutLog> logs;
	Map<Integer, Activity> activities = new HashMap<Integer, Activity>();
	Map<Integer, Integer> elementRequirements = new HashMap<Integer, Integer>();
	Map<Integer, Integer> completion = new HashMap<Integer, Integer>();
	
	String[] planTable_ColumnNames = {"Exercise", "Equipment", "Amount", "Total % Complete"};
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
		
		getDatabaseData();  // call this before working on data
		
		// List of plans at left
		planList_MyPlans = new JList<String>(getMyPlans(factory, gym));
        planList_MyPlans.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        planList_MyPlans.setSelectedIndex(0);
        planList_MyPlans.addListSelectionListener(new ListSelectionListener(){
        	public void valueChanged(ListSelectionEvent arg0) { // when new index is selected, pulls corresponding plan data and displays
        		if (!arg0.getValueIsAdjusting()) {
        			getPlanTableData(plans.get(planList_MyPlans.getMinSelectionIndex()));
        			updatePlanDetailsTable();
        			System.out.println("Switch!");
        		}
        	}
        });
        
        if (planList_MyPlans.getModel().getSize() != 0) // if user has no plans, info is not retrieved
        {
        	getPlanTableData(plans.get(planList_MyPlans.getMinSelectionIndex()));
        }
        
        // List of workout logs at bottom
		
		worklogTable_TableData = getWorklogTableData();
        
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
        gym.btnSeeFeedback_MyPlans = new JButton("Goals and Feedback");
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
	
	private void getDatabaseData(){
		try
		{
			ArrayList<Activity> actList = factory.getActivities();
			for(Activity activity : actList){
				activities.put(activity.getKey(), activity);
			}
			
			plans = factory.getWorkoutPlansForUser(gym.loggedIn);
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
			
			logs = factory.getWorkoutLogs(gym.loggedIn);
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
	
	private void getPlanTableData(WorkoutPlan plan){
		// populates array with the PlanElements for the chosen WorkoutPlan.
		ArrayList<PlanElement> planElements = getPlanElements(plan);
		planTable_TableData = new Object[planElements.size()][4];
		for (int i = 0; i < planElements.size(); i++)
		{
			PlanElement planElement = planElements.get(i);
			if(plan.getKey() != planElement.getPlan().getKey())
				continue;
				
			planTable_TableData[i][0] = planElement.getActivityName();
			planTable_TableData[i][1] = planElement.getEquipment().getName();
			planTable_TableData[i][2] = planElement.getNRequired() + " " + activities.get(planElement.getActivity().getKey()).getUnit();
			planTable_TableData[i][3] = (double)completion.get(planElement.getKey()) / planElement.getNRequired() * 100;
		}
		
	}
	
	private ArrayList<PlanElement> getPlanElements(WorkoutPlan plan){
		ArrayList<PlanElement> result = new ArrayList<PlanElement>();
		for(PlanElement e : elements.values()){
			if(e.getPlan().getKey() == plan.getKey())
				result.add(e);
		}
		return result;
	}
	
	public Object[][] getWorklogTableData(){
		Object[][] result = new Object[logs.size()][4];
		for (int i = 0; i < logs.size(); i++)
		{
			int elementKey = logs.get(i).getElementKey();
			String unit = activities.get(elements.get(elementKey).getActivity().getKey()).getUnit();
			
			result[i][0] = logs.get(i).getDate();
			result[i][1] = logs.get(i).getExerciseName();
			result[i][2] = logs.get(i).getNCompleted() + " " + unit;
			
			int reqd = elementRequirements.get(elementKey);
			result[i][3] = (double)logs.get(i).getNCompleted() / reqd * 100;
		}
		
		return result;
	}
	

	private String[] getMyPlans(Factory factory, GymTrack gym){
		
		// populates list of plans; returns list of plan names used to create a JList
		// a given index of JList should correspond with the correct workoutPlan in the plans list
		
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
		for(key = 0; key <= Integer.MAX_VALUE && elements.containsKey(key); ++key);
		elements.put(key, new PlanElement(activity, equipment, plan, nRequired, key, null, true));
		
		try {
			commitElementChanges();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getDatabaseData();
		getPlanTableData(plans.get(planList_MyPlans.getMinSelectionIndex()));
		updatePlanDetailsTable();
		
//		// update UI elements list
//		for (int i = 0; i < elements.size(); i++)
//		{
//			planTable_TableData[i][0] = elements.get(i).getActivityName();
//			planTable_TableData[i][1] = elements.get(i).getNRequired();
//			planTable_TableData[i][2] = null; // what's the third element? compare to schema
//		}
//		TableModel myData = new DefaultTableModel(planTable_TableData, planTable_ColumnNames);
//		gym.planDetailsTable_MyPlans.setModel(myData);
//		gym.planDetailsTable_MyPlans.updateUI();
	}
	
	public final void newLog(int key, int elementKey, Date date, int completed)
	{
		logs.add(new WorkoutLog(key, elementKey, date, completed, null, true));
		
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
		factory.updatePlanElements(new ArrayList<PlanElement>(elements.values()));
	}
	
	public final void commitLogChanges() throws SQLException {
		factory.updateWorkoutLogs(logs);
	}
}
