package service;

import model.Student;
import model.Course;
import java.util.*;

public class EnrollmentService {
    private StudentService studentService;
    private CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }


    /**
     * @param studentId student to enroll
     * @param courseId course to enroll in
     * @return return true if the student was enrolled successfully, false otherwise
     */
    public boolean enrollStudent(String studentId, String courseId) {
        Student student = studentService.getStudent(studentId);
        Course course = courseService.getCourse(courseId);

        if (student == null || course == null) {
            return false;
        }

        if (student.isEnrolled(courseId)) {
            return false; // Already enrolled
        }

        student.enrollCourse(courseId);
        course.enrollStudent(studentId);

        studentService.saveStudents();
        courseService.saveCourses();

        return true;

    }

    /**
     * @param studentId student to withdraw
     * @param courseId course to withdraw from
     * @return return true if the student was withdrawn successfully, false otherwise
     */
    public boolean withdrawStudent(String studentId, String courseId) {
        Student student = studentService.getStudent(studentId);
        Course course = courseService.getCourse(courseId);

        if (student == null || course == null) {
            return false;
        }

        if (!student.isEnrolled(courseId)) {
            return false; // Not enrolled
        }

        student.withdrawCourse(courseId);
        course.withdrawStudent(studentId);

        studentService.saveStudents();
        courseService.saveCourses();

        return true;
    }

    /**
     * @param studentId student to get courses from
     * @return list containing all courses a student is enrolled in
     */
    public List<Course> getStudentCourses(String studentId) {
        Student student = studentService.getStudent(studentId);
        if (student == null) {
            return new ArrayList<>();
        }

        List<Course> enrolledCourses = new ArrayList<>();
        for (String courseId : student.getEnrolledCourses()) {
            Course course = courseService.getCourse(courseId);
            if (course != null) {
                enrolledCourses.add(course);
            }
        }
        return enrolledCourses;
    }

    /**
     * @param courseId course to get students from
     * @return list containing all enrolled students
     */
    public List<Student> getCourseStudents(String courseId) {
        Course course = courseService.getCourse(courseId);
        if (course == null) {
            return new ArrayList<>();
        }

        List<Student> enrolledStudents = new ArrayList<>();
        for (String studentId : course.getEnrolledStudents()) {
            Student student = studentService.getStudent(studentId);
            if (student != null) {
                enrolledStudents.add(student);
            }
        }
        return enrolledStudents;
    }

}
