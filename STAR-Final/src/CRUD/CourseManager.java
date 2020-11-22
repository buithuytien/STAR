package CRUD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.ClassType;
import entity.Course;
import entity.CourseIndex;
import entity.DBObj;
import util.Database;

public class CourseManager {
	Database db = new Database(System.getProperty("user.dir") + "\\src\\database\\");	
	DBObj dbo = new DBObj(); //ADDED BY JY

	public void editIndexVacancy(String index, int vacancy) {
		db.setFilename("Course");
		int index_line = db.findCourseIndexRecord(index, "Course");
		if(index_line < 0) { // course index not found
			System.out.println("Course index not found in database. Please create one or try again!");
			return ;
		} 
		// if index exists, create a course index object, and edit vacancy, then save to file. If no, do nothing, return 0;
		// course index found at line index_line
//		CourseIndex ci = getCourseIndexObj(index); //COMMENTED OUT BY JY
		CourseIndex ci = dbo.getCourseObj(index); // ADDED BY JY
//		System.out.println("Current vacancy of index " + index + "is: " + ci.getVacancy()); //COMMENTED OUT BY TT
//		System.out.println("Current number of registered students " + ci.getRegisteredIDs().size()); //ADDED BY JY
//		System.out.println("Current number of registered students " + ci.getNumRegisteredIDs()); //COMMENTED OUT BY JY
		
		ci.setVacancy(vacancy); // we will save this object to file
		int updated_vacancy = ci.getVacancy();
		
		if(updated_vacancy == vacancy) {
			// update file here
			db.updateFile(index_line, 2, String.valueOf(updated_vacancy)); // 2 is the position of vacancy in a row
			System.out.println("Index " + index + "vacancy updated sucessfully to " + updated_vacancy);
			return;
		} else {
			System.out.println("Index " + index + "vacancy NOT updated");
			return ;
		}
	}
	
	public int returnIndexVacancy(String index) {
		db.setFilename("Course");
		int index_line = db.findCourseIndexRecord(index, "Course");
		if(index_line < 0) { // course index not found
			System.out.println("Course index not found in database. Please create one or try again!");
			return -1;
		} 
		// if index exists, create a course index object, and edit vacancy, then save to file. If no, do nothing, return 0;
		// course index found at line index_line
//		CourseIndex ci = getCourseIndexObj(index); //COMMENTED OUT BY JY
		CourseIndex ci = dbo.getCourseObj(index); // ADDED BY JY
		int vacancy = ci.getVacancy();
		System.out.println("Index " + index + " has " + vacancy + " vacancies");
		return vacancy;
	}

	
	public void addIndex2ExistingCourse(CourseIndex ci) { // add a new course index to existing course 
		// TODO: implement this
		// return null means not created
		Database db = new Database(System.getProperty("user.dir") + "\\src\\database\\");
		DBObj dbo = new DBObj(); //ADDED BY JY
		
		// get string that saved to db
		//String[] stringCourseIndexDB = ci.toStringDB(); //COMMENTED BY JY
		String[] stringCourseIndexDB = dbo.setCourseRow(ci); //ADDED BY JY
		db.appendFile(stringCourseIndexDB, "Course");
		
		// add new index string to CourseInfo, update to file
		db.setFilename("CourseInfo");		
		int line = db.findRecord(ci.getCourseCode(), "CourseInfo", 0);		
		if(line < 0) { //  course not created before
//			String [] stringCourseInfoDB = {ci.getCourseCode(), ci.getIndex(), ""};
			System.out.println("Course not created. Please try again");
			return;
		} 
		
		String [] stringCourseInfoDB = db.getRecord(ci.getCourseCode(), "CourseInfo", 0); //  find course code at column 0
		stringCourseInfoDB[1] += ";" + ci.getIndex();
		db.updateFile(line, 1, stringCourseInfoDB[1]);					
		System.out.println("New course index " + ci.getIndex() + " created and saved to database");		
	}
	
	public void addNewCourse(Course course) { // add a new course index to existing course 
		// TODO: implement this
		// return null means not created
		Database db = new Database(System.getProperty("user.dir") + "\\src\\database\\");
		
		DBObj dbo = new DBObj(); //ADDED BY JY
		
		// get string that saved to db
		ArrayList<CourseIndex> indices = course.getCourseIndexList();
		db.setFilename("Course");	
		for(int i = 0; i < indices.size(); i++ ) {
			CourseIndex ci = indices.get(i);
			//String [] stringCourseIndexDB = ci.toStringDB(); //COMMENTED BY JY
			String [] stringCourseIndexDB = dbo.setCourseRow(ci); //ADDED BY JY
			db.appendFile(stringCourseIndexDB, "Course");			
		}
		
		// append new line to CourseInfo, update to file
		db.setFilename("CourseInfo");	
		db.appendFile(dbo.setCourseInfoRow(course), "CourseInfo"); //ADDED BY JY
		//db.appendFile(course.toStringDB(), "CourseInfo"); //COMMENTED BY JY
		
		System.out.println("New course " + course.getCourseCode() + " created and saved to database");		
	}
	
	
	
	
	
	public String removeCourseIndex() {
		// only allowed if no students has registered
		return null;
		
	}
	
	public int updateCourseCode(String oldCourseCode, String newCourseCode) { // String course_code. change info in 2 database
		/*/ TODO
		 * get all records from Course.csv, update
		 * get all records from CourseInfo.csv, only 1 line, update.
		 */
		// from CourseInfo.csv
		db.setFilename("CourseInfo");
		int line1 = db.findRecord(oldCourseCode, "CourseInfo");
		if(line1 < 0) { // not found
			return -1;
		}
		db.updateFile(line1, 0, newCourseCode);
		
		// from Course.csv
		db.setFilename("Course");
		int[] line2 = db.findAllRecord(oldCourseCode, 1, "Course");
		if(line2 == null || line2.length == 0) {
			return -1; // record not found
		}
		for(int i = 0; i < line2.length; i++) {
			db.updateFile(line2[i], 1, newCourseCode);
		}
		return 1;		
	}
	
	public int updateCourseSchool(String oldCourseCode, String newSchoolCode) {
		// from CourseInfo.csv
		db.setFilename("CourseInfo");
		int line1 = db.findRecord(oldCourseCode, "CourseInfo");
		if(line1 < 0) { // not found
			return -1;
		}
		db.updateFile(line1, 2, newSchoolCode);
		return 1;		
	}
	
	/*/
	 *  PRINT STUDENT LIST METHODS
	 */
	public void printStdByIndex(String index) {
		db.setFilename("Course");
		String[] indexInfo = db.getRecord(index, "Course", 0);
		if(indexInfo == null || indexInfo.length == 0) {
			return ; // course index not found
		}
		
		if(!indexInfo[3].equals("")) {
			String[] stdList = indexInfo[3].split(";");
			System.out.println("List of " + stdList.length + " students sucessfully registered to index " + index + ":");
			for(int i = 0; i < stdList.length; i++) {
//				if()
				System.out.println(stdList[i]);			
			}	
		} else {
			System.out.println("0 student registered to index " + index); 
		}
		
		//print
			
		return ; //  record found
	}
	
	public int printStdByCourse(String courseCode) {
		db.setFilename("CourseInfo");
		String[] CourseInfo = db.getRecord(courseCode, "CourseInfo", 0);		
		if(CourseInfo == null || CourseInfo.length == 0) {
			return -1; // course index not found
		}
		
		String[] indices = CourseInfo[1].split(";");
		//print
		System.out.println("List of students sucessfully registered to course " + courseCode + ":");
		for(int i = 0; i < indices.length; i++) {
			printStdByIndex(indices[i]);
		}		
		return 1; //  record found
	}
	
	// check if course code or school code exist
	
	public boolean checkCourseCodeExist(String courseCode) {
		int line = db.findRecord(courseCode, "Course", 1); // find course code existence at Course.csv
		if(line > 0) {
			return true;
		}
		return false;
	}
	
	public boolean checkCourseIndexExist(String index) {
		int line = db.findRecord(index, "Course", 0); // find index code existence at Course.csv
		if(line > 0) {
			return true;
		}
		return false;
	}

}
