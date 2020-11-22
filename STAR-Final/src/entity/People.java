package entity;

public class People extends Faculty{
	public String username;
	public String password;
	public char peopleType;
	
	/**
	 * Constructor
	 */
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
	/**
	 * Get People UserId (Eg.Student Matric Number)
	 * @return this person's username
	 */
	public String getUsername() {return username;}
	/**
	 * Set UserId
	 * @param username
	 */
	public void setUsername(String username) {this.username = username;}
	/**
	 * Get Password
	 * @return this person's password
	 */
	public String getPassword() {return password;}
	/**
	 * Set Password
	 * @param password
	 */
	public void setPassword(String password) {this.password = password;}
	/**
	 * Get Poeple Type (Eg. Admin/Student)
	 * @return this people type ('S' or 'A')
	 */
	public char getPeopleType() {return peopleType;}	
	/**
	 * Set People Type (Eg. Admin/Student)
	 * @param peopleType
	 */
	public void setPeopleType(char peopleType) {this.peopleType = peopleType;}	
	/**
	 * Get School Code
	 * @return this school code
	 */
	public String getSchool() {return super.schoolCode;}
	/**
	 * Set School Code
	 * @param schoolCode
	 */
	public void setSchool(String schoolCode) {super.schoolCode = schoolCode;}	
	/**
	 * Get Faculty Name
	 */
	public String getFaculty() {return super.facultyName;}	
	/**
	 * Set Faculty Name
	 */
	public void setFaculty(String FacultyName) {super.facultyName = facultyName;}
	
}
