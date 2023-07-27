package project;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DeleteCashier extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1945621436922680697L;
	JTextField userField;
	JButton btnDeleteCashier;
	private JPasswordField passwordField;
	private JLabel error;
	String err="Enter username and password";
	String user;
	String pass;
	/**
	 * Create the panel.
	 */
	public DeleteCashier() {
		setLayout(null);
		setBounds(100, 100, 840, 619);
		JLabel lblDeleteCashier = new JLabel("DELETE CASHIER");
		lblDeleteCashier.setBounds(328, 45, 182, 21);
		lblDeleteCashier.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 17));
		add(lblDeleteCashier);
		
		JLabel lblUserName = new JLabel("User name");
		lblUserName.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 14));
		lblUserName.setBounds(246, 104, 124, 21);
		add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 14));
		lblPassword.setBounds(246, 136, 124, 21);
		add(lblPassword);
		
		userField = new JTextField();
		userField.setBounds(449, 106, 136, 20);
		add(userField);
		userField.setColumns(10);
		
		btnDeleteCashier = new JButton("Delete Cashier");
		btnDeleteCashier.addActionListener(e -> {
				user=userField.getText().trim();
				pass=passwordField.toString().trim().toLowerCase();
				if(user.equals("")||pass.equals(""))
					error.setText(err);
				else
				{
					error.setText("");
					DB.deleteCashier(user,pass);
					userField.setText("");
					passwordField.setText("");
				}
		});
		btnDeleteCashier.setBounds(449, 194, 136, 23);
		add(btnDeleteCashier);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(449, 138, 136, 19);
		add(passwordField);
		
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setBounds(277, 247, 248, 14);
		add(error);
		
	}

}
