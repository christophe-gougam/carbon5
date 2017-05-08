package Vues;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;

public class test extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public test() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Button button = new Button("New button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		add(button);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		add(btnNewButton);

	}

}
