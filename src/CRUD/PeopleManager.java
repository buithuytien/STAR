package CRUD;

import java.util.InputMismatchException;

import entity.*;
import util.*;


public class PeopleManager {
	private People ppl;
	private HashFunction h = new HashFunction();
	private Database d = new Database(System.getProperty("user.dir") + "\\src\\database\\");

	/**
	 * Constructor
	 */
	public PeopleManager() {
		d.CreateUserFile("Users"); // This checks if the file exists and if not creates the file
	}
	
	/**
	 * Checking The Authentication Of The LogIn Credentials 
	 * @param userId
	 * @param password
	 * @return this person type, if not found return ' '
	 */
	public char signIn(String userId,String password){	//return student or admin with the username and password
		ppl = new People(userId,password,' ');
		
		boolean tempBool = d.matchUserRecord(userId, password); //check authentication
		if (tempBool) {
			String[] record = d.getUserRecord(userId);
			return record[0].charAt(0);
		}
		else {
			System.out.println("Incorrect username or password.");
			return ' ';
		}
	}
	
	/**
	 * Generating The Hash For Password
	 * @param password
	 * @return hashed password (note that this password is hashed with username)
	 */
	public String generateHash(String password){ return String.valueOf(h.hash(password));} //hash the password
	
	
	/**
	 * Checking The Type Of People (Eg. Admin/Student)
	 * @param userId
	 * @return this person type
	 */
	public char checkPplType(String userId){	//return S or A or ' '
		ppl = new People(userId);
		String[] record = d.getUserRecord(userId);
		if(record.length>0) {
			return record[0].charAt(0);
		}else{
			System.out.println("Cannot be found!");
			return ' ';
		}
	}
}
