package project;
import java.awt.Color;
import java.awt.Font;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ShowStock extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5386532590306152459L;
	private JTable stockTable;
	JComboBox<String> comp;
	DefaultTableModel model;
	/**
	 * Create the panel.
	 */
	public ShowStock() {
		setLayout(null);
		setBounds(100, 100, 840, 619);
		JLabel lblStock = new JLabel("AVAILABLE STOCK");
		lblStock.setBounds(328, 26, 182, 21);
		lblStock.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 17));
		add(lblStock);
		
		model = new DefaultTableModel(); 
		stockTable = new JTable(model);
		stockTable.setBounds(98, 112, 645, 397);
		add(stockTable);
		model.addColumn("Product ID");
		model.addColumn("Product Detail");
		model.addColumn("Company");
		model.addColumn("Quantity");
		JScrollPane scroll = new JScrollPane(stockTable);
		scroll.setBounds(98, 112, 645, 397);
		add(scroll);
		
		comp = new JComboBox<>();
		comp.setBackground(Color.WHITE);
		comp.setBounds(583, 81, 160, 20);
		add(comp);
		comp.addItem("All");
		comp.addItem("General");
		comp.addItem("Mats & Rugs");
		comp.addItem("N/S & Electric");
		comp.addItemListener(arg0 -> updateTable());
		
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setBounds(582, 68, 161, 14);
		add(lblCompany);
		
		JButton btnExportToExcel = new JButton("Export to Excel");
		btnExportToExcel.addActionListener( arg0 -> {
				toExcel(stockTable, new File("availableStock.xls"));
				JOptionPane.showMessageDialog(null, "Export file created");
		});
		btnExportToExcel.setBounds(605, 525, 138, 23);
		add(btnExportToExcel);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener( arg0 -> updateTable());
		btnRefresh.setBounds(457, 525, 138, 23);
		add(btnRefresh);
		updateTable();
		
	}
	
	public void updateTable()
	{
		model.setRowCount(0);
		List<String> stock=DB.showStock(comp.getSelectedItem().toString());
		for(int x=0;x<stock.size();x+=4)
		{
			model.addRow(new Object[]{stock.get(x),stock.get(x+1),stock.get(x+2),stock.get(x+3)});
		}
	}
	

		public void toExcel(JTable table, File file){
		    try(FileWriter excel = new FileWriter(file);){
		        TableModel model1 = table.getModel();
		        

		        for(int i = 0; i < model1.getColumnCount(); i++){
		            excel.write(model1.getColumnName(i) + "\t");
		        }

		        excel.write("\n");

		        for(int i=0; i< model1.getRowCount(); i++) {
		            for(int j=0; j < model1.getColumnCount(); j++) {
		                excel.write(model1.getValueAt(i,j).toString()+"\t");
		            }
		            excel.write("\n");
		        }

		    }catch(IOException e){ JOptionPane.showMessageDialog(null, e); }
		}
}
