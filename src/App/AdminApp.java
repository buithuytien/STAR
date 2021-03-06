package App;

import java.io.*;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

import CRUD.*;
import enums.*;
import entity.*;
import util.*;

/**
 * class prompting for user's input for admin features
 * @author BUITT
 *
 */
public class AdminApp {
	public static void printAdminMenu() {
		Scanner sc = new Scanner(System.in);
		int subMenuOption = 0, subMenuOption2 = 0;
		CourseManager cm = new CourseManager();
		StudentManager sm = new StudentManager();
		PeopleManager pm = new PeopleManager();
		Database db = new Database(System.getProperty("user.dir") + "\\src\\database\\");
		DBObj dbo = new DBObj();	
		boolean repeat;
		Student s = new Student();
		
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
			String startTime = null, endTime = null;
			Date startReg = null, endReg = null;
			switch(subMenuOption){
			case 1: // 1 Edit student access period				
				do{
					System.out.print("Enter student matric number to edit access period: ");
					
					try{ //ADDED BY JY
						studMatric = sc.next();	
						if(pm.checkPplType(studMatric) != 'S') {
							throw new ErrorException("studentmatric");
						}
					}
					catch(InputMismatchException e){
						System.out.println("Please enter a valid matric no:");
						studMatric = sc.next();
					}
					catch(ErrorException e){
					}

					
					try {
						Student stud = dbo.getStudentObj(studMatric);
						System.out.println("Access Start Time: " + stud.getStartTime() + " and End Time: " + stud.getEndTime());
						
						do {
							Scanner sc2 = new Scanner(System.in);
							System.out.print("Enter access start time with format: dd/mm/yyyy HH:mm:ss ");
							startTime = sc2.nextLine();
							try {
								startReg = TimeHelper.GetStudentDateTime(startTime);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								System.out.println("ERROR: Incorrect date time format");
								startTime = null;
							}
						} while (startTime == null);
						
						do {
							Scanner sc2 = new Scanner(System.in);
							System.out.print("Enter access end time with format: dd/mm/yyyy HH:mm:ss ");
							endTime = sc2.nextLine();
							try {
								endReg = TimeHelper.GetStudentDateTime(endTime);
							} catch (ParseException e) {
								System.out.println("ERROR: Incorrect date time format");
								endTime = null;
							}
						} while (endTime == null);

						sm.changeAccessPeriod(stud, startReg, endReg);

						
					} catch (ParseException e1) {
						System.out.println("ERROR: Incorrect date time format");
						startTime = null;
					} catch (ErrorException e2) { // custom exception from dbo.getStudentObj
						System.out.println("Please try again");
						studMatric = null;
					}
					
				} while(studMatric == null || startTime == null);

				break;
				
			case 2: // Add student
				studMatric = null;
				do{
					System.out.print("Enter student matric number to create: ");
					
					while(true) {
						try{ //ADDED BY JY
							studMatric = sc.next();	
							if(db.findRecord(studMatric, "Users", 2) > 0) { //  record found
								throw new ErrorException("matricFound");
							}
							break;
						}
						catch(InputMismatchException e){
							System.out.println("Please enter a valid matric number: ");
							studMatric = sc.next();
							System.out.println("");
							continue;
						}
						catch(ErrorException e){
						}
					}
				}while(studMatric==null);
				s.setStudMat(studMatric);
				
				// asking for other details
				String name, faculty, nat; //email, startTime, endTime - auto generated
				int year, phone;				
				char gender;
				//name
				do{
					System.out.print("Enter student name ");
					String regex = "(.)*(\\d)(.)*";      
					Pattern pattern = Pattern.compile(regex);
					
					while(true) {
						try{ //ADDED BY JY
							name = sc.next();	
							if(pattern.matcher(name).matches()) {
								throw new ErrorException("nameError");
							}
							break;
						}
						catch(InputMismatchException e){
							System.out.println("Please enter a valid name (without digits): ");
							name = sc.next();
							System.out.println("");
							continue;
						}
						catch(ErrorException e){
						}
					}
				}while(name==null);
				s.setStudName(name);
				//gender
				do{
					System.out.print("Enter gender (F/M): ");
					
					while(true) {
						try{ //ADDED BY JY
							gender = sc.next().charAt(0);
							if(gender == 'F' || gender == 'M') {
								
							} else {
								throw new ErrorException("genderError");
							}
							break;
						}
						catch(InputMismatchException e){
							System.out.println("Please enter a valid gender (F/M) : ");
							gender = sc.next().charAt(0);
							System.out.println("");
							continue;
						}
						catch(ErrorException e){
						}
					}
				}while(gender==' ');
				s.setStudGender(gender);
				//faculty
				do{
					System.out.print("Enter faculty (eg.Math): ");
					String regex = "(.)*(\\d)(.)*";      
					Pattern pattern = Pattern.compile(regex);
					while(true) {
						try{ //ADDED BY JY
							faculty = sc.next();	
							if(pattern.matcher(faculty).matches()) {
								throw new ErrorException("facultyError");
							}
							break;
						}
						catch(InputMismatchException e){
							System.out.println("Please enter a valid faculty: ");
							faculty = sc.next();
							System.out.println("");
							continue;
						}
						catch(ErrorException e){
						}
					}
				}while(faculty==null);
				s.setFaculty(faculty);
				//year
				do{
					System.out.print("Enter year of study: ");
					
					while(true) {
						try{ //ADDED BY JY
							year = sc.nextInt();	
							if(year>5 || year<1) {
								throw new ErrorException("yearError");
							}
							break;
						}
						catch(InputMismatchException e){
							System.out.println("Please enter a valid year: ");
							year = sc.nextInt();
							System.out.println("");
							continue;
						}
						catch(ErrorException e){
						}
					}
				}while(year==' ');
				s.setStudYear(year);
				//nationality
				do{
					System.out.print("Enter nationality (eg. SG): ");
					String regex = "(.)*(\\d)(.)*";      
					Pattern pattern = Pattern.compile(regex);
					while(true) {
						try{ 
							nat = sc.next();
							if(pattern.matcher(nat).matches()) {
								throw new ErrorException("natError");
							}
							break;
						}
						catch(InputMismatchException e){
							System.out.println("Please enter a valid nationality: ");
							nat = sc.next();
							System.out.println("");
							continue;
						}
						catch(ErrorException e){
						}
					}
				}while(nat==null);
				s.setStudNat(nat);
				//phone
				do{
					System.out.print("Enter SG Phone Number: ");
					
					while(true) {
						try{ 
							Scanner sc_phone = new Scanner(System.in);
							phone = sc_phone.nextInt();
							if(String.valueOf(phone).length() == 8) {
							} else { 
								throw new ErrorException("phoneError");
							}
							break;
						}
						catch(InputMismatchException e){
							System.out.println("Please enter a valid SG phone number: ");
//							phone = sc.nextInt();
//							System.out.println("");
							continue;
						}
						catch(ErrorException e){
						}
					}
				}while(phone==' ');
				s.setStudNum(phone);
				
				do {
					Scanner sc2 = new Scanner(System.in);
					System.out.print("Enter access start time with format: dd/mm/yyyy HH:mm:ss ");
					startTime = sc2.nextLine();
					try {
						startReg = TimeHelper.GetStudentDateTime(startTime);
					} catch (ParseException e) {
						System.out.println("ERROR: Incorrect date time format");
						startTime = null;
					}
				} while (startTime == null);
				try {
					s.setStartTime(startTime);
				} catch (ParseException e1) {
					System.out.println("ERROR: Incorrect date time format");
					e1.printStackTrace();
				}
				do {
					Scanner sc2 = new Scanner(System.in);
					System.out.print("Enter access end time with format: dd/mm/yyyy HH:mm:ss ");
					endTime = sc2.nextLine();
					try {
						endReg = TimeHelper.GetStudentDateTime(endTime);
					} catch (ParseException e) {
						System.out.println("ERROR: Incorrect date time format");
						endTime = null;
					}
				} while (endTime == null);
				try {
					s.setEndTime(endTime);
				} catch (ParseException e1) {
					System.out.println("ERROR: Incorrect date time format");
					e1.printStackTrace();
				}
				sm.createNewStudent(s);	
				break;
				
			case 3: // Add a new course
				repeat = true;
				while(repeat) {
					System.out.print("Enter a new course code to create: "); // , or press Enter to return
					courseCode = sc.next();
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
					System.out.println("Current hosting school" + db.getRecord(courseCode, "CourseInfo", 0)[2]);
					System.out.print("Enter new school code for course " + courseCode + ": ");
					String newSchoolCode = sc.next();
					cm.updateCourseSchool(courseCode, newSchoolCode);
					break;
					
				case 3: // add a new index number to an existing course code
					// verify if this index number exists. If exists return null and do nothing;	
					CourseIndex ci = printNewIndexMenu();
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
					ArrayList<String> strIndex = db.returnIndexRecord(courseCode, 1);
					System.out.println("Existing index number in course " +  courseCode + " : ");
					for(int i = 0; i < strIndex.size(); i++) {
						System.out.print(strIndex.get(i) + "  ");
					}
					
					index = null;
					do {
						System.out.print("\nEnter the index number to update vacancy: ");
						index = sc.next();
						if(! strIndex.contains(index)) {
							System.out.println("ERROR: Invalid index entry. Please try again");
							index = null;
						}
					} while (index == null);
						
					System.out.println("Current vacancy in index " + index + ": " + db.getRecord(index, "Course", 0)[2]);
					System.out.print("Enter new vacancy to update: ");
					
					int vacancy = sc.nextInt();
					cm.editIndexVacancy(index, vacancy);
					break;
					
				case 5: // exit
					break;
					
				default:
					break;
				}
				break;
			case 5: // Check vacancy based on index number
				index = null;						
				// verify if this index number exists. If exists return null and do nothing;	
				do {
					System.out.print("Enter an index number: ");
					index = sc.next();
					boolean courseIndexExist  = cm.checkCourseIndexExist(index);
					if(!courseIndexExist) {
						index = null;
						System.out.println("ERROR: Course index NOT found. Please try again"); // or press Enter to return.
					}
				} while(index == null);
				
				int vacancy = cm.returnIndexVacancy(index);
				
				break;
				
			case 6: // Print student list by index number
				repeat = true;
				while(repeat) {
					System.out.print("Enter an index number to print student list: ");
					index = sc.next();
					boolean courseIndexExist  = cm.checkCourseIndexExist(index);
					if(!courseIndexExist) {
						System.out.println("ERROR: Course index not existed. Please try again"); // or press Enter to return.
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
						System.out.println("ERROR: Course code not existed. Please try again"); // or press Enter to return.
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
		
	}
	
	/**
	 * helper method to create a new index number, by prompting for user's input of new index number and check its existence
	 * if index number already exists, it will keep prompting for user's input
	 * @return a partial courseIndex object. 
	 * other information added later: group number, coursetype, GERPE type, aus
	 */
	
	private static CourseIndex printNewIndexMenu() {
		Scanner sc = new Scanner(System.in);
		CourseManager cm = new CourseManager();
		String index = null;		
		
		// verify if this index number exists. If exists return null and do nothing;	
		do {
			Scanner sc1 = new Scanner(System.in);
			System.out.print("Enter a new index number: ");
			index = sc1.next();
			boolean courseIndexExist  = cm.checkCourseIndexExist(index);
			if(courseIndexExist) {
//				throw new ErrorException("recordFound");
				index = null;
				System.out.println("ERROR: Course index already existed. Please try again"); // or press Enter to return.
			}
		} while(index == null);
		
		CourseIndex ci = new CourseIndex(index);
		System.out.print("Please enter group number in index" + index + ": ");
		int grpNum = sc.nextInt();
		ci.setGrpNum(grpNum);
		
		System.out.print("Please enter vacancy in index " + index + ": ");
		int vacancy = sc.nextInt();
		ci.setVacancy(vacancy);		
		
		System.out.print("How many sessions in index number " + index + ": ");
		int n_session = sc.nextInt();
		

		ArrayList<ClassType> classTypes = new ArrayList<ClassType>();
		String typeStr = null, dayStr = null, startTimeStr = null, endTimeStr = null, venue;
		for(int i = 0 ; i < n_session; i++) {
			
			System.out.println("Enter the class info for session # " + (i+1));
			ClassType ct = new ClassType();
			do {
				Scanner sc2 = new Scanner(System.in);
				System.out.print("Enter class type. Only accept either {LEC, TUT, LAB}: ");
				typeStr = sc2.next();
				try {
					InstructionType type = InstructionType.valueOf(typeStr);
					ct.setType(type);
				} catch (IllegalArgumentException e) {
					typeStr = null;
					System.out.println("ERROR: Incorrect class type format. Please try again");
				}
			} while (typeStr == null);
			
			do {
				Scanner sc2 = new Scanner(System.in);
				System.out.print("Enter weekday of class. Only accept either {MONDAY, TUESDAY, ...}: ");
				dayStr = sc2.next();
				try {
					DayOfWeek day = DayOfWeek.valueOf(dayStr);
					ct.setDay(day);
				} catch (IllegalArgumentException e) {
					dayStr = null;
					System.out.println("ERROR: Incorrect day of week format. Please try again");
//					e.printStackTrace();
				}
			} while (dayStr == null);
			
			do {
				Scanner sc2 = new Scanner(System.in);
				System.out.print("Enter start time of class in for mat HH:mm ");
				startTimeStr = sc2.next();
				try {
					LocalTime startTime = TimeHelper.convertStringToTime(startTimeStr);
					ct.setStartTime(startTime);
				} catch (DateTimeParseException e) {
					startTimeStr = null;
					System.out.println("ERROR: Incorrect time format. Please try again");
				}
			} while (startTimeStr == null);
			
			do {
				Scanner sc2 = new Scanner(System.in);
				System.out.print("Enter end time of class in for mat HH:mm ");
				endTimeStr = sc2.next();
				try {
					LocalTime endTime = TimeHelper.convertStringToTime(endTimeStr);
					ct.setEndTime(endTime);
				} catch (DateTimeParseException e) {
					endTimeStr = null;
					System.out.println("ERROR: Incorrect time format. Please try again");
				}
			} while (endTimeStr == null);
			
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Enter venue");
			venue = sc2.next();
			ct.setVenue(venue);
			
			classTypes.add(ct);
		}
		
		ci.setClassList(classTypes);
		return ci;
	}
}