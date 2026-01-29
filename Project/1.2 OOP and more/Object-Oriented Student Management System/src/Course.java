import java.util.*;

public class Course {
    private String courseId;
    private String title;
    private Set<String> enrolledStudents;

    // Constructor for Course class
    public Course(String courseId, String title) {
        this.courseId = courseId;
        this.title = title;
        this.enrolledStudents = new HashSet<>();
    }

    // Getters and Setters
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    /**
     * Enrolls a student in this course.
     * @param studentId ID of student to enroll
     */
    public void enrollStudent(String studentId) {
        this.enrolledStudents.add(studentId);
    }

    /**
     * Withdraws a student from this course.
     * @param studentId ID of student to withdraw
     */
    public void withdrawStudent(String studentId) {
        this.enrolledStudents.remove(studentId);
    }

    /**
     * Checks if a student is enrolled in this course.
     * @param studentId ID of student to check
     * @return true if enrolled, false otherwise
     */
    public boolean hasStudent(String studentId) {
        return this.enrolledStudents.contains(studentId);
    }

    /**
     * @return Formatted course information
     */
    public String toString() {
        return String.format("Course ID: %s | Title: %s | Students Enrolled: %s",
                courseId, title, enrolledStudents);
    }

    /**
     * Creates a Course object from a file string.
     * @param string String read from file
     * @return Course object
     */
    public static Course fromFileString(String string) {
        String[] parts = string.split("\\|");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid file format for Course");
        }

        Course course = new Course(parts[0], parts[1]);

        // Parse enrolled student IDs
        String studentIdsString = parts[2];
        if (!studentIdsString.equals("none")) {
            String[] studentIds = studentIdsString.split(",");
            for (String studentId : studentIds) {
                if (!studentId.trim().isEmpty()) {
                    course.enrollStudent(studentId);
                }
            }
        }

        return course;
    }

    /**
     * @return string representation for file storage
     */
    public String toFileString(){
        StringBuilder sb = new StringBuilder();
        sb.append(courseId).append("|");
        sb.append(title).append("|");

        // Append enrolled student IDs separated by commas
        if (enrolledStudents.isEmpty()) {
            sb.append("none");
        } else {
            for (String student : enrolledStudents) {
                sb.append(student);
                sb.append(",");
            }
            // Delete trailing comma
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
