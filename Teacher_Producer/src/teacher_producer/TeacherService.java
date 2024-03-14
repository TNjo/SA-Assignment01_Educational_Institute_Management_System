package teacher_producer;

import java.util.List;


public interface TeacherService {
    public void displayTeachers();

    void createTeacher();

    void editTeacher();

    void assignCourses(String teacherID);

    void deleteTeacher(String teacherID);
    
    void searchTeacher(String teacherID);
    
    List<Teacher> getAllTeachers();
}
