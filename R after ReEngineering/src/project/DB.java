package project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class DB {
	public static Logger logger= Logger.getLogger(DB.class.getName());
	
	public static Connection dBConnection()
	{
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		
			conn = DriverManager.getConnection("jdbc:mysql://localhost/caddey","root", "P@ssw0rd");
			logger.log(Level.INFO,"Database is connected !" );
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Do not connect to DB - Error:"+e);
		
		}
		return conn;
	}
	public static void addProductToDB(String id,String detail,String comp,int quan) 
	{
		Connection conn=dBConnection();
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO stock VALUES ('"+id+"','"+detail+"','"+comp+"',"+quan+");");
			JOptionPane.showMessageDialog(null, "Product added to database");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void updateProductToDB(String id,String detail,String comp,int quan) 
	{
		Connection conn=dBConnection();
		try {
			Statement statement = conn.createStatement();
			int status=statement.executeUpdate("UPDATE stock set Detail = '"+detail+"', Company = '"+comp+"', Quantity = "+quan+" "+Variables.WHERE_CLAUSE+" '"+id+"';");
			if(status==1)
		    	JOptionPane.showMessageDialog(null,  "Product updted");
		    else
		    	JOptionPane.showMessageDialog(null,  "ProductID not found!");
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	
	public static void deleteProductToDB(String id)
	{
		Connection conn=dBConnection();
		try {
			Statement statement = conn.createStatement();
			int status=statement.executeUpdate("DELETE from stock WHERE ProductID = '"+id+"';");
		    if(status==1)
		    	JOptionPane.showMessageDialog(null,  "Product deleted");
		    else
		    	JOptionPane.showMessageDialog(null,  "ProductID not found!");
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	public static void searchProduct(String id)
	{
		Connection conn=dBConnection();
		try {
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery(Variables.SELECT_ALL_STOCK+"'"+id+"';");
			if (!rs.next()) 
				JOptionPane.showMessageDialog(null,"No product found with this id!");
			else
				JOptionPane.showMessageDialog(null, "ProductID: "+id+"\nQuantity: "+rs.getString(Variables.QUANTITY));
		        
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	public static void searchCashier(String email) 
	{
		Connection conn=dBConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from users WHERE Email = '"+email+"';");
			if (!rs.next()) 
				JOptionPane.showMessageDialog(null,"No cashier found with this email!");
			else
				JOptionPane.showMessageDialog(null, "Email: "+email+"\nPassword: "+rs.getString("Password"));
		        
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	public static boolean varifyLogin(String email,String pass) 
	{
		boolean login=false;
		Connection conn=dBConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from users WHERE Email = '"+email+"' and Password = '"+pass+"';");
			if (!rs.next()) 
				login=false;
			else
				login=true;
		        
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return login;
	}
	public static void addCashier(String user,String pass) 
	{
		Connection conn=dBConnection();
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO users VALUES ('"+user+"','"+pass+"');");
			JOptionPane.showMessageDialog(null, "Cashier added to database");
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	public static void deleteCashier(String user,String pass) 
	{
		Connection conn=dBConnection();
		try {
			Statement statement = conn.createStatement();
			int status=statement.executeUpdate("DELETE from users WHERE Email = '"+user+"' AND Password = '"+pass+"';");
			 if(status==1)
			    	JOptionPane.showMessageDialog(null,  "Cashier deleted");
			    else
			    	JOptionPane.showMessageDialog(null,  "Cashier not found!");
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public static String searchPDetail(String id,int q) 
	{
		Connection conn=dBConnection();
		String rt="";
		try {
			int quan;
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '"+id+"';");
			if (!rs.next()) 
				rt="nill";
			else{
				quan=Integer.parseInt(rs.getString(Variables.QUANTITY))-q;
				if(quan<0)
					rt="item is out of stock";
				else
				{
					rt=rs.getString(Variables.DETAIL)+"%"+rs.getString(Variables.COMPANY);
					statement.executeUpdate(Variables.UPDATE_STOCK_QUANTITY+""+quan+" WHERE ProductID = '"+id+"';");
				}
					
			}
		        
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return rt;
	}
	public static void addSaleToDB(Object[] data,List<String> comp,String name) 
	{
		Connection conn=dBConnection();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String d=dateFormat.format(date); 
		try {
			Statement statement = conn.createStatement();
			for(int x=0;x<data.length;x=x+5)
			{
				statement.executeUpdate("INSERT INTO sale VALUES ('"+data[x]+"','"+comp.get(0)+"','"+d+"','"+data[x+3]+"',"+data[x+4]+",'"+name+"');");
				comp.remove(0);
			}
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	public static List<String> getSale(String date,String comp) 
	{
		String q;
		ArrayList<String> r=new ArrayList<>();
		
		if(comp.equals("All"))
			q="Select * from sale WHERE Date = '"+date+"';";
		else
			q="Select * from sale WHERE Date = '"+date+"' AND Company = '"+comp+"';";
		Connection conn=dBConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(q);
			while(rs.next())
			{
				r.add(rs.getString("Date"));
				r.add(rs.getString("ProductID"));
				r.add(rs.getString(Variables.COMPANY));
				r.add(rs.getString("Payment"));
			}
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return r ;
	}
	
	public static List<String> showStock(String comp) 
	{
		String q;
		ArrayList<String> r=new ArrayList<>();
		if(comp.equals("All"))	
			q="Select * from stock;";
		else
			q="Select * from stock WHERE Company = '"+comp+"';";
		Connection conn=dBConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(q);
			while(rs.next())
			{
				r.add(rs.getString("ProductID"));
				r.add(rs.getString(Variables.DETAIL));
				r.add(rs.getString(Variables.COMPANY));
				r.add(rs.getString(Variables.QUANTITY));
			}
		} 
		catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return r;
	}
	
	public static String getPDetail(String id,int q) 
	{
		Connection conn=dBConnection();
		String rt="";
		try {
			int quan;
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '"+id+"';");
			if (!rs.next()) 
				rt="nill";
			else{
				quan=Integer.parseInt(rs.getString(Variables.QUANTITY))-q;
				if(quan<0)
					rt="item is out of stock";
				else
				{
					rt=rs.getString(Variables.DETAIL)+"%"+rs.getString(Variables.COMPANY);
					statement.executeUpdate(Variables.UPDATE_STOCK_QUANTITY+""+quan+" WHERE ProductID = '"+id+"';");
				}
					
			}
		        
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return rt;
	}
	
	public static List<String> searchP(String id) 
	{
		Connection conn=dBConnection();
		ArrayList<String> data=new ArrayList<>();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '"+id+"';");
			if (rs.next()) 
			{
				data.add(rs.getString(Variables.DETAIL));
				data.add(rs.getString(Variables.COMPANY));
				data.add(rs.getString(Variables.QUANTITY));
			}
			  
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public static void updateProduct(String id,int quan) 
	{
		Connection conn=dBConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '"+id+"';");
			int q=0;
			if(rs.next())
			{
				q=Integer.parseInt(rs.getString(Variables.QUANTITY))+quan;
				statement.executeUpdate(Variables.UPDATE_STOCK_QUANTITY+""+q+" WHERE ProductID = '"+id+"';");
			
			}
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args)
	{
		/* This class is mainly for Connection to Databse and Query fetching the data from the Databse, there is no explictely invokation of this main metho, that's why this main method is empty.
		 * */
	}
}
