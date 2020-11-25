package entity;
import java.text.ParseException;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.*;

import App.*;
import util.*;
import entity.*;

/**
 * Helper class to construct CourseIndex object and Student object from database,
 * and save information of CourseIndex object and Student object to database
 * @author BUITT
 *
 */
public class DBObj {
	
	/**
	 * declare strings in a particular format
	 */
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	/**
	 * initialized Database objects for 3 different Database files
	 */
	private Database dUser = new Database(System.getProperty("user.dir") + "\\src\\database\\");
	private Database dIndex = new Database(System.getProperty("user.dir") + "\\src\\database\\");
	private Database dInfo = new Database(System.getProperty("user.dir") + "\\src\\database\\");
	
	/**
	 * Constructor
	 */
	public DBObj() {}
	
	/**
	 * Get Student Object Using UserId From Database
	 * @param matric
	 * @return a Student object with its information extracted from database
	 * @throws ErrorException
	 * @throws ParseException
	 */
	public Student getStudentObj(String matric) throws ErrorException, ParseException {
		dUser.setFilename("Users");
		String[] details = dUser.getUserRecord(matric);
		if(details.length == 0) {   // ADJUSTED BY THUYTIEN
			throw new ErrorException("recordNotFound"); 
		} else {
			Student s = new Student(matric);
			//for (int i=0; i<details.length; i++) {System.out.print(details[i] + " ");}
			s.setStudMat(details[0]);
			s.setStudName(details[1]);
			s.setStudMat(details[2]);
			s.setPassword(details[3]);
			s.setFaculty(details[4]);
			s.setStudGender(details[5].charAt(0));
			s.setStudNat(details[6]);
			s.setStudNum(Integer.parseInt(details[7]));
			s.setStudEmail(details[8]);
			if(details[9] == null) {details[9] = "0";}
			s.setStudYear(Integer.parseInt(details[9]));
			s.setStartTime(dateFormat.parse(details[10]));
			s.setEndTime(dateFormat.parse(details[11]));	
			return s;
		}		
	}
/**
 * Return Student Row Using Student Object
 * @param s
 * @return a list of information regarding this student object
 */
	public String[] setStudentRow(Student s) { //usually folo by similarcode to update to db: dUsers.updateCourseInfo(index, setStudentRow(s));
		String[] details = new String[12];
		details[0] = "S"; // s.getStudMat();
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
	/**
	 * Get CourseIndex Object Using Index From Database
	 * @param index
	 * @return a CourseIndex object with its information extracted from database
	 */
	public CourseIndex getCourseObj(String index) {
		dIndex.setFilename("Course");
		String[] details = dIndex.getCourseInfoRecord(index);
		//for (int i=0; i<details.length; i++) {System.out.print(details[i] + " ");}
		CourseIndex ci = new CourseIndex(index);
		ci.setIndex(index);
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
		//read in String[] {"LEC MONDAY 10:00 13:00 LT12"; "TUT MONDAY 9:00 10:00 TR101"}
		String[] elements3 = details[9].split(";"); //ADDED BY JY
		ArrayList<ClassType> ctArr = new ArrayList<ClassType>();//ADDED BY JY
		for(int i=0; i<elements3.length;i++) {//ADDED BY JY
			//System.out.println("classtype element" + elements3[i]);
			ClassType ct = new ClassType(elements3[i]);//ADDED BY JY
			ctArr.add(ct);//ADDED BY JY
		}
		ci.setClassList(ctArr); //ADDED BY JY
		return ci;
	}
	/**
	 * Return CourseIndex Row Using CourseIndex Object
	 * @param ci
	 * @return a list of information regarding this courseindex object
	 */
	public String[] setCourseRow(CourseIndex ci) { //usually folo by similarcode to update to db: dIndex.updateCourseInfo(index, setCourseRow(ci)); 
		String[] details = new String[10];
		details[0] = ci.getIndex();
		details[1] = ci.getCourseCode();
		details[2] = String.valueOf(ci.getVacancy());
		details[3] = String.join(";", ci.getRegisteredIDs());
		details[4] = String.join(";", ci.getWaitIDs());
		details[5] = String.valueOf(ci.getGrpNum());
		details[6] = String.valueOf(ci.getCourseType());
		details[7] = ci.getGERType();
		details[8] = String.valueOf(ci.getIndexAU());
		ArrayList<ClassType> ctArr = ci.getClassList(); //ADDED BY JY
		ClassType ct;
		ArrayList<String> ctArrStr = new ArrayList<String>();
		for (int i=0; i<ctArr.size(); i++) {
			ct = ctArr.get(i);
			ctArrStr.add(ct.toString());
		}
		details[9] = String.join(";",ctArrStr); 
		return details;
	}
	/**
	 * Return Course Row Using Course Object
	 * @param c
	 * @return a list of information regarding this course object
	 */
	public String[] setCourseInfoRow(Course c) {
		String[] s = new String[3]; // course code, index list and school code
		ArrayList<CourseIndex> ciArr = c.getCourseIndexList();
		ArrayList<String> indexesArr = new ArrayList<String>();
		for (int i =0; i<ciArr.size(); i++) {indexesArr.add(ciArr.get(i).getIndex());}
		String indices = String.join(";", indexesArr);
		s[0] = c.getCourseCode();
		s[1] = indices;
		s[2] = c.getSchoolCode();
		return s;
	}
	
	
	
}
