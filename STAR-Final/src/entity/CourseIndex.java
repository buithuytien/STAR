package entity;

import java.util.ArrayList;
import enums.*;

public class CourseIndex {
	private String index;
	private String courseCode;
	private int indexAU;
	private int grpNum;
	private String courseType; // CORE, UE,... // string // ADJUSTED BY JY
	private String GERType;
	private int vacancy;
//	private int totalSize; //COMMENTS BY JY : we dont have totalsize
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
	
	/*
	 * getter and setter
	 */
	public int getGrpNum() {return grpNum;}
	public void setGrpNum(int grpNum) {this.grpNum = grpNum;}
	public String getGERType() {return GERType;}
	public void setGERType(String gERType) {GERType = gERType;}
	public int getIndexAU() {return this.indexAU;}
	public void setIndexAU(int indexAU) {this.indexAU = indexAU;}
	public String getCourseType() {return courseType;}
	public void setCourseType(String courseType) {this.courseType = courseType;}
	public ArrayList<ClassType> getClassList() {return classList;}
	public void setClassList(ArrayList<ClassType> classList) {this.classList = classList;}
	public ArrayList<String> getRegisteredIDs() {return registerIDs;}
	public void setRegisterIDs(ArrayList<String> RegisterIDs) {this.registerIDs = RegisterIDs;}
	public ArrayList<String> getWaitIDs() {return waitIDs;}
	public void setWaitIDs(ArrayList<String> waitIDs) {this.waitIDs = waitIDs;}
	public String getIndex() {return index;}
	public void setIndex(String index) {this.index = index;}
	public String getCourseCode() {return courseCode;}
	public void setCourseCode(String courseCode) {this.courseCode = courseCode;}
	public int getVacancy() {return vacancy;}
	public void setVacancy(int vacancy) { this.vacancy = vacancy;} // JY NEED THIS
//	public void setVacancy(int vacancy) { //JY: NEED TO CHECK WHY !!!!!!!!!!!!!!!!!!!!!!! ???? -- i think doesnt matter. the vacancy alr exclude the registered students. 
//		// update vacancy only allowed if number of register students is smaller than new vacancy 
//		int n = this.registerIDs.size();
//		if(vacancy < n) {
//			System.out.println("Vancancy smaller than number of registered students. Please enter a larger number");
//			return;
//		}		
//		this.vacancy = vacancy;
//	}

//
//	public void setClassList(String[] classListRaw) { // setClassList with input as array of raw string
////		{LEC MONDAY 10:00 13:00 LT12, TUT MONDAY 9:00 10:00 TR101}
//		ArrayList<ClassType> classListTemp = new ArrayList<ClassType>();
//		for(int i =0; i< classListRaw.length ; i++ ) {
//			String raw = classListRaw[i];
//			ClassType c = new ClassType(raw);
//			classListTemp.add(c);
//		}
//		this.classList = classListTemp;
//	}

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
						 String.valueOf(this.grpNum), this.courseType, this.GERType, 
						 String.valueOf(this.indexAU), this.toStringClassList() }; //ADJUSTED BY JY REGARDING COUSRETYPE
		return temp;
	}

}
