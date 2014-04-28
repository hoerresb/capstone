package edu.gymtrack.view;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import edu.gymtrack.db.Factory;
import edu.gymtrack.model.User;

@SuppressWarnings("serial")
public class UsersUI extends GTUI {
	
	private String[] columnNames = {"Username", "Type", "ID"};
	private JScrollPane scrollablePane;
	private ArrayList<User> currentUserSet = null;

	public void createUsersUI(GymTrack gym){
		Factory factory = new Factory();
		ButtonGroup usersUI_buttonGroup = new ButtonGroup();

		gym.getContentPane().removeAll();
		gym.getContentPane().revalidate();
		gym.getContentPane().repaint();
		gym.setSize(800,400);
		
		Object[][] tableData = this.getTableData(factory, gym, null);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		gym.setContentPane(contentPane);

		JPanel topContainer = new JPanel();
		contentPane.add(topContainer, BorderLayout.NORTH);

		gym.rdbtnTrainers_users = new JRadioButton("Trainers");
		usersUI_buttonGroup.add(gym.rdbtnTrainers_users);
		topContainer.add(gym.rdbtnTrainers_users);
		gym.rdbtnTrainers_users.addActionListener(gym);

		gym.rdbtnMembers_users = new JRadioButton("Members");
		usersUI_buttonGroup.add(gym.rdbtnMembers_users);
		topContainer.add(gym.rdbtnMembers_users);
		gym.rdbtnMembers_users.addActionListener(gym);

		gym.rdbtnAll_users = new JRadioButton("All");
		usersUI_buttonGroup.add(gym.rdbtnAll_users);
		topContainer.add(gym.rdbtnAll_users);
		gym.rdbtnAll_users.addActionListener(gym);
		gym.rdbtnAll_users.setSelected(true);

		Component horizontalStrut = Box.createHorizontalStrut(60);
		topContainer.add(horizontalStrut);

		gym.btnAdd_users = new JButton(new ImageIcon("images/add.png", "Add"));
		gym.btnAdd_users.setPreferredSize(new Dimension(90, 30));
		gym.btnAdd_users.setRolloverIcon(new ImageIcon("images/add_over.png", "Add"));
		gym.btnAdd_users.addActionListener(gym);
		topContainer.add(gym.btnAdd_users);

		gym.btnEdit_users = new JButton(new ImageIcon("images/edit.png", "Edit"));
		gym.btnEdit_users.setPreferredSize(new Dimension(90, 30));
		gym.btnEdit_users.setRolloverIcon(new ImageIcon("images/edit_over.png", "Edit"));
		gym.btnEdit_users.addActionListener(gym);
		topContainer.add(gym.btnEdit_users);

		gym.btnDelete_users = new JButton(new ImageIcon("images/delete.png", "Delete"));
		gym.btnDelete_users.setPreferredSize(new Dimension(90, 30));
		gym.btnDelete_users.setRolloverIcon(new ImageIcon("images/delete_over.png", "Delete"));
		gym.btnDelete_users.addActionListener(gym);
		topContainer.add(gym.btnDelete_users);
		
		JPanel bottomContainer = new JPanel();
		contentPane.add(bottomContainer, BorderLayout.CENTER);
		bottomContainer.setLayout(new BorderLayout(0, 0));
		
		JPanel bottomPanel = new JPanel();
		bottomContainer.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new FlowLayout());

		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(530, 0));
		bottomPanel.add(separator);
		
		gym.btnBack_users = new JButton(new ImageIcon("images/back.png", "Back"));
		gym.btnBack_users.setPreferredSize(new Dimension(90, 30));
		gym.btnBack_users.setRolloverIcon(new ImageIcon("images/back_over.png", "Back"));
		gym.btnBack_users.addActionListener(gym);
		bottomPanel.add(gym.btnBack_users);
		
		gym.btnLogout = new JButton(new ImageIcon("images/logout.png", "Logout"));
		gym.btnLogout.setPreferredSize(new Dimension(120, 30));
		gym.btnLogout.setRolloverIcon(new ImageIcon("images/logout_over.png", "Logout"));
		gym.btnLogout.addActionListener(gym);
		bottomPanel.add(gym.btnLogout);
		
		/*JPanel bottomPanel = new JPanel();
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
		bottomPanel.setLayout(gl_bottomPanel);*/
		
		scrollablePane = new JScrollPane();
		bottomContainer.add(scrollablePane, BorderLayout.CENTER);
		
		gym.usersTable_users = new JTable(tableData, columnNames) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		gym.usersTable_users.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gym.usersTable_users.setFillsViewportHeight(true);
		scrollablePane.setViewportView(gym.usersTable_users);
	}
	
	protected  Object[][] getTableData(Factory factory, GymTrack gym, User.UserType filterType){
		gym.largetId = 0;
        currentUserSet = new ArrayList<>();
		try 
		{
			currentUserSet = factory.getUsers();
		} catch (SQLException e) {
			// TODO handle this
			e.printStackTrace();
		} 
		
		for (int i = 0; filterType != null && i < currentUserSet.size(); i++) {
			if(currentUserSet.get(i).getUserType() != filterType){
				currentUserSet.remove(i);
				--i;
			}
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
	
	public void filterTableData(User.UserType filterType, GymTrack gym){
		Factory factory = new Factory();
		Object[][] tableData = this.getTableData(factory, gym, filterType);
		gym.usersTable_users = new JTable(tableData, columnNames);
		scrollablePane.setViewportView(gym.usersTable_users);
		gym.usersTable_users.repaint();
	}

	public User getSelectedUser(GymTrack gym){
		int selected = gym.usersTable_users.getSelectedRow();
		System.out.println(selected + ": " + currentUserSet.get(selected));
		if(selected < 0)
			return null;
		
		return currentUserSet.get(selected);
	}
	
	@Override
	public GTUI showUI(GymTrack gym) {
		createUsersUI(gym);
		return this;
	}
}
