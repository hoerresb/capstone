package edu.gymtrack.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class LogWorkDialog extends JDialog{
	private final JPanel contentPanel = new JPanel();
	private static final long serialVersionUID = 1L;
	
	public LogWorkDialog(GymTrack gym, MyPlansUI plans) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Log Work");
		setBounds(100, 100, 450, 300);
		setContentPane(contentPanel);
		
		String[] columnNames = {"Date", "Exercise", "Amount", "% of plan complete"};
		Object[][] tableDataObjects = getWorklogTableData(plans);
		
		
		JPanel topContainer = new JPanel();
		getContentPane().setLayout(new BorderLayout());
		topContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(topContainer, BorderLayout.CENTER);
		
		JPanel tableTitleContainer = new JPanel();
		JScrollPane scrollableTableContainer = new JScrollPane();
		JPanel dropDownTitleContainer = new JPanel();
		JPanel dropDownContainer = new JPanel();
		
		GroupLayout gl_topContainer = new GroupLayout(topContainer);
		gl_topContainer.setHorizontalGroup(
			gl_topContainer.createParallelGroup(Alignment.LEADING)
				.addComponent(tableTitleContainer, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addComponent(scrollableTableContainer, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addComponent(dropDownContainer, GroupLayout.PREFERRED_SIZE, 424, Short.MAX_VALUE)
				.addComponent(dropDownTitleContainer, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_topContainer.setVerticalGroup(
			gl_topContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topContainer.createSequentialGroup()
					.addComponent(tableTitleContainer, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollableTableContainer, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dropDownTitleContainer, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dropDownContainer, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
		);
		
		gym.exerciseComboBox_LogWork = new JComboBox();
		gym.exerciseComboBox_LogWork.setModel(new DefaultComboBoxModel(getDropDownMenuOptions()));
		
		gym.amountTextField_LogWork = new JTextField();
		gym.amountTextField_LogWork.setColumns(10);
		GroupLayout gl_dropDownContainer = new GroupLayout(dropDownContainer);
		gl_dropDownContainer.setHorizontalGroup(
			gl_dropDownContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dropDownContainer.createSequentialGroup()
					.addGap(35)
					.addComponent(gym.exerciseComboBox_LogWork, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(76)
					.addComponent(gym.amountTextField_LogWork, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		gl_dropDownContainer.setVerticalGroup(
			gl_dropDownContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dropDownContainer.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_dropDownContainer.createParallelGroup(Alignment.BASELINE)
						.addComponent(gym.exerciseComboBox_LogWork, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(gym.amountTextField_LogWork, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		dropDownContainer.setLayout(gl_dropDownContainer);
		
		JTable recentlyLoggedWorkTable = new JTable(tableDataObjects, columnNames);
		scrollableTableContainer.setViewportView(recentlyLoggedWorkTable);
        recentlyLoggedWorkTable.setFillsViewportHeight(true);
		
		
		JLabel lblLogNewWork = new JLabel("Log new work");
		GroupLayout gl_dropDownTitleContainer = new GroupLayout(dropDownTitleContainer);
		gl_dropDownTitleContainer.setHorizontalGroup(
			gl_dropDownTitleContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dropDownTitleContainer.createSequentialGroup()
					.addComponent(lblLogNewWork)
					.addContainerGap(358, Short.MAX_VALUE))
		);
		gl_dropDownTitleContainer.setVerticalGroup(
			gl_dropDownTitleContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dropDownTitleContainer.createSequentialGroup()
					.addComponent(lblLogNewWork)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		dropDownTitleContainer.setLayout(gl_dropDownTitleContainer);
		
		JLabel lblRecentlyLoggedWork = new JLabel("Recently logged work");
		GroupLayout gl_tableTitleContainer = new GroupLayout(tableTitleContainer);
		gl_tableTitleContainer.setHorizontalGroup(
			gl_tableTitleContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tableTitleContainer.createSequentialGroup()
					.addComponent(lblRecentlyLoggedWork)
					.addContainerGap(378, Short.MAX_VALUE))
		);
		gl_tableTitleContainer.setVerticalGroup(
			gl_tableTitleContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tableTitleContainer.createSequentialGroup()
					.addComponent(lblRecentlyLoggedWork)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		tableTitleContainer.setLayout(gl_tableTitleContainer);
		topContainer.setLayout(gl_topContainer);
		{
			JPanel buttonContainer = new JPanel();
			buttonContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonContainer, BorderLayout.SOUTH);
			{
				gym.okButton_LogWork = new JButton("OK");
				gym.okButton_LogWork.addActionListener(gym);
				gym.okButton_LogWork.setActionCommand("OK");//find out what this does
				buttonContainer.add(gym.okButton_LogWork);
				getRootPane().setDefaultButton(gym.okButton_LogWork);
			}
			{
				gym.cancelButton_LogWork = new JButton("Cancel");
				gym.cancelButton_LogWork.addActionListener(gym);
				gym.cancelButton_LogWork.setActionCommand("Cancel");//find out what this does
				buttonContainer.add(gym.cancelButton_LogWork);
			}
		}
	}
	
	private Object[][] getWorklogTableData(MyPlansUI plans){
		Object[][] data = plans.getWorklogTableData();
		return data;
	}
	
	//TODO fill this static String[] with real exercises
	private String[] getDropDownMenuOptions(){
		String[] options = new String[]{
			"running (# of miles)", "curls (# of reps)", 
			"leg curls (# of reps)"
		};
		return options;
	}
}
