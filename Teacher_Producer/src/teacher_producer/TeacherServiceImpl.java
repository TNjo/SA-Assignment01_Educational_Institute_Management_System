package teacher_producer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class TeacherServiceImpl implements TeacherService {
	private static final String FILE_NAME = ".\\teacher_info.txt";
    private Scanner scan;
    private String ANSI_BOLD = "\u001B[1m";
    private String ANSI_RESET = "\u001B[0m";
    private String ANSI_YELLOW = "\u001B[33m";
    private String ANSI_BLUE = "\u001B[34m";
    private String ANSI_GREEN = "\u001B[32m";
    private String ANSI_RED = "\u001B[31m";
    
    private List<Teacher> registeredTeachers;

    public TeacherServiceImpl() {
    	scan = new Scanner(System.in);
    	registeredTeachers = new ArrayList<>();
    }

    @Override
    public void createTeacher() {
        String teacherID, name, email, contact;

        System.out.println("\n \u001B[33m \u001B[1mRegister New Teacher To System\u001B[0m");

        System.out.print("\n \u001B[1m Teacher's ID: \u001B[0m");
        teacherID = scan.nextLine();

        System.out.print(" \u001B[1m Teacher's Name: \u001B[0m");
        name = scan.nextLine();

        System.out.print(" \u001B[1m Teacher's Email: \u001B[0m");
        email = scan.nextLine();

        System.out.print(" \u001B[1m Teacher's Contact Number: \u001B[0m");
        contact = scan.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(teacherID + "," + name + "," + email + "," + contact + "\n");

            System.out.println("\n \u001B[32m Teacher with ID " + teacherID + " registered successfully.\u001B[0m");
        } catch (IOException e) {
            System.err.println("\n \u001B[31m Error registering new Teacher to system: " + e.getMessage() + "\u001B[0m");
        }
    }

    
    
    @Override
    public void displayTeachers() {
        // Define the content width and the title
        int contentWidth = 80; // Adjust as needed
        String title = "  Teachers' Details";

        // Calculate the padding for center alignment
        int titlePadding = (contentWidth - title.length()) / 2;
        
        System.out.println();
        System.out.print(ANSI_BOLD + ANSI_YELLOW);
        for (int i = 0; i < titlePadding; i++) {
            System.out.print(" ");
        }
        System.out.print(title);
        System.out.println(ANSI_RESET);

        String lineSeparator = "  +------------+----------------------+---------------------------+-----------------+%n";
        String widthFormat = "  | %-10s | %-20s | %-25s | %-15s |%n";

        System.out.printf(lineSeparator);
        System.out.printf(widthFormat, "TeacherID", "Name", "Email", "Contact No");
        System.out.printf(lineSeparator);

        List<Teacher> teacherList = getAllTeachers();

        for (Teacher teacher : teacherList) {
            String formattedString = String.format(widthFormat, teacher.getTeacherID(), teacher.getName(), teacher.getEmail(), teacher.getContact());
            System.out.printf(formattedString);
        }

        System.out.printf(lineSeparator);
    }



    @Override
    public void editTeacher() {
        String teacherID, newName, newContact, newEmail;
        
        System.out.println("\n \u001B[33m \u001B[1mEdit Teacher Profile\u001B[0m \n");
       
        System.out.print(ANSI_BOLD +"  Enter Teacher's ID: " + ANSI_RESET);
        teacherID = scan.nextLine();

        Teacher teacherToEdit = findTeacherById(teacherID);
        if (teacherToEdit != null) {
            // Display existing details
            System.out.println("\n  Current Details:");
            System.out.println(ANSI_BOLD + "  1. Name" + ANSI_RESET + "  - " + teacherToEdit.getName());
            System.out.println(ANSI_BOLD + "  2. Email" + ANSI_RESET + " - " + teacherToEdit.getEmail());
            System.out.println(ANSI_BOLD + "  3. Contact" + ANSI_RESET + " - " + teacherToEdit.getContact());

            // Prompt user for changes
            System.out.print("\n" + ANSI_BLUE + "  Enter the indexes of the properties you want to edit (comma-separated): " + ANSI_RESET);
            String indexesInput = scan.nextLine();
            String[] indexes = indexesInput.split(",");

            // Edit the chosen properties
            for (String index : indexes) {
                int propertyIndex = Integer.parseInt(index.trim());
                switch (propertyIndex) {
                    case 1:
                        System.out.print(ANSI_BOLD + "  Enter new name" + ANSI_RESET + ": ");
                        newName = scan.nextLine();
                        teacherToEdit.setName(newName);
                        break;
                    case 2:
                        System.out.print(ANSI_BOLD + "  Enter new email" + ANSI_RESET + ": ");
                        newEmail = scan.nextLine();
                        teacherToEdit.setEmail(newEmail);
                        break;
                    case 3:
                        System.out.print(ANSI_BOLD + "  Enter new contact" + ANSI_RESET + ": ");
                        newContact = scan.nextLine();
                        teacherToEdit.setContact(newContact);
                        break;
                    default:
                        System.out.println("  Invalid index: " + propertyIndex);
                }
            }
            
            // Update the file with the edited details
            updateTeacherInFile(teacherToEdit);
            System.out.println();
            System.out.println(ANSI_GREEN +"  Teacher details updated for ID " + teacherID + ":" + ANSI_RESET);
            System.out.println("  Name: " + teacherToEdit.getName());
            System.out.println("  Email: " + teacherToEdit.getEmail());
            System.out.println("  Contact: " + teacherToEdit.getContact());
        } else {
        	 System.err.println("\n \u001B[31m Teacher not found with ID: " + teacherID + "\u001B[0m");
        }
    }

   
    
    @Override
    public void deleteTeacher(String teacherID) {
        try {
            List<Teacher> teacherListToDelete = getAllTeachers();

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false));

            for (Teacher teacher : teacherListToDelete) {
                if (!teacher.getTeacherID().equals(teacherID)) {
                    writer.write(teacher.getTeacherID() + "," + teacher.getName() + "," + teacher.getEmail() + "," + teacher.getContact() + "\n");
                }
            }

            writer.close();
            System.out.println("\n \u001B[32m Teacher with ID " + teacherID + " Deleted Successfully.\u001B[0m");
        } catch (IOException e) {
        	 System.err.println("\n \u001B[31m Error deleting Teacher: " + e.getMessage() + "\u001B[0m");
        }
    }
    
    
    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teacherList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(FILE_NAME)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String teacherID = parts[0];
                String name = parts[1];
                String email = parts[2];
                String contact = parts[3];
                Teacher teacher = new Teacher(teacherID, name, email, contact);
                teacherList.add(teacher);
            }
        } catch (IOException e) {
            System.err.println("Error reading teacher data from file: " + e.getMessage());
        }
        return teacherList;
    }
    
    @Override
    public void assignCourses(String teacherID) {
        Teacher teacher = findTeacherById(teacherID);
        if (teacher != null) {
                System.out.print("\n" + ANSI_BLUE + "  Enter subjects to assign (comma-separated): " + ANSI_RESET);
                String subjectsInput = scan.nextLine();
                List<String> subjects = Arrays.asList(subjectsInput.split("\\s*,\\s*"));

                // Update the assigned subjects for the teacher
                List<String> currentSubjects = teacher.getAssignedCourses();
                currentSubjects.addAll(subjects);
                teacher.setAssignedCourses(currentSubjects);

                // Update the file with the assigned subjects
                updateTeacherInFile(teacher);

                System.out.println(ANSI_GREEN + "  Subjects assigned successfully." + ANSI_RESET);
            
        } else {
            System.err.println("\n" + ANSI_RED + "  Teacher not found with ID: " + teacherID + ANSI_RESET);
        }
    }

   
    private Teacher findTeacherById(String teacherID) {
        List<Teacher> teacherList = getAllTeachers();
        for (Teacher teacher : teacherList) {
            if (teacher.getTeacherID().equals(teacherID)) {
                return teacher;
            }
        }
        return null;
    }
    
    private void updateTeacherInFile(Teacher updatedTeacher) {
        List<Teacher> teacherList = getAllTeachers();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(FILE_NAME), false))) {
            for (Teacher teacher : teacherList) {
                if (teacher.getTeacherID().equals(updatedTeacher.getTeacherID())) {
                    // Write the updated record with assigned subjects
                    writer.write(updatedTeacher.getTeacherID() + "," +
                            updatedTeacher.getName() + "," +
                            updatedTeacher.getEmail() + "," +
                            updatedTeacher.getContact() + "," +
                            String.join(",", updatedTeacher.getAssignedCourses()) + "\n");
                } else {
                    // Write the unchanged record
                    writer.write(teacher.getTeacherID() + "," +
                            teacher.getName() + "," +
                            teacher.getEmail() + "," +
                            teacher.getContact() + "," +
                            String.join(",", teacher.getAssignedCourses()) + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error updating teacher data in file: " + e.getMessage());
        }
    }

	@Override
	public void searchTeacher(String teacherID) {
        Teacher teacher = findTeacherById(teacherID);
        if (teacher != null) {
        	System.out.println("\n  Teacher Details:");
            System.out.println(ANSI_BOLD + "  Teacher ID" + ANSI_RESET + "    - " + teacher.getTeacherID());
            System.out.println(ANSI_BOLD + "  Name" + ANSI_RESET + "          - " + teacher.getName());
            System.out.println(ANSI_BOLD + "  Email" + ANSI_RESET + "         - " + teacher.getEmail());
            System.out.println(ANSI_BOLD + "  Contact" + ANSI_RESET + "       - " + teacher.getContact());

            List<String> assignedSubjects = teacher.getAssignedCourses();
            if (!assignedSubjects.isEmpty()) {
                System.out.println(ANSI_BOLD + "  Assigned Subjects" + ANSI_RESET + " - " + String.join(", ", assignedSubjects));
            }
        } else {
            System.err.println("\n \u001B[31m Teacher not found with ID: " + teacherID + "\u001B[0m");
        }
    }
    

}
