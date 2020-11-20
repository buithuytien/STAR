package App;

import java.io.*;

import java.util.*;

import CRUD.*;
import enums.*;
import entity.*;
import util.*;

public class StudentApp {
	public static void printStudentMenu(Student stud){
		Date currentDayTime = new Date();
		Scanner sc = new Scanner(System.in);

		Database db = new Database(System.getProperty("user.dir") + "\\src\\database\\");
		StudentRegistrationManager srm = new StudentRegistrationManager();
		
		int subMenuOption = 0;

		PeopleManager people = new PeopleManager();	

		do{
			System.out.println("=========Welcome to Student Menu===============");
			System.out.println("1 Add Course");
			System.out.println("2 Drop Course");
			System.out.println("3 Check/Print Course Registered");
			System.out.println("4 Check Vacancies Available");
			System.out.println("5 Change Course Index");
			System.out.println("6 Swop Index Number with Another Student");
			System.out.println("7 Exit");
			System.out.println("===============================================");
			System.out.print("Enter your choice: ");
			try{
				subMenuOption = sc.nextInt();

				if(subMenuOption<0 || subMenuOption>10)
					throw new ErrorException("choice");
			}
			catch(InputMismatchException e){
				System.out.println("Please enter a valid choice");
				sc.nextLine();
				System.out.println("");
				subMenuOption=0;
			}
			catch(ErrorException e){
			}

			System.out.println("");

			String courseIndex = null,swopCourseIndex = null, swopUserId, swopUserPassword;

			switch(subMenuOption){
			case 1:
				do{
					db.setFilename("Course");
					System.out.print("Enter index number of course to add: ");
					while(true) {
						try{ //ADDED BY JY
							courseIndex = sc.next();	
							if(db.matchCourseInfoRecord(courseIndex) == false) {
								throw new ErrorException("indexNotFound");
							}
							break;
						}
						catch(InputMismatchException e){
							System.out.println("Please enter a valid index no:");
							courseIndex = sc.next();
							System.out.println("");
							continue;
						}
						catch(ErrorException e){
						}
					}
				}while(courseIndex==null);
				srm.addIntoList(stud, courseIndex);
				break;
				
			case 2:
				do{
					db.setFilename("Course");
					System.out.print("Enter index number of course to drop: ");
					while(true) {
						try{ //ADDED BY JY
							courseIndex = sc.next();	
							if(db.matchCourseInfoRecord(courseIndex) == false) {
								throw new ErrorException("indexNotFound");
							}
							break;
						}
						catch(InputMismatchException e){
							System.out.println("Please enter a valid index no:");
							courseIndex = sc.next();
							System.out.println("");
							continue;
						}
						catch(ErrorException e){
						}
					}
				}while(courseIndex==null);
				srm.dropFromList(stud, courseIndex);
				break;
				
			case 3:
				srm.printCourseReg(stud);
				break;
				
			case 4:
				do{
					db.setFilename("Course");
					System.out.print("Enter index number of course to check vacancy: ");
					while(true) {
						try{ //ADDED BY JY
							courseIndex = sc.next();	
							if(db.matchCourseInfoRecord(courseIndex) == false) {
								throw new ErrorException("indexNotFound");
							}
							break;
						}
						catch(InputMismatchException e){
							System.out.println("Please enter a valid index no:");
							courseIndex = sc.next();
							System.out.println("");
							continue;
						}
						catch(ErrorException e){
						}
					}
				}while(courseIndex==null);
				
				System.out.println("Vacancies for " + courseIndex + " is: " + srm.checkVacancies(courseIndex));
				break;
				
			case 5:
				do{
					db.setFilename("Course");
					System.out.print("Enter your current index number: ");
					while(true) {
						try{ //ADDED BY JY
							courseIndex = sc.next();	
							if(db.matchCourseInfoRecord(courseIndex) == false || !db.returnIndexRecord(stud.getStudMat(), 3).contains(courseIndex)) {
								throw new ErrorException("indexNotMatch");
							}
							break;
						}
						catch(InputMismatchException e){
							System.out.println("Please enter a valid index no:");
							courseIndex = sc.next();
							System.out.println("");
							continue;
						}
						catch(ErrorException e){
						}
					}
				}while(courseIndex==null);
				do{
					db.setFilename("Course");
					System.out.print("Enter the index number you wish to swop to: ");
					while(true) {
						try{ //ADDED BY JY
							swopCourseIndex = sc.next();	
							if(db.matchCourseInfoRecord(swopCourseIndex) == false) {
								throw new ErrorException("indexNotMatch");
							}
							break;
						}
						catch(InputMismatchException e){
							System.out.println("Please enter a valid index no:");
							swopCourseIndex = sc.next();
							System.out.println("");
							continue;
						}
						catch(ErrorException e){
						}
					}
				}while(swopCourseIndex==null);
				srm.changeIndex(stud, courseIndex, swopCourseIndex);
				break;
				
			case 6:
				do{
					db.setFilename("Course");
					System.out.print("Enter your current index number: ");
					while(true) {
						try{ //ADDED BY JY
							courseIndex = sc.next();	
							if(db.matchCourseInfoRecord(courseIndex) == false || !db.returnIndexRecord(stud.getStudMat(), 3).contains(courseIndex)) {
								throw new ErrorException("indexNotMatch");
							}
							break;
						}
						catch(InputMismatchException e){
							System.out.println("Please enter a valid index no from the list you have registered:");
							courseIndex = sc.next();
							System.out.println("");
							continue;
						}
						catch(ErrorException e){
						}
					}
				}while(courseIndex==null);
				do{
					db.setFilename("Users");
					System.out.print("Enter your peer matric number: ");
					while(true) {
						try{ //ADDED BY JY
							swopUserId = sc.next();
							System.out.println("");
							System.out.print("Enter peer student password: ");
							swopUserPassword = sc.next();
							System.out.println("");
							swopUserPassword = swopUserId + swopUserPassword;
							String swopUserPassword_hash = people.generateHash(swopUserPassword);
							if(people.signIn(swopUserId, swopUserPassword_hash) != 'S') {
								throw new ErrorException("studentmatric");
							}
							break;
						}
						catch(InputMismatchException e){
							System.out.println("Please enter a valid peer matric no:");
							swopUserId = sc.next();
							System.out.println("");
							System.out.print("Enter peer student password: ");
							swopUserPassword = sc.next();
							System.out.println("");
							continue;
						}
						catch(ErrorException e){
						}
					}
				}while(swopUserId==null || swopUserPassword==null);
				do{
					db.setFilename("Course");
					System.out.print("Enter your peer's index number you wish to swop to: ");
					while(true) {
						try{ //ADDED BY JY
							swopCourseIndex = sc.next();	
							if(db.matchCourseInfoRecord(swopCourseIndex) == false || !db.returnIndexRecord(swopUserId, 3).contains(swopCourseIndex)) {
								throw new ErrorException("indexNotMatch");
							}
							break;
						}
						catch(InputMismatchException e){
							System.out.println("Please enter a valid index no from the list your peer has registered for:");
							swopCourseIndex = sc.next();
							System.out.println("");
							continue;
						}
						catch(ErrorException e){
						}
					}
				}while(swopCourseIndex==null);
				
				Student stud2 = new Student(swopUserId);
				srm.swopIndex(stud, courseIndex, stud2, swopCourseIndex);
				break;
				
			case 7:
				System.out.println("Logging Out from Stars Application.");
				break;
			default : 
				break;
			}
			System.out.println("");
		}while(subMenuOption!=7);
		stud = null;
		srm = null;
	}
}
