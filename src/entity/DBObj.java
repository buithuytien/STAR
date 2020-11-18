package entity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.*;

import App.*;
import util.*;

public class DBObj {
	SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");	
//	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yy HH:mm:ss");
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Database dUser = new Database(System.getProperty("user.dir") + "\\src\\database\\");
	Database dIndex = new Database(System.getProperty("user.dir") + "\\src\\database\\");
	Database dInfo = new Database(System.getProperty("user.dir") + "\\src\\database\\");
	
	public DBObj() {}
	
	// ADJUSTED BY THUYTIEN
	public Student getStudentObj(String matric) throws ParseException, ErrorException {
		dUser.setFilename("Users");
		String[] details = dUser.getUserRecord(matric);
		if(details.length == 0) {   // ADJUSTED BY THUYTIEN
			throw new ErrorException("recordNotFound"); 
		} else {
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
	}
	
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
	
	// ADJUSTED BY THUYTIEN
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
		//read in String[] {"LEC MONDAY 10:00 13:00 LT12"; "TUT MONDAY 9:00 10:00 TR101"}
		ci.setClassList(details[9].split(";")); // ADDED BY THUYTIEN // array of String
		return ci;
	}
	
	// ADJUSTED BY THUYTIEN
	public String[] setCourseRow(CourseIndex ci) { //usually folo by similarcode to update to db: dIndex.updateCourseInfo(index, setCourseRow(ci)); 
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
		details[6] = String.valueOf(ci.getCourseType()); //details[6] = ci.getCourseType(); // ADDED BY THUYTIEN
		details[7] = ci.getGERType();
		details[8] = String.valueOf(ci.getIndexAU());
		details[9] = ci.toStringClassList(); // return LEC MONDAY 10:00 13:00 LT12;TUT MONDAY 9:00 10:00 TR101	// ADDED BY THUYTIEN
		return details;
	}
	
	
	
	
	
	
}
