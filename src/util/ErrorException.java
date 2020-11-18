package util;

public class ErrorException extends Exception{
	
	public static final String error1 = "choice";
	public static final String error2 = "studentYear";
	public static final String error3 = "recordNotFound";
	public static final String error4 = "recordFound";
	

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
//			System.out.println("ERROR: Record already exists. Please try again");
		}
	}
}