package CRUD;

public class SendMailSSL{
	
	 public static void main(String[] args) {
		 
		 String from = "ntucz2002@gmail.com";
		 String pw = "cz2002assignment"; 
		 String to ="mojunyi20@gmail.com";
		 String sub = "You have been allocated a new course";
		 String msg = "Your registraction for course XXXX is sucessful";
		 
		 
		 
		// Mailer a = new Mailer();
	     //from,password,to,subject,message  
	     //Mailer.send("ntucz2002@gmail.com","cz2002assignment","f77@yahoo.com","hello javatpoint","How r u?");  
	    
		 Mailer.send(from, pw, to, sub, msg);
	     
	     //change from, password and to  
	 }    
	}  