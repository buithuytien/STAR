package App;

import java.io.*;
import java.util.*;

import CRUD.*;
import enums.*;
import entity.*;
import util.*;

public class AdminApp {
	public static void printAdminMenu() {
		Scanner sc = new Scanner(System.in);
		int subMenuOption = 0, subMenuOption2 = 0;
		CourseManager cm = new CourseManager();
		Database db = new Database(System.getProperty("user.dir") + "\\src\\database\\");
		boolean repeat;
		
		do{
		    System.out.println("=========Welcome to Admin Menu===============");
			System.out.println("1 Edit student access period");
			System.out.println("2 Add student");
			System.out.println("3 Add course");
			System.out.println("4 Update course");
			System.out.println("5 Check vacancy based on index number");
			System.out.println("6 Print student list by index number");
			System.out.println("7 Print student list by course");
			System.out.println("8 Exit");
			System.out.println("=============================================");
			System.out.print("Enter your choice: ");
			try{
				subMenuOption = sc.nextInt();

				if(subMenuOption<0 || subMenuOption>8)
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
			
			String studMatric = null, courseCode = null, index = null;
			switch(subMenuOption){
			case 1:
				
				do{
					try{
						System.out.print("Enter student matric number to edit access period: ");
						studMatric = sc.next();
						// TODO: edit student access period
					}
					catch(InputMismatchException e){
						System.out.println("Please enter a valid index number");
						sc.nextLine();
						System.out.println("");
					}
				} while(studMatric == null);

				break;
				
			case 2:
				// TODO
				break;
				
			case 3: // Add a new course
				repeat = true;
				while(repeat) {
					System.out.print("Enter a new course code to create: "); // , or press Enter to return
					courseCode = sc.next();
//					if(courseCode.equals("") ) {
//						repeat = false;
//						break;
//					}
					// if course code already exist, ask for a new one
					boolean courseCodeExist = cm.checkCourseCodeExist(courseCode);
					if(courseCodeExist) {
						System.out.println("Course code already existed. Please try again"); // or press Enter to return.
					} else {
						repeat = false;
					}
				}
				// ask for details: school code, aus, gerpe type, 
				System.out.println("Enter details of new course " + courseCode);
				System.out.print("Enter school code ");
				String schoolCode = sc.next();
				
				System.out.print("Enter course type (CORE, MPE, GERPE): ");
				String courseType = sc.next();
				
				String GERType = "";
				if(courseType.equals("GERPE")) {
					System.out.print("Enter GER PE type if applicable: ");
					GERType = sc.next();
				}
				
				System.out.print("Enter number of AUs: ");
				int au = sc.nextInt();
				
				System.out.print("Enter number of indices in couse" + courseCode + " : ");
				int n_index = sc.nextInt();
				
				ArrayList<CourseIndex> indices = new ArrayList<CourseIndex>();
				for(int i = 0; i < n_index; i++) {
					System.out.println("Enter the details of index # " + i+1 + ": ");
					CourseIndex ci = printNewIndexMenu();
					ci.setCourseCode(courseCode);
					ci.setCourseType(courseType);
					ci.setGERType(GERType);
					ci.setIndexAU(au);
					indices.add(ci);
				}
				
				Course course = new Course(courseCode);
				course.setCourseIndexList(indices);
				course.setSchoolCode(schoolCode);
				
				cm.addNewCourse(course);
				break;
				
			case 4: // Update course
				repeat = true;
				while(repeat) {
					System.out.print("Enter course code to update: "); // , or press Enter to return
					courseCode = sc.next();
//					if(courseCode.equals("") ) {
//						repeat = false;
//						break;
//					}
					// check if course code available first
					boolean courseCodeExist = cm.checkCourseCodeExist(courseCode);
					if(!courseCodeExist) {
						System.out.println("Course code not existed. Please try again"); // or press Enter to return.
					} else {
						repeat = false;
					}
				} 
				
				// if yes, load the following menu
				System.out.println("=========Update Course Menu===============");				
				System.out.println("1 Update course code");
				System.out.println("2 Update hosting school");
				System.out.println("3 Add a new index number");
				System.out.println("4 Update vacancy of its existing index numbers");
				System.out.println("5 Exit");
				
				try{
					subMenuOption2 = sc.nextInt();

					if(subMenuOption2 < 0 || subMenuOption2 > 5)
						throw new ErrorException("choice");
				}
				catch(InputMismatchException e){
					System.out.println("Please enter a valid choice");
					sc.nextLine();
					System.out.println("");
					subMenuOption2 = 0;
				}
				catch(ErrorException e){
				}
				System.out.println("");
				
				switch(subMenuOption2) {
				case 1: // update course code. change in both database
					// make sure new course code is not existed
					repeat = true;
					String courseCodeNew = courseCode;
					while(repeat) {
						System.out.print("Enter a new course code to update" + courseCode + " into: "); // , or press Enter to return
						courseCodeNew = sc.next();
//						if(courseCode.equals("") ) {
//							repeat = false;
//							break;
//						}
						// if course code already exist, ask for a new one
						boolean courseCodeExist = cm.checkCourseCodeExist(courseCodeNew);
						if(courseCodeExist) {
							System.out.println("Course code already existed. Please try again"); // or press Enter to return.
						} else {
							repeat = false;
						}
					}
					cm.updateCourseCode(courseCode, courseCodeNew);
					break;
					
				case 2: // update host school
					System.out.print("Enter new school code for course " + courseCode + ": ");
					String newSchoolCode = sc.next();
					cm.updateCourseSchool(courseCode, newSchoolCode);
					break;
					
				case 3: // add a new index number to an existing course code
					// verify if this index number exists. If exists return null and do nothing;	
					CourseIndex ci = printNewIndexMenu();
//					System.out.println(ci.toStringDB());
					// get info from an index from a similar course code
					String[] indexInfo = db.getRecord(courseCode, "Course", 1);
					ci.setCourseCode(courseCode);
					ci.setCourseType(indexInfo[6]);
					ci.setGERType(indexInfo[7]);
					ci.setIndexAU(Integer.parseInt(indexInfo[8]));
					
//					 create new course index and save to db
					cm.addIndex2ExistingCourse(ci);					
					break;
					
				case 4: // update vacancy of its index
					System.out.print("Enter the index number to update vacancy: ");
					index = sc.next();
					System.out.print("Enter new vacancy to update: ");
					int vacancy = sc.nextInt();
					int editVacancy  = -1; // unsucessful update
					do {
						editVacancy =  cm.editIndexVacancy(index, vacancy);
					} while(editVacancy < 0);
					
					
				case 5: // exit
					break;
				default:
					break;
				}
			case 5: // Check vacancy based on index number
				// verify if this index number exists. If exists return null and do nothing;	
				
				break;
				
			case 6: // Print student list by index number
				repeat = true;
				while(repeat) {
					System.out.print("Enter an index number to print student list: ");
					index = sc.next();
					boolean courseIndexExist  = cm.checkCourseIndexExist(index);
					if(!courseIndexExist) {
						System.out.println("Course index not existed. Please try again"); // or press Enter to return.
					} else {
						repeat = false;
					}
				}
				cm.printStdByIndex(index);
				break;
				
			case 7: // Print student list by course code
				repeat = true;
				while(repeat) {
					System.out.print("Enter a course code to print student list: ");
					courseCode = sc.next();
					boolean courseCodeExist  = cm.checkCourseCodeExist(courseCode);
					if(!courseCodeExist) {
						System.out.println("Course code not existed. Please try again"); // or press Enter to return.
					} else {
						repeat = false;
					}
				}
				cm.printStdByCourse(courseCode);
				break;
				
			case 8: // 
				System.out.println("Logging Out from Stars Application.");
				break;
			default : 
				break;
			}			
		}while(subMenuOption!=8);
//		sr = null;
		
	}
	// TODO:
	// create method for creating course index information: sessions details, enter index code, check if index exists, then not allow; arg: couseCode, index
	// use for create a new index in 1 course, and create a 
	// finished as new course saved to database
	public static CourseIndex printNewIndexMenu() {
		// return a partial courseIndex object. other information added later: group number, coursetype, GERPE type, aus
		// then convert to string, then call db method to save.
		Scanner sc = new Scanner(System.in);
		CourseManager cm = new CourseManager();
		String index = null;
		
		
		// verify if this index number exists. If exists return null and do nothing;	
		boolean repeat = true;
		while(repeat) {
			System.out.print("Enter a new index number: ");
			index = sc.next();
			boolean courseIndexExist  = cm.checkCourseIndexExist(index);
			if(courseIndexExist) {
				System.out.println("Course index already existed. Please try again"); // or press Enter to return.
			} else {
				repeat = false;
			}
		}
		
		CourseIndex ci = new CourseIndex(index);
		System.out.print("Please enter group number in " + index + ": ");
		int grpNum = sc.nextInt();
		ci.setGrpNum(grpNum);
		
		System.out.print("Please enter vacancy in " + index + ": ");
		int vacancy = sc.nextInt();
		ci.setVacancy(vacancy);		
		
		System.out.print("How many sessions in index number " + index + ": ");
		int n_session = sc.nextInt();
		
//		String[] classListRaw = new String[n_session];
		ArrayList<ClassType> classTypes = new ArrayList<ClassType>();
//		ClassType [] classTypes = new ClassType[n_session];
		for(int i = 0 ; i < n_session; i++) {
			// TODO:  add check pattern here if have time
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Enter session info with the following format");
			System.out.println("CLASS_TYPE DAY START_TIME(hh:mm) END_TIME(hh:mm) VENUE. EXAMPLE:");
			System.out.println("LAB TUESDAY 09:00 11:00 SCELAB1");
			String session_info = sc2.nextLine();
			ClassType ct = new ClassType(session_info);
			classTypes.add(ct);
			if(i == n_session-1) { // final loop
//				sc2.close();
			}
		}
		
		ci.setClassList(classTypes);
//		sc.close();
		return ci; // default, if error exists.
	}
}