package edu.gymtrack.view;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import edu.gymtrack.db.Factory;
import edu.gymtrack.model.User;

public class UsersUI extends GTUI {
	private final long serialVersionUID = 1L;

	public void createUsersUI(GymTrack gym){
		Factory factory = new Factory();
		ButtonGroup usersUI_buttonGroup = new ButtonGroup();

		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(800,400);
		
		String[] columnNames = {"Name", "Type", "ID"};
		Object[][] tableData = this.getTableData(factory, gym);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		gym.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel topContainer = new JPanel();
		contentPane.add(topContainer, BorderLayout.NORTH);

		gym.rdbtnTrainers_users = new JRadioButton("Trainers");
		usersUI_buttonGroup.add(gym.rdbtnTrainers_users);
		topContainer.add(gym.rdbtnTrainers_users);

		gym.rdbtnMembers_users = new JRadioButton("Members");
		usersUI_buttonGroup.add(gym.rdbtnMembers_users);
		topContainer.add(gym.rdbtnMembers_users);

		gym.rdbtnAll_users = new JRadioButton("All");
		usersUI_buttonGroup.add(gym.rdbtnAll_users);
		topContainer.add(gym.rdbtnAll_users);

		Component horizontalStrut = Box.createHorizontalStrut(60);
		topContainer.add(horizontalStrut);

		gym.btnAdd_users = new JButton("Add");
		gym.btnAdd_users.addActionListener(gym);
		topContainer.add(gym.btnAdd_users);

		gym.btnEdit_users = new JButton("Edit");
		topContainer.add(gym.btnEdit_users);

		gym.btnDelete_users = new JButton("Delete");
		topContainer.add(gym.btnDelete_users);
		
		JPanel bottomContainer = new JPanel();
		contentPane.add(bottomContainer, BorderLayout.CENTER);
		bottomContainer.setLayout(new BorderLayout(0, 0));
		
		JPanel bottomPanel = new JPanel();
		bottomContainer.add(bottomPanel, BorderLayout.SOUTH);
		
		gym.btnBack_users = new JButton("Back");
		gym.btnBack_users.addActionListener(gym);
		GroupLayout gl_bottomPanel = new GroupLayout(bottomPanel);
		gl_bottomPanel.setHorizontalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottomPanel.createSequentialGroup()
					.addComponent(gym.btnBack_users, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(538, Short.MAX_VALUE))
		);
		gl_bottomPanel.setVerticalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_bottomPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(gym.btnBack_users, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
		);
		bottomPanel.setLayout(gl_bottomPanel);
		
		JScrollPane scrollablePane = new JScrollPane();
		bottomContainer.add(scrollablePane, BorderLayout.CENTER);
		
		gym.usersTable_users = new JTable(tableData, columnNames);
		gym.usersTable_users.setFillsViewportHeight(true);
		scrollablePane.setViewportView(gym.usersTable_users);
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
		Object[][] tableData = new Object[currentUserSet.size()][3];
		
		for (int i = 0; i < currentUserSet.size(); i++) {
			tableData[i][0] = currentUserSet.get(i).getUsername();
			tableData[i][1] = currentUserSet.get(i).getUserType().toString();
			tableData[i][2] = currentUserSet.get(i).getID();
			if(currentUserSet.get(i).getID() > gym.largetId){
				gym.largetId = currentUserSet.get(i).getID();
			}
		}
		
		return tableData;
	}// end getTableData

	@Override
	public GTUI showUI(GymTrack gym) {
		createUsersUI(gym);
		return this;
	}
}
