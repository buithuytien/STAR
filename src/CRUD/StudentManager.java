package CRUD;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

import entity.*;
import util.*;
import util.ErrorException;

/**
 * class to change student's record in database, including:
 * change student access period to STARS system, and
 * create new student, with auto-generated password
 * @author BUITT
 *
 */
public class StudentManager {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");	 
	
	/**
	 * method to change student access period, and save to database file Users.csv
	 * @param stud
	 * @param startReg
	 * @param endReg
	 */
	public void changeAccessPeriod(Student stud, Date startReg, Date endReg ) {
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
		System.out.println("Student matric " + stud.getStudMat() + " access period updated to");
		System.out.println("Start time: " + startReg);
		System.out.println("End time: " + startReg);
		return ; // success
	}

	/**
	 * method to create new student record and save to database file Users.csv
	 * password is auto-generated and hashed
	 * @param s
	 */
	public void createNewStudent(Student s) {
		DBObj dbo = new DBObj();	
		Database db = new Database(System.getProperty("user.dir") + "\\src\\database\\");
		db.setFilename("Users");
		PeopleManager pm = new PeopleManager();
		// auto-generated password, and hashed
		String pw = "name.toLowerCase().charAt(0)" + "name.toLowerCase().charAt(0)"+"name.toLowerCase().charAt(0)"+"name.toLowerCase().charAt(0)"+"name.toLowerCase().charAt(0)"; //ADDED BY JY
		String password = s.getStudMat() + pw; //ADDED BY JY
		String password_hash = pm.generateHash(password); //ADDED BY JY
		s.setPassword(password_hash); //ADDED BY JY
		s.setStudEmail(s.getStudMat() + "@e.ntu.edu.sg");
		String[] r = dbo.setStudentRow(s);
		db.appendFile(r, "Users");
		
		System.out.println("Student " + s.getStudMat() + " sucessfully created");
		System.out.println("list of all student accounts created: ");
		Database db2 = new Database(System.getProperty("user.dir") + "\\src\\database\\");
		db2.setFilename("Users");
		db2.printStudentDB();
		
		
	}

}
