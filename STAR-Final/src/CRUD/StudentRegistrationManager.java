package CRUD;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.io.*;
import java.util.*;
import util.*;
import entity.*;

public class StudentRegistrationManager {

	SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	Database dUser = new Database(System.getProperty("user.dir") + "\\src\\database\\"); // "C:\\"
	Database dIndex = new Database(System.getProperty("user.dir") + "\\src\\database\\"); // "C:\\"
	DBObj dbo = new DBObj();
	
	/**
	 * Constructor
	 */
	public StudentRegistrationManager() {}
	
	/**
	 * Add Course Index (Student Action) 
	 * @param stud
	 * @param index
	 */
	public void addIntoList(Student stud, String index) {		
		CourseIndex ci = new CourseIndex(index);
		ci = dbo.getCourseObj(index);
		String matric = stud.getStudMat();
		int vac = ci.getVacancy();
		dIndex.setFilename("Course");
		
		int main_std_i = ci.getRegisteredIDs().indexOf(matric);
		int wait_std_i = ci.getWaitIDs().indexOf(matric);
		if(main_std_i >= 0 || wait_std_i >= 0) { // student already registered for this course yet
			System.out.println("Student " + matric + " already registered/ currently in waitlist!");
		} else if(vac > 0) { //student able to add course
	
			//CHECK CLASH
			if (checkClash(stud,ci, null)) {
				System.out.println("Course Clash!");
			} else {
				ArrayList<String> regIdArr = ci.getRegisteredIDs(); 
				if(regIdArr.get(0).equals("")) { //null
					regIdArr.set(0, matric);
				} else {
					regIdArr.add(matric); 
				}
				vac = vac-1;
				ci.setVacancy(vac);
				ci.setRegisterIDs(regIdArr); //column 3
				dIndex.updateCourseInfo(index,dbo.setCourseRow(ci)); // update the row for this course into db
				System.out.println("You are added into the course successfully.");
			}
		} else {
			if (checkClash(stud,ci, null)) {
				System.out.println("Vacancy exceeded. Unable to add into waitlist. Course Clash!");
			} else {
				ArrayList<String> waitIdArr = ci.getWaitIDs(); 
				if(waitIdArr.get(0).equals("")) { //null
					waitIdArr.set(0, matric);
				} else {
					waitIdArr.add(matric); 
				}
				ci.setWaitIDs(waitIdArr); //column 4
				dIndex.updateCourseInfo(index,dbo.setCourseRow(ci)); // update the row for this course into db
				System.out.println("You are added to waitlist. Vacancy exceeded");
			}
		} 
		
	}
	
	/**
	 * Drop Course Index (Student Action)
	 * @param stud
	 * @param index
	 */
	public void dropFromList(Student stud, String index) {
		CourseIndex ci = new CourseIndex();
		ci = dbo.getCourseObj(index);
		String matric = stud.getStudMat();
		
		int main_std_i = ci.getRegisteredIDs().indexOf(matric);
		int wait_std_i = ci.getWaitIDs().indexOf(matric);
		int vac = ci.getVacancy();
		dIndex.setFilename("Course");
		
		if(main_std_i < 0 && wait_std_i < 0) {
			System.out.println("Error! Student not registered for this course from the start!");
		} else if(main_std_i < 0 && wait_std_i >= 0) { //remove student from waitlist
			ArrayList<String> waitIdArr = ci.getWaitIDs(); 
			waitIdArr.remove(matric); 
			ci.setWaitIDs(waitIdArr);; //column 4
			dIndex.updateCourseInfo(index,dbo.setCourseRow(ci)); // update the row for this course into db
			System.out.println("Student removed to waitlist.");
		} else { //remove student from registered list
			ArrayList<String> regIdArr = ci.getRegisteredIDs(); 
			regIdArr.remove(main_std_i); 
			ci.setRegisterIDs(regIdArr); //column 3 
			System.out.println("You are removed from the registered list.");
			if (ci.getWaitIDs().get(0).length()>1) { //not empty waitlist 
				for (int xx =0; xx < ci.getWaitIDs().size() ; xx++) {
					Student newStud = new Student(ci.getWaitIDs().get(xx));
					if (checkClash(newStud, ci, null) == true) {
						xx = xx+1;
					} else {
						if(regIdArr.get(0).equals("")) { //null
							regIdArr.set(0, newStud.getStudMat());
						} else {
							regIdArr.add(newStud.getStudMat()); 
						}
						ci.setRegisterIDs(regIdArr); //column 3
						ArrayList<String> waitIdArr = ci.getWaitIDs(); 
						waitIdArr.remove(newStud.getStudMat()); 
						ci.setWaitIDs(waitIdArr);; //column 4
						dIndex.updateCourseInfo(index, dbo.setCourseRow(ci)); // update the row for this course into db
						//send notification
						String from = "ntucz2002@gmail.com"; //admin email
						String pw = "cz2002assignment"; //set this as the password 
						String to = "mojunyi20@gmail.com";
						String sub = "You have been allocated a new course!";
						String msg = "Your registration for" + index + " is sucessful!";
						Mailer.send(from, pw, to, sub, msg);
						System.out.println("A new student has been added to this course index.");
						break;
					}
				}
			} else {
				vac = vac+1;
				ci.setVacancy(vac);
			}
			dIndex.updateCourseInfo(index, dbo.setCourseRow(ci)); // update the row for this course into db
		}
	}
	
	/**
	 * Check Vacancies (Student Action)
	 * @param courseIndex
	 * @return vacancy of this class
	 */
	public int checkVacancies(String courseIndex) {
		CourseIndex ci = dbo.getCourseObj(courseIndex);
		return ci.getVacancy();
	}
	
	/**
	 * Print Course Registration (Student Action)
	 * @param stud
	 */
	public void printCourseReg(Student stud) {
		dIndex.setFilename("Course");
		System.out.println("Name: " + stud.getStudName());
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("Index	| AU |Course Code|Course Type	|GER Type|Status    |Group Number|Session    							");
		ArrayList<String> regIndex = dIndex.returnIndexRecord(stud.getStudMat(), 3); //return the list of indexes student is registered
		ArrayList<String> waitIndex = dIndex.returnIndexRecord(stud.getStudMat(), 4); // return the list of wait list index for this student
		for (int i=0; i<regIndex.size();i++) {
			CourseIndex c = dbo.getCourseObj(regIndex.get(i));
			System.out.print(c.getIndex() + "	| ");
			System.out.print(c.getIndexAU() + "  |	");
			System.out.print(c.getCourseCode() + "	 |	");
			System.out.print(c.getCourseType() + "	|   ");
			System.out.print(c.getGERType() + "	 |");
			System.out.print("Registered" + "|	  ");
			System.out.print(c.getGrpNum() + "	 |");
			System.out.print(String.join(";",c.getClassList().toString()));
			System.out.println("");
		}
		for (int i=0; i<waitIndex.size();i++) {
			CourseIndex c = dbo.getCourseObj(waitIndex.get(i));
			System.out.print(c.getIndex() + "	| ");
			System.out.print(c.getIndexAU() + "  |	");
			System.out.print(c.getCourseCode() + "	 |	");
			System.out.print(c.getCourseType() + "	|   ");
			System.out.print(c.getGERType() + "	 |");
			System.out.print("Pending" + "   |	  ");
			System.out.print(c.getGrpNum() + "	 |");
			System.out.print(String.join(";",c.getClassList().toString()));
			System.out.println("");
		}
			
	}
	
	/**
	 * Switching Index With Another Peer (Student Action)
	 * @param stud1
	 * @param index1
	 * @param stud2
	 * @param index2
	 */
	public void swopIndex(Student stud1, String index1, Student stud2, String index2) {
		dIndex.setFilename("Course");
		ArrayList<String> stud1RegIndex = dIndex.returnIndexRecord(stud1.getStudMat(), 3);
		ArrayList<String> stud2RegIndex = dIndex.returnIndexRecord(stud2.getStudMat(), 3);

		if (stud1RegIndex.contains(index1) & stud2RegIndex.contains(index2)) {
			
			CourseIndex ci1 = new CourseIndex();
			ci1 = dbo.getCourseObj(index1); //got course info from db
			ArrayList<String> regIDList1 = ci1.getRegisteredIDs();
			int ind1 = regIDList1.indexOf(stud1.getStudMat()); //the index where this stud matric lies in the reg id list
			
			CourseIndex ci2 = new CourseIndex();
			ci2 = dbo.getCourseObj(index2); // got course info from db
			ArrayList<String> regIDList2 = ci2.getRegisteredIDs();
			int ind2 = regIDList2.indexOf(stud2.getStudMat()); //the index where this stud matric lies in the reg id list
			
			if (checkClash(stud1,ci2,ci1) || checkClash(stud2, ci1, ci2)) { //one of the stud timetable clashes w new index
				System.out.println("Error! Clash!");
			} else {
				regIDList1.set(ind1, stud2.getStudMat());
				regIDList2.set(ind2, stud1.getStudMat());
				ci1.setRegisterIDs(regIDList1);
				ci2.setRegisterIDs(regIDList2);
				dIndex.updateCourseInfo(index1,dbo.setCourseRow(ci1)); // update the row for this course into db
				dIndex.updateCourseInfo(index2,dbo.setCourseRow(ci2)); // update the row for this course into db
				
				System.out.println("You have successfully switched your index with your coursemate!");
			}
		} else {System.out.println("Error! Unable to swop index!!");}
	}
	
	/**
	 * Changing Index (Student Action)
	 * @param stud
	 * @param index1
	 * @param index2
	 */
	public void changeIndex(Student stud, String index1, String index2) {
		dIndex.setFilename("Course");
		ArrayList<String> studRegIndex = dIndex.returnIndexRecord(stud.getStudMat(), 3); // return student current registered indexes
		CourseIndex ci1 = new CourseIndex(index1);
		ci1 = dbo.getCourseObj(index1);
		CourseIndex ci2 = new CourseIndex(index2);
		ci2 = dbo.getCourseObj(index2);
		int vac = ci2.getVacancy();
		
		if (vac > 0 && studRegIndex.contains(index1)) {
			if (checkClash(stud,ci2, ci1)) { //there is clash
				System.out.println("Failed to switch to another index of the course! Course Clash!");
			} else {
				//add student to index 2
				//ci2.getRegisteredIDs().add(stud.getStudMat());
				ArrayList<String> regIdArr = ci2.getRegisteredIDs(); 
				regIdArr.add(stud.getStudMat()); 
				ci2.setRegisterIDs(regIdArr); //column 3 
				vac = vac-1;
				ci2.setVacancy(vac);
				dIndex.updateCourseInfo(index2,dbo.setCourseRow(ci2)); // update the row for this course into db
				//remove student from index 1
				CourseIndex ci = new CourseIndex();
				ci = dbo.getCourseObj(index1); //got course info from db
				ArrayList<String> regIDList = ci.getRegisteredIDs();
				regIDList.remove(stud.getStudMat());
				ci.setRegisterIDs(regIDList);
				dIndex.updateCourseInfo(index1,dbo.setCourseRow(ci)); // update the row for this course into db
				System.out.println("You have successfully switch to another index of the course!");
			}
		} else {System.out.println("Failed to switch to another index of the course!");}
	}
	
	
	/**
	 * Check Course Against Current Registered Courses
	 * @param stud
	 * @param ci
	 * @param del
	 * @return if this course index is clashed with the current registered courses
	 */
	public boolean checkClash(Student stud, CourseIndex ci, CourseIndex del) { //ADJUSTMENT
		dIndex.setFilename("Course");
		ArrayList<String> studRegIndex = dIndex.returnIndexRecord(stud.getStudMat(), 3); // return student current registered indexes
		if (del!=null) {studRegIndex.remove(del.getIndex());}
		ArrayList<LocalTime> regStartArr = new ArrayList<LocalTime>();
		ArrayList<LocalTime> regEndArr = new ArrayList<LocalTime>();
		ArrayList<String> regDayArr = new ArrayList<String>();
		for (int i=0;i<studRegIndex.size();i++) { //3 arraylist of the registered info timings
			String[] c = dIndex.getCourseInfoRecord(studRegIndex.get(i));
			String[] strList = c[9].split(";"); //read in String[] {"LEC MONDAY 10:00 13:00 LT12"; "TUT MONDAY 9:00 10:00 TR101"}
			for (int j=0; j<strList.length;j++) {
				String[] sl = strList[j].split(" ");
				regDayArr.add(sl[1]);
				regStartArr.add(TimeHelper.convertStringToTime(sl[2]));
				regEndArr.add(TimeHelper.convertStringToTime(sl[3]));
			}
		}
		//System.out.println("Hello! 2");
		//check clash with new ci
		ArrayList<LocalTime> newStartArr = new ArrayList<LocalTime>();
		ArrayList<LocalTime> newEndArr = new ArrayList<LocalTime>();
		ArrayList<String> newDayArr = new ArrayList<String>();
		String[] c1 = dIndex.getCourseInfoRecord(ci.getIndex());
		String[] strList1 = c1[9].split(";"); //read in String[] {"LEC MONDAY 10:00 13:00 LT12"; "TUT MONDAY 9:00 10:00 TR101"}
		for (int j=0; j<strList1.length;j++) { //3 arraylist of the new info timings
			String[] sl1 = strList1[j].split(" ");
			newDayArr.add(sl1[1]);
			newStartArr.add(TimeHelper.convertStringToTime(sl1[2]));
			newEndArr.add(TimeHelper.convertStringToTime(sl1[3]));
		}
		//check clash
		for(int i=0; i<newStartArr.size();i++) { //run through the new index timings, eg this new index has 2 classtype, so there's 2 start time in this array
			for(int j=0; j<regStartArr.size();j++) { //run through all the registered array timings
				if (newDayArr.get(i).equals(regDayArr.get(j))) { //session fall on same day 
					if (newEndArr.get(i).compareTo(regStartArr.get(i)) <=0 || newStartArr.get(i).compareTo(regEndArr.get(i)) >=0) {
					}else { //clashes
						return true;
					}
				} else { //doesnt fall on same day, skip through as it doesnt clash
					continue;
				} 
				
			}
		}
		return false;	
	}
}
