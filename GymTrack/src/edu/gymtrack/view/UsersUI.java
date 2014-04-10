package edu.gymtrack.view;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import edu.gymtrack.db.Factory;
import edu.gymtrack.model.User;

public class UsersUI extends GymTrack {
	private static final long serialVersionUID = 1L;


	public static void createUsersUI(GymTrack gym){
		Factory factory = new Factory();
		
		ButtonGroup usersUI_buttonGroup = new ButtonGroup();

		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(800,400);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		gym.setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		gym.rdbtnTrainers = new JRadioButton("Trainers");
		usersUI_buttonGroup.add(gym.rdbtnTrainers);
		panel.add(gym.rdbtnTrainers);

		gym.rdbtnMembers = new JRadioButton("Members");
		usersUI_buttonGroup.add(gym.rdbtnMembers);
		panel.add(gym.rdbtnMembers);

		gym.rdbtnAll = new JRadioButton("All");
		usersUI_buttonGroup.add(gym.rdbtnAll);
		panel.add(gym.rdbtnAll);

		Component horizontalStrut = Box.createHorizontalStrut(60);
		panel.add(horizontalStrut);

		gym.btnAddUser = new JButton("Add");
		panel.add(gym.btnAddUser);

		gym.btnEditUser = new JButton("Edit");
		panel.add(gym.btnEditUser);

		gym.btnDeleteUser = new JButton("Delete");
		panel.add(gym.btnDeleteUser);

		String[] columnNames = {"Username", "Type", "Id"};

		gym.ownerTable = new JTable(getTableData(factory), columnNames);
		gym.ownerTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
		gym.ownerTable.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(gym.ownerTable);
		gym.add(scrollPane);
	}
	
	protected static Object[][] getTableData(Factory factory){
        ArrayList<User> currentUserSet = new ArrayList<>();
		try 
		{
			currentUserSet = factory.getUsers();
		} catch (SQLException e) {
			// TODO handle this
			e.printStackTrace();
		}  
		Object[][] tableData = new Object[currentUserSet.size()][3];
		
		for (int i = 0; i < currentUserSet.size(); i++) {
			tableData[i][0] = currentUserSet.get(i).getUsername();
			tableData[i][1] = currentUserSet.get(i).getUserType().toString();
			tableData[i][2] = currentUserSet.get(i).getID();
		}
		
		return tableData;
	}// end getTableData
}
