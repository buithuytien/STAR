package util;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class to write the data in a text file
 *
 */
public class TextDB {
	/**
	 * method to declare strings
	 */
	public static final String PATH="./db/";
	public static final String SEPERATOR="|";
	public static final String INDENT="\t\t\t";
	
	/**
	 * method to read the data stored in the text file of the given file path 
	 * @param filePath
	 * @return array of strings containing file path
	 * @throws IOException if an input or output exception occurred
	 */
	public static ArrayList read(String filePath) throws IOException {
		ArrayList<String> data = new ArrayList();
		Scanner scanner = new Scanner(new FileInputStream(PATH+filePath));
		
		try {
			while (scanner.hasNextLine()) {
				data.add(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
		
		return data;
	}
	
	/**
	 * method to write the data in the text file of the given file path
	 * @param filePath
	 * @param data
	 * @throws IOException if an input or output exception occurred
	 */
	public static void write(String filePath, ArrayList data) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(PATH+filePath));
		
		try {
			for (int i=0; i < data.size(); i++) {
				out.println((String)data.get(i));
			}
		} finally {
			out.close();
		}
	}
}