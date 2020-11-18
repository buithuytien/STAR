package CRUD;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

import entity.*;
import util.*;
import util.ErrorException;

public class StudentManager {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	 
	
	public int changeAccessPeriod(Student stud, Date startReg, Date endReg ) { // String startTime, String endTime //throws ParseException 
		DBObj dbo = new DBObj();	
		Database db = new Database(System.getProperty("user.dir") + "\\src\\database\\", "Users");
		
//		Student stud = dbo.getStudentObj(matric); // ErrorException if not found record
		int i = db.findRecord(stud.getStudMat(), "Users", 2);
		
		// update student object
		stud.setStartTime(startReg);
		stud.setEndTime(endReg);
		String [] r = dbo.setStudentRow(stud);
		db.updateFile(i, 10, r[10]);
		db.updateFile(i, 11, r[11]);
		System.out.println("Student matric " + stud.getStudMat() + " access period updated!");
		return 1; // success
	}
	
	public int createNewStudent(String name, String matric, char gender, String faculty, String nationality, int phone, int year, Date startReg, Date endReg) {
		// password auto generated: equals matric number
		DBObj dbo = new DBObj();	
		Database db = new Database(System.getProperty("user.dir") + "\\src\\database\\", "Users");
		
		Student s = new Student(matric);
		s.setStudName(name);
		s.setStudMat(matric);
		s.setPassword(matric);
		s.setFaculty(faculty);
		s.setStudGender(gender);
		s.setStudNat(nationality);
		s.setStudNum(phone);
		s.setStudEmail(matric + "@e.ntu.edu.sg");
		s.setStudYear(year);
		
		s.setStartTime(startReg);
		s.setEndTime(endReg);	
		
		String[] r = dbo.setStudentRow(s);
		db.appendFile(r);
		
		return 1;
		// email, 
	}

}
