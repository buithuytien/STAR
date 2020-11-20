package entity;

public class Faculty{
	public String schoolCode;
	public String facultyName;
	
	public Faculty() {}
	
	public Faculty(String schoolCode, String facultyName) {
		this.schoolCode = schoolCode;
		this.facultyName = facultyName;
	}

	public String getFaculty(){return facultyName;}	
	public void setFaculty(String facultyName) {this.facultyName = facultyName;}	
	public String getSchoolCode() {return schoolCode;}	
	public void setSchoolCode(String schoolCode) {this.schoolCode = schoolCode;}
	
	
}
