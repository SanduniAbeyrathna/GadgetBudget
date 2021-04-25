package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class Order {

	//A common method to connect to the DB
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
	 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/order", "root", "Abcd123#"); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		return con; 
	 } 
	
	
	public String insertItem(String code, String name, String price, String desc,int quantity) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for inserting."; 
			} 
			// create a prepared statement
			String query = " insert into order_tb (`orderID`,`orderCode`,`orderName`,`orderPrice`,`orderDesc`,`quantity`)"+ " values (?, ?, ?, ?, ? , ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, code); 
			preparedStmt.setString(3, name); 
			preparedStmt.setDouble(4, Double.parseDouble(price)); 
			preparedStmt.setString(5, desc); 
			preparedStmt.setInt(6, quantity);
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the order."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	
	public Map<String,String> getOrders() {
		String output = ""; 
		Map<String,String> results = new HashMap<String, String>();
		try
		{ 
			Connection con = connect(); 

			// Prepare the html table to be displayed
			
	 
			String query = "select * from order_tb"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String orderID = Integer.toString(rs.getInt("orderID")); 
				String orderCode = rs.getString("orderCode"); 
				String orderName = rs.getString("orderName"); 
				String orderPrice = Double.toString(rs.getDouble("orderPrice")); 
				String orderDesc = rs.getString("orderDesc"); 
				String quantity = Integer.toString(rs.getInt("quantity"));
				
				results.put("orderID",orderID);
				results.put("ordeCode", orderCode);
				results.put("ordeName", orderName);
				results.put("ordePrice", orderPrice);
				results.put("orderDesc", orderDesc);
				results.put("quantity", quantity);				
						
			} 
			con.close(); 
			
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the orders."; 
			System.err.println(e.getMessage()); 
		} 
		return results;
	}
	
	public String readOrders() 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for reading."; 
			} 
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>orderID</th>"
					+ "<th>Order Code</th>"
					+ "<th>Order Name</th>" +
					"<th>Order Price</th>" + 
					"<th>Order Description</th>" +
					"<th>Quantity</th>"+
					"<th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from order_tb"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String orderID = Integer.toString(rs.getInt("orderID")); 
				String orderCode = rs.getString("orderCode"); 
				String orderName = rs.getString("orderName"); 
				String orderPrice = Double.toString(rs.getDouble("orderPrice")); 
				String orderDesc = rs.getString("orderDesc"); 
				String quantity = Integer.toString(rs.getInt("quantity"));
				// Add into the html table
				output += "<tr><td>" + orderID + "</td>"; 
				output += "<tr><td>" + orderCode + "</td>"; 
				output += "<td>" + orderName + "</td>"; 
				output += "<td>" + orderPrice + "</td>"; 
				output += "<td>" + orderDesc + "</td>";
				output += "<td>" + quantity + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
					   + "<td><form method='delete' action='order.jsp'>"
					   + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
					   + "<input name='orderID' type='hidden' value='" + orderID + "'>" + "</form></td></tr>"; 
			} 
			con.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the items."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	
	
	public String updateOrder(String ID, String code, String name, String price, String desc, String quantity)
	{ 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for updating."; 
			 } 
			 // create a prepared statement
			 String query = "UPDATE order_tb SET orderCode=?,orderName=?,orderPrice=?,orderDesc=? ,quantity=? WHERE orderID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, code); 
			 preparedStmt.setString(2, name); 
			 preparedStmt.setDouble(3, Double.parseDouble(price)); 
			 preparedStmt.setString(4, desc); 
			 preparedStmt.setInt(5, Integer.parseInt(quantity)); 
			 preparedStmt.setInt(6, Integer.parseInt(ID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the order."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	
	} 
	
	public String deleteOrder(String orderID) 
	{ 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for deleting.";
			 } 
			 // create a prepared statement
			 String query = "delete from order_tb where orderID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(orderID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while deleting the order."; 
			 System.err.println(e.getMessage()); 
		 } 
		 
		 return output; 
	} 

	
}
