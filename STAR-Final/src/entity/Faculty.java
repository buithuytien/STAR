package entity;

public class Faculty{
	public String schoolCode;
	public String facultyName;
	/**
	 * Constructor
	 */
	public Faculty() {}
	
	public Faculty(String schoolCode, String facultyName) {
		this.schoolCode = schoolCode;
		this.facultyName = facultyName;
	}
	/**
	 * Get Faculty Name
	 * @return this faculty name
	 */
	public String getFaculty(){return facultyName;}	
	/**
	 * Set Faculty Name
	 * @param facultyName
	 */
	public void setFaculty(String facultyName) {this.facultyName = facultyName;}	

	
}
