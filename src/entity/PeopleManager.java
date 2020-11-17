package entity;

import java.io.*;
import java.util.*;
import enums.*;
import entity.*;
import util.*;

public class PeopleManager {
	private People ppl;
	private Authentication auth = new Authentication();
	private HashFunction h = new HashFunction();
	
	/*
	 * Constructor
	 */
	
	public PeopleManager() {}
	
	
	public char signIn(String userId,String password){
		
		ppl = new People(userId,password,' ');
		char peopleType = this.auth.userType(userId, password);
		if(peopleType == ' '){
			System.out.println("Incorrect username or password.");
			return ' ';
		}
		
		return peopleType;
	}
	
	public String generateHash(String password){
		return String.valueOf(h.hash(password));
		}
		
	
}
