package edu.gymtrack.view;


import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SeeFeedbackDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public SeeFeedbackDialog(){
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Recent Feedback");
		setBounds(100, 100, 450, 300);
		setContentPane(contentPanel);
		
		String[] columnNames = {"Date", "Feedback"};
		Object[][] tableDataObjects = getTableData();
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				JTable table = new JTable(tableDataObjects, columnNames);
				scrollPane.setViewportView(table);
				table.setFillsViewportHeight(true);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getColumnModel().getColumn(0).setPreferredWidth(80);
				table.getColumnModel().getColumn(1).setPreferredWidth(400);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			}
		}
	}

	//TODO implement
	private Object[][] getTableData(){
		Object[][] data = {
			{"1-15-2013","Good work"},
			{"1-22-2013","You are lazy"},
			{"1-26-2013","That's all you can do?"},
			{"2-15-2013","meh.."},
			{"4-15-2013","Good work"}
		};
		return data;
	}
}
