package entity;
import java.util.StringTokenizer;

import App.AdminApp;
import App.StudentApp;
import CRUD.CourseManager;
import enums.*;
import util.Database;
import util.DateTimeHelper;
import util.TextDB;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
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
		 
		 AdminApp a = new AdminApp();
		 a.printAdminMenu();	
		 
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

	}

}
