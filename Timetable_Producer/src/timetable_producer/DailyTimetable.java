package timetable_producer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DailyTimetable implements TimetableService {

    private static final String[] WEEKDAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private static final String[] SUBJECTS = {"Science", "Maths", "English", "History", "Geography"};
    private static final int[][][] TIMETABLES = {
            // Grade 6 timetable
            {{9, 11}, {11, 13}, {14, 16}, {16, 18}, {18, 20}},
            // Grade 7 timetable
            {{8, 10}, {10, 12}, {13, 15}, {15, 17}, {17, 19}},
            // Grade 8 timetable
            {{8, 10}, {10, 12}, {13, 15}, {15, 17}, {17, 19}},
            // Grade 9 timetable
            {{8, 10}, {10, 12}, {13, 15}, {15, 17}, {17, 19}},
            // Grade 10 timetable
            {{9, 11}, {11, 13}, {14, 16}, {16, 18}, {18, 20}},
            // Grade 11 timetable
            {{9, 11}, {11, 13}, {14, 16}, {16, 18}, {18, 20}}
    };

    private Map<Integer, Map<String, String>> teacherAssignments; // Mapping grade to subject and teacher

    public DailyTimetable() {
        teacherAssignments = new HashMap<>();
    }

    @Override
    public void displayTimetable(int grade) {
        if (grade < 6 || grade > 11) {
            System.out.println("Invalid grade. Grade should be between 6 and 11.");
            return;
        }

        System.out.println("Grade " + grade + " Time Table");
        System.out.println("+----------------------+---------------------+-----------------------+");
        System.out.println("|         Day          |         Time        |        Subject        |");
        System.out.println("+----------------------+---------------------+-----------------------+");

        for (int i = 0; i < WEEKDAYS.length; i++) {
            System.out.printf("| %-20s |     %02d:00 - %02d:00   | %-20s  |\n", WEEKDAYS[i], TIMETABLES[grade - 6][i][0], TIMETABLES[grade - 6][i][1], SUBJECTS[i]);
        }

        System.out.println("+----------------------+---------------------+-----------------------+");
    }
    
    @Override
    public void assignTeachers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the grade (6-11):");
        int grade = scanner.nextInt();
        scanner.nextLine(); 

        if (grade < 6 || grade > 11) {
            System.out.println("Invalid grade. Grade should be between 6 and 11.");
            return;
        }

        Map<String, String> gradeTeachers = new HashMap<>();
        for (String subject : SUBJECTS) {
            System.out.println("Enter the teacher for " + subject + ":");
            String teacher = scanner.nextLine();
            gradeTeachers.put(subject, teacher);
        }
        teacherAssignments.put(grade, gradeTeachers); 

        System.out.println("Teachers assigned successfully for grade " + grade + ".");
    }
    
    @Override
    public void displayTeachers(int grade) {
       

        if (grade < 6 || grade > 11) {
            System.out.println("Invalid grade. Grade should be between 6 and 11.");
            return;
        }

        Map<String, String> gradeTeachers = teacherAssignments.get(grade);
        if (gradeTeachers == null) {
            System.out.println("No teachers assigned for grade " + grade + ".");
            return;
        }

        System.out.println("Teachers assigned for grade " + grade + ":");
        for (Map.Entry<String, String> entry : gradeTeachers.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    @Override
    public void updateTeacher() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the grade (6-11):");
        int grade = scanner.nextInt();

        if (grade < 6 || grade > 11) {
            System.out.println("Invalid grade. Grade should be between 6 and 11.");
            return;
        }

        Map<String, String> gradeTeachers = teacherAssignments.get(grade);
        if (gradeTeachers == null || gradeTeachers.isEmpty()) {
            System.out.println("No teachers assigned for grade " + grade + ".");
            return;
        }

        System.out.println("Enter the subject to update the teacher:");
        String subject = scanner.next();

        if (!gradeTeachers.containsKey(subject)) {
            System.out.println("No teacher assigned for the subject " + subject + ".");
            return;
        }

        System.out.println("Enter the new teacher for " + subject + ":");
        String newTeacher = scanner.next();

        
        gradeTeachers.put(subject, newTeacher);

        System.out.println("Teacher updated successfully for subject " + subject + " in grade " + grade + ".");
    }
}
