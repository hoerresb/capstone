package edu.gymtrack.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConnectionErrorDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public ConnectionErrorDialog(GymTrack gym) {

		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setTitle("Connection Error");
		this.setPreferredSize(new Dimension(400,200));
		this.setBounds(100, 100, 400, 200);
		this.setLayout(new FlowLayout());
		this.setResizable(false);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new FlowLayout());
		contentPane.setPreferredSize(new Dimension(400,120));
		this.getContentPane().add(contentPane);

		JLabel dialogText1 = new JLabel("Cannot connect to server.");
		contentPane.add(dialogText1);

		JLabel separator = new JLabel();
		separator.setPreferredSize(new Dimension(400, 60));
		contentPane.add(separator);

		JLabel dialogText2 = new JLabel("If this problem persists, the system may be down.");
		contentPane.add(dialogText2);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout());
		this.getContentPane().add(buttonPane);

		gym.btnOk_connectionerr = new JButton("Ok");
		gym.btnOk_connectionerr.addActionListener(gym);
		buttonPane.add(gym.btnOk_connectionerr);
	}

}
