package edu.txstate.internet.cyberflix.data.db;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class CustomerDAO extends DAO{
	
	public static Customer findCustomerWithEmail(String emailAddress){
		try {
			  PreparedStatement st = null;
			  Connection cn = null;
			  cn = DAO.getDBConnection();
			  st = cn.prepareStatement("select * from customer where email=?");
			  st.setString(1, emailAddress);
			  
			  ResultSet result = st.executeQuery();
			
			//java.sql.PreparedStatement statement = Driver.conn.prepareStatement();
			//statement.setString(1, emailAddress);
				
			//ResultSet result = statement.executeQuery(statement.toString());
				
				int id;
				String firstName, lastName, email, password;
				while(result.next())
				{
					id = result.getInt("customer.customer_id");
					firstName = result.getString("customer.first_name");
					lastName = result.getString("customer.last_name");
					email = result.getString("customer.email");
					password = result.getString("customer.password");
					
					Customer customer = new Customer(id, firstName, lastName, email, password);
					return customer;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		Customer customer = new Customer();
		customer.setEmail("nobody");
		return customer;
	}
	
	public static void registerCustomer(String first, String last, String email, String password)
	{
		try {
			  Connection cn = null;
			  cn = DAO.getDBConnection();
			  Statement st = null;
			  st = cn.createStatement();
			  String sql = "insert into customer " + 
			  " (first_name, last_name, email, password) " + 
			  " values( '" + first + "', '" + last + "', '" + email +"', '" + password + "')";
			  st.executeUpdate(sql);
			
			//java.sql.PreparedStatement statement = Driver.conn.prepareStatement();
			//statement.setString(1, emailAddress);
				
			//ResultSet result = statement.executeQuery(statement.toString());
				

				}
			 
	   catch (SQLException e) 
	   {
		e.printStackTrace();
	   }
	}

	
	@Override
	public void save(Object anObject) throws SQLException {
	}
}
