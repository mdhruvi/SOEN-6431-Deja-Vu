package project;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.Toolkit;

public class GenerateInvoice extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8734095048383611851L;
	private JPanel contentPane;
	int jp;
	ArrayList<JPanel> panels=new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
				try {
					GenerateInvoice frame = new GenerateInvoice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		});
	}

	/**
	 * Create the frame.
	 */
	public GenerateInvoice() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\Working Directory\\fianl project with sql\\Bill\\logo.png"));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850,600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmGenerateInvoice = new JMenuItem(Variables.GENERATE_INVOICE);
		mnNewMenu.add(mntmGenerateInvoice);
		mntmGenerateInvoice.addActionListener(this);
		
		JMenuItem mntmSearch = new JMenuItem(Variables.SEARCH_PRODUCT);
		mnNewMenu.add(mntmSearch);
		mntmSearch.addActionListener(this);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mnNewMenu.add(mntmLogout);
		mntmLogout.addActionListener(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		@SuppressWarnings("unused")
		SearchProduct sp=new SearchProduct();
		
		panels.add(new Invoice());
		panels.add(new SearchProduct());
		getContentPane().add(panels.get(0));
		jp=0;
		this.setTitle(Variables.GENERATE_INVOICE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(Variables.SEARCH_PRODUCT))
		{
			this.remove(panels.get(jp));
			this.revalidate();
			this.repaint();
			getContentPane().add(panels.get(1));
			jp=1;
			this.setVisible(true);
			this.setTitle("Search Product");
			
		}
		else if(e.getActionCommand().equals(Variables.GENERATE_INVOICE))
		{
			this.remove(panels.get(jp));
			this.revalidate();
			this.repaint();
			getContentPane().add(panels.get(0));
			this.setVisible(true);
			jp=0;
			this.setTitle(Variables.GENERATE_INVOICE);
		}
		
		else if(e.getActionCommand().equals("Logout"))
		{
			this.dispose();
		}
	}
}
