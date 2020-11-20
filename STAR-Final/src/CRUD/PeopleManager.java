package CRUD;

import java.util.InputMismatchException;

import entity.*;
import util.*;


public class PeopleManager {
	private People ppl;
	private HashFunction h = new HashFunction();
	private Database d = new Database(System.getProperty("user.dir") + "\\src\\database\\");
	/*
	 * Constructor
	 */
	
	public PeopleManager() {
		d.CreateUserFile("Users"); // This checks if the file exists and if not creates the file
	}
	
	
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
	
	public String generateHash(String password){ return String.valueOf(h.hash(password));} //hash the password
		
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
