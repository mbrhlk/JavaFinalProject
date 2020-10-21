package marina1020w3d2;

public class Student {
	private String firstName;
	private String lastName;
//	private int ;
	
	public Student(String fName, String lName) {
		
//		if (fName == "" || lName == "") {
//			System.out.println("Invalid First Name");
		
		if (fName == "" || fName == null) {
			firstName = "Unknown";
		} else {
			firstName = fName;
		}
		
		if(lName == "") {
			lastName = "Unknown";
		}else {
			lastName = lName;
		}
		
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}	
	
	public void setFirstName(String fName) {
		if(fName == "" || fName == null) {
			firstName = "Unknown";
		} else {
			firstName = fName;
		}
	}
	
	public void setLastName(String lNAme) {
		if(lName == "" || lName == null) {
			lastName = "Unknown";
		} else {
			lastName = lName;	
		}
}
}
	