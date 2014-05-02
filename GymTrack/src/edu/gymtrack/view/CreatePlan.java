package edu.gymtrack.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class CreatePlan extends JDialog{
	private final JPanel contentPanel = new JPanel();
	private static final long serialVersionUID = 1L;
	private GymTrack gym;
	private GTUI gtui;
	private JButton btnAdd_CreatePlanDialog;
	private String exercise;
	private String reps;
	
	public CreatePlan(final GymTrack gym, GTUI callingUI) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.gym = gym;
		this.gtui = callingUI;
		setModal(true);
		setTitle("Create Plan");
		setBounds(100, 100, 450, 300);
		setContentPane(new JPanel());
		getContentPane().setLayout(new BorderLayout());
		setTitle("Create Plan");
	
		String[] columnNames = {"Exercise", "reps"};
		Object[][] tableData = getTableData();

		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
		);
		
		gym.comboBox_CreatePlan = new JComboBox();
		gym.comboBox_CreatePlan.setModel(new DefaultComboBoxModel(getExercises()));
		
		JLabel lblExerciseType = new JLabel("Exercise type");
		
		gym.textField_CreatePlan = new JTextField();
		gym.textField_CreatePlan.setColumns(10);
		
		JLabel lblDuration = new JLabel("Duration");
		
		btnAdd_CreatePlanDialog = new JButton("Add");
		btnAdd_CreatePlanDialog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				reps = gym.textField_CreatePlan.getText();
				exercise = gym.comboBox_CreatePlan.getSelectedItem().toString();
				gym.model.insertRow(0,new Object[]{exercise,reps});
			}
		});
		
		JLabel lblGoal = new JLabel("Goal");
		
		gym.goalTextField_CreatePlan = new JTextField();
		gym.goalTextField_CreatePlan.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(gym.comboBox_CreatePlan, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(gym.textField_CreatePlan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(btnAdd_CreatePlanDialog))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblExerciseType)
							.addGap(77)
							.addComponent(lblDuration))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblGoal)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(gym.goalTextField_CreatePlan, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblExerciseType)
						.addComponent(lblDuration))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(gym.comboBox_CreatePlan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(gym.textField_CreatePlan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd_CreatePlanDialog))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblGoal)
							.addGap(20))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(gym.goalTextField_CreatePlan, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
							.addContainerGap())))
		);
		panel.setLayout(gl_panel);
		
		gym.model = new DefaultTableModel(tableData, columnNames);
		gym.table_CreatePlan = new JTable(gym.model);
		scrollPane.setViewportView(gym.table_CreatePlan);
		gym.table_CreatePlan.setFillsViewportHeight(true);
		
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				gym.okButton_CreatePlan = new JButton("OK");
				gym.okButton_CreatePlan.addActionListener(gym);
				gym.okButton_CreatePlan.setActionCommand("OK");
				buttonPane.add(gym.okButton_CreatePlan);
				getRootPane().setDefaultButton(gym.okButton_CreatePlan);
			}
			{
				gym.cancelButton_CreatePlan = new JButton("Cancel");
				gym.cancelButton_CreatePlan.addActionListener(gym);
				gym.cancelButton_CreatePlan.setActionCommand("Cancel");
				buttonPane.add(gym.cancelButton_CreatePlan);
			}
		}
	}
	
	private Object[][] getTableData(){
		Object[][] data = {};//Table is empty to start with
		return data;
	}
	
	//not sure if this should be dynamic or static
	private String[] getExercises(){
		String[] list = new String[] {"Curls", "Walk"};
		return list;
	}
}
