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
		Database db = new Database(System.getProperty("user.dir") + "\\src\\database\\");
		db.setFilename("Users");
		
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

	public void createNewStudent(Student s) {
		DBObj dbo = new DBObj();	
		Database db = new Database(System.getProperty("user.dir") + "\\src\\database\\");
		db.setFilename("Users");
		PeopleManager pm = new PeopleManager();
		String pw = "name.toLowerCase().charAt(0)" + "name.toLowerCase().charAt(0)"+"name.toLowerCase().charAt(0)"+"name.toLowerCase().charAt(0)"+"name.toLowerCase().charAt(0)"; //ADDED BY JY
		String password = s.getStudMat() + pw; //ADDED BY JY
		String password_hash = pm.generateHash(password); //ADDED BY JY
		s.setPassword(password_hash); //ADDED BY JY
		s.setStudEmail(s.getStudMat() + "@e.ntu.edu.sg");
		String[] r = dbo.setStudentRow(s);
		db.appendFile(r, "Users");
	}

}
