package entity;

import java.util.ArrayList;
import enums.*;

public class CourseIndex {
	private String index;
	private String courseCode;
	private int indexAU;
	private int grpNum;
	private InstructionCourseType courseType; // CORE, UE,...
	private String GERType;
	private int vacancy;
	private int totalSize;
	private ArrayList<String> registerIDs = new ArrayList<String>();
	private ArrayList<String> waitIDs = new ArrayList<String>();
	private ArrayList<ClassType> classList = new ArrayList<ClassType>(); // lab, tut, lecture
	
	public CourseIndex() {}
	
	public CourseIndex(String index) {
		this.index = index;
	}
	
	public CourseIndex(int vacancy) {
		this.vacancy = vacancy;
	}
	
	public CourseIndex(String index, String courseCode, int vacancy, int totalSize) {
		// TODO: check if vacancy > totalSize. If yes, throw and error.
		// registerIDs and waitIDs = null;
		this.index = index;
		this.courseCode = courseCode;
		this.vacancy = vacancy;
		this.totalSize = totalSize;
	}
	
	public CourseIndex(String index, String courseCode, int vacancy, int totalSize, String[] classListRaw) {
		// TODO: check if vacancy > totalSize. If yes, throw and error.
		this.index = index;
		this.courseCode = courseCode;
		this.vacancy = vacancy;
		this.totalSize = totalSize;
		setClassList(classListRaw);
	}
	
	public CourseIndex(String index, String courseCode, int au, int grpNum ,InstructionCourseType courseType, String GERType, int vacancy, int totalSize) {
		// TODO: check if vacancy > totalSize. If yes, throw and error.
		// registerIDs and waitIDs = null;
		this.index = index;
		this.courseCode = courseCode;
		this.indexAU = au;
		this.grpNum = grpNum;
		this.courseType = courseType;
		this.GERType = GERType;
		this.vacancy = vacancy;
		this.totalSize = totalSize;
	}
	
	public CourseIndex(String index, String courseCode, int au, int grpNum ,InstructionCourseType courseType, 
			String GERType, int vacancy, int totalSize, ArrayList<String> registerIDs,
			ArrayList<String> waitIDs) {
		// TODO: check if vacancy > totalSize. If yes, throw and error.
		this(index, courseCode, au, grpNum, courseType, GERType, vacancy, totalSize);
		this.registerIDs = registerIDs;
		this.waitIDs = waitIDs;
	}
	
	
	
	public CourseIndex(String index, String courseCode, int au, int grpNum ,InstructionCourseType courseType, 
			String GERType, int vacancy, int totalSize, ArrayList<String> registerIDs,
			ArrayList<String> waitIDs, ArrayList<ClassType> classList) {
		// TODO: check if vacancy > totalSize. If yes, throw and error.
		// super();
		this(index, courseCode, au, grpNum, courseType, GERType, vacancy, totalSize, registerIDs, waitIDs);
		this.classList = classList;
	}
	
	public CourseIndex(String index, String courseCode, int au, int grpNum ,InstructionCourseType courseType, 
			String GERType, int vacancy, int totalSize, ArrayList<String> registerIDs,
			ArrayList<String> waitIDs, String[] classListRaw) {
		// TODO: check if vacancy > totalSize. If yes, throw and error.
//		super();
		this(index, courseCode, au, grpNum, courseType, GERType, vacancy, totalSize, registerIDs, waitIDs);
		this.setClassList(classListRaw); //this.classList is constructed here.
	}
	
	public CourseIndex(String index, String courseCode, int au, int grpNum ,InstructionCourseType courseType, 
			String GERType, int vacancy, int totalSize, ArrayList<ClassType> classList) {
		// TODO: check if vacancy > totalSize. If yes, throw and error.
		// super();
		this(index, courseCode, au, grpNum, courseType, GERType, vacancy, totalSize);
		this.classList = classList;
	}
	
	public CourseIndex(String index, String courseCode, int au, int grpNum ,InstructionCourseType courseType, 
			String GERType, int vacancy, int totalSize, String[] classListRaw) {
		// TODO: check if vacancy > totalSize. If yes, throw and error.
//		super();
		this(index, courseCode, au, grpNum, courseType, GERType, vacancy, totalSize);
		this.setClassList(classListRaw); //this.classList is constructed here.
	}
	
	/*
	 * getter and setter
	 */

	public int getGrpNum() {
		return grpNum;
	}

	public void setGrpNum(int grpNum) {
		this.grpNum = grpNum;
	}

	public String getGERType() {
		return GERType;
	}

	public void setGERType(String gERType) {
		GERType = gERType;
	}

	public int getIndexAU() {
		return this.indexAU;
	}
	public void setIndexAU(int indexAU) {
		this.indexAU = indexAU;
	}

	public InstructionCourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(InstructionCourseType courseType) {
		this.courseType = courseType;
	}
	
	public void setCourseType(String courseType) {
		this.courseType = InstructionCourseType.valueOf(courseType);
	}
	

	public ArrayList<ClassType> getClassList() {
		return classList;
	}

	public void setClassList(ArrayList<ClassType> classList) {
		this.classList = classList;
	}
	
	public void setClassList(String[] classListRaw) { // setClassList with input as array of raw string
//		{LEC MONDAY 10:00 13:00 LT12, TUT MONDAY 9:00 10:00 TR101}
		ArrayList<ClassType> classListTemp = new ArrayList<ClassType>();
		for(int i =0; i< classListRaw.length ; i++ ) {
			String raw = classListRaw[i];
			ClassType c = new ClassType(raw);
			classListTemp.add(c);
		}
		this.classList = classListTemp;
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
		// update vacancy only allowed if number of register students is smaller than new vacancy 
		int n = this.registerIDs.size();
		if(vacancy < n) {
			System.out.println("Vancancy smaller than number of registered students. Please enter a larger number");
			return;
		}		
		this.vacancy = vacancy;
	}

	public ArrayList<String> getRegisteredIDs() {
		return registerIDs;
	}
	
	public void setRegisterIDs(ArrayList<String> RegisterIDs) {
		this.registerIDs = RegisterIDs;
	}
	
	public int getNumRegisteredIDs() {
		return registerIDs.size();
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
	
	public String toStringClassList() {
		// eg. "LEC MONDAY 10:00 13:00 LT12;TUT MONDAY 9:00 10:00 TR101;LAB TUESDAY 9:00 11:00 SCELAB1"
		String temp = "";
		if(this.classList == null) {
			return "";
		}
		int n = this.classList.size();
		for(int i = 0; i < n; i++) {
			temp += this.classList.get(i).toString();
			if(i < n-1 ) {
				temp += ";";
			}
		}
		return temp;
	}
	
	public String toStringRegisterIDs() {
		// eg. U123456B;U123456D;U123456H
		String temp = null;
		if(this.registerIDs == null || this.registerIDs.isEmpty()) {
			return "";
		}
		int n = this.registerIDs.size();
		for(int i = 0; i < n; i++) {
			temp += this.registerIDs.get(i);
			if(i < n-1 ) {
				temp += ";";
			}
		}
		return temp;
	}
	
	public String toStringWaitIDs() {
		// eg. U123456B;U123456D;U123456H
		String temp = null;
		if(this.waitIDs == null || this.waitIDs.isEmpty()) {
			return "";
		}
		int n = this.waitIDs.size();
		for(int i = 0; i < n; i++) {
			temp += this.waitIDs.get(i);
			if(i < n-1 ) {
				temp += ";";
			}
		}
		return temp;
	}
	
	public String[] toStringDB() {
		// eg. LEC MONDAY 10:00 13:00 LT12; TUT MONDAY 9:00 10:00 TR101; LAB TUESDAY 9:00 11:00 SCELAB1; 
		String[] temp = {this.index, this.courseCode, String.valueOf(this.vacancy), 
						 this.toStringRegisterIDs(), this.toStringWaitIDs(),
						 String.valueOf(this.grpNum), this.courseType.name(), this.GERType, 
						 String.valueOf(this.indexAU), this.toStringClassList() };
		return temp;
	}
	
	
	public static void main(String[] args) {
//		CourseIndex courseIndex = new CourseIndex(1);
//		courseIndex.addStudent("std001");
//		courseIndex.addStudent("std002");
//		
//		System.out.println(courseIndex.getRegisteredIDs());
//		
//		courseIndex.removeStudent("std003");
//		System.out.println(courseIndex.getRegisteredIDs());	
//		
//		courseIndex.removeStudent("std002");
//		System.out.println(courseIndex.getRegisteredIDs());
		String [] cl = {"LEC MONDAY 10:00 13:00 LT12", "TUT MONDAY 09:00 10:00 TR101"};
		CourseIndex c = new CourseIndex("18001", "MH1800", 3, 1 ,InstructionCourseType.valueOf("CORE"), 
				"abc", 30, 30, cl);
		System.out.println("Print toString");
		System.out.println(c.toString());
		System.out.println("Print toStringDB");
		String [] toStringDBtest = c.toStringDB();
		for(int i = 0 ; i < toStringDBtest.length; i++ ) {
			System.out.print(toStringDBtest[i] + "|");
		}
		System.out.println("\n");		
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

}
