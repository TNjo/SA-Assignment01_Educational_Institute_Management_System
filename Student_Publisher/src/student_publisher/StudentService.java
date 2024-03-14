package student_publisher;

import java.util.List;

public interface StudentService {
	
	public void displayStudent();

	void insertStudentDetails();

	void deleteStudent(String studentID);

	List<Student> getStudentAll();
	

//	void enrollForSubjects(String studentID, List<String> subjects);

	void enrollForSubjects(String studentID);

	 public Student searchStudentById(String studentID);

	void updateStudentDetails(String studentID);

	List<String> determineSubjectsForGrade(String grade);
	 
	 
	

}
