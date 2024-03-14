package student_publisher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StuentServiceImpl implements StudentService{

	
	 private static final String FILE_NAME = ".\\text1.txt";
	    private Scanner scan;
	    
	    private List<Student> enrolledStudents;

	    public StuentServiceImpl() {
	        scan = new Scanner(System.in);
	        enrolledStudents = new ArrayList<>();
	    }

	    @SuppressWarnings("unused")
	    @Override
	    public void insertStudentDetails() {
	        String sID, name, grade, email, contact;

	        System.out.println("\n------- Register New Student To System --------");
	        System.out.print("Enter Student's ID: ");
	        sID = scan.nextLine();
	        System.out.print("Enter Student's name: ");
	        name = scan.nextLine();
	        System.out.print("Enter Student's grade: ");
	        grade = scan.nextLine();
	        System.out.print("Enter Student's email: ");
	        email = scan.nextLine();
	        System.out.print("Enter Student's contact number: ");
	        contact = scan.nextLine();

	        
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
	            writer.write(sID + "," + name + "," + grade + "," + email + "," + contact + "\n");
	            
	            Student newStudent = new Student(sID, name, grade, email, contact);
	            enrolledStudents.add(newStudent);
	            
//	            System.out.println(enrolledStudents);

	            System.out.println("Student with ID " + sID + " added Successfully.");
	        } catch (IOException e) {
	            System.err.println("Error adding new Student to system: " + e.getMessage());
	        }
	    }
	    
	    
	    
	    
	    @Override
	    public List<Student> getStudentAll() {
	        List<Student> studentList = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(new File(FILE_NAME)))) {
	            String line;
	            // Print table headers
	            System.out.printf("%-15s %-20s %-10s %-30s %-15s%n", "Student ID", "Name", "Grade", "Email", "Contact");
	            System.out.println("-------------------------------------------------------------------------------------");
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                String sID = parts[0];
	                String name = parts[1];
	                String grade = parts[2];
	                String email = parts[3];
	                String contact = parts[4];
	                Student student = new Student(sID, name, grade, email, contact);
	                studentList.add(student);
	                // Print student data in tabular format
	                System.out.printf("%-15s %-20s %-10s %-30s %-15s%n", sID, name, grade, email, contact);
	            }
	        } catch (IOException e) {
	            System.err.println("Error reading Student data from file: " + e.getMessage());
	        }
	        return studentList;
	    }
	    
	    
	    
	    
	    @Override
	    public void deleteStudent(String studentID) {
	        try {
	            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false));

	            for (Student student : enrolledStudents) {
	                if (!student.getsID().equals(studentID)) {
	                    writer.write(student.getsID() + "," + student.getName() + "," + student.getGrade() + "," + student.getEmail() + "," + student.getContact() + "\n");
	                }
	            }

	            writer.close();
	            System.out.println("\nStudent Deleted Successfully.\n");

	        } catch (IOException e) {
	            System.out.println("Writing error: " + e.getMessage());
	        }
	    }

	    
	    
	    
	    
	    @Override
	    public void enrollForSubjects(String studentID) {
	        Student student = findStudentById(studentID); // Find the student
	        if (student != null) {
	            // **Ensure student is not already added to enrolledStudents list**
	            if (!enrolledStudents.contains(student)) {
	                enrolledStudents.add(student); // Add student to enrolledStudents list
	            }
	            
	            Scanner scanner = new Scanner(System.in);
	            System.out.println("Enter subjects to enroll (comma-separated): ");
	            String subjectsInput = scanner.nextLine();
	            List<String> subjects = new ArrayList<>();
	            for (String subject : subjectsInput.split(",")) {
	                subjects.add(subject.trim());
	            }
	            student.setEnrolledSubjects(subjects); // Set enrolled subjects for the student
	            System.out.println("Student with ID " + studentID + " enrolled for subjects: " + subjects);
	        } else {
	            System.out.println("Student with ID " + studentID + " not found.");
	        }
	    }

//	    public void enrollForSubjects(String studentID) {
//	        Student student = findStudentById(studentID);
//	        if (student != null) {
//	            // If the student is found
//	            Scanner scanner = new Scanner(System.in);
//	            System.out.println("Enter subjects to enroll (comma-separated): ");
//	            String subjectsInput = scanner.nextLine();
//	            List<String> subjects = new ArrayList<>();
//	            for (String subject : subjectsInput.split(",")) {
//	                subjects.add(subject.trim());
//	            }
//	            student.setEnrolledSubjects(subjects);
//	            enrolledStudents.add(student); // Add the student to the list of enrolled students
//	            System.out.println("Student with ID " + studentID + " enrolled for subjects: " + subjects);
//	        } else {
//	            System.out.println("Student with ID " + studentID + " not found.");
//	        }
//	    }
	    
	    
	    @Override
	    public Student searchStudentById(String studentID) {
	        Student student = findStudentById(studentID);
	        if (student != null) {
	            // Print student details
	            System.out.println("Student Details:");
	            System.out.println("ID: " + student.getsID());
	            System.out.println("Name: " + student.getName());
	            System.out.println("Grade: " + student.getGrade());
	            System.out.println("Email: " + student.getEmail());
	            System.out.println("Contact: " + student.getContact());
	        } else {
	            System.out.println("Student with ID " + studentID + " not found.");
	        }
	        return student;
	    }
	


	    
	 
	    
//	    private Student findStudentById(String studentID) {
//	        // Iterate through the list of enrolled students
//	        for (Student student : enrolledStudents) {
//	            // Check if the student ID matches the provided studentID
//	            if (student.getsID().equals(studentID)) {
//	                // Return the student if found
//	                return student;
//	            }
//	        }
//	        // Return null if no student with the given ID is found
//	        return null;
//	    }
	    
	    
	    
	    private Student findStudentById(String studentID) {
	        // Iterate through the list of enrolled students
	        for (Student student : enrolledStudents) {
	            // Check if the student ID matches the provided studentID
	            if (student.getsID().equals(studentID)) {
	                // Return the student if found
	                return student;
	            }
	        }
	        
	        // If the student is not found in the enrolledStudents list, 
	        // check the file for the student ID
	        try (BufferedReader reader = new BufferedReader(new FileReader(new File(FILE_NAME)))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                String sID = parts[0];
	                if (sID.equals(studentID)) {
	                    // If the student ID matches, create a new student object and return it
	                    String name = parts[1];
	                    String grade = parts[2];
	                    String email = parts[3];
	                    String contact = parts[4];
	                    return new Student(sID, name, grade, email, contact);
	                }
	            }
	        } catch (IOException e) {
	            System.err.println("Error reading Student data from file: " + e.getMessage());
	        }
	        
	        // Return null if no student with the given ID is found
	        return null;
	    }
	    
	    
	    
	    
	    
	    
	    @Override
	    public void updateStudentDetails(String studentID) {
	        Student studentToUpdate = findStudentById(studentID);
	        if (studentToUpdate != null) {
	            Scanner scanner = new Scanner(System.in);
	            System.out.println("Enter updated details for student with ID " + studentID + ":");
	            System.out.print("Name: ");
	            String name = scanner.nextLine();
	            System.out.print("Grade: ");
	            String grade = scanner.nextLine();
	            System.out.print("Email: ");
	            String email = scanner.nextLine();
	            System.out.print("Contact: ");
	            String contact = scanner.nextLine();

	            // Update the student details
	            studentToUpdate.setName(name);
	            studentToUpdate.setGrade(grade);
	            studentToUpdate.setEmail(email);
	            studentToUpdate.setContact(contact);

	            System.out.println("Student details updated successfully.");
	        } else {
	            System.out.println("Student with ID " + studentID + " not found.");
	        }
	    }
	    
	    
	    @Override
	    public List<String> determineSubjectsForGrade(String grade) {
	        List<String> subjects = new ArrayList<>();
	        // Logic to determine subjects based on the grade
	        switch (grade.toUpperCase()) {
	            case "GRADE9":
	                subjects.add("Mathematics");
	                subjects.add("Science");
	                subjects.add("English");
	                break;
	            case "GRADE10":
	                subjects.add("Physics");
	                subjects.add("Chemistry");
	                subjects.add("Biology");
	                break;
	            // Add cases for other grades as needed
	            default:
	                break;
	        }

	        // Display the subjects as a table
	        System.out.println("Subjects for Grade " + grade + ":");
	        System.out.println("---------------------------------");
	        System.out.println("| Subject        |");
	        System.out.println("---------------------------------");
	        for (String subject : subjects) {
	            System.out.printf("| %-15s |%n", subject);
	        }
	        System.out.println("---------------------------------");

	        return subjects;
	    }




	  @Override
	   public void displayStudent() {
		// TODO Auto-generated method stub
		
	}

	
	

}
