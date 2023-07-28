package project;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteProduct extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3218908934153057814L;
	JTextField idField;
	JButton btnDeleteProduct;
	private JLabel error;
	String id;
	String err="Enter product id!";
	/**
	 * Create the panel.
	 */
	public DeleteProduct() {
		setLayout(null);
		setBounds(100, 100, 840, 619);
		JLabel lblUpdateProduct = new JLabel("DELETE PRODUCT");
		lblUpdateProduct.setBounds(319, 84, 182, 21);
		lblUpdateProduct.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 17));
		add(lblUpdateProduct);
		
		JLabel lblProductName = new JLabel("Product ID");
		lblProductName.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 14));
		lblProductName.setBounds(253, 156, 124, 21);
		add(lblProductName);
		
		idField = new JTextField();
		idField.setBounds(449, 158, 136, 20);
		add(idField);
		idField.setColumns(10);
		
		btnDeleteProduct = new JButton("Delete Product");
		btnDeleteProduct.addActionListener(e -> {
				
				if(idField.getText().equals(""))
				{
					error.setText(err);
				}
				else
				{
					error.setText("");
					id=idField.getText().trim();
					DB.deleteProductToDB(id);
					idField.setText("");
				}
		});
		btnDeleteProduct.setBounds(449, 219, 136, 23);
		add(btnDeleteProduct);
		
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setBounds(349, 277, 217, 14);
		add(error);
		
	}

}
