package entity;
import App.*;
import database.*;
import enums.*;
import util.*;
import java.util.*;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pw = "name.toLowerCase().charAt(0)" + "name.toLowerCase().charAt(0)"+"name.toLowerCase().charAt(0)"+"name.toLowerCase().charAt(0)"+"name.toLowerCase().charAt(0)"; //ADDED BY JY
		String password = "U111111A" + pw; //ADDED BY JY
		System.out.println(password);
		
		StarsLogin s = new StarsLogin();
		
		AdminApp aa = new AdminApp();
		aa.printAdminMenu();
	}

}
