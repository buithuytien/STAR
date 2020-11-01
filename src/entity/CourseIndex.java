package entity;

import java.util.ArrayList;

public class CourseIndex {
	private String index; //TODO this will be another class
	private String courseCode;
	private int vacancy;
	private int totalSize;
	private ArrayList<String> registerIDs = new ArrayList<String>( );
	private ArrayList<String> waitIDs = new ArrayList<String>();
	private ArrayList<ClassType> classList = new ArrayList<ClassType>();
	
	public CourseIndex() {}
	
	public CourseIndex(int vacancy) {
		this.vacancy = vacancy;
	}
	
	public CourseIndex(String index, String courseCode, int vacancy, int totalSize, ArrayList<String> registerIDs,
			ArrayList<String> waitIDs, ArrayList<ClassType> classList) {
		// TODO: check if vacancy > totalSize. If yes, throw and error.
		super();
		this.index = index;
		this.courseCode = courseCode;
		this.vacancy = vacancy;
		this.totalSize = totalSize;
		this.registerIDs = registerIDs;
		this.waitIDs = waitIDs;
		this.classList = classList;
	}
	
	/*
	 * getter and setter
	 */

	public ArrayList<ClassType> getClassList() {
		return classList;
	}

	public void setClassList(ArrayList<ClassType> classList) {
		this.classList = classList;
	}

	public ArrayList<String> getRegisterIDs() {
		return registerIDs;
	}

	public ArrayList<String> getWaitIDs() {
		return waitIDs;
	}

	public void setWaitIDs(ArrayList<String> waitIDs) {
		this.waitIDs = waitIDs;
	}

	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public int getVacancy() {
		return vacancy;
	}
	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}

	public ArrayList<String> getRegisteredIDs() {
		return registerIDs;
	}
	public void setRegisterIDs(ArrayList<String> RegisterIDs) {
		this.registerIDs = RegisterIDs;
	}
	
	/*
	 * add and remove student to this index
	 */
	
	public int addStudent(String studentID) {
		if(studentID != null) {
			int main_std_i = this.registerIDs.indexOf(studentID);
			int wait_std_i = this.waitIDs.indexOf(studentID);
			if(main_std_i >= 0 || wait_std_i >= 0) { // student already registered for this course yet
				System.out.println("Student " + studentID + " already registered!");
				return -1;
			}
			
			if(this.vacancy > 0) { 
				registerIDs.add(studentID);
				this.vacancy-- ;
				System.out.println("Vancancy = " + this.vacancy);
				return 1;
			} else {
				waitIDs.add(studentID);
				System.out.println("Student added to waitlist. Vacancy exceeded");
				return 0;
			}
		}
		return -1;
	}
	
	public int removeStudent(String studentID) {
		int main_std_i = this.registerIDs.indexOf(studentID);
		int wait_std_i = this.waitIDs.indexOf(studentID);
		if(main_std_i < 0 && wait_std_i < 0) {
			return -1; // students not registered for this course
		} else if(main_std_i < 0 && wait_std_i >= 0) {
			waitIDs.remove(wait_std_i);
			return 0;
		} else {
			registerIDs.remove(main_std_i);
			this.vacancy ++;
			return 1;
		}
	}
	
	
	public static void main(String[] args) {
		CourseIndex courseIndex = new CourseIndex(1);
		courseIndex.addStudent("std001");
		courseIndex.addStudent("std002");
		
		System.out.println(courseIndex.getRegisteredIDs());
		
		courseIndex.removeStudent("std003");
		System.out.println(courseIndex.getRegisteredIDs());	
		
		courseIndex.removeStudent("std002");
		System.out.println(courseIndex.getRegisteredIDs());
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

}
