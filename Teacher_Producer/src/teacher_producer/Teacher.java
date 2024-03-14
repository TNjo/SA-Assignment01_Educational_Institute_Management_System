package teacher_producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Teacher {
    private String teacherID;
    private String name;
    private String contact;
    private String email;
    private List<String> assignedSubjects;

    public Teacher(String teacherID, String name, String email,String contact) {
        super();
    	this.teacherID = teacherID;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.assignedSubjects = new ArrayList<>();
    }

	public String getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAssignedCourses(List<String> subjects) {
		this.assignedSubjects = subjects;
	}

	public List<String> getAssignedCourses() {
		return this.assignedSubjects;
	}


}
