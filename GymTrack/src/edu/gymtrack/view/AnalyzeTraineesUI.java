package edu.gymtrack.view;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class AnalyzeTraineesUI extends GTUI {
	private static final long serialVersionUID = 1L;

	static ArrayList<User> users;
	
	JList<String> traineesList_TrkTrainees;
	
	final Factory factory = new Factory();
	
	public void createAnalyzeTraineesUI(GymTrack gym){
		
		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(800,400);
		gym.getContentPane().setLayout(new FlowLayout());
		
		
		final XYDataset dataset = createDataset(factory, gym); 
		final JFreeChart chart = ChartFactory.createTimeSeriesChart(
				gym.loggedIn.getUsername() + "'s Workout History", 
				"Date",
				"Work",
				dataset);
		
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(600,350));
		chartPanel.setMinimumSize(new Dimension(600,350));
		JPanel contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(800,350));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		gym.getContentPane().add(contentPane);

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
		
        JScrollPane leftScrollablePane = new JScrollPane(gym.traineesList_TrkTrainees);
        JPanel rightPanel = new JPanel();
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                   leftScrollablePane, rightPanel);
        rightPanel.setLayout(new BorderLayout(0, 0));
        rightPanel.setMinimumSize(new Dimension(600,50));
        
        rightPanel.add(chartPanel, BorderLayout.CENTER);
        
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
	
	private XYDataset createDataset(Factory factory, GymTrack gym){
		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		
		try {
			ArrayList<WorkoutPlan> plans = factory.getWorkoutPlansForUser(gym.loggedIn);
			
		for (WorkoutPlan currentPlan: plans)
		{
			ArrayList<PlanElement> elements = factory.getPlanElements(currentPlan);
			for (PlanElement currentElement: elements)
			{
				ArrayList<WorkoutLog> logs = factory.getWorkoutLogs(currentElement.getKey());
				if (!logs.isEmpty()){
					final TimeSeries series = new TimeSeries(currentElement.getActivityName());
					for (WorkoutLog currentLog: logs)
					{
						series.add(new Second(currentLog.getDate()), currentLog.getNCompleted());
					}
					dataset.addSeries(series);
				}
			}
		}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataset;
	}
	
	// methods to refresh UI elements
	public void refreshUserList() {
				
	}

	@Override
	public GTUI showUI(GymTrack gym) {
		createAnalyzeTraineesUI(gym);
		return this;
	}
}
