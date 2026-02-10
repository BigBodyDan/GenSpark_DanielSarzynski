import java.io.*;
import java.util.*;

public class FileManager {
    private static final String STUDENT_FILENAME = "students.txt";
    private static final String COURSE_FILENAME = "courses.txt";

    public static HashMap<String, Course> loadCourses() {
        HashMap<String, Course> courses = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(COURSE_FILENAME))){
            String line;
            while((line = reader.readLine()) != null){
                line = line.trim();
                if(line.isEmpty()) continue;
                Course course = Course.fromFileString(line);
                courses.put(course.getCourseId(), course);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing course data file found.");
        } catch (IOException e) {
            System.out.println("Error reading from course file: " + e.getMessage());
        }

        return courses;
    }

    public static HashMap<String, Student> loadStudents(){
        HashMap<String, Student> students = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILENAME))) {
            String line;
            while((line = reader.readLine()) != null){
                line = line.trim();
                if(line.isEmpty()) continue;
                Student student = Student.fromFileString(line);
                students.put(student.getStudentId(), student);
            }

        } catch (FileNotFoundException e) {
            System.out.println("No existing student data file found.");
        } catch (IOException e) {
            System.out.println("Error reading from students file: " + e.getMessage());
        }

        return students;
    }

    public static void saveCourses(HashMap<String, Course> courses){
        try (PrintWriter writer = new PrintWriter(new FileWriter(COURSE_FILENAME))) {
            for (Course course : courses.values()) {
                writer.println(course.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving courses file: " + e.getMessage());
        }
    }

    public static void saveStudents(HashMap<String, Student> students){
        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENT_FILENAME))) {
            for (Student student : students.values()) {
                writer.println(student.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving student file: " + e.getMessage());
        }
    }

}
