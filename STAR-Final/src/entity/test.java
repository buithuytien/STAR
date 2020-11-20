package entity;
import java.util.StringTokenizer;


import App.AdminApp;
import App.StarsLogin;
import App.StudentApp;
import CRUD.CourseManager;
import enums.*;
import util.Database;

import util.TextDB;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String raw = "LEC MONDAY 10:00 09:00 LT12"; // must be in this format
//		
//		StringTokenizer star = new StringTokenizer(raw);
//		
//		String typeStr = star.nextToken().trim();
//		String dayStr = star.nextToken().trim();
//		String startTimeStr = star.nextToken().trim();
//		String endTimeStr = star.nextToken().trim();
//		String venue = star.nextToken().trim();	
//		
//		InstructionType type = InstructionType.valueOf(typeStr);
//		DayOfWeek day = DayOfWeek.valueOf(dayStr);
//		LocalTime startTime = DateTimeHelper.convertStringToTime(startTimeStr);	
//		LocalTime endTime = DateTimeHelper.convertStringToTime(endTimeStr);	
//		
//		System.out.println(type.getClass());
//		System.out.println(day.getClass());
//		System.out.println(startTime.getClass());
//		System.out.println(endTime.getClass());
//		System.out.println(venue.getClass());
//		
//		// compare 2 times/ 2 days of week
//		int timeDif = endTime.compareTo(startTime); // -1, 0, or 1
//		System.out.println(timeDif);
//		
//		DayOfWeek day2 = DayOfWeek.valueOf("MONDAY");
//		System.out.println(day.equals(day2) );
		
		 System.out.println("Working Directory = " + System.getProperty("user.dir"));
		 
//		 Database db = new Database(System.getProperty("user.dir") + "\\src\\database\\");
//		 db.setFilename("Course");
//		 String[] course_info = db.getCourseInfoRecord("20009");
//		 for(int i = 0; i < course_info.length; i++ ) {
//			 System.out.print(course_info[i] + " ");
//		 }
		 
//		 System.out.println("10009 found at");
//		 System.out.println(db.findCourseIndexRecord("10009"));
//		 System.out.println("20009 found at" );
//		 System.out.println(db.findCourseIndexRecord("20009"));			
		 
//		 CourseManager cm = new CourseManager();
//		 Course course = cm.getCourseObj("AB1234");
//		 Course course2 = cm.getCourseObj("QW1234");
//		 int test1 = cm.editIndexVacancy("10009", 10);
//		 int test2 = cm.editIndexVacancy("10009", -10);
//		 int test3 = cm.editIndexVacancy("20009", 10);
		 
//		 Database db = new Database(System.getProperty("user.dir") + "\\src\\database\\", "filename_test");
//		 int [] aa = db.findAllRecord("AB1234", 1, "Course");
//		 for(int i = 0; i < aa.length; i ++) {
//			 System.out.print(aa[i] + " ");
//		 }
//		 db.updateFile(1, 1, "test11");
		 

//		 
//		 Scanner sc = new Scanner(System.in);
//		 Course course = new Course("AA1111");
//		 System.out.print("Enter school code ");
//		 String schoolCode = sc.next();
//		 course.setSchoolCode(schoolCode);
//		 // get index
//		 System.out.print("Enter number of indices in couse" + "AA1111" + " : ");
//		 int n_index = sc.nextInt();
//		 ArrayList<CourseIndex> indices = new ArrayList<CourseIndex>();
//			for(int i = 0; i < n_index; i++) {
//				System.out.println("Enter the details of index # " + i+1 + ": ");
//				CourseIndex ci = a.printNewIndexMenu();
////				ci.setCourseCode(courseCode);
////				ci.setCourseType(courseType);
////				ci.setGERType(GERType);
////				ci.setIndexAU(au);
//				indices.add(ci);
//			}
//			
//			course.setCourseIndexList(indices);
//			
//			String[] courseDB = course.toStringDB();
//			for(int i = 0; i < courseDB.length; i ++) {
//				System.out.println(courseDB[i]  + "  ");
//			}		
		 
//		 DBObj dbo = new DBObj();
//		 Scanner sc = new Scanner(System.in);
//		 System.out.print("Enter matric: ");
//		 String matric = sc.next();
//
//		 try {
//			 Student stud = dbo.getStudentObj(matric);
//			 System.out.println(stud.getStartTime() );
//		 } catch (ParseException e) {
//			 // TODO Auto-generated catch block
//			 e.printStackTrace();
//		 }
//		 
//		 InstructionType type = InstructionType.valueOf("lec");
		 
//		 CourseIndex ci = AdminApp.printNewIndexMenu();
//		 System.out.println(ci.getClassList().get(0).toString() );
//		 System.out.println(ci.getClassList().get(0).getStartTime() );
//		 System.out.println(ci.getClassList().get(0).getEndTime() );
		 
		 Faculty s = new Faculty();
		 s.setFaculty("math");
		 System.out.println(s.getFaculty());

		 DBObj dbo = new DBObj();	
		 Database db = new Database(System.getProperty("user.dir") + "\\src\\database\\", "Users");
		 int i = db.findRecord("U123456B", "Users", 2);
		 System.out.println("i = " + i);
		 
//		 ClassType ct = new ClassType("LEC MONDAY 10:00:00 13:00 LT12");
		 
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); //
		 Date aa = null;
		 try {
			 aa = dateFormat.parse("19/11/20 08:08:08");
		 } catch (ParseException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 System.out.println(aa);
		 
		 AdminApp a = new AdminApp();
		 a.printAdminMenu();

	}

}
