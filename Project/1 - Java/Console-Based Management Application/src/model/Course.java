package model;

import java.util.HashSet;
import java.util.Set;

public class Course {
    private String courseId;
    private String title;
    private int credits;
    private Set<String> enrolledStudents;

    public Course(String courseId, String title, int credits) {
        this.courseId = courseId;
        this.title = title;
        this.credits = credits;
        this.enrolledStudents = new HashSet<>();
    }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public Set<String> getEnrolledStudents() { return enrolledStudents; }

    /**
     * Enrolls a student in this course.
     * @param studentId ID of student to enroll
     */
    public void enrollStudent(String studentId) { this.enrolledStudents.add(studentId); }

    /**
     * Withdraws a student from this course.
     * @param studentId ID of student to withdraw
     */
    public void withdrawStudent(String studentId) { this.enrolledStudents.remove(studentId); }

    /**
     * Checks if a student is enrolled in this course.
     * @param studentId ID of student to check
     * @return true if enrolled, false otherwise
     */
    public boolean hasStudent(String studentId) { return this.enrolledStudents.contains(studentId); }

    /**
     * @return Formatted course information
     */
    public String toString() {
        return String.format("Course ID: %s | Title: %s | Students Enrolled: %s",
                courseId, title, enrolledStudents);
    }

    /**
     * @return string representation for file storage
     */
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(courseId).append("|");
        sb.append(title).append("|");
        sb.append(credits).append("|");

        if (enrolledStudents.isEmpty()) {
            sb.append("none");
        } else {
            sb.append(String.join(",", enrolledStudents));
        }
        return sb.toString();
    }

    /**
     * Creates a Course object from a file string.
     * @param fileString String read from file
     * @return Course object
     */
    public static Course fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid file format for Course");
        }

        Course course = new Course(parts[0], parts[1],
                Integer.parseInt(parts[2]));

        String studentIdsStr = parts[3];
        if (!studentIdsStr.equals("none")) {
            String[] studentIds = studentIdsStr.split(",");
            for (String studentId : studentIds) {
                if (!studentId.trim().isEmpty()) {
                    course.enrollStudent(studentId.trim());
                }
            }
        }

        return course;
    }
}
