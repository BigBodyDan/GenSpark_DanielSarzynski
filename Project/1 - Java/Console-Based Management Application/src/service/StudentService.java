package service;

import model.Student;
import dao.FileManager;
import java.util.*;

public class StudentService {
    private HashMap<String, Student> students;

    public StudentService() {
        this.students = new HashMap<>();
        loadStudents();
    }

    private void loadStudents() { students = FileManager.loadStudents(); }
    public void saveStudents() {  FileManager.saveStudents(students); }

    public Student getStudent(String studentId) {return students.get(studentId); }
    public int getStudentCount() { return students.size(); }

    /**
     * @param student student to add
     * @return return true if student added successfully, false otherwise
     */
    public boolean addStudent(Student student) {
        if (students.containsKey(student.getId())) {
            return false; // Student already exists
        }
        students.put(student.getId(), student);
        saveStudents();
        return true;
    }

    /**
     * @param studentId ID of student to update
     * @return return true if student updated successfully, false otherwise
     */
    public boolean updateStudent(String studentId, Student updatedStudent) {
        if (!students.containsKey(studentId)) {
            return false;
        }
        students.put(studentId, updatedStudent);
        saveStudents();
        return true;
    }

    /**
     * @param studentId ID of student to delete
     * @return return true if student deleted successfully, false otherwise
     */
    public boolean deleteStudent(String studentId) {
        if (!students.containsKey(studentId)) {
            return false;
        }
        students.remove(studentId);
        saveStudents();
        return true;
    }

    public List<Student> getAllStudents() { return new ArrayList<>(students.values()); }

}
