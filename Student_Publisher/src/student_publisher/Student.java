package student_publisher;

import java.util.ArrayList;
import java.util.List;

public class Student {
	
	private String sID;
	private String name;
	private String grade;
	private String email;
	private String contact;
	private List<String> enrolledSubjects;
	
	

    public Student(String sID, String name, String grade, String email, String contact) {
		super();
		this.sID = sID;
		this.name = name;
		this.grade = grade;
		this.email = email;
		this.contact = contact;
		this.enrolledSubjects = new ArrayList<>();
	}


	public String getsID() {
		return sID;
	}


	public void setsID(String sID) {
		this.sID = sID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public void setEnrolledSubjects(List<String> subjects) {
		this.enrolledSubjects = subjects;
	}
	
	public List<String> getEnrolledSubjects() {
		return enrolledSubjects;
	}
	
	
	
	
	

}
