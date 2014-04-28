package edu.gymtrack.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InvalidSelectionDialog extends JDialog {
	
	public InvalidSelectionDialog(GymTrack gym) {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setTitle("Selection Error");
		this.setPreferredSize(new Dimension(400,120));
		this.setBounds(100, 100, 400, 120);
		this.setLayout(new FlowLayout());
		this.setResizable(false);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new FlowLayout());
		contentPane.setPreferredSize(new Dimension(400,40));
		this.getContentPane().add(contentPane);

		JLabel dialogText1 = new JLabel("You must select a row to use delete.");
		contentPane.add(dialogText1);

		/*JLabel separator = new JLabel();
		separator.setPreferredSize(new Dimension(400, 60));
		contentPane.add(separator);
*/
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout());
		this.getContentPane().add(buttonPane);

		gym.btnOk_invalidSelection = new JButton(new ImageIcon("images/dialog_ok.png", "Ok"));
		gym.btnOk_invalidSelection.setPreferredSize(new Dimension(70,25));
		gym.btnOk_invalidSelection.setRolloverIcon(new ImageIcon("images/dialog_ok_over.png", "Ok"));
		gym.btnOk_invalidSelection.addActionListener(gym);
		buttonPane.add(gym.btnOk_invalidSelection);
	}
	
}
