package project;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchProduct extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3397799387842390553L;
	JTextField idField;
	JButton btnSearch;
	private JLabel error;
	String err="Enter product id!";
	String id;
	/**
	 * Create the panel.
	 */
	public SearchProduct() {
		setLayout(null);
		setBounds(100, 100, 840, 619);
		JLabel lblSearchProduct = new JLabel("SEARCH PRODUCT");
		lblSearchProduct.setBounds(319, 84, 182, 21);
		lblSearchProduct.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 17));
		add(lblSearchProduct);
		
		JLabel lblProductName = new JLabel("Product ID");
		lblProductName.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 14));
		lblProductName.setBounds(253, 156, 124, 21);
		add(lblProductName);
		
		idField = new JTextField();
		idField.setBounds(449, 158, 136, 20);
		add(idField);
		idField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener( e -> {
				
				if(idField.getText().equals(""))
				{
					error.setText(err);
				}
				else
				{
					error.setText("");
					id=idField.getText().trim();
					DB.searchProduct(id);
					idField.setText("");
				}
		});
		btnSearch.setBounds(449, 219, 136, 23);
		add(btnSearch);
		
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setBounds(349, 277, 217, 14);
		add(error);
		
	}

}
