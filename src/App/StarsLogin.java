package App;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.*;
import util.*;


import java.util.*;
import enums.*;
import entity.*;
import util.*;

public class StarsLogin {

	public static void main(String args[]){
		String userId,password;
		char userType;
		int menuOption = 0;
		PeopleManager people = new PeopleManager();
		
		LocalDateTime nowx = LocalDateTime.now();  
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
        String formatDateTime = nowx.format(format);
		
		Scanner sc = new Scanner(System.in);
		
		do{
		    System.out.println("=========Welcome to STARS Planner============");
			System.out.println("Welcome to STARS Planner");
			System.out.println("(1) Login");
			System.out.println("(2) Exit");
			System.out.println("=============================================");
			System.out.print("Enter your choice: ");
			
			try{
				menuOption = sc.nextInt();
				sc.nextLine();
				if(menuOption<0 || menuOption>2)
					throw new ErrorException("choice");
			}
			catch(InputMismatchException e){
				System.out.println("Please enter a valid choice.");
				menuOption = 0;
				sc.nextLine();
			}
			catch(ErrorException e){
			}
			
			System.out.println("");
			
			if(menuOption == 1){
				System.out.println("Enter username: ");
				userId = sc.next();
				System.out.println("Enter password: ");
				password = sc.next();
				
				
				password = userId + password;
				String password_hash = people.generateHash(password);
				userType = people.signIn(userId, password_hash);
		
				if(userType == 'A'){
					AdminApp.printAdminMenu();
				}
		
				else if(userType == 'S'){
					/*
					 * check access time
					 */
					Student stud = new Student(userId);
					Date startTime = stud.getStartTime();
					Date endTime = stud.getEndTime();
					
					try {
						Calendar calendar1 = Calendar.getInstance();
					    calendar1.setTime(startTime);
					    calendar1.add(Calendar.DATE, 1);

					    Calendar calendar2 = Calendar.getInstance();
					    calendar2.setTime(endTime);
					    calendar2.add(Calendar.DATE, 1);

					    Date d = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss").parse(formatDateTime);
					    Calendar calendar3 = Calendar.getInstance();
					    calendar3.setTime(d);
					    calendar3.add(Calendar.DATE, 1);

					    Date x = calendar3.getTime();
					    if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) { //checks whether the current time is between start and end time.
					        StudentApp.printStudentMenu(stud);
					    }
					} catch (ParseException e) {
					    e.printStackTrace();
					}
					
				}	
				else {
					System.out.println("Invalid credentials!");
				}
			}
			System.out.println("");
		}while(menuOption!=2);
		people = null;
		sc.close();
	}
}