package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.io.*;
import java.util.*;
import util.*;


public class StudentRegistration {

	SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yy HH:mm:ss");
	Database dUser = new Database("C:\\");
	Database dIndex = new Database("C:\\");
	
	public Student getStudentObj(String matric) throws ParseException {
		dUser.setFilename("Users");
		String[] details = dUser.getUserRecord(matric);
		Student s = new Student(matric);
		s.setStudName(details[1]);
		s.setStudMat(details[2]);
		s.setPassword(details[3]);
		s.setFaculty(details[4]);
		s.setStudGender(details[5].charAt(0));
		s.setStudNat(details[6]);
		s.setStudNum(Integer.parseInt(details[7]));
		s.setStudEmail(details[8]);
		s.setStudYear(Integer.parseInt(details[9]));
		s.setStartTime(dateFormat.parse(details[10]));
		s.setEndTime(dateFormat.parse(details[11]));	
		return s;
	}
	
	public String[] setStudentRow(Student s) {
		String[] details = new String[12];
		details[0] = s.getStudMat();
		details[1] = s.getStudName();
		details[2] = s.getStudMat();
		details[3] = s.getPassword();
		details[4] = s.getFaculty();
		details[5] = String.valueOf(s.getStudGender());
		details[6] = s.getStudNat();
		details[7] = String.valueOf(s.getStudNum());
		details[8] = s.getStudEmail();
		details[9] = String.valueOf(s.getStudYear());
		details[10] = dateFormat.format(s.getStartTime());
		details[11] = dateFormat.format(s.getEndTime());
		return details;
	}
	
	public CourseIndex getCourseObj(String index) {
		dIndex.setFilename("Course");
		String[] details = dIndex.getCourseInfoRecord(index);
		CourseIndex ci = new CourseIndex(index);
		ci.setIndex(details[0]);
		ci.setCourseCode(details[1]);
		ci.setVacancy(Integer.parseInt(details[2]));
		String[] elements1 = details[3].split(";");
		List<String> list1 = Arrays.asList(elements1); 
		ArrayList<String> regId = new ArrayList<String>(list1);
		ci.setRegisterIDs(regId);
		String[] elements2 = details[4].split(";");
		List<String> list2 = Arrays.asList(elements2); 
		ArrayList<String> waitId = new ArrayList<String>(list2);
		ci.setWaitIDs(waitId);
		ci.setGrpNum(Integer.parseInt(details[5]));
		ci.setCourseType(details[6]);
		ci.setGERType(details[7]);
		ci.setIndexAU(Integer.parseInt(details[8]));
		return ci;
	}
	
	public String[] setCourseRow(CourseIndex ci) {
		String[] details = new String[9];
		details[0] = ci.getIndex();
		details[1] = ci.getCourseCode();
		details[2] = String.valueOf(ci.getVacancy());
		String listString = "";
		for (String s : ci.getRegisteredIDs()) {listString += s + ";";}
		details[3] = listString;
		String listString2 = "";
		for (String s : ci.getWaitIDs()) {listString2 += s + ";";}
		details[4] =listString2;
		details[5] = String.valueOf(ci.getGrpNum());
		details[6] = String.valueOf(ci.getCourseType());
		details[7] = ci.getGERType();
		details[8] = String.valueOf(ci.getIndexAU());
		return details;
	}
	
	/*
	 * add index to student OR student to this index
	 */
	public void addIntoList(Student stud, String index) {		
		CourseIndex ci = new CourseIndex(index);
		ci = getCourseObj(index);
		String matric = stud.getStudMat();
		int vac = ci.getVacancy();
		dIndex.setFilename("Course");
		
		int main_std_i = ci.getRegisterIDs().indexOf(matric);
		int wait_std_i = ci.getWaitIDs().indexOf(matric);
		if(main_std_i >= 0 || wait_std_i >= 0) { // student already registered for this course yet
			System.out.println("Student " + matric + " already registered/ currently in waitlist!");
		} else if(vac > 0) { //student able to add course
			ci.getRegisteredIDs().add(matric);
			vac = vac-1;
			ci.setVacancy(vac);
			dIndex.updateCourseInfo(index,setCourseRow(ci)); // update the row for this course into db
			System.out.println("Vancancy = " + vac);
			
		} else {
			ci.getWaitIDs().add(matric);
			dIndex.updateCourseInfo(index,setCourseRow(ci)); // update the row for this course into db
			System.out.println("Student added to waitlist. Vacancy exceeded");
		} 
		
	}
	
	/*
	 * remove student from index OR index from student
	 */
	public void dropFromList(Student stud, String index) {
		CourseIndex ci = new CourseIndex();
		ci = getCourseObj(index);
		String matric = stud.getStudMat();
		
		int main_std_i = ci.getRegisterIDs().indexOf(matric);
		int wait_std_i = ci.getWaitIDs().indexOf(matric);
		int vac = ci.getVacancy();
		dIndex.setFilename("Course");
		
		if(main_std_i < 0 && wait_std_i < 0) {
			System.out.println("Error! Student not registered for this course from the start!");
		} else if(main_std_i < 0 && wait_std_i >= 0) { //remove student from waitlist
			ci.getWaitIDs().remove(wait_std_i);
			dIndex.updateCourseInfo(index,setCourseRow(ci)); // update the row for this course into db
			System.out.println("Student removed to waitlist.");
		} else {
			ci.getRegisteredIDs().remove(main_std_i);
			System.out.println("Student removed to registered list. Vacancy +1");
			if (ci.getWaitIDs().size()>0) { //not empty waitlist 
				Student newStud = new Student(ci.getWaitIDs().get(0));
				addIntoList(newStud, index);
			} else {
				ci.setVacancy(vac+1);
			}
			dIndex.updateCourseInfo(index, setCourseRow(ci)); // update the row for this course into db
		}
	}
	
	/*
	 * Check vacancies
	 */
	public int checkVacancies(String courseIndex) {
		CourseIndex ci = getCourseObj(courseIndex);
		return ci.getVacancy();
	}
	
	/*/
	 * Print course registered
	 */
	public void printCourseReg(Student stud) {
		dIndex.setFilename("Course");
		System.out.println("Name: " + stud.getStudName());
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("Course |AU|Course Type       |GER Type|Index Number|Status    |Choice|Class Type|Group|Day  |Time  |Venue  |Remarks    ");
		ArrayList<String> regIndex = dIndex.returnIndexRecord(stud.getStudMat(), 3); //return the list of indexes student is registered
		ArrayList<String> waitIndex = dIndex.returnIndexRecord(stud.getStudMat(), 4); // return the list of wait list index for this student
		for (int i=0; i<regIndex.size();i++) {
			CourseIndex c = getCourseObj(regIndex.get(i));
			System.out.print(c.getCourseCode());
			System.out.print(c.getIndexAU());
			System.out.print(c.getCourseType());
			System.out.print(c.getGERType());
			System.out.print(c.getIndex());
			System.out.print("Registered");
			System.out.print("-");
			System.out.print("CLASS TYPE");
			System.out.print(c.getGrpNum());
			System.out.print("DAY");
			System.out.print("TIME");
			System.out.print("VENUE");
			System.out.print("REMARKS");
		}
		for (int i=0; i<waitIndex.size();i++) {
			CourseIndex c = getCourseObj(waitIndex.get(i));
			System.out.print(c.getCourseCode());
			System.out.print(c.getIndexAU());
			System.out.print(c.getCourseType());
			System.out.print(c.getGERType());
			System.out.print(c.getIndex());
			System.out.print("WaitList");
			System.out.print("-");
			System.out.print("CLASS TYPE");
			System.out.print(c.getGrpNum());
			System.out.print("DAY");
			System.out.print("TIME");
			System.out.print("VENUE");
			System.out.print("REMARKS");
		}
			
	}
	
	/*
	 * Switching index with another person
	 */
	public void swopIndex(Student stud1, String index1, Student stud2, String index2) {
		dIndex.setFilename("Course");
		ArrayList<String> stud1RegIndex = dIndex.returnIndexRecord(stud1.getStudMat(), 3);
		ArrayList<String> stud2RegIndex = dIndex.returnIndexRecord(stud2.getStudMat(), 3);

		/*
		 * TODO: check for clash
		 */

		
		if (stud1RegIndex.contains(index1) & stud2RegIndex.contains(index2)) {
			
			CourseIndex ci1 = new CourseIndex();
			ci1 = getCourseObj(index1); //got course info from db
			ArrayList<String> regIDList1 = ci1.getRegisteredIDs();
			int ind1 = regIDList1.indexOf(stud1.getStudMat()); //the index where this stud matric lies in the reg id list
			
			CourseIndex ci2 = new CourseIndex();
			ci2 = getCourseObj(index2); // got course info from db
			ArrayList<String> regIDList2 = ci2.getRegisteredIDs();
			int ind2 = regIDList2.indexOf(stud2.getStudMat()); //the index where this stud matric lies in the reg id list
			
			regIDList1.set(ind1, stud2.getStudMat());
			regIDList2.set(ind2, stud1.getStudMat());
			ci1.setRegisterIDs(regIDList1);
			ci2.setRegisterIDs(regIDList2);
			dIndex.updateCourseInfo(index1,setCourseRow(ci1)); // update the row for this course into db
			dIndex.updateCourseInfo(index2,setCourseRow(ci2)); // update the row for this course into db
			
			System.out.println("You have successfully switched your index with your coursemate!");
		} else {System.out.println("Error! Unable to swop index!!");}
	}
	
	
	/*
	 * Switching indexes of registered course
	 */
	
	public void changeIndex(Student stud, String index1, String index2) {
		dIndex.setFilename("Course");
		ArrayList<String> studRegIndex = dIndex.returnIndexRecord(stud.getStudMat(), 3); // return student current registered indexes
		
		CourseIndex ci2 = new CourseIndex(index2);
		ci2 = getCourseObj(index2);
		int vac = ci2.getVacancy();
		
		if (vac > 0 && studRegIndex.contains(index1)) {
			/*
		 * TODO: check for clash for index 2 with current registered courses
		 */
			
			//add student to index 2
			ci2.getRegisteredIDs().add(stud.getStudMat());
			vac = vac-1;
			ci2.setVacancy(vac);
			dIndex.updateCourseInfo(index2,setCourseRow(ci2)); // update the row for this course into db
			//remove student from index 1
			CourseIndex ci = new CourseIndex();
			ci = getCourseObj(index1); //got course info from db
			ArrayList<String> regIDList = ci.getRegisteredIDs();
			regIDList.remove(stud.getStudMat());
			ci.setRegisterIDs(regIDList);
			dIndex.updateCourseInfo(index1,setCourseRow(ci)); // update the row for this course into db
			System.out.println("You have successfully switch to another index of the course!");
		} else {System.out.println("Failed to switch to another index of the course!");}
	}
	
}
