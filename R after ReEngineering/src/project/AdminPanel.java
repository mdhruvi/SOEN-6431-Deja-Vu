package project;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.JMenu;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AdminPanel extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4955733321479262985L;
	transient Logger logger= Logger.getLogger(this.getClass().getName());
	JMenuItem itmAddProduct;
	JMenu mnProduct;
	JMenuItem itmUpdateProduct;
	JMenuItem itmDeleteProduct;
	JMenu mnCashier ;
	JMenuItem itmDeleteCashier;
	JMenuItem itmAddCashier;
	JMenu mnStock ;
	JMenuItem itmShowStock;
	JMenu mnExport;
	ArrayList<JPanel> panels=new ArrayList<>();
	int cPanel=0;
	private JMenu mnSearch;
	private JMenuItem mntmSearchProduct;
	private JMenuItem mntmSearchCashier;
	private JMenu mnSale;
	private JMenuItem mntmPrintSale;

	
	/**
	 * Create the frame.
	 */
	public AdminPanel() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\Working Directory\\fianl project with sql\\Bill\\logo.png"));
		setTitle("Admin Panel");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 840, 619);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnProduct = new JMenu("Product");
		menuBar.add(mnProduct);
		
		itmAddProduct = new JMenuItem(Variables.ADD_PRODUCT);
		mnProduct.add(itmAddProduct);
		itmAddProduct.addActionListener(this);
		
		itmUpdateProduct = new JMenuItem(Variables.UPDATE_PRODUCT);
		mnProduct.add(itmUpdateProduct);
		itmUpdateProduct.addActionListener(this);
		
		itmDeleteProduct = new JMenuItem(Variables.DELETE_PRODUCT);
		mnProduct.add(itmDeleteProduct);
		itmDeleteProduct.addActionListener(this);
		
		mnCashier = new JMenu("Cashier");
		menuBar.add(mnCashier);
		
		itmAddCashier = new JMenuItem(Variables.ADD_CASHIER);
		mnCashier.add(itmAddCashier);
		itmAddCashier.addActionListener(this);
		
		itmDeleteCashier = new JMenuItem(Variables.DELETE_CASHIER);
		mnCashier.add(itmDeleteCashier);
		itmDeleteCashier.addActionListener(this);
		
		mnStock = new JMenu("Stock");
		menuBar.add(mnStock);
		
		itmShowStock = new JMenuItem(Variables.SHOW_STOCK);
		mnStock.add(itmShowStock);
		itmShowStock.addActionListener(this);
		
		mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);
		
		mntmSearchProduct = new JMenuItem(Variables.SEARCH_PRODUCT);
		mnSearch.add(mntmSearchProduct);
		mntmSearchProduct.addActionListener(this);
		
		mntmSearchCashier = new JMenuItem(Variables.SEARCH_CASHIER);
		mnSearch.add(mntmSearchCashier);
		
		mnSale = new JMenu("Sale");
		menuBar.add(mnSale);
		
		mntmPrintSale = new JMenuItem(Variables.PRINT_SALE);
		mnSale.add(mntmPrintSale);
		mntmPrintSale.addActionListener(this);
		
		mnExport = new JMenu("Account");
		menuBar.add(mnExport);
		
		JMenuItem logout = new JMenuItem("Logout");
		mnExport.add(logout);
		logout.addActionListener(this);
		mntmSearchCashier.addActionListener(this);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panels.add(new AddProduct());
		panels.add(new UpdateProduct());
		panels.add(new DeleteProduct());
		panels.add(new AddCashier());
		panels.add(new DeleteCashier());
		panels.add(new ShowStock());
		panels.add(new SearchProduct());
		panels.add(new SearchCashier());
		panels.add(new Sale());
		getContentPane().add(panels.get(0));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.log(Level.INFO, String.format("Selected: {0}",e.getActionCommand()));
		if(e.getActionCommand().equals(Variables.ADD_PRODUCT))
		{
			logger.log(Level.INFO, panels.get(cPanel).toString());
			this.remove(panels.get(cPanel));
			this.revalidate();
			this.repaint();
			getContentPane().add(panels.get(0));
			this.setVisible(true);
			cPanel=0;
			this.setTitle(Variables.ADD_PRODUCT);
		}
		else if(e.getActionCommand().equals(Variables.UPDATE_PRODUCT))
		{
			this.remove(panels.get(cPanel));
			this.revalidate();
			this.repaint();
			getContentPane().add(panels.get(1));
			this.setVisible(true);
			cPanel=1;
			this.setTitle(Variables.UPDATE_PRODUCT);
		}
		else if(e.getActionCommand().equals(Variables.DELETE_PRODUCT))
		{
			this.remove(panels.get(cPanel));
			this.revalidate();
			this.repaint();
			getContentPane().add(panels.get(2));
			this.setVisible(true);
			cPanel=2;
			this.setTitle(Variables.DELETE_PRODUCT);
		}
		else if(e.getActionCommand().equals(Variables.ADD_CASHIER))
		{
			this.remove(panels.get(cPanel));
			this.revalidate();
			this.repaint();
			getContentPane().add(panels.get(3));
			this.setVisible(true);
			cPanel=3;
			this.setTitle(Variables.ADD_CASHIER);
		}
		else if(e.getActionCommand().equals(Variables.DELETE_CASHIER))
		{
			this.remove(panels.get(cPanel));
			this.revalidate();
			this.repaint();
			getContentPane().add(panels.get(4));
			this.setVisible(true);
			cPanel=4;
			this.setTitle(Variables.DELETE_CASHIER);
		}
		else if(e.getActionCommand().equals(Variables.SHOW_STOCK))
		{
			this.remove(panels.get(cPanel));
			getContentPane().add(panels.get(5));
			this.revalidate();
			this.repaint();
			this.setVisible(true);
			cPanel=5;
			this.setTitle(Variables.SHOW_STOCK);
		}
		else if(e.getActionCommand().equals(Variables.SEARCH_PRODUCT))
		{
			this.remove(panels.get(cPanel));
			getContentPane().add(panels.get(6));
			this.revalidate();
			this.repaint();
			this.setVisible(true);
			cPanel=6;
			this.setTitle(Variables.SEARCH_PRODUCT);
		}
		else if(e.getActionCommand().equals(Variables.SEARCH_CASHIER))
		{
			this.remove(panels.get(cPanel));
			getContentPane().add(panels.get(7));
			this.revalidate();
			this.repaint();
			this.setVisible(true);
			cPanel=7;
			this.setTitle(Variables.SEARCH_CASHIER);
		}
		else if(e.getActionCommand().equals(Variables.PRINT_SALE))
		{
			this.remove(panels.get(cPanel));
			getContentPane().add(panels.get(8));
			this.revalidate();
			this.repaint();
			this.setVisible(true);
			cPanel=8;
			this.setTitle(Variables.PRINT_SALE);
		}
		else if(e.getActionCommand().equals("Logout"))
		{
			this.dispose();
		}
	}
}
