package project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7837990501601736234L;
	private JLabel error;
	private String errorText="Invalid user name or password!";
	JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(() ->  {
				try {

					Login frame = new Login();
					frame.setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\Working Directory\\fianl project with sql\\Bill\\logo.png"));
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login()
	{
		gUI();
	}
	void gUI()
	{
		String password;
		String username;
		JLabel label;
		JLabel lblCaddeyLogin;
		JPanel contentPane;
		setTitle("Login");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblUserName = new JLabel("User name");
		lblUserName.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 14));
		lblUserName.setBounds(154, 141, 91, 14);
		contentPane.add(lblUserName);
		
		usernameField = new JTextField();
		usernameField.setBounds(282, 140, 129, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password\r\n");
		lblPassword.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 14));
		lblPassword.setBounds(154, 174, 91, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(282, 173, 129, 20);
		contentPane.add(passwordField);
		
		passwordField.addActionListener(e -> btnLogin.doClick());
		
		btnLogin = new JButton("Login");
	
		btnLogin.addActionListener(arg0 -> {
				password=passwordField.toString().toLowerCase();
				username=usernameField.toString().toLowerCase();
				passwordField.setText("");
				usernameField.setText("");
				if(password.equals("")||username.equals(""))
					error.setText(errorText);
				else
				{
					error.setText("");
					if(username.equals("admin"))
					{
						if(DB.varifyLogin(username,password))
							{
								error.setText("");
								AdminPanel p=new AdminPanel();
								p.setVisible(true);
							}
						else
							error.setText(errorText);
					}
					else
					{
						if(DB.varifyLogin(username,password))
						{
							error.setText("");
							GenerateInvoice g=new GenerateInvoice();
							g.setVisible(true);
						}
					else
						error.setText(errorText);
					}
					
				}
		});
		btnLogin.setBounds(282, 227, 89, 23);
		contentPane.add(btnLogin);
		
		error = new JLabel("");
		error.setForeground(Color.RED);
		error.setBounds(104, 236, 220, 14);
		contentPane.add(error);
		
		lblCaddeyLogin = new JLabel("Caddey Login");
		lblCaddeyLogin.setFont(new Font(Variables.TAHOMA, Font.PLAIN, 17));
		lblCaddeyLogin.setBounds(204, 26, 167, 28);
		contentPane.add(lblCaddeyLogin);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("E:\\XAMPP\\htdocs\\logo.png"));
		label.setBounds(10, 11, 167, 91);
		contentPane.add(label);
		
		
	}
	public static String getMac()
	{
		InetAddress ip;
		String mc="";
		try {
			ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);

			byte[] mac = network.getHardwareAddress();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
		
			mc= sb.toString();

		} catch (UnknownHostException|SocketException e) {
			e.printStackTrace();
		} 
		return mc;
		
	
	}
	
}
