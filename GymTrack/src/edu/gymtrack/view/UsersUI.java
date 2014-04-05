package edu.gymtrack.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UsersUI extends GymTrack {
	private static final long serialVersionUID = 1L;

	public static void createUsersUI(GymTrack gym){
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

		String[] columnNames = {"Name", "Email", "Type"};

		//Filled with temporary data
		//TODO get actual data
		Object[][] tableData = {
				{"UserOne", "UserOne@gmail.com",
				"Member"},
				{"UserTwo", "UserTwo@gmail.com",
				"Member"},
				{"UserThree", "UserThree@gmail.com",
				"Member"},
				{"UserFour", "UserFour@gmail.com",
				"Trainer"},
				{"UserFive", "UserFive@gmail.com",
				"Trainer"}
		};

		gym.ownerTable = new JTable(tableData, columnNames);
		gym.ownerTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
		gym.ownerTable.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(gym.ownerTable);
		gym.add(scrollPane);
	}
}
