package edu.txstate.internet.cyberflix.data.db;

public class Customer {
	
	private int cus_id;
	private String cus_firstName;
	private String cus_lastName;
	private String cus_emailAddress;
	private String cus_password;
	
	Customer(int id, String firstName, String lastName, String emailAddress, String password) {
		cus_id = id;
		cus_firstName = firstName;
		cus_lastName = lastName;
		cus_emailAddress = emailAddress;
		cus_password = password;
	}
	
	Customer() {
	}
	
	public int getID() {
		return cus_id;
	}
	public String getFirstName() {
		return cus_firstName;
	}
	public String getLastName() {
		return cus_lastName;
	}
	public String getEmail() {
		return cus_emailAddress;
	}
	public String getPassword() {
		return cus_password;
	}
	
	public void setID(int id) {
		cus_id = id;
	}
	public void setFirstName(String firstname) {
		cus_firstName = firstname;
	}
	public void setLastName(String lastname) {
		cus_lastName = lastname;
	}
	public void setEmail(String emailAddress) {
		cus_emailAddress = emailAddress;
	}
	public void setPassword(String password) {
		cus_password = password;
	}
	
	@Override
	public String toString() {
		return "Customer [ID: " + cus_id + ", First Name: " + cus_firstName + ", Last Name: " + cus_lastName + ", Email: " + cus_emailAddress + ", Password: " + cus_password + "]";
	}
}
