package backup;

public class Content {
	public String get(String column){
		if(column.equals("name"))
			return("a text");
		if(column.equals("id"))
			return("3");
		return null;
	}
	
	public static void main(String args[]){
		
	}

}
