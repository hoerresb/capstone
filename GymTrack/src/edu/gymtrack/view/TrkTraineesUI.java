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
import edu.gymtrack.model.User;
import edu.gymtrack.model.WorkoutLog;
import edu.gymtrack.model.WorkoutPlan;

public class TrkTraineesUI extends GTUI {
	private static final long serialVersionUID = 1L;
	
	final Factory factory = new Factory();
	GymTrack gym;
	
	ArrayList<WorkoutPlan> plans;
	ArrayList<PlanElement> elements;
	ArrayList<WorkoutLog> logs;
	
	String[] planTable_ColumnNames = {"Plan number", "Created On", "Goal", "Latest Feedback", "Percentage complete"};
	Object[][] planTable_TableData = null;
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

		String[] memberNames = getMemberNames(factory);
		gym.traineesList_TrkTrainees = new JList<String>(memberNames);
		gym.traineesList_TrkTrainees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gym.traineesList_TrkTrainees.setSelectedIndex(0);

        
		
        JScrollPane leftScrollablePane = new JScrollPane(gym.traineesList_TrkTrainees);
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
	
	//TODO implement
	private static Object[][] getWorklogTableData(){
		Object[][] data = {
				{"1-15-2013","running", "1 mile", "5%"},
				{"1-25-2013","bench press", "20 reps", "15%"},
				{"1-30-2013","running", "2 mile", "25%"},
				{"2-05-2013","sit ups", "100", "30%"},
				{"2-20-2013","running", "1 mile", "50%"},
				{"2-25-2013","pull ups", "25", "65%"}
		};
		return data;
	}

	//TODO implement
	private static Object[][] getPlanTableData(){
		Object[][] data = {
			{"PlanOne", "1-15-2013","Kill a bear", "good luck with that", "100%"},
			{"PlanTwo", "6-20-2013","run a mile", "better get a bike", "100%"},
			{"PlanThree", "9-19-2013","do a pull up", "here is a step stool", "100%"},
			{"PlanFour", "1-1-2014","do a sit up", "elbows to the knees", "75%"},
			{"PlanFive", "4-5-2014","touch my toes", "no no no, without bending your knees","1%"}
		};
		return data;
	}
	private static String[] getMemberNames(Factory factory){
        ArrayList<User> currentUserSet = new ArrayList<>();
		try 
		{
			currentUserSet = factory.getUsers();
		} catch (SQLException e) {
			// TODO handle this
			e.printStackTrace();
		}  
		String[] result = new String[currentUserSet.size()];
		for (int i = 0; i < currentUserSet.size(); i++) {
			result[i] = currentUserSet.get(i).getUsername();
		}
		return result;
	}

	@Override
	public GTUI showUI(GymTrack gym) {
		createTrkTraineesUI(gym);
		return this;
	}
}
