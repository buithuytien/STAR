package util;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter; // To allow us to append to csv
import java.io.BufferedReader; // To allow us to read from csv
import java.io.FileReader; // To allow us to read from csv
import java.util.*;  

/**
 * Class to handle database files
 * @author BUITT
 *
 */
public class Database {

	/**
	 * method to declare strings
	 */
	private String filepath;
	private String filename;
	private FileWriter pw;
	
	/**
	 * Constructor
	 * @param filepath
	 */
	public Database (String filepath) {
		this.filepath = filepath;
		this.filename = "";
	}
	
	/**
	 * set the filename without extension that this object will handle
	 * @param filename
	 */
	public void setFilename(String filename) {this.filename = filename;} 
	
	/**
	 * method to append one row to an existing file
	 * @param rows: array of String to append to file, each element of array will be saved into one separate cell
	 * @param filename:
	 */
	public void appendFile(String[] rows, String filename) {
		try {
			 // This allows us to append to the csv file without overwriting it
			this.filename = filename;
			pw = new FileWriter(this.filepath + this.filename + ".csv", true); // ADDED BY THUYTIEN
			for (int i = 0; i<rows.length;i++) {
			    if (i != rows.length -1) {
			    	//System.out.println(rows[i]);
			    	pw.append(rows[i]);
			    	pw.append(",");
			    } else{
			    	//System.out.println(rows[i]);
			    	pw.append(rows[i]);
			    	pw.append("\n");
			    	pw.flush();
			    }
			}
			pw.close();
			
		} catch(Exception e) {
			System.out.println("File not created.");
		}		
	}
	
	/**
	 * method to edit the cell at row i, column j in this object's file name
	 * row number and column number count starts from 0
	 * @param i
	 * @param j
	 * @param updateString
	 */
	public void updateFile(int i, int j, String updateString){
		try {
			// First we read in the whole file first
			BufferedReader br = new BufferedReader(new FileReader(this.filepath + this.filename + ".csv"));
			ArrayList<String> lines;
			lines =  new ArrayList<String>();
			String[] data;
			String newLine;
			String joined;
			while ((newLine = br.readLine()) != null) {
				lines.add(newLine);
			}
			
			// if i > number of rows in file, throw an error
			if(i > lines.size() - 1) { //  error handling
				System.out.println("row or column to edit exceeds number of current rows in file");
			}
			if(j > lines.size() - 1) { //  error handling
				System.out.println("row or column to edit exceeds number of current rows in file");
			}
			
			// After reading we retrieve the row and column that we want to change
			data = lines.get(i).split(","); // So if you are storing multiple items in a single column do not use comma to separate them!
			data[j] = updateString;
			joined = String.join(",", data);
			lines.set(i,joined);
			br.close(); // we need to close the buffered reader to start writing to it
			
			// Write back to csv
			FileWriter writer = new FileWriter(this.filepath + this.filename + ".csv");
			for (int k = 0; k < lines.size();k++) {
				data = lines.get(k).split(",");
				this.appendFile(data, this.filename);
			}
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	/**
	 * get record where its entry at the specified column matches the provided id
	 * @param id
	 * @param filename
	 * @param colNum
	 * @return the record of the whole matching row if found, null if not found
	 */
	public String[] getRecord(String id, String filename, int colNum) { 
		// find record in any file name at column number colNum; only return the first row found.
		this.filename = filename;
		String[] data;
		try {
			String row;
			String path_to_csv = this.filepath + this.filename + ".csv";
			BufferedReader csvReader = new BufferedReader(new FileReader(path_to_csv));
			while ((row = csvReader.readLine()) != null) {
			    data = row.split(",");
			    if (data[colNum].equals(id)) {
//			    	System.out.println("Record Found in " + filename + ".csv at column" + colNum);
			    	return data;
			    }
			}
		} catch(IOException e) {
			System.out.println("File Error.");
			e.printStackTrace();
			}
		System.out.println("Record not found");
		return new String[0];
	}
	
	/**
	 * Print list of students with name and ID and access period from database
	 */
	public void printStudentDB() {
		this.filename = "Users";
		String[] data;
		String row;
		String path_to_csv = this.filepath + this.filename + ".csv";
		
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(path_to_csv));
			while ((row = csvReader.readLine()) != null) {
			    data = row.split(",");
			    if (data[0].equals("S")) {
			    	System.out.println(data[1] + "  " + data[2] + "  " + data[10] + "   " + data[11]);
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * get the first row number of the record where its entry at the specified matches the provided String
	 * @param id
	 * @param filename
	 * @param colNum
	 * @return row number of the matching record if found, null if not found
	 */
	public int findRecord(String id, String filename, int colNum) {
		// find record in any file name at column number colNum; only return the first row found.
		int i = 0;
		this.filename = filename;
		String[] data;
		try {
			String row;
			String path_to_csv = this.filepath + this.filename + ".csv";
			BufferedReader csvReader = new BufferedReader(new FileReader(path_to_csv));
			while ((row = csvReader.readLine()) != null) {
			    data = row.split(",");
			    if (data[colNum].equals(id)) {
//			    	System.out.println("Record Found");
			    	return i;
			    }
			    i++;
			}
		} catch(IOException e) {
			System.out.println("File Error.");
			e.printStackTrace();
			}
		return -1;
	}
	
	/**
	 * get the first row number of the record where its entry at the column 0 matches the provided String
	 * @param id
	 * @param filename
	 * @return row number of the matching record if found, null if not found
	 */
	public int findRecord(String id, String filename) {
		// find record in any file name; only return the first row found.
		int i = findRecord(id, filename, 0); // return
		return i;
	}
	
	/**
	 * get all row numbers of the records where their individual entry at the specified matches the provided String
	 * @param id
	 * @param colNum
	 * @param filename
	 * @return array row number of the matching records, null if not found
	 */
	public int[] findAllRecord(String id, int colNum, String filename) {
		// find record in any file name at column number colNum; only return the first row found.
		int[] arrRow; // return this
		String arrRowString = ""; // this will grow
		
		int i = 0;
		this.filename = filename;
		String[] data;
		try {
			String row;
			String path_to_csv = this.filepath + this.filename + ".csv";
			BufferedReader csvReader = new BufferedReader(new FileReader(path_to_csv));
			while ((row = csvReader.readLine()) != null) {
			    data = row.split(",");
			    if (data[colNum].equals(id)) {
			    	arrRowString += i + ";" ;
			    }
			    i++;
			}
			if(arrRowString == "") {
				System.out.println("Record not found");
				return null;
			}
			String [] temp = arrRowString.split(";");
			arrRow = new int[temp.length ];
			for(int j = 0; j < temp.length ; j++) {
				arrRow[j] = Integer.parseInt(temp[j]);
			}
			return arrRow;
		} catch(IOException e) {
			System.out.println("File Error.");
			e.printStackTrace();
			}
		System.out.println("findAllRecord cannot find id requested");
		return null;
	}
	
	/**
	 * check if an user ID (matric number) exists in Users.csv
	 * @param userID
	 * @param password
	 * @return True or False
	 */
	public boolean matchUserRecord(String userID, String password) {
		filename = "Users";
		String[] data = new String[12];
		try {
			String path_to_csv = this.filepath + this.filename + ".csv";
			BufferedReader csvReader = new BufferedReader(new FileReader(path_to_csv));
			String row = csvReader.readLine();
			while (row  != null) {
			    data = row.split(",");
			    if (data.length == 0) {
			    	break;
			    }
			    if ((data[2].contains(userID)) && (data[3].contains(password))) {
			    	System.out.println("User Found");
			    	return true;
			    }
			    row = csvReader.readLine();
			}
			System.out.println("User not found");
			return false;
		} catch(IOException e) {
			System.out.println("File Error.");
			e.printStackTrace();
			}
		System.out.println("User not found");
		return false;
	}
	
	/**
	 * get record of the specified user if their ID exists in Users.csv
	 * @param userID
	 * @return records of the user as array of String
	 */
	public String[] getUserRecord(String userID) { 
		filename = "Users";
		String[] data = new String[12];
		try {
			String row;
			String path_to_csv = this.filepath + this.filename + ".csv";
			BufferedReader csvReader = new BufferedReader(new FileReader(path_to_csv));
			while ((row = csvReader.readLine()) != null) {
			    data = row.split(",");
			    if (data[2].equals(userID)) {
			    	//System.out.println("User Record Retrieved.");
			    	return data;
			    }
			}
		} catch(IOException e) {
			System.out.println("File Error.");
			e.printStackTrace();
			}
//		System.out.println("User not found, Record not retrieved.");
		return new String[0];
	}
	
	/**
	 * create Users.csv file with necessary column headers if the file is not existed
	 * @param filename
	 */
	public void CreateUserFile(String filename) {
		this.filename = "Users";
		try {
			File myObj = new File(this.filepath + filename + ".csv");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
				pw = new FileWriter(this.filepath + this.filename + ".csv",true); // This is to prepare to write to the file
				pw.append("User Type");
				pw.append(",");
				pw.append("Name");
				pw.append(",");
				pw.append("Matric Number");
				pw.append(",");
				pw.append("Password");
				pw.append(",");
				pw.append("Faculty");
				pw.append(",");
				pw.append("Gender");
				pw.append(",");
				pw.append("Nationality");
				pw.append(",");
				pw.append("Phone Number");
				pw.append(",");
				pw.append("Email");
				pw.append(",");
				pw.append("Year of Study");
				pw.append(",");
				pw.append("Date Time Start");
				pw.append(",");
				pw.append("Date Time End");
				pw.append("\n");
				pw.flush();
			} 
			else {
				pw = new FileWriter(this.filepath + this.filename + ".csv",true); // This is to prepare to write to the file
				pw.flush();
			}

			
		} 
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	/**
	 * create Course.csv file with necessary columns headers if the file has not existed
	 * @param filename
	 */
	public void CreateCourseFile(String filename) { 
		this.filename = "Course";
		try {
			File myObj = new File(this.filepath + filename + ".csv");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
				pw = new FileWriter(this.filepath + this.filename + ".csv",true); // This is to prepare to write to the file
				pw.append("Course Index");
				pw.append(",");
				pw.append("Course Code");
				pw.append(",");
				pw.append("Vacancies");
				pw.append(",");
				pw.append("registerID"); //pls note the id are separated by ;
				pw.append(",");
				pw.append("waitlistID"); //pls note the id are separated by ;
				pw.append(",");
				pw.append("Group Number");
				pw.append(",");
				pw.append("Course Type");
				pw.append(",");
				pw.append("GERPE Type");
				pw.append(",");
				pw.append("AUs");
				pw.append(",");
				pw.append("Sessions"); // class info, eg: LEC MONDAY 10:00 13:00 LT12;TUT MONDAY 9:00 10:00 TR101
				pw.append("\n");
				pw.flush();
			} 
			else {
				//System.out.println("File already exists.");
				pw = new FileWriter(this.filepath + this.filename + ".csv",true); // This is to prepare to write to the file
				pw.flush();
			}
		} 
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * get record of the specified course index if the index number exists in Course.csv
	 * @param course_index
	 * @return record of the specified course index as an array of String
	 */
	public String[] getCourseInfoRecord(String course_index) {
		String[] data;
		try {
			String row;
			String path_to_csv = this.filepath + this.filename + ".csv";
			BufferedReader csvReader = new BufferedReader(new FileReader(path_to_csv));
			while ((row = csvReader.readLine()) != null) {
			    data = row.split(",");
			    if (data[0].equals(course_index)) {
			    	return data;
			    }
			}
		} catch(IOException e) {
			System.out.println("File Error.");
			e.printStackTrace();
			}
		System.out.println("Course Index not found");
		return new String[0];
	}
	
	/**
	 * check if a course index exists in Course.csv file
	 * @param course_index
	 * @return True or False
	 */
	public boolean matchCourseInfoRecord(String course_index) { //USED BY JY
		this.filename = "Course";
		String[] data;
		try {
			String path_to_csv = this.filepath + this.filename + ".csv";
			BufferedReader csvReader = new BufferedReader(new FileReader(path_to_csv));
			String row = csvReader.readLine();
			while (row  != null) {
			    data = row.split(",");
			    if (data.length == 0) {
			    	return false;
			    }
			    if (data[0].equals(course_index)) {
			    	return true;
			    }
			    row = csvReader.readLine();
			}
		} catch(IOException e) 
		{
			System.out.println("File Error.");
			e.printStackTrace();
		}
		System.out.println("Course Index Not Found!!!");
		return false;
	}
	
	/**
	 * get the row number of the record whose entry in column 0 matches the provided course index number
	 * @param course_index
	 * @param filename
	 * @return the row number of the matching entry
	 */
	public int findCourseIndexRecord(String course_index, String filename) {
		// fine line number of course_index or course. -1 means not found.
		// eg. findCourseIndexRecord("10001", "Course") OR findCourseIndexRecord("AB1001", "CourseInfo") 
		int i = 0;
		this.filename = filename;
		String[] data;
		try {
			String row;
			String path_to_csv = this.filepath + this.filename + ".csv";
			BufferedReader csvReader = new BufferedReader(new FileReader(path_to_csv));
			while ((row = csvReader.readLine()) != null) {
			    data = row.split(",");
			    if (data[0].equals(course_index)) {
			    	return i;
			    }
			    i++;
			}
		} catch(IOException e) {
			System.out.println("File Error.");
			e.printStackTrace();
			}
		System.out.println("Course Index not found");
		return -1;
	}
	
	/**
	 * get all index numbers in Course.csv file if the specified item is found in the specified column of the index number's record
	 * @param item
	 * @param loc
	 * @return ArrayList of matching index numbers
	 */
	public ArrayList<String> returnIndexRecord(String item, int loc) { //eg. matric no. and regid location
		filename = "Course";
		String[] data;
		ArrayList<String> index = new ArrayList<String>();
		try {
			String path_to_csv = this.filepath + this.filename + ".csv";
			BufferedReader csvReader = new BufferedReader(new FileReader(path_to_csv));
			String row = csvReader.readLine();
			while (row  != null) {
				
			    data = row.split(",");
			    if (data.length == 0) { //no rows inside
			    	break;
			    }
			    if (loc == 3 || loc == 4) { //if its a list of IDs
			    	String[] elements1 = data[loc].split(";");
					for (int j=0; j < elements1.length ; j++) {
						if (elements1[j].equals(item)) {
							index.add(data[0]);
						}
					}				
			    } else {
			    	if (data[loc].equals(item)) {
			    	index.add(data[0]);
			    	}
			    }
			    row = csvReader.readLine();
			}
			return index;
		} catch(IOException e) {
			System.out.println("File Error.");
			e.printStackTrace();
			}
		System.out.println(item + "Not Found");
		return null;
	}
	
	
}

