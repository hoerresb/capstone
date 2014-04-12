package edu.gymtrack.view;
import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

public class EditTraineesUI extends GTUI {
	private static final long serialVersionUID = 1L;
	
	public void createEditTraineesUI(GymTrack gym){
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
		//setTitle("Edit Trainees");
		
		String[] columnNames = {"Name", "Email", "Member Since"};
		Object[][] tableData = getTableData();

		JPanel topContainer = new JPanel();
		contentPane.add(topContainer, BorderLayout.NORTH);

		gym.btnAdd_EditTrainees = new JButton("Add");
		topContainer.add(gym.btnAdd_EditTrainees);

		gym.btnEdit_EditTrainees = new JButton("Edit");
		topContainer.add(gym.btnEdit_EditTrainees);

		gym.btnDelete_EditTrainees = new JButton("Delete");
		topContainer.add(gym.btnDelete_EditTrainees);
		
		JPanel bottomContainer = new JPanel();
		contentPane.add(bottomContainer, BorderLayout.CENTER);
		bottomContainer.setLayout(new BorderLayout(0, 0));
		
		JPanel bottomPanel = new JPanel();
		bottomContainer.add(bottomPanel, BorderLayout.SOUTH);
		
		gym.btnBack_EditTrainees = new JButton("Back");
		GroupLayout gl_bottomPanel = new GroupLayout(bottomPanel);
		gl_bottomPanel.setHorizontalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bottomPanel.createSequentialGroup()
					.addComponent(gym.btnBack_EditTrainees)
					.addContainerGap(546, Short.MAX_VALUE))
		);
		gl_bottomPanel.setVerticalGroup(
			gl_bottomPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_bottomPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(gym.btnBack_EditTrainees))
		);
		bottomPanel.setLayout(gl_bottomPanel);
		
		JScrollPane scrollablePane = new JScrollPane();
		bottomContainer.add(scrollablePane, BorderLayout.CENTER);
		
		gym.traineesTable_EditTrainees = new JTable(tableData, columnNames);
		gym.traineesTable_EditTrainees.setFillsViewportHeight(true);
		scrollablePane.setViewportView(gym.traineesTable_EditTrainees);
	}
	
	//TODO implement
	private Object[][] getTableData(){
		Object[][] tableData = {
			{"UserOne", "UserOne@gmail.com", "01-01-2009"},
			{"UserTwo", "UserTwo@gmail.com", "03-25-2011"},
			{"UserThree", "UserThree@gmail.com", "09-20-2013"},
			{"UserFour", "UserFour@gmail.com", "08-14-2013"},
			{"UserFive", "UserFive@gmail.com", "01-01-2009"}};
		return tableData;
	}

	@Override
	public GTUI showUI(GymTrack gym) {
		createEditTraineesUI(gym);
		return this;
	}
}
