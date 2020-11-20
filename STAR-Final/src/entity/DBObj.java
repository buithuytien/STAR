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

public class DBObj {
	SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Database dUser = new Database(System.getProperty("user.dir") + "\\src\\database\\");
	Database dIndex = new Database(System.getProperty("user.dir") + "\\src\\database\\");
	Database dInfo = new Database(System.getProperty("user.dir") + "\\src\\database\\");
	
	public DBObj() {}
	
	// ADJUSTED BY THUYTIEN
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
		//ci.setClassList(details[9].split(";")); // ADDED BY THUYTIEN // array of String //COMMENTED OUT BY JY
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
	
	// ADJUSTED BY THUYTIEN
	public String[] setCourseRow(CourseIndex ci) { //usually folo by similarcode to update to db: dIndex.updateCourseInfo(index, setCourseRow(ci)); 
		String[] details = new String[10];
		details[0] = ci.getIndex();
		details[1] = ci.getCourseCode();
		details[2] = String.valueOf(ci.getVacancy());
		details[3] = String.join(";", ci.getRegisteredIDs());
//		String listString = "";
//		for (String s : ci.getRegisteredIDs()) {listString += s + ";";}
//		details[3] = listString;
//		String listString2 = "";
//		for (String s : ci.getWaitIDs()) {listString2 += s + ";";}
//		details[4] =listString2;
		details[4] = String.join(";", ci.getWaitIDs());
		details[5] = String.valueOf(ci.getGrpNum());
		details[6] = String.valueOf(ci.getCourseType()); //details[6] = ci.getCourseType(); // ADDED BY THUYTIEN
		details[7] = ci.getGERType();
		details[8] = String.valueOf(ci.getIndexAU());
//		details[9] = ci.toStringClassList(); // return LEC MONDAY 10:00 13:00 LT12;TUT MONDAY 9:00 10:00 TR101	// ADDED BY THUYTIEN //COMMENTED OUT BY JY
//		String listString3 = ""; //ADDED BY JY
		ArrayList<ClassType> ctArr = ci.getClassList(); //ADDED BY JY
		ClassType ct;
		ArrayList<String> ctArrStr = new ArrayList<String>();
		for (int i=0; i<ctArr.size(); i++) {
			ct = ctArr.get(i);
			ctArrStr.add(ct.toString());
		}
		details[9] = String.join(";",ctArrStr); 
//		for (int i=0; i<ctArr.size(); i++) {listString3 += ctArr.get(i).toString() + ";";} //ADDED BY JY
//		details[9] = listString3; //ADDED BY JY
//		String listString3 =  ci.getClassList().toString();
//		details[9] = String.join(";",listString3); //ADDED BY JY
		return details;
	}
	
	//ADDED BY JY
	public String[] setCourseInfoRow(Course c) {
		String[] s = new String[3]; // course code, index list and school code
		
		//ADDED BY JY
		ArrayList<CourseIndex> ciArr = c.getCourseIndexList();
		ArrayList<String> indexesArr = new ArrayList<String>();
		for (int i =0; i<ciArr.size(); i++) {indexesArr.add(ciArr.get(i).getIndex());}
		String indices = String.join(";", indexesArr);
		
		//COMMENTED OUT BY JY
		// get string of indices: 10001;10002;10003 ...
//		for(int i = 0; i < c.getCourseIndexList().size(); i++ ) {
//			if(i < c.getCourseIndexList().size()-1 ) {
//				indices += c.getCourseIndexList().get(i).getIndex() + ";";
//			} else {
//				indices += c.getCourseIndexList().get(i).getIndex();
//			}
//		}
		s[0] = c.getCourseCode();
		s[1] = indices;
		s[2] = c.getSchoolCode();
		return s;
	}
	
	
	
}
