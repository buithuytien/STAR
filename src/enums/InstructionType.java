package enums;

public enum InstructionType {
	LECTURE("Lecture"), TUT("Tutorial"), LAB("Laboratory");
	
	private String nameStr;
	
	/**
	 * constructor
	 * @param nameStr
	 */
	InstructionType(String nameStr) {
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
		int N = InstructionType.values().length;
		for (int i=0; i<N; i++) {
			System.out.println(i+" : "+InstructionType.values()[i].getName());
		}
		
		return N;
	}

}
