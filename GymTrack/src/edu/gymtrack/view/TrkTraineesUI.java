package edu.gymtrack.view;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import edu.gymtrack.db.Factory;
import edu.gymtrack.model.User;

public class TrkTraineesUI extends GTUI {
	private static final long serialVersionUID = 1L;

	public void createTrkTraineesUI(GymTrack gym){
		Factory factory = new Factory();
		
		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(800,400);

		String[] memberNames = getMemberNames(factory);

		String[] columnNames = {"Plan number", 
				"Created On", "Goal", 
				"Feedback", "Percentage complete"
		};

		//Filled with temporary data
		//TODO get actual data
		Object[][] tableData = {
				{"PlanOne", "1-15-2013",
					"Kill a bear", "good luck with that", "100%"},
					{"PlanTwo", "6-20-2013",
						"run a mile", "better get a bike", "100%"},
						{"PlanThree", "9-19-2013",
							"do a pull up", "here is a step stool", "100%"},
							{"PlanFour", "1-1-2014",
								"do a sit up", "elbows to the knees", "75%"},
								{"PlanFive", "4-5-2014",
									"touch my toes", "no no no, without bending your knees","1%"}
		};

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		gym.setContentPane(contentPane);

		gym.memberList = new JList(memberNames);
		gym.memberList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gym.memberList.setSelectedIndex(0);
		//list.addListSelectionListener(this);

		gym.trainerTable = new JTable(tableData, columnNames);
		gym.trainerTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
		gym.trainerTable.setFillsViewportHeight(true);

		JScrollPane leftPanel = new JScrollPane(gym.memberList);
		JScrollPane rightPanel = new JScrollPane(gym.trainerTable);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				leftPanel, rightPanel);

		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);
		Dimension minimumSize = new Dimension(100, 50);
		leftPanel.setMinimumSize(minimumSize);
		splitPane.setPreferredSize(new Dimension(400, 200));

		contentPane.add(splitPane);
		
	}//end createTrkTraineesUI()
	
	protected static String[] getMemberNames(Factory factory){
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
	}// end getUsers

	@Override
	public GTUI showUI(GymTrack gym) {
		createTrkTraineesUI(gym);
		return this;
	}
}
