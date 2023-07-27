package project;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchCashier extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6992586461787534300L;
	JTextField idField;
	JButton btnUpdateProduct;
	private JLabel error;
	String err="Enter product id!";
	String id;
	/**
	 * Create the panel.
	 */
	public SearchCashier() {
		setLayout(null);
		setBounds(100, 100, 840, 619);
		JLabel lblsearch = new JLabel("SEARCH CASHIER");
		lblsearch.setBounds(319, 84, 182, 21);
		lblsearch.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 17));
		add(lblsearch);
		
		JLabel lbluser = new JLabel("User name");
		lbluser.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 14));
		lbluser.setBounds(253, 156, 124, 21);
		add(lbluser);
		
		idField = new JTextField();
		idField.setBounds(449, 158, 136, 20);
		add(idField);
		idField.setColumns(10);
		
		btnUpdateProduct = new JButton("Search");
		btnUpdateProduct.addActionListener (e -> {
				
				if(idField.getText().equals(""))
				{
					error.setText(err);
				}
				else
				{
					error.setText("");
					id=idField.getText().trim();
					DB.searchCashier(id);
					idField.setText("");
				}
		});
		btnUpdateProduct.setBounds(449, 219, 136, 23);
		add(btnUpdateProduct);
		
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setBounds(349, 277, 217, 14);
		add(error);
		
	}

}
