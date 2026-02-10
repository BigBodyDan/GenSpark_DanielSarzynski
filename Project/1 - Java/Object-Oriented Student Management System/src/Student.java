import java.util.*;

public class Student {
    private String studentId;
    private String name;
    private String dateOfBirth;
    private Set<String> enrolledCourses;

    // Constructor for Student class
    public Student(String studentId, String name, String dateOfBirth) {
        this.studentId = studentId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.enrolledCourses = new HashSet<>();
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<String> getEnrolledCourses() {
        return enrolledCourses;
    }

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

    /**
     * @return Formatted student information
     */
    public String toString() {
        return String.format("Student ID: %s | Name: %s | DOB: %s | Courses: %s",
                studentId, name, dateOfBirth, enrolledCourses);
    }

    /**
     * Creates a Student object from a file string.
     * @param string String read from file
     * @return Student object
     */
    public static Student fromFileString(String string) {
        String[] parts = string.split("\\|");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid file format for Student");
        }

        Student student = new Student(parts[0], parts[1], parts[2]);

        // Parse enrolled course IDs
        String courseIdString = parts[3];
        if (!courseIdString.equals("none")) {
            String[] courseIds = courseIdString.split(",");
            for (String courseId : courseIds) {
                if (!courseId.trim().isEmpty()) {
                    student.enrollCourse(courseId);
                }
            }
        }

        return student;
    }


    /**
     * @return string representation for file storage
     */
    public String toFileString(){
        StringBuilder sb = new StringBuilder();
        sb.append(studentId).append("|");
        sb.append(name).append("|");
        sb.append(dateOfBirth).append("|");

        // Append enrolled course IDs separated by commas
        if (enrolledCourses.isEmpty()) {
            sb.append("none");
        } else {
            for (String course : enrolledCourses) {
                sb.append(course);
                sb.append(",");
            }
            // Delete trailing comma
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
