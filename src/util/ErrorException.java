package util;

public class ErrorException extends Exception{
	
	public static final String error1 = "choice";
	public static final String error2 = "studentYear";
	

	public ErrorException(String error){
		if(error.equals(error1)){
			System.out.println("Please enter a valid choice.");
            System.out.println("");
		}
        if(error.equals(error2)){
            System.out.println("Please enter a valid student year.");
            System.out.println("");
        }
	}
}