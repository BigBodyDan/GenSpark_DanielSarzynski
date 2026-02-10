package model;

import java.util.HashSet;
import java.util.Set;

public class Student extends Person {
    private Set<String> enrolledCourses;
    private String major;
    private String email;

    // Constructor for Student class
    public Student(String studentId, String name, String dateOfBirth, String department, String email) {
        super(studentId, name, dateOfBirth);
        this.major = department;
        this.email = email;
        this.enrolledCourses = new HashSet<>();
    }

    // Getters and Setters
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<String> getEnrolledCourses() {
        return enrolledCourses;
    }
    public void setEnrolledCourses(Set<String> enrolledCourses) { this.enrolledCourses = enrolledCourses; }

    /**
     * Enrolls student in a course.
     * @param courseId ID of course to enroll in
     */
    public void enrollCourse(String courseId) {
        this.enrolledCourses.add(courseId);
    }

    /**
     * Withdraws student from a course.
     * @param courseId ID of course to withdraw from
     */
    public void withdrawCourse(String courseId) {
        this.enrolledCourses.remove(courseId);
    }

    /**
     * Checks if student is enrolled in a specific course.
     * @param courseId ID of course to check
     * @return true if enrolled, false otherwise
     */
    public boolean isEnrolled(String courseId) {
        return this.enrolledCourses.contains(courseId);
    }

    public String getRole() {
        return "student";
    }

    /**
     * @return Formatted student information
     */
    public String toString() {
        return super.toString() + String.format(" | Major: %s | Email: %s | Courses: %s",
                major, email, enrolledCourses);
    }

    /**
     * @return string representation for file storage
     */
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("|");
        sb.append(name).append("|");
        sb.append(dateOfBirth).append("|");
        sb.append(major).append("|");
        sb.append(email).append("|");

        if (enrolledCourses.isEmpty()) {
            sb.append("none");
        } else {
            sb.append(String.join(",", enrolledCourses));
        }
        return sb.toString();
    }

    /**
     * Creates a Student object from a file string.
     * @param fileString String read from file
     * @return Student object
     */
    public static Student fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        if (parts.length < 6) {
            throw new IllegalArgumentException("Invalid file format for Student");
        }

        Student student = new Student(parts[0], parts[1], parts[2], parts[3], parts[4]);

        String courseIdsStr = parts[5];
        if (!courseIdsStr.equals("none")) {
            String[] courseIds = courseIdsStr.split(",");
            for (String courseId : courseIds) {
                if (!courseId.trim().isEmpty()) {
                    student.enrollCourse(courseId.trim());
                }
            }
        }

        return student;
    }

}
