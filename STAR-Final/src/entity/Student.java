package entity;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Student extends People{
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
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
	

	/*
	 * Constructor
	 */
	
	public Student() {}
	
	public Student(String matric) {this.studMatric = matric;}
	
	
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
	public void setStartTime(String timeStr) throws ParseException {this.studStartTime = dateFormat.parse(timeStr);}
	public void setEndTime(String timeStr) throws ParseException {this.studEndTime = dateFormat.parse(timeStr);}
	
	public void setFacaulty(String fac) {super.facultyName = fac;}
	public String getFaculty() {return super.facultyName;}
	
}