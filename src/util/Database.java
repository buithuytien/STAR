package util;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter; // To allow us to append to csv
import java.io.BufferedReader; // To allow us to read from csv
import java.io.FileReader; // To allow us to read from csv
import java.util.*;  

public class Database {
	private String filepath;
	private String filename;
	private FileWriter pw;
	
//	public Database (String filename) {
//		this.filepath = System.getProperty("user.dir") + "\\database";
//		this.filename = filename;
//	}
	
	public Database (String filepath) {
		this.filepath = filepath;
		this.filename = "";
	}
	
	public Database (String filepath, String filename) {
		this.filepath = filepath;
		this.filename = filename; // WITHOUT the .csv
	}

	
	public void appendFile(String[] rows, String filename) { // ADDED BY THUYTIEN, OVERLOAD THE PREVIOUS appendFile
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
	
	public void appendFile(String[] rows) { 
		appendFile(rows, this.filename);
	}
	
	public void updateFile(int i, int j, String updateString){ // WROTE BY YIKSIANG
		try {
			// First we read in the whole file first
			BufferedReader br = new BufferedReader(new FileReader(this.filepath + this.filename + ".csv"));
			ArrayList<String> lines;
			lines =  new ArrayList<String>();
			String[] data;
			String newLine;
			String joined;
			while ((newLine = br.readLine()) != null) {
				//System.out.println(newLine);
				lines.add(newLine);
			}
			
			// if i > number of rows in file, throw an error
			if(i > lines.size() - 1) { //  error handling
				System.out.println("row or column to edit exceeds number of current rows in file");
//				return;
			}
			if(j > lines.size() - 1) { //  error handling
				System.out.println("row or column to edit exceeds number of current rows in file");
//				return;
			}
			
			// After reading we retrieve the row and column that we want to change
			// System.out.println(lines.get(i));
			data = lines.get(i).split(","); // So if you are storing multiple items in a single column do not use comma to separate them!
			data[j] = updateString;
			joined = String.join(",", data);
			lines.set(i,joined);
			br.close(); // we need to close the buffered reader to start writing to it
			
			// Write back to csv
			FileWriter writer = new FileWriter(this.filepath + this.filename + ".csv");
			for (int k = 0; k < lines.size();k++) {
				data = lines.get(k).split(",");
				this.appendFile(data);
			}
			
//			writer.flush();
//			writer.close();
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public String[] getRecord(String id, String filename, int colNum) {  // ADDED BY THUYTIEN
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
	
	public int findRecord(String id, String filename, int colNum) {   // ADDED BY THUYTIEN
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
//		System.out.println("Course Index not found");
		return -1;
	}
	
	public int findRecord(String id, String filename) {  // ADDED BY THUYTIEN
		// find record in any file name; only return the first row found.
		int i = findRecord(id, filename, 0); // return
		return i;
	}
	
	public int findRecord(String id) {  // ADDED BY THUYTIEN
		int i = findRecord(id, this.filename, 0); // return
		return i;
	}
	
	public int[] findAllRecord(String id, int colNum, String filename) {  // ADDED BY THUYTIEN
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
		System.out.println("Course Index not found");
		return null;
	}
	
	public int[] findAllRecord(String id, int colNum) {   // ADDED BY THUYTIEN
		return findAllRecord(id, colNum, this.filename);
	}
	
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
			    	System.out.println("User Found");
			    	return data;
			    }
			}
		} catch(IOException e) {
			System.out.println("File Error.");
			e.printStackTrace();
			}
		System.out.println("User not found");
		return new String[0];
	}
	
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
				System.out.println("File already exists.");
				pw = new FileWriter(this.filepath + this.filename + ".csv",true); // This is to prepare to write to the file
				pw.flush();
			}

			
		} 
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	// ADJUSTED BY THUYTIEN
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
				pw.append("Sessions"); // class info, eg: LEC MONDAY 10:00 13:00 LT12;TUT MONDAY 9:00 10:00 TR101 // ADDED BY THUYTIEN
				pw.append("\n");
				pw.flush();
			} 
			else {
				System.out.println("File already exists.");
				pw = new FileWriter(this.filepath + this.filename + ".csv",true); // This is to prepare to write to the file
				pw.flush();
			}

			
		} 
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	
	
	public String[] getCourseInfoRecord(String course_index, String filename) {
		this.filename = filename;
		String[] data;
		try {
			String row;
			String path_to_csv = this.filepath + this.filename + ".csv";
			BufferedReader csvReader = new BufferedReader(new FileReader(path_to_csv));
			while ((row = csvReader.readLine()) != null) {
			    data = row.split(",");
			    if (data[0].equals(course_index)) {
			    	System.out.println("Course Index Found");
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
	
	public String[] getCourseInfoRecord(String course_index) {
		String [] temp = getCourseInfoRecord(course_index, "Course");
		return temp;
	}
	
	public boolean matchCourseInfoRecord(String course_index) {
		this.filename = "Course";
		String[] data;
		try {
			String path_to_csv = this.filepath + this.filename + ".csv";
			BufferedReader csvReader = new BufferedReader(new FileReader(path_to_csv));
			String row = csvReader.readLine();
			while (row  != null) {
			    data = row.split(",");
			    if (data.length == 0) {
			    	break;
			    }
			    if (data[0].equals(course_index)) {
			    	System.out.println("Course Index Found");
			    	return true;
			    }
			    row = csvReader.readLine();
			}
			System.out.println("Course Index Found");
			return false;
		} catch(IOException e) {
			System.out.println("File Error.");
			e.printStackTrace();
			}
		System.out.println("Course Index Found");
		return false;
	}
	
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
			    	System.out.println("Course Index Found");
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
	
	public int findCourseIndexRecord(String course_index) {
		// fine line number of course_index or course. -1 means not found.
		// eg. findCourseIndexRecord("10001", "Course") OR findCourseIndexRecord("AB1001", "CourseInfo") 
		int temp = findCourseIndexRecord(course_index, "Course");
		return temp;
	}
	
	public void CreateCourseIndexFile(String filename) {
		this.filename = "IndexInfo";
		try {
			File myObj = new File(this.filepath + filename + ".csv");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
				pw = new FileWriter(this.filepath + this.filename + ".csv",true); // This is to prepare to write to the file
				pw.append("Course Index");
				pw.append(",");
				pw.append("Course Class Type");
				pw.append(",");
				pw.append("Session");  // TODO: remove//
				pw.append(",");
				pw.append("Time");
				pw.append(",");
				pw.append("Venue");
				pw.append(",");
				pw.append("Remarks");
				pw.append("\n");
				pw.flush();
			} 
			else {
				System.out.println("File already exists.");
				pw = new FileWriter(this.filepath + this.filename + ".csv",true); // This is to prepare to write to the file
				pw.flush();
			}

			
		} 
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public String[] getCourseIndexRecord(String course_index, String class_type, String session) {
		filename = "IndexInfo";
		String[] data = new String[5];
		try {
			String row;
			String path_to_csv = this.filepath + this.filename + ".csv";
			BufferedReader csvReader = new BufferedReader(new FileReader(path_to_csv));
			while ((row = csvReader.readLine()) != null) {
			    data = row.split(",");
			    if ((data[0].equals(course_index)) && (data[1].equals(class_type)) && (data[2].equals(session)) ) {
			    	System.out.println("Course Index, Session and  Found");
			    	return data;
			    }
			}
		} catch(IOException e) {
			System.out.println("File Error.");
			e.printStackTrace();
			}
		System.out.println("Course Index, Session and  Found");
		return new String[0];
	}
	
	public boolean matchCourseIndexRecord(String course_index, String class_type, String session) {
		filename = "IndexInfo";
		String[] data = new String[5];
		try {
			String path_to_csv = this.filepath + this.filename + ".csv";
			BufferedReader csvReader = new BufferedReader(new FileReader(path_to_csv));
			String row = csvReader.readLine();
			while (row  != null) {
			    data = row.split(",");
			    if (data.length == 0) {
			    	break;
			    }
			    if ((data[0].equals(course_index)) && (data[1].equals(class_type)) && (data[2].equals(session))) {
			    	System.out.println("Course Index Found");
			    	return true;
			    }
			    row = csvReader.readLine();
			}
			System.out.println("Course Index, Session and  Found");
			return false;
		} catch(IOException e) {
			System.out.println("File Error.");
			e.printStackTrace();
			}
		System.out.println("Course Index, Session and  Found");
		return false;
	}
	
	
	// jy add
	public void setFilename(String filename) {this.filename = filename;}
	
	public void updateCourseInfo(String index, String[] str) { //to update the whole row
		// codes to flush the updated info into excel file
		filename = "Course";
		
		try {
			String path_to_csv = this.filepath + this.filename + ".csv";
			BufferedReader csvReader = new BufferedReader(new FileReader(path_to_csv));
			String row = csvReader.readLine();
			String[] data;
			while (row  != null) {
			    data = row.split(",");
			    if (data.length == 0) { //no rows inside
			    	break;
			    }
			    if (data[0] == index) {
			    	for (int i = 0; i< data.length; i++) 
			    	{
			    	pw.write(data[i]);
			    	pw.append(",");
			    	}
			    	pw.flush();
			    	
			    	break;
			    }
			    row = csvReader.readLine();
			}			
		} catch(IOException e) {
			System.out.println("Unable to edit content.");
			e.printStackTrace();
			}
		System.out.println("Not Found");
	}
	
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

