package entity;
import util.*;

public class Authentication {
	private String userID;
	private String password;
	private Database d = new Database("C:\\");
	
	public Authentication() {
		this.userID = "default";
		this.password = "default";
		d.CreateUserFile("Users"); // This checks if the file exists and if not creates the file
	}

	public char userType(String userID, String password) {
		
		/*
		 * read from database first and check what people type is this person with its username and password
		 * return its people type
		 * set the people type
		 */
		boolean tempBool = d.matchUserRecord(userID, password); //check authentication
		if (tempBool) {
			String[] record = d.getUserRecord(userID);
			return record[0].charAt(0);
		}
		else {
			return ' ';
		}
		
		
	}
	
	public void setUserInfo(String userID, String password) {
		this.userID = userID;
		this.password = password;
	}
	
}
