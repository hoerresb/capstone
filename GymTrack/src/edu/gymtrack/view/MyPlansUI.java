package edu.gymtrack.view;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import edu.gymtrack.db.Factory;
import edu.gymtrack.model.PlanElement;
import edu.gymtrack.model.WorkoutLog;
import edu.gymtrack.model.WorkoutPlan;

public class MyPlansUI extends GTUI {
	private static final long serialVersionUID = 1L;
	ArrayList<WorkoutPlan> plans;
	
	public void createMyPlansUI(GymTrack gym){
		Factory factory = new Factory();
		
		
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
		
		JList<String> planList_MyPlans = new JList<String>(getMyPlans(factory, gym));
        planList_MyPlans.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        planList_MyPlans.setSelectedIndex(0);
        //TODO action listener - needs to populate window with info from workout plan that corresponds
        // to selected index
        
        String[] planTable_ColumnNames = {"Exercise", "Rep/Duration", "How Often"};
		Object[][] planTable_TableData = getPlanTableData(factory, plans.get(planList_MyPlans.getSelectedIndices()[0]));
		String[] worklogTable_ColumnNames = {"Logged on","exercise","reps/duration/distance", "% of plan complete"};
		Object[][] worklogTable_TableData = getWorklogTableData(factory, gym);
        
        JScrollPane leftScrollablePane = new JScrollPane(planList_MyPlans);
        
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout(0, 0));
        JPanel bottomContainer = new JPanel();
        rightPanel.add(bottomContainer, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                   leftScrollablePane, rightPanel);

        gym.btnBack_MyPlans = new JButton("Back");
        gym.btnBack_MyPlans.addActionListener(gym);
        gym.btnLogWork_MyPlans = new JButton("Log Work");
        gym.btnLogWork_MyPlans.addActionListener(gym);
        gym.btnSeeFeedback_MyPlans = new JButton("See Feedback");
        gym.btnSeeFeedback_MyPlans.addActionListener(gym);
        
        GroupLayout gl_bottomContainer = new GroupLayout(bottomContainer);
        gl_bottomContainer.setHorizontalGroup(
        	gl_bottomContainer.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_bottomContainer.createSequentialGroup()
        			.addComponent(gym.btnBack_MyPlans)
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
        				.addComponent(gym.btnBack_MyPlans)
        				.addComponent(gym.btnLogWork_MyPlans)
        				.addComponent(gym.btnSeeFeedback_MyPlans)))
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
	
	private Object[][] getPlanTableData(Factory factory, WorkoutPlan plan){
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
		
		Object[][] data = new Object[elements.size()][3];
		
		for (int i = 0; i < elements.size(); i++)
		{
			data[i][0] = elements.get(i).getActivityName();
			data[i][1] = elements.get(i).getNRequired();
			data[i][2] = null; // what's the third element? compare to schema
		}
		return data;
	}
	
	//TODO implement
	private Object[][] getWorklogTableData(Factory factory, GymTrack gym){
		ArrayList<WorkoutLog> logs = new ArrayList<WorkoutLog>();
		try
		{
			logs = factory.getWorkoutLogs(gym.loggedIn);
		} catch (SQLException e) {
			// TODO handle this
			e.printStackTrace();
		}   
		
		Object[][] data = new Object[logs.size()][4];
		
		for (int i = 0; i < logs.size(); i++)
		{
			// TODO populate array from log data
		}
		
		/*Object[][] data = {
			{"1-15-2013","running", "1 mile", "5%"},
			{"1-25-2013","bench press", "20 reps", "15%"},
			{"1-30-2013","running", "2 mile", "25%"},
			{"2-05-2013","sit ups", "100", "30%"},
			{"2-20-2013","running", "1 mile", "50%"},
			{"2-25-2013","pull ups", "25", "65%"}
		};*/
		return data;
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
}
