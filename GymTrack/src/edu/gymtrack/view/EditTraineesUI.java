package edu.gymtrack.view;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import edu.gymtrack.db.Factory;
import edu.gymtrack.model.User;
import edu.gymtrack.model.User.UserType;

public class EditTraineesUI extends GTUI {
	private static final long serialVersionUID = 1L;

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

		String[] columnNames = {"Username", "Type", "ID"};
		Object[][] tableData = getTableData(factory, gym);

		JPanel topContainer = new JPanel();
		contentPane.add(topContainer, BorderLayout.NORTH);

		gym.btnAdd_EditTrainees = new JButton("Add");
		gym.btnAdd_EditTrainees.addActionListener(gym);
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
		gym.btnBack_EditTrainees.addActionListener(gym);
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
		
		int j =0;
		for (int i = 0; i < currentUserSet.size(); i++) {
			if(currentUserSet.get(i).getUserType() == UserType.CLIENT){
				tableData[j][0] = currentUserSet.get(i).getUsername();
				tableData[j][1] = currentUserSet.get(i).getUserType().toString();
				tableData[j][2] = currentUserSet.get(i).getID();
				++j;
			}
			
			if(currentUserSet.get(i).getID() > gym.largetId){
				gym.largetId = currentUserSet.get(i).getID();
			}
		}
		return tableData;
	}// end getTableData

	@Override
	public GTUI showUI(GymTrack gym) {
		createEditTraineesUI(gym);
		return this;
	}
}
