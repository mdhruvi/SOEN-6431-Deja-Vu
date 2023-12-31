package project;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Invoice extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5936700818392784923L;
	private JTextField name;
	private JTextField pID;
	private JTextField pQuan;
	private JTable items;
	private JTextField unitPrice;
	JLabel lblName;
	JLabel error ;
	static int invo=1;
	DefaultTableModel dtm;
	transient Object[] data;
	JComboBox<String> cType;
	ArrayList<String> comp=new ArrayList<>();
	private JTextField dField;
	long total=0;
	JLabel gtotal ;

	/**
	 * Create the panel.
	 */
	public Invoice() {
		setLayout(null);
		
		JLabel lblCustomer = new JLabel("Customer");
		lblCustomer.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 14));
		lblCustomer.setBounds(88, 59, 97, 23);
		add(lblCustomer);
		
		cType = new JComboBox<>();
		cType.setBounds(201, 62, 89, 20);
		add(cType);
		cType.addItem("Walk-in customer");
		cType.addItem("Company/customer name");
		cType.setSelectedIndex(1);
		cType.addItemListener(arg0 ->  {
				if(cType.getSelectedIndex()==1)
				{
					lblName.setVisible(true);
					name.setVisible(true);
				}
				else
				{
					lblName.setVisible(false);
					name.setVisible(false);
				}
		});
		lblName = new JLabel("Name");
		lblName.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 14));
		lblName.setBounds(364, 59, 64, 23);
		add(lblName);
		
		name = new JTextField();
		name.setBounds(438, 62, 150, 20);
		add(name);
		name.setColumns(10);
		
		JLabel lblProductId = new JLabel(Variables.PRODUCT_ID);
		lblProductId.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 14));
		lblProductId.setBounds(88, 383, 80, 23);
		add(lblProductId);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 14));
		lblQuantity.setBounds(88, 174, 97, 23);
		add(lblQuantity);
		
		pID = new JTextField();
		pID.setBounds(201, 133, 89, 20);
		add(pID);
		pID.setColumns(10);
		
		pQuan = new JTextField();
		pQuan.setColumns(10);
		pQuan.setBounds(201, 177, 89, 20);
		add(pQuan);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(arg0 -> {
				if(unitPrice.getText().equals("")||pQuan.getText().equals("")||pID.getText().equals(""))
				{
					error.setText("Enter required data");
					return;
				}
				else
					error.setText("");
				
				long up;
				long fp;
				long q;
				String id;
				String detail="";
				up=Long.parseLong(unitPrice.getText().trim());
				q=Long.parseLong(pQuan.getText().trim());
				id=pID.getText().trim();
				unitPrice.setText("");
				pQuan.setText("");
				pID.setText("");
				fp=(up*q);
				detail=DB.searchPDetail(id,(int)q);
				String[] str=detail.split("%");
				detail=str[0];
				if(detail.equals("nill"))
				{
					error.setText("Invalid product id!");
					return;
				}
				else if(detail.equals("item is out of stock"))
				{
					error.setText(detail);
					return;
				}
				else
				{
					error.setText("");
					comp.add(str[1]);
				}
				dtm.addRow(new Object[]{id,detail,up,q,fp});
				total+=fp;
				gtotal.setText(total+"");
				
		});
		btnAdd.setBounds(201, 265, 89, 23);
		add(btnAdd);
		    
		String [] header={Variables.PRODUCT_ID,"Item Details","Unit Price","Quantity","Final Price"};
		dtm= new DefaultTableModel(header, 0);  
		items = new JTable(dtm);
		items.setBounds(361, 135, 316, 298);
		JScrollPane s=new JScrollPane(items);
		s.setEnabled(false);
		s.setBounds(361, 135, 392, 265);
		add(s);
		
		unitPrice = new JTextField();
		unitPrice.setColumns(10);
		unitPrice.setBounds(201, 220, 89, 20);
		add(unitPrice);
		
		JLabel lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 14));
		lblUnitPrice.setBounds(88, 217, 97, 23);
		add(lblUnitPrice);
		
		JButton btnPrint = new JButton("PRINT");
		btnPrint.addActionListener(arg0-> {
				int x=0;
				String n="";
				if(cType.getSelectedIndex()==1)
					n=name.getText().trim();
				data=new Object[dtm.getColumnCount()*dtm.getRowCount()];
				for(int row=0;row<dtm.getRowCount();row++)
				{
					for(int col=0;col<dtm.getColumnCount();col++)
					{
						data[x]=items.getValueAt(row, col);
						x++;
					}
				}
			invo++;
			DB.addSaleToDB(data,comp,n);
		});
		btnPrint.setBounds(664, 411, 89, 52);
		add(btnPrint);
		
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setBounds(92, 319, 218, 14);
		add(error);
		
		JLabel lblDeleteProduct = new JLabel("DELETE PRODUCT");
		lblDeleteProduct.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 14));
		lblDeleteProduct.setBounds(88, 344, 132, 14);
		add(lblDeleteProduct);
		
		JLabel label = new JLabel(Variables.PRODUCT_ID);
		label.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 14));
		label.setBounds(88, 135, 80, 23);
		add(label);
		
		dField = new JTextField();
		dField.setColumns(10);
		dField.setBounds(201, 386, 89, 20);
		add(dField);
		
		JButton delbutton = new JButton("Delete");
		delbutton.addActionListener(arg0 -> {
				String df=dField.getText().trim();
				for(int row=0;row<dtm.getRowCount();row++)
				{
						if(items.getValueAt(row, 0).equals(df))
						{
							long q=(long)items.getValueAt(row, 3);
							String i=(String)items.getValueAt(row, 1);
							DB.updateProduct(i,(int) q);
							total-=(long)items.getValueAt(row, 4);
							dtm.removeRow(row);
							gtotal.setText(total+"");
							dField.setText("");
							break;
						}
				}
		});
		delbutton.setBounds(201, 440, 89, 23);
		add(delbutton);
		
		JLabel lblGrandTotal = new JLabel("Grand total");
		lblGrandTotal.setFont(new Font(Variables.TAHOMA, Font.BOLD, 15));
		lblGrandTotal.setBounds(364, 449, 89, 14);
		add(lblGrandTotal);
		
		gtotal = new JLabel("");
		gtotal.setFont(new Font(Variables.TAHOMA, Font.BOLD, 15));
		gtotal.setBounds(470, 449, 132, 14);
		add(gtotal);		

	}
}
