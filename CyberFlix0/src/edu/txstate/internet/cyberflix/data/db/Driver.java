package edu.txstate.internet.cyberflix.data.db;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.*;

import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.data.film.Film.FilmRating;

public class Driver {

	public static Connection conn;
	
	public static void main(String[] args) {
		try {
			conn = null;
			try {
				conn = DAO.getDBConnection();
				FilmDAO filmDAO = new FilmDAO();
				if(conn != null)
				{
					System.out.println("connected successfully");
				}
				
				//updateMovieYears();
				//populatePhoneNums();
				//populatePasswords();
				System.out.println("movie years, phone numbers added, passwords added");
				List<Film> testFilms = new ArrayList<Film>();
				testFilms = filmDAO.findNewestFilms(5);
				for (int i = 0; i < testFilms.size(); i++ )
				{
				   System.out.print(testFilms.get(i).getTitle() + " ");
				   System.out.print(testFilms.get(i).getDescription() + " ");
				   System.out.print(testFilms.get(i).getLength() + " ");
				   System.out.print(testFilms.get(i).getRating());
				   System.out.print("\n");   
				}
				
				FilmRating r = testFilms.get(0).getRating();
				testFilms = filmDAO.findFilmsByAttributes("ACADEMY DINOSAUR", "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies" + 
						"", 86, r);				
				for (int i = 0; i < testFilms.size(); i++ )
				{
					   System.out.print(testFilms.get(i).getTitle() + " ");
					   System.out.print(testFilms.get(i).getDescription() + " ");
					   System.out.print(testFilms.get(i).getLength() + " ");
					   System.out.print(testFilms.get(i).getRating() + " ");
					   System.out.print("\n");   
				}
				String tEmail = "MARY.SMITH@sakilacustomer.org";
				Customer target = CustomerDAO.findCustomerWithEmail(tEmail);
				String tPassword = target.getPassword();
				System.out.println(tPassword);
				System.out.println(target.getPassword());
				System.out.println(target.getEmail());
				System.out.println(tEmail);
				
				System.out.println("Attempt to log in to CyberFlix");
				System.out.println("Correct email and password");
				
				if ((target.getEmail() == tEmail) || (target.getPassword() == tPassword))
				{
					System.out.println("Login Success.");
				}
				else
				{
					System.out.println("Login Failed.");
				}
				
				String tPassword2 = "go";
				System.out.println("Correct email and  incorrect password");
				if ((target.getEmail() == tEmail) || (target.getPassword() == tPassword2))
				{
					System.out.println("Login Success.");
				}
				else
				{
					System.out.println("Login Failed.");
				}
				
				
				System.out.println("Incorrect email and  correct password");
				String tEmail2 ="go@boo.org";
				if ((target.getEmail() == tEmail2) && (target.getPassword() == tPassword))
				{
					System.out.println("Login Success.");
				}
				else
				{
					System.out.println("Login Failed.");
				}
				
			
			} catch (Exception e) {
				System.out.println("not connected to database");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateMovieYears() {
		int[] newFilms = {1, 50, 111, 206, 271, 299, 438, 494};
		for(int i = 0; i < newFilms.length; i++)
		{
			try {
				Statement statement = conn.createStatement();
				String statementString = "update film " + " set release_year='2017'" + " where film_id = " + newFilms[i];
				statement.executeUpdate(statementString);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void populatePhoneNums() {
		
		for(int i = 0; i < 600; i++)
		{
			Random generator = new Random();
			
			int dig1, dig2, dig3, group1, group2;
			String phoneNumber = null;
			
			dig1 = generator.nextInt(7) + 1;
			dig2 = generator.nextInt(8);
			dig3 = generator.nextInt(8);
			group1 = generator.nextInt(899) + 100;
			group2 = generator.nextInt(8999) + 1000;
			
			phoneNumber = Integer.toString(dig1) + Integer.toString(dig2) + Integer.toString(dig3) + Integer.toString(group1) + Integer.toString(group2);
			
			try {
				Statement statement = conn.createStatement();
				String statementString = "update customer " + " set phone= '" + phoneNumber + "' where customer_id = " + Integer.toString(i);
				statement.executeUpdate(statementString);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		System.out.println("phone numbers added");
	}
	
	public static void populatePasswords(){
		String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

		
		for(int i = 0; i < 600; i++)
		{
			Random generator = new Random();
			String password = "";
			
			//get random character
			int passwordLengthCount = 0;
			while(passwordLengthCount <= 19)
			{
				char randomChar;
				String randomCharString;
				randomChar = validChars.charAt(generator.nextInt(validChars.length()));
				randomCharString = Character.toString(randomChar);
				password = password + randomCharString;
				passwordLengthCount++;
			}
			
			try {
				Statement passStatement = conn.createStatement();
				String statementString = "update customer " + " set password='" + password + "' where customer_id = " + Integer.toString(i);
				passStatement.executeUpdate(statementString);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
}
