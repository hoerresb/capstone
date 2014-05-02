package edu.gymtrack.view;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import edu.gymtrack.db.Factory;
import edu.gymtrack.model.Equipment;
import edu.gymtrack.model.User;
import edu.gymtrack.model.User.UserType;
import edu.gymtrack.model.WorkoutPlan;

@SuppressWarnings("serial")
public class EditTraineesUI extends GTUI {
	private final String[] columnNames = {"Username", "Type", "My Trainee"};
	
	private JScrollPane scrollablePane;
	
	private ArrayList<User> trainees;
	private ArrayList<Boolean> userMine;
	
	public void createEditTraineesUI(GymTrack gym){
		Factory factory = new Factory();
		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(800,400);

		JPanel contentPane = new JPanel();
		gym.setContentPane(contentPane);
		GridLayout gridLayout = new GridLayout(2,0);
		contentPane.setLayout(gridLayout);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		Object[][] tableData = getTableData(factory, gym);

		JPanel topContainer = new JPanel();
		contentPane.add(topContainer, BorderLayout.NORTH);

		gym.btnAdd_EditTrainees = new JButton(new ImageIcon("images/add.png", "Add"));
		gym.btnAdd_EditTrainees.setPreferredSize(new Dimension(90, 30));
		gym.btnAdd_EditTrainees.setRolloverIcon(new ImageIcon("images/add_over.png", "Add"));
		gym.btnAdd_EditTrainees.addActionListener(gym);
		topContainer.add(gym.btnAdd_EditTrainees);

		gym.btnEdit_EditTrainees = new JButton(new ImageIcon("images/edit.png", "Edit"));
		gym.btnEdit_EditTrainees.setPreferredSize(new Dimension(90, 30));
		gym.btnEdit_EditTrainees.setRolloverIcon(new ImageIcon("images/edit_over.png", "Edit"));
		gym.btnEdit_EditTrainees.addActionListener(gym);
		topContainer.add(gym.btnEdit_EditTrainees);

		gym.btnDelete_EditTrainees = new JButton(new ImageIcon("images/delete.png", "Delete"));
		gym.btnDelete_EditTrainees.setPreferredSize(new Dimension(90, 30));
		gym.btnDelete_EditTrainees.setRolloverIcon(new ImageIcon("images/delete_over.png", "Delete"));
		gym.btnDelete_EditTrainees.addActionListener(gym);
		topContainer.add(gym.btnDelete_EditTrainees);

		JPanel bottomContainer = new JPanel();
		contentPane.add(bottomContainer, BorderLayout.CENTER);
		bottomContainer.setLayout(new BorderLayout(0, 0));

		JPanel bottomPanel = new JPanel();
		bottomContainer.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new FlowLayout());

		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(530, 0));
		bottomPanel.add(separator);
		
		gym.btnBack_EditTrainees = new JButton(new ImageIcon("images/back.png", "Back"));
		gym.btnBack_EditTrainees.setPreferredSize(new Dimension(90, 30));
		gym.btnBack_EditTrainees.setRolloverIcon(new ImageIcon("images/back_over.png", "Back"));
		gym.btnBack_EditTrainees.addActionListener(gym);
		bottomPanel.add(gym.btnBack_EditTrainees);
		
		gym.btnLogout = new JButton(new ImageIcon("images/logout.png", "Logout"));
		gym.btnLogout.setPreferredSize(new Dimension(120, 30));
		gym.btnLogout.setRolloverIcon(new ImageIcon("images/logout_over.png", "Logout"));
		gym.btnLogout.addActionListener(gym);
		bottomPanel.add(gym.btnLogout);

		scrollablePane = new JScrollPane();
		bottomContainer.add(scrollablePane, BorderLayout.CENTER);

		gym.traineesTable_EditTrainees = new JTable(tableData, columnNames) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		gym.traineesTable_EditTrainees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gym.traineesTable_EditTrainees.setFillsViewportHeight(true);
		scrollablePane.setViewportView(gym.traineesTable_EditTrainees);
	}

	protected  Object[][] getTableData(Factory factory, GymTrack gym){
		gym.largetId = 0;
        ArrayList<User> currentUserSet = new ArrayList<>();
		try 
		{
			currentUserSet = factory.getUsers();
		} catch (SQLException e) {
			// TODO handle this
			e.printStackTrace();
		}  
		
		trainees = new ArrayList<User>();
		for (User u : currentUserSet) {
			if(u.getUserType() == UserType.CLIENT)
				trainees.add(u);
			
			if(u.getID() > gym.largetId){
				gym.largetId = u.getID();
			}
		}
		
		ArrayList<WorkoutPlan> userPlans = new ArrayList<WorkoutPlan>();
		try {
			userPlans = (new Factory()).getWorkoutPlans();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		userMine = new ArrayList<Boolean>();
		Object[][] tableData = new Object[trainees.size()][3];
		for(int i = 0; i < trainees.size(); ++i){
			User u = trainees.get(i);
			
			tableData[i][0] = u.getUsername();
			tableData[i][1] = u.getUserType().toString();
			tableData[i][2] = u.getTrainerID() == gym.loggedIn.getID();
		}
		
		return tableData;
	}// end getTableData
	
	public void refreshTable(GymTrack gym){
		gym.traineesTable_EditTrainees = new JTable(getTableData(new Factory(), gym), columnNames) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		scrollablePane.setViewportView(gym.traineesTable_EditTrainees);
		gym.traineesTable_EditTrainees.updateUI();
	}
	
	public User getSelectedTrainee(GymTrack gym){
		int row = gym.traineesTable_EditTrainees.getSelectedRow();
		
		if(row < 0)
			return null;
		
		return trainees.get(row);
	}
	
	public boolean selectedIsMine(GymTrack gym){
		int row = gym.traineesTable_EditTrainees.getSelectedRow();
		
		if(row < 0)
			return false;
		
		return userMine.get(row);
	}
	

	@Override
	public GTUI showUI(GymTrack gym) {
		createEditTraineesUI(gym);
		return this;
	}
}