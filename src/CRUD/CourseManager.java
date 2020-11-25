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

/**
 * class to handle Course and CourseIndex objects, 
 * with methods to construct object from database and access object information
 * @author BUITT
 *
 */

public class CourseManager {
	Database db = new Database(System.getProperty("user.dir") + "\\src\\database\\");	
	Database db2 = new Database(System.getProperty("user.dir") + "\\src\\database\\");	
	DBObj dbo = new DBObj(); //ADDED BY JY
	
	/**
	 * method to edit the vacancy of a course index number, and save to database file Course.csv
	 * @param index
	 * @param vacancy
	 */
	public void editIndexVacancy(String index, int vacancy) {
		db.setFilename("Course");
		int index_line = db.findCourseIndexRecord(index, "Course");
		if(index_line < 0) { // course index not found
			System.out.println("Course index not found in database. Please create one or try again!");
			return ;
		} 
		// if index exists, create a course index object, and edit vacancy, then save to file. If no, do nothing, return 0;
		// course index found at line index_line
		CourseIndex ci = dbo.getCourseObj(index); 
		
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
	
	/**
	 * method to return index vacancy
	 * by checking the database file Course.csv
	 * @param index
	 * @return int value of vacancy of a course index number
	 */
	
	public int returnIndexVacancy(String index) {
		db.setFilename("Course");
		int index_line = db.findCourseIndexRecord(index, "Course");
		if(index_line < 0) { // course index not found
			System.out.println("Course index not found in database. Please create one or try again!");
			return -1;
		} 
		// if index exists, create a course index object, and edit vacancy, then save to file. If no, do nothing, return 0;
		// course index found at line index_line
		CourseIndex ci = dbo.getCourseObj(index); // ADDED BY JY
		int vacancy = ci.getVacancy();
		System.out.println("Index " + index + " has " + vacancy + " vacancies");
		return vacancy;
	}

	/**
	 * method to add a new course index number to an existing course, and save to database
	 * @param ci
	 */
	public void addIndex2ExistingCourse(CourseIndex ci) { // 
		// return null means not created
		Database db = new Database(System.getProperty("user.dir") + "\\src\\database\\");
		DBObj dbo = new DBObj(); //ADDED BY JY
		
		// get string that saved to db
		String[] stringCourseIndexDB = dbo.setCourseRow(ci); //ADDED BY JY
		db.appendFile(stringCourseIndexDB, "Course");
		
		// add new index string to CourseInfo, update to file
		db.setFilename("CourseInfo");		
		int line = db.findRecord(ci.getCourseCode(), "CourseInfo", 0);		
		if(line < 0) { //  course not created before
			System.out.println("Course not created. Please try again");
			return;
		} 
		
		String [] stringCourseInfoDB = db.getRecord(ci.getCourseCode(), "CourseInfo", 0); //  find course code at column 0
		stringCourseInfoDB[1] += ";" + ci.getIndex();
		db.updateFile(line, 1, stringCourseInfoDB[1]);					
		System.out.println("New course index " + ci.getIndex() + " created and saved to database with vacancy " + ci.getVacancy() + " and class schedule:");	
		System.out.println(ci.toStringClassList());
	}
	
	/**
	 * method to add a new course index to existing course, and save to database
	 * @param course
	 */	
	public void addNewCourse(Course course) {
		// return null means not created
		Database db = new Database(System.getProperty("user.dir") + "\\src\\database\\");
		
		DBObj dbo = new DBObj(); //ADDED BY JY
		
		// get string that saved to db
		ArrayList<CourseIndex> indices = course.getCourseIndexList();
		db.setFilename("Course");	
		for(int i = 0; i < indices.size(); i++ ) {
			CourseIndex ci = indices.get(i);
			String [] stringCourseIndexDB = dbo.setCourseRow(ci); 
			db.appendFile(stringCourseIndexDB, "Course");			
		}
		
		// append new line to CourseInfo, update to file
		db.setFilename("CourseInfo");	
		db.appendFile(dbo.setCourseInfoRow(course), "CourseInfo");
		
		System.out.println("New course " + course.getCourseCode() + " created and saved to database with index numbers: ");
		System.out.println("Index | Vacancy | Schedule");
		System.out.println("-------------------------------");
		for(int i = 0; i < indices.size(); i++) {
			System.out.print(indices.get(i).getIndex() + ": ");
			System.out.print(indices.get(i).getVacancy() + "      ");
			System.out.println(indices.get(i).toStringClassList());
		}
	}
	
	/**
	 * method to update courseCode of an existing course, and save information to database
	 * @param oldCourseCode
	 * @param newCourseCode
	 * @return
	 */
	
	public int updateCourseCode(String oldCourseCode, String newCourseCode) {
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
		System.out.println("Course code " + oldCourseCode + " updated to " + newCourseCode);
		return 1;		
	}
	
	/**
	 * method to update school code of an existing course
	 * @param oldCourseCode
	 * @param newSchoolCode
	 * @return
	 */
	
	public int updateCourseSchool(String oldCourseCode, String newSchoolCode) {
		// from CourseInfo.csv
		db.setFilename("CourseInfo");
		int line1 = db.findRecord(oldCourseCode, "CourseInfo");
		if(line1 < 0) { // not found
			return -1;
		}
		db.updateFile(line1, 2, newSchoolCode);
		System.out.println("Hosting school of course " + oldCourseCode + " updated to: " + newSchoolCode);
		return 1;		
	}
	
	/**
	 * method to print out list of students registered to a course index
	 * @param index
	 */
	public void printStdByIndex(String index) {
		db.setFilename("Course");
		db2.setFilename("Users");
		String[] indexInfo = db.getRecord(index, "Course", 0);
		if(indexInfo == null || indexInfo.length == 0) {
			return ;
		}
		
		if(!indexInfo[3].equals("")) {
			String[] stdList = indexInfo[3].split(";");
			System.out.println("List of " + stdList.length + " students sucessfully registered to index " + index + ":");
			System.out.println("Name      | Gender      | Nationality");
			System.out.println("-------------------------------------");
			for(int i = 0; i < stdList.length; i++) {
				String[] stdRecord = db2.getUserRecord(stdList[i]);
				System.out.println(stdRecord[1] + "   | " + stdRecord[5] + "       | " + stdRecord[6]);
			}	
		} else {
			System.out.println("0 student registered to index " + index); 
		}			
		System.out.println("");
		return ; //  record found
	}
	
	/**
	 * method to print out list of students registered to a course,
	 * by printing the list of students registered to each of the course's index numbers
	 * @param index
	 */
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
	
	/**
	 * method check if the course code matches any record in the database file Course.csv
	 * @param index
	 * @return True or False
	 */
	
	public boolean checkCourseCodeExist(String courseCode) {
		int line = db.findRecord(courseCode, "Course", 1); // find course code existence at Course.csv
		if(line > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * method to check if the course index number matches any record in the database file Course.csv
	 * @param index
	 * @return True or False
	 */
	public boolean checkCourseIndexExist(String index) {
		int line = db.findRecord(index, "Course", 0); // find index code existence at Course.csv
		if(line > 0) {
			return true;
		}
		return false;
	}

}
