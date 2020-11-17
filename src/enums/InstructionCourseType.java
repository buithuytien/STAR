package enums;

public enum InstructionCourseType {
	CORE("Core"), MPE("MajorPE"), GERPE("GerPE"), UE("Unrestricted Electives");
	
	private String nameStr;
	
	/**
	 * constructor
	 * @param nameStr
	 */
	InstructionCourseType(String nameStr) {
		this.nameStr = nameStr;
	}
	
	/**
	 * accessor method to get the name of the categories
	 * @return name of the enumerations
	 */
	public String getName() {
		return nameStr;
	}
	
	/**
	 * method to print the class categories of the all the instruction types
	 * @return length of the data
	 */
	public static int printChoices() {
		int N = InstructionCourseType.values().length;
		for (int i=0; i<N; i++) {
			System.out.println(i+" : "+InstructionCourseType.values()[i].getName());
		}
		
		return N;
	}
}
