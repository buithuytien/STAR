package entity;

public class People extends Faculty{
	public String username;
	public String password;
	public char peopleType;
	
	public People() {}
	
	public People(String username) {
		this.username = username;
	}
	
	public People(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public People(String username, String password, char peopleType) {
		this.username = username;
		this.password = password;
		this.peopleType = peopleType;
	}
	

	public People(String schoolCode, String facultyName, String username, String password, char peopleType) {
		super(schoolCode,facultyName);
		this.username = username;
		this.password = password;
		this.peopleType = peopleType;
	}
	
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public char getPeopleType() {return peopleType;}	
	public void setPeopleType(char peopleType) {this.peopleType = peopleType;}	
	public String getSchool() {return super.schoolCode;}
	public void setSchool(String schoolCode) {super.schoolCode = schoolCode;}	
	public String getFaculty() {return super.facultyName;}	
	public void setFaculty(String FacultyName) {super.facultyName = facultyName;}
	
}
