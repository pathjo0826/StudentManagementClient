
/**
 * This class' function is to get the JSON from the TestClients to a more readable text through setters and getters.
 * @author Oscar Nordgren
 *
 */

public class Student {

	private int id;
	private String firstName;
	private String lastName;
	private String admissionYear;
	private String school;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAdmissionYear() {
		return admissionYear;
	}
	public void setAdmissionYear(String admissionYear) {
		this.admissionYear = admissionYear;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	@Override
	public String toString() {
		return String.format("Student (%d) - %s %s, Skola: %s, Antagen: %s", id, firstName, lastName, school, admissionYear);
	}
	
}
