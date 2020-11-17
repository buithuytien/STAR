package App;

import java.io.*;
import java.util.*;
import enums.*;
import entity.*;
import util.*;

public class StudentApp {
	public static void printStudentMenu(Student stud){
		Date currentDayTime = new Date();
		Scanner sc = new Scanner(System.in);
		
		StudentRegistration sr = new StudentRegistration();
		
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
					try{
						System.out.print("Enter index number of course to add: ");
						courseIndex = sc.next();
						sr.addIntoList(stud, courseIndex);
					}
					catch(InputMismatchException e){
						System.out.println("Please enter a valid index number");
						sc.nextLine();
						System.out.println("");
					}
				}while(courseIndex==null);

				break;
			case 2:
				do{
					try{
						System.out.print("Enter index no of course to drop: ");
						courseIndex = sc.next();
						sr.dropFromList(stud, courseIndex);
					}
					catch(InputMismatchException e){
						System.out.println("Please enter a valid index number");
						sc.nextLine();
						System.out.println("");
					}
				}while(courseIndex==null);
				break;
			case 3:
				sr.printCourseReg(stud);
				break;
			case 4:
				sc.nextLine();
				System.out.print("Enter course index to check vacancy: ");
				courseIndex = sc.next();
				sr.checkVacancies(courseIndex);
				break;
			case 5:
				do{
					try{
						System.out.print("Enter current index number: ");
						courseIndex = sc.next();
						System.out.println("");
						System.out.print("Enter index you want: ");
						swopCourseIndex = sc.next();
						System.out.println("");
						sr.changeIndex(stud, courseIndex, swopCourseIndex);
					}
					catch(InputMismatchException e){
						System.out.println("Please enter a valid index number");
						sc.nextLine();
						System.out.println("");
						System.out.print("Enter index you want: ");
						swopCourseIndex = sc.next();
						System.out.println("");
					}
				}while(courseIndex==null || swopCourseIndex==null);
				break;
			case 6:
				do{
					try{
						Console console = System.console();
						System.out.print("Enter your index no: ");
						courseIndex = sc.next();
						sc.nextLine();
						System.out.print("Enter peer student Id: ");
						swopUserId = sc.nextLine();
						swopUserPassword = String.copyValueOf((console.readPassword("Enter peer student password: ")));
						System.out.print("Enter peer index no to swop: ");
						swopCourseIndex = sc.next();

						if(people.signIn(swopUserId, swopUserPassword) == 'S'){
							Student stud2 = new Student(swopUserId);
							sr.swopIndex(stud, courseIndex, stud2, swopCourseIndex);
						}
					}
					catch(InputMismatchException e){
						System.out.println("Please enter a valid index number");
						sc.nextLine();
						System.out.println("");
					}
				}while(courseIndex==null || swopCourseIndex==null);
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
		sr = null;
	}
}
