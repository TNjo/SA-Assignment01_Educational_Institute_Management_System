package teacher_consumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import teacher_producer.TeacherService;

public class Teacher_Consumer_Activator implements BundleActivator {
	 
	ServiceReference serviceReference ;
	private Scanner scan;
	private String ANSI_BOLD = "\u001B[1m";
    private String ANSI_RESET = "\u001B[0m";
    private String ANSI_YELLOW = "\u001B[33m";
    private String ANSI_BLUE = "\u001B[34m";
    private String ANSI_GREEN = "\u001B[32m";
    String ANSI_PURPLE_BOLD = "\u001B[1;31m";
    
	public void start(BundleContext context) throws Exception {
	    System.out.println("Teacher Subscriber Start");
	    serviceReference = context.getServiceReference(TeacherService.class.getName());

	    @SuppressWarnings("unchecked")
	    TeacherService teacherService = (TeacherService) context.getService(serviceReference);
	    scan = new Scanner(System.in);
	    
        String header = "STAFF MANAGEMENT";
        int contentWidth = 50;  // Assuming the content width is 80 characters
        int padding = (contentWidth - header.length()) / 2;
        String styledHeader = ANSI_PURPLE_BOLD + " ".repeat(padding) + header + " ".repeat(padding) + ANSI_RESET;
        System.out.println("\n" + styledHeader);
        
        
	    while (true) {
	    	System.out.println("\n \u001B[34m\u001B[1m+----------------------------------------------+\u001B[0m");
	    	System.out.println(" \u001B[34m\u001B[1m|\u001B[0m   \u001B[35m\u001B[1mChoose an option from Teacher Management\u001B[0m   \u001B[34m\u001B[1m|\u001B[0m");
	        System.out.println(" \u001B[34m\u001B[1m+----------------------------------------------+\u001B[0m");
	        System.out.println(" \u001B[34m\u001B[1m|   1. Insert a New Teacher                    |\u001B[0m");
	        System.out.println(" \u001B[34m\u001B[1m|   2. Display Teachers List                   |\u001B[0m");
	        System.out.println(" \u001B[34m\u001B[1m|   3. Update Teacher Details                  |\u001B[0m");
	        System.out.println(" \u001B[34m\u001B[1m|   4. Delete Teacher                          |\u001B[0m");
	        System.out.println(" \u001B[34m\u001B[1m|   5. Assign Subjects for Teacher             |\u001B[0m");
	        System.out.println(" \u001B[34m\u001B[1m|   6. Search Teacher                          |\u001B[0m");
	        System.out.println(" \u001B[34m\u001B[1m|   7. Quit                                    |\u001B[0m");
	        System.out.println(" \u001B[34m\u001B[1m+----------------------------------------------+\u001B[0m");

	        System.out.print("\n \u001B[1m Enter your choice: \u001B[0m");
	        int choice = scan.nextInt();
	        scan.nextLine(); // Consume newline character
	        System.out.println("  ------------------------------------------------------------------------------------------------------------------------------------");
	        
	        switch (choice) {
	            case 1:
	                teacherService.createTeacher();
	                break;
	            case 2:
	                teacherService.displayTeachers();
	                break;
	            case 3:
	            	teacherService.editTeacher();
	                break;
	            case 4:
	            	System.out.println("\n \u001B[33m\u001B[1m Delete Teacher\u001B[0m ");
	            	System.out.print("\n \u001B[34m Enter the ID of the teacher you want to delete: \u001B[0m");
	                String teacherID = scan.nextLine();
	                teacherService.deleteTeacher(teacherID);
	                break;    
	            case 5:
	            	System.out.println("\n \u001B[33m\u001B[1m Assign Subject to the Teacher\u001B[0m ");
	            	System.out.print("\n \u001B[1m Enter the ID of the teacher: \u001B[0m");
	                String tID = scan.nextLine();
	                teacherService.assignCourses(tID);
	                break;
	            case 6:
	            	System.out.println("\n \u001B[33m\u001B[1m Search Teacher\u001B[0m ");
	            	System.out.print("\n \u001B[34m Enter the ID of the teacher you want to search: \u001B[0m");
	                String tchID = scan.nextLine();
	            	teacherService.searchTeacher(tchID);
	            	break;
	            case 7:
	            	System.out.println("\n \u001B[34m Exiting...\u001B[0m");
	                return;
	            default:
	            	System.out.println("\n \u001B[31m Invalid choice! Please try again.\u001B[0m");
	        }
	    }
	
	}



 
	public void stop(BundleContext context) throws Exception {
		System.out.println("Student Subscriber Stop");
		context.ungetService(serviceReference);
	}
 
}