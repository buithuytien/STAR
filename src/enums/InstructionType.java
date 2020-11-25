package enums;

/**
 * Represents the mode of instruction of a class session
 * @author BUITT
 *
 */
public enum InstructionType {
	LEC("Lecture"), TUT("Tutorial"), LAB("Laboratory");
	
	/**
	 * type of class instruction as a String
	 */
	private String nameStr;
	
	/**
	 * constructor
	 * @param nameStr
	 */
	InstructionType(String nameStr) {
		this.nameStr = nameStr;
	}
	
	/**
	 * Convert a suitable String to its corresponding InstructionType enum
	 * @param x
	 * @return
	 */
	public InstructionType typeIT(String x) { //ADDED BY JY
		InstructionType it;
		if(x == "LEC") {
			it = InstructionType.LEC;
		} else if (x == "TUT") {
			it = InstructionType.TUT;
		} else if (x == "LAB") {
			it = InstructionType.LAB;
		} else {
			it = null;
		}
		return it;
	}
	
	/**
	 * Convert an enum object to its corresponding String object
	 * @param it
	 * @return
	 */
	public String typeStr(InstructionType it) { //ADDED BY JY
		String typeStr;
		if(it == InstructionType.LEC) {
			typeStr = "LEC";
		} else if (it == InstructionType.TUT) {
			typeStr = "TUT";
		} else if (it == InstructionType.LAB) {
			typeStr = "LAB";
		} else {
			typeStr = null;
		}
		return typeStr;
	}
	
	/**
	 * accessor method to get the name of the mode if instruction
	 * @return name of the enumerations
	 */
	public String getName() {
		return nameStr;
	}
	
	/**
	 * mutator method to set the name of the mode if instruction
	 * @param name
	 */
	public void setName(String name) {
		this.nameStr = name;
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
