package entity;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * Represent a student user of the STARS system
 * One student can register multiple courses
 * @author BUITT
 *
 */
public class Student extends People{
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	
	/**
	 * this student's name
	 */
	private String studName;
	
	/**
	 * this student's matric number, or ID
	 */
	private String studMatric;
	
	/**
	 * this student's gender, either F or M
	 */
	private char studGender;
	
	/**
	 * this student's nationality
	 */
	private String studNationality;
	
	/**
	 * this student's study year, from 1 to 6
	 */
	private int studYear;
	
	/**
	 * this student's phone number
	 */
	private int studPhoneNum;
	
	/**
	 * this student's email
	 */
	private String studEmail;
	
	/**
	 * this student's start time of the access period to the STARS system
	 */
	private Date studStartTime;
	
	/**
	 * this student's end time of the access period to the STARS system
	 */
	private Date studEndTime;
	
	/**
	 * Constructor
	 */
	public Student() {}
	
	public Student(String matric) {this.studMatric = matric;}
	
	/**
	 * Get Student Name
	 * @return this student's name
	 */
	public String getStudName() {return studName;}
	/**
	 * Set Student Name
	 * @param studName
	 */
	public void setStudName(String studName) {this.studName = studName;}
	/**
	 * Get Student UserId
	 * @return this student's userid/matric number
	 */
	public String getStudMat() {return studMatric;}
	/**
	 * Set Student UserId
	 * @param studMatric
	 */
	public void setStudMat(String studMatric) {this.studMatric = studMatric;}
	/**
	 * Get Student Gender
	 * @return this student's gender
	 */
	public char getStudGender() {return studGender;}
	/**
	 * Set Student Gender
	 * @param studGender
	 */
	public void setStudGender(char studGender) {this.studGender = studGender;}
	/**
	 * Get Student Nationality
	 * @return this student's nationality
	 */
	public String getStudNat() {return studNationality;}
	/**
	 * Set Student Nationality
	 * @param studNationality
	 */
	public void setStudNat(String studNationality) {this.studNationality = studNationality;}
	/**
	 * Get Student Year
	 * @return this student's year
	 */
	public int getStudYear() {return studYear;}
	/**
	 * Set Student Year
	 * @param studYear
	 */
	public void setStudYear(int studYear) {this.studYear = studYear;}
	/**
	 * Get Student Number
	 * @return this student's phone number
	 */
	public int getStudNum() {return studPhoneNum;}
	/**
	 * Set Student Number
	 * @param studPhoneNum
	 */
	public void setStudNum(int studPhoneNum) {this.studPhoneNum = studPhoneNum;}
	/**
	 * Get Student Email
	 * @return this student's email address
	 */
	public String getStudEmail() {return studEmail;}
	/**
	 * Set Student Email
	 * @param studEmail
	 */
	public void setStudEmail(String studEmail) {this.studEmail = studEmail;}
	/**
	 * Get Student Access Start Time
	 * @return this student's access start time
	 */
	public Date getStartTime() {return studStartTime;}
	/**
	 * Set Student Access Start Time (Using Time Format)
	 * @param time
	 */
	public void setStartTime(Date time) {this.studStartTime = time;}
	/**
	 * Get Student Access End Time 
	 * @return this student's access end time
	 */
	public Date getEndTime() {return studEndTime;}
	/**
	 * Set Student Access End Time
	 * @param time
	 */
	public void setEndTime(Date time) {this.studEndTime = time;}
	/**
	 * Set Student Access Start Time (Using Time In String Format)
	 * @param timeStr
	 * @throws ParseException
	 */
	public void setStartTime(String timeStr) throws ParseException {this.studStartTime = dateFormat.parse(timeStr);}
	/**
	 * Set Student Access End Time (Using Time In String Format)
	 * @param timeStr
	 * @throws ParseException
	 */
	public void setEndTime(String timeStr) throws ParseException {this.studEndTime = dateFormat.parse(timeStr);}

	
}