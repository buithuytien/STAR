package entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import util.Database;

public class testDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String filepath = System.getProperty("user.dir") + "\\src\\database\\";
			String filename = "file_test";
			String updateString = "update string test";
			Database D = new Database(System.getProperty("user.dir") + "\\src\\database\\", "filename_test");
			int i= 0, j = 0;

			BufferedReader br = new BufferedReader(new FileReader(filepath + filename + ".csv"));
			ArrayList<String> lines;
			lines =  new ArrayList<String>();
			String[] data;
			String newLine;
			String joined;
			while ((newLine = br.readLine()) != null) {
				//System.out.println(newLine);
				lines.add(newLine);
			}

			// print the lines
			//			for(int i = 0; i < lines.size(); i++) {
			//				String line = lines.get(i);
			//				System.out.println(line);
			//			}

			// After reading we retrieve the row and column that we want to change
			// System.out.println(lines.get(i));
			data = lines.get(i).split(","); // So if you are storing multiple items in a single column do not use comma to separate them!
			data[j] = updateString;
			joined = String.join(",", data);
			lines.set(i,joined);
			br.close(); // we need to close the buffered reader to start writing to it

			// Write back to csv
			FileWriter writer = new FileWriter(filepath + filename + ".csv");
			for (int k = 0; k < lines.size();k++) {
				data = lines.get(k).split(",");
				D.appendFile(data);
			}


		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}
}
