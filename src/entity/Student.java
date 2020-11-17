package entity;
import java.util.*;
import java.time.DayOfWeek;
import enums.*;
import java.text.SimpleDateFormat;

public class Student extends People{
	SimpleDateFormat fullformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//String dateString = fullformat.format( new Date()   );
	//Date   date       = fullformat.parse ( "2009-12-31" );    
	
	private String studName;
	private String studMatric;
	private char studGender;
	private String studNationality;
	private int studYear;
	private int studPhoneNum;
	private String studEmail;
	private Date studStartTime;
	private Date studEndTime;
	
//	private ArrayList<String> studAUBalance;
	
//	private Course course;
	
//	print(){
//		Course.getAllCourse();//return arraylist courses
//		CourseRegistration.getAllRegisteredCourse(Student stud); // return arraylist courseRegistration
//		
//		for(CourseRegistration) {
//			//Course Index CourseRegistration.getIndex();
//			
//			//if arraylist.getCourse() == Index
//				//Print();
//		}
//	}
//	
//	
	public ArrayList<String> studRegCourse;
	public ArrayList<Integer> studRegAU;
	public ArrayList<String> studRegCourseType;
	public ArrayList<String> studRegGERType;	
	public ArrayList<String> studRegIndex;
	public ArrayList<String> studRegStatus;
	public ArrayList<Integer> studRegChoice;
	public ArrayList<InstructionType> studRegClassType;
	public ArrayList<Integer> studRegClassGroup;
	public ArrayList<DayOfWeek> studRegClassDay;
	public ArrayList<String> studRegClassTime;
	public ArrayList<String> studRegVenue;
	public ArrayList<String> studRegRemarks;
	
	public ArrayList<String> studPendCourse;
	public ArrayList<Integer> studPendAU;
	public ArrayList<String> studPendCourseType;
	public ArrayList<String> studPendGERType;	
	public ArrayList<String> studPendIndex;
	public ArrayList<String> studPendStatus;
	public ArrayList<Integer> studPendChoice;
	public ArrayList<InstructionType> studPendClassType;
	public ArrayList<Integer> studPendClassGroup;
	public ArrayList<DayOfWeek> studPendClassDay;
	public ArrayList<String> studPendClassTime;
	public ArrayList<String> studPendVenue;
	public ArrayList<String> studPendRemarks;
	
	/*
	 * Constructor
	 */
	
	public Student() {}
	
	public Student(String matric) {
		this.studMatric = matric;
	}
	
	
	/*
	 * Getter and Setter
	 */
	
	public String getStudName() {return studName;}
	public void setStudName(String studName) {this.studName = studName;}
	public String getStudMat() {return studMatric;}
	public void setStudMat(String studMatric) {this.studMatric = studMatric;}
	public char getStudGender() {return studGender;}
	public void setStudGender(char studGender) {this.studGender = studGender;}
	public String getStudNat() {return studNationality;}
	public void setStudNat(String studNationality) {this.studNationality = studNationality;}
	public int getStudYear() {return studYear;}
	public void setStudYear(int studYear) {this.studYear = studYear;}
	public int getStudNum() {return studPhoneNum;}
	public void setStudNum(int studPhoneNum) {this.studPhoneNum = studPhoneNum;}
	public String getStudEmail() {return studEmail;}
	public void setStudEmail(String studEmail) {this.studEmail = studEmail;}
	public Date getStartTime() {return studStartTime;}
	public void setStartTime(Date time) {this.studStartTime = time;}
	public Date getEndTime() {return studEndTime;}
	public void setEndTime(Date time) {this.studEndTime = time;}
	
	
	public ArrayList<String> getRegCourse() { return studRegCourse;}
	public ArrayList<Integer> getRegAU() { return studRegAU;}
	public ArrayList<String> getRegCourseType() { return studRegCourseType;}
	public ArrayList<String> getRegGERPEType() { return studRegGERType;}	
	public ArrayList<String> getRegIndex() { return studRegIndex;}
	public ArrayList<String> getRegStatus() { return studRegStatus;}
	public ArrayList<Integer> getRegChoice() { return studRegChoice;}
	public ArrayList<InstructionType> getRegClassType() { return studRegClassType;}
	public ArrayList<Integer> getRegClassGroup() { return studRegClassGroup;}
	public ArrayList<DayOfWeek> getRegClassDay() { return studRegClassDay;}
	public ArrayList<String> getRegClassTime() { return studRegClassTime;}
	public ArrayList<String> getRegVenue() { return studRegVenue;}
	public ArrayList<String> getRegRemarks() { return studRegRemarks;}
	
	public void setRegCourse(ArrayList<String> studRegCourse) {this.studRegCourse = studRegCourse;}
	public void setRegAU(ArrayList<Integer> studRegAU) {this.studRegAU = studRegAU;}
	public void setRegCourseType(ArrayList<String> studRegCourseType) {this.studRegCourseType = studRegCourseType;}
	public void setRegGERType(ArrayList<String> studRegGERType) {this.studRegGERType = studRegGERType;}	
	public void setRegIndex(ArrayList<String> studRegIndex) {this.studRegIndex = studRegIndex;}
	public void setRegStatus(ArrayList<String> studRegStatus) {this.studRegStatus = studRegStatus;}
	public void setRegChoice(ArrayList<Integer> studRegChoice) {this.studRegChoice = studRegChoice;}
	public void setRegClassType(ArrayList<InstructionType> studRegClassType) {this.studRegClassType = studRegClassType;}
	public void setRegClassGroup(ArrayList<Integer> studRegClassGroup) {this.studRegClassGroup = studRegClassGroup;}
	public void setRegClassDay(ArrayList<DayOfWeek> studRegClassDay) {this.studRegClassDay = studRegClassDay;}
	public void setRegClassTime(ArrayList<String> studRegClassTime) {this.studRegClassTime = studRegClassTime;}
	public void setRegVenue(ArrayList<String> studRegVenue) {this.studRegVenue = studRegVenue;}
	public void setRegRemarks(ArrayList<String> studRegRemarks) {this.studRegRemarks = studRegRemarks;}
	

	public ArrayList<String> getPendCourse() { return studPendCourse;}
	public ArrayList<Integer> getPendAU() { return studPendAU;}
	public ArrayList<String> getPendCourseType() { return studPendCourseType;}
	public ArrayList<String> getPendGERPEType() { return studPendGERType;}	
	public ArrayList<String> getPendIndex() { return studPendIndex;}
	public ArrayList<String> getPendStatus() { return studPendStatus;}
	public ArrayList<Integer> getPendChoice() { return studPendChoice;}
	public ArrayList<InstructionType> getPendClassType() { return studPendClassType;}
	public ArrayList<Integer> getPendClassGroup() { return studPendClassGroup;}
	public ArrayList<DayOfWeek> getPendClassDay() { return studPendClassDay;}
	public ArrayList<String> getPendClassTime() { return studPendClassTime;}
	public ArrayList<String> getPendVenue() { return studPendVenue;}
	public ArrayList<String> getPendRemarks() { return studPendRemarks;}
	
	public void setPendCourse(ArrayList<String> studPendCourse) {this.studPendCourse = studPendCourse;}
	public void setPendAU(ArrayList<Integer> studPendAU) {this.studPendAU = studPendAU;}
	public void setPendCourseType(ArrayList<String> studPendCourseType) {this.studPendCourseType = studPendCourseType;}
	public void setPendGERType(ArrayList<String> studPendGERType) {this.studPendGERType = studPendGERType;}	
	public void setPendIndex(ArrayList<String> studPendIndex) {this.studPendIndex = studPendIndex;}
	public void setPendStatus(ArrayList<String> studPendStatus) {this.studPendStatus = studPendStatus;}
	public void setPendChoice(ArrayList<Integer> studPendChoice) {this.studPendChoice = studPendChoice;}
	public void setPendClassType(ArrayList<InstructionType> studPendClassType) {this.studPendClassType = studPendClassType;}
	public void setPendClassGroup(ArrayList<Integer> studPendClassGroup) {this.studPendClassGroup = studPendClassGroup;}
	public void setPendClassDay(ArrayList<DayOfWeek> studPendClassDay) {this.studPendClassDay = studPendClassDay;}
	public void setPendClassTime(ArrayList<String> studPendClassTime) {this.studPendClassTime = studPendClassTime;}
	public void setPendVenue(ArrayList<String> studPendVenue) {this.studPendVenue = studPendVenue;}
	public void setPendRemarks(ArrayList<String> studPendRemarks) {this.studPendRemarks = studPendRemarks;}
	

	
}