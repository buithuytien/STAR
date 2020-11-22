package util;

public class ErrorException extends Exception{
	
	public static final String error1 = "choice";
	public static final String error2 = "studentYear";
	public static final String error3 = "recordNotFound";
	public static final String error4 = "recordFound";
	public static final String error5 = "studentmatric";
	public static final String error6 = "indexNotFound";
	public static final String error7 = "indexNotMatch";
	public static final String error8 = "nameError";
	public static final String error9 = "genderError";
	public static final String error10 = "facultyError";
	public static final String error11 = "yearError";
	public static final String error12 = "natError";
	public static final String error13 = "phoneError";
	public static final String error14 = "matricFound";
	public static final String error15 = "timeError";
	
	/**
	 * Handles All Error Exceptions
	 * Prompt User To Re-enter Their Choice
	 * @param error
	 */
	public ErrorException(String error){
		if(error.equals(error1)){
			System.out.println("ERROR: Please enter a valid choice.");
            System.out.println("");
		}
		
        if(error.equals(error2)){
            System.out.println("ERROR: Please enter a valid student year.");
            System.out.println("");
        }
        
        if(error.equals(error3)){
			System.out.print("ERROR: Record not found ");
		}
        
        if(error.equals(error4)){
			System.out.println("ERROR: Record already exists");
		}
        
        if(error.equals(error5)) {
        	System.out.println("ERROR: Please enter a valid student matric no.");
        }
        
        if(error.equals(error6)) {
        	System.out.println("ERROR: Please enter a valid index:");
        }
        if(error.equals(error7)) {
        	System.out.println("Please enter a valid index no from the list you have registered:");
        }
        if(error.equals(error8)) {
        	System.out.println("Please enter a valid name (without digits):");
        }
        if(error.equals(error9)) {
        	System.out.println("Please enter a valid gender (F/M):");
        }
        if(error.equals(error10)) {
        	System.out.println("Please enter a valid faculty (without digits):");
        }
        if(error.equals(error11)) {
        	System.out.println("Please enter a valid year (1-5):");
        }
        if(error.equals(error12)) {
        	System.out.println("Please enter a valid nationality (without digits):");
        }
        if(error.equals(error13)) {
        	System.out.println("Please enter a valid sg phone number (8 digits only):");
        }
        if(error.equals(error14)) {
        	System.out.println("Student exist. Please enter a new matric number: ");
        }
        if(error.equals(error15)) {
        	System.out.println("Please enter a valid access time (dd/mm/yyyy HH:mm:ss): ");
        }
	}
}