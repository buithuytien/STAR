package util;

public class HashFunction {
	
	private String string_to_hash;
	private int hash;
	
	public void HashFunction(){
		this.string_to_hash = "";
		this.hash = 0;
	}
	
	public int hash(String string_hash){
		this.hash = 0; // reset to zero before I run anything
		this.string_to_hash = string_hash;
		if (string_hash.length() == 0) {
			return hash;
		}
		else {
			int c_num;
			for (int i = 0; i < string_to_hash.length(); i++) {
		        char c = string_to_hash.charAt(i);
		        c_num = Character.getNumericValue(c);
		        hash = ((hash<<5)-hash)+c_num;
		        hash = hash & hash; // Convert to 32bit integer
		    }
		    return hash;
		}
	}
	


}
