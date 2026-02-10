import model.Student;
import model.Course;
import service.*;
import util.*;
import java.util.*;


public class Application {
    private Scanner scanner;
    private StudentService studentService;
    private CourseService courseService;
    private EnrollmentService enrollmentService;

    public  Application() {
        scanner = new Scanner(System.in);
        studentService = new StudentService();
        courseService = new CourseService();
        enrollmentService = new EnrollmentService(studentService, courseService);

        System.out.println("Students loaded: " + studentService.getStudentCount());
        System.out.println("Courses loaded: " + courseService.getCourseCount());
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }

    private void run(){
        boolean running = true;

        while(running){
            MenuUtil.displayMainMenu();
            int choice = Util.getInput(scanner);

            switch (choice) {
                case 1 -> studentMenu();
                case 2 -> courseMenu();
                case 3 -> enrollmentMenu();
                case 0 -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
        System.out.println("Exiting program.");
        scanner.close();
    }

    private void studentMenu(){
        boolean inStudentMenu = true;

        while (inStudentMenu) {
            MenuUtil.displayStudentMenu();
            int choice = Util.getInput(scanner);

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> deleteStudent();
                case 4 -> viewStudent();
                case 5 -> listStudents();
                case 0 -> inStudentMenu =  false;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void courseMenu(){
        boolean inCourseMenu = true;

        while (inCourseMenu) {
            MenuUtil.displayCourseMenu();
            int choice = Util.getInput(scanner);

            switch (choice) {
                case 1 -> addCourse();
                case 2 -> updateCourse();
                case 3 -> deleteCourse();
                case 4 -> viewCourse();
                case 5 -> listCourses();
                case 0 -> inCourseMenu =  false;
                default -> System.out.println("Invalid option. ");
            }
        }
    }

    private void enrollmentMenu(){
        boolean inEnrollmentMenu = true;

        while (inEnrollmentMenu) {
            MenuUtil.displayEnrollmentMenu();
            int choice = Util.getInput(scanner);

            switch (choice) {
                case 1 -> enrollStudent();
                case 2 -> withdrawStudent();
                case 3 -> viewStudentCourses();
                case 4 -> viewCourseStudents();
                case 0 -> inEnrollmentMenu =  false;
                default -> System.out.println("Invalid option. ");
            }
        }

    }

    // -------------------- STUDENT --------------------
    /**
     * Add new student: prompt for ID, name, date of birth, major and email
     */
    private void addStudent(){
        System.out.println("\n--- Add New Student ---");

        String studentId;
        while (true) {
            System.out.print("Enter Student ID: ");
            studentId = scanner.nextLine().trim();

            if (studentId.isEmpty()) {
                System.out.println("Student ID cannot be empty.");
            } else if (studentService.getStudent(studentId) != null) {
                System.out.println("Student ID already exists. Please use a different ID.");
            } else {
                break;
            }
        }

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            System.out.print("Enter Student Name: ");
            name = scanner.nextLine().trim();
        }

        String dateOfBirth;
        while (true) {
            System.out.print("Enter Date of Birth (MM/dd/yyyy): ");
            dateOfBirth = scanner.nextLine().trim();

            if (Util.isValidDate(dateOfBirth)) {
                break;
            } else {
                System.out.println("Invalid date. Please use MM/dd/yyyy");
            }
        }

        String major;
        System.out.print("Enter Major: ");
        major = scanner.nextLine().trim();

        String email;
        while (true) {
            System.out.print("Enter Email: ");
            email = scanner.nextLine().trim();

            if (Util.isValidEmail(email)) {
                break;
            } else {
                System.out.println("Invalid email.");
            }
        }


        Student student = new Student(studentId, name, dateOfBirth, major, email);
        if (studentService.addStudent(student)){
            System.out.println("Student added.");
        }
    }

    /**
     * Update student details by ID
     */
    private void updateStudent(){
        System.out.println("\n--- Update Student ---");
        System.out.print("Enter Student ID to update: ");
        String studentId = scanner.nextLine().trim();

        Student student = studentService.getStudent(studentId);
        if (student == null) {
            System.out.println("Student not found with ID: " + studentId);
            return;
        }

        System.out.println("Current details:");
        System.out.println(student);

        System.out.print("Enter new name (press Enter to keep current): ");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) {
            student.setName(newName);
        }

        String newDateOfBirth;
        while (true) {
            System.out.print("Enter new date of birth MM/dd/yyyy (press Enter to keep current): ");
            newDateOfBirth = scanner.nextLine().trim();

            if (newDateOfBirth.isEmpty()) {
                break;
            } else if (Util.isValidDate(newDateOfBirth)) {
                student.setDateOfBirth(newDateOfBirth);
                break;
            } else {
                System.out.println("Invalid date. Please use MM/dd/yyyy");
            }
        }

        System.out.print("Enter new major (press Enter to keep current): ");
        String newMajor = scanner.nextLine().trim();
        if (!newMajor.isEmpty()) {
            student.setMajor(newMajor);
        }

        String newEmail;
        while (true) {
            System.out.print("Enter new email (press Enter to keep current): ");
            newEmail = scanner.nextLine().trim();

            if (newDateOfBirth.isEmpty()) {
                break;
            } else if (Util.isValidEmail(newEmail)) {
                student.setEmail(newEmail);
                break;
            } else {
                System.out.println("Invalid email.");
            }

        }

        if (studentService.updateStudent(studentId, student)) {
            System.out.println("Student updated.");
        }
    }

    /**
     * Delete student by ID (also remove student from any course list)
     */
    private void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        System.out.print("Enter Student ID to delete: ");
        String studentId = scanner.nextLine().trim();

        Student student = studentService.getStudent(studentId);
        if (student == null) {
            System.out.println("Student not found with ID: " + studentId);
            return;
        }

        // Remove student from all courses
        for (String courseId : student.getEnrolledCourses()) {
            enrollmentService.withdrawStudent(studentId, courseId);
        }

        if (studentService.deleteStudent(studentId)) {
            System.out.println("Student deleted.");
        }
    }

    /**
     * View details of one student
     */
    private void viewStudent(){
        System.out.println("\n--- View Student ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        Student student = studentService.getStudent(studentId);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found with ID: " + studentId);
        }
    }

    /**
     * Display all students in system
     */
    private void listStudents(){
        System.out.println("\n--- All Students ---");
        List<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("Total Students: " + students.size());
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // -------------------- COURSE --------------------
    /**
     * Add new course: prompt for course ID, title and credits
     */
    private void addCourse(){
        System.out.println("\n--- Add New Course ---");

        String courseId;
        while (true) {
            System.out.print("Enter Course ID: ");
            courseId = scanner.nextLine().trim();

            if (courseId.isEmpty()) {
                System.out.println("Course ID cannot be empty.");
            } else if (courseService.getCourse(courseId) != null) {
                System.out.println("Course ID already exists. Please use a different ID.");
            } else {
                break;
            }
        }

        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine().trim();
        while (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            System.out.print("Enter Course Title: ");
            title = scanner.nextLine().trim();
        }

        int credits = 0;
        while (true){
            System.out.print("Enter Course Credits: ");
            credits = scanner.nextInt();

            if (credits > 0) {
                break;
            } else {
                System.out.println("Invalid course credits.");
            }
        }


        Course course = new Course(courseId, title, credits);

        if (courseService.addCourse(course)) {
            System.out.println("Course added.");
        }
    }

    /**
     * Update course title by ID
     */
    private void updateCourse(){
        System.out.println("\n--- Update Course ---");
        System.out.print("Enter Course ID to update: ");
        String courseId = scanner.nextLine().trim();

        Course course = courseService.getCourse(courseId);
        if (course == null) {
            System.out.println("Course not found with ID: " + courseId);
            return;
        }

        System.out.println("Current details:");
        System.out.println(course);

        System.out.print("Enter new title (press Enter to keep current): ");
        String newTitle = scanner.nextLine().trim();
        if (!newTitle.isEmpty()) {
            course.setTitle(newTitle);
        }

        System.out.print("Enter new credits (press Enter to keep current): ");
        String newCredits = scanner.nextLine().trim();
        if (!newCredits.isEmpty()) {
            course.setCredits(Integer.parseInt(newCredits));
        }
         if (courseService.updateCourse(courseId, course)) {
             System.out.println("Course updated.");
         }
    }

    /**
     * Delete a course by ID (also update student enrollments)
     */
    private void deleteCourse(){
        System.out.println("\n--- Delete Course ---");
        System.out.print("Enter Course ID to delete: ");
        String courseId = scanner.nextLine().trim();

        Course course = courseService.getCourse(courseId);
        if (course == null) {
            System.out.println("Course not found with ID: " + courseId);
            return;
        }

        // Remove course from all students
        for (String studentId : course.getEnrolledStudents()) {
            enrollmentService.withdrawStudent(studentId, courseId);
        }

        if (courseService.deleteCourse(courseId)) {
            System.out.println("Course deleted.");
        }
    }

    /**
     * View details of one course
     */
    private void viewCourse(){
        System.out.println("\n--- View Course ---");
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();

        Course course = courseService.getCourse(courseId);
        if (course != null) {
            System.out.println(course);
        } else {
            System.out.println("Course not found with ID: " + courseId);
        }
    }

    /**
     * Display all courses in system
     */
    private void listCourses(){
        System.out.println("\n--- All Courses ---");
        List<Course> courses = courseService.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println("Total Courses: " + courses.size());
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    // -------------------- ENROLLMENT --------------------
    /**
     * Enroll student in a course (check that both exist)
     */
    private void enrollStudent(){
        System.out.println("\n--- Enroll Student in Course ---");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();

        if (enrollmentService.enrollStudent(studentId, courseId)) {
            System.out.println("Student enrolled.");
        } else if (studentService.getStudent(studentId) == null) {
            System.out.println("Student not found with ID: " + studentId);
        } else if (courseService.getCourse(courseId) == null) {
            System.out.println("Course not found with ID: " + courseId);
        } else if (studentService.getStudent(studentId).isEnrolled(courseId)) {
            System.out.println("Student is already enrolled in this course.");
        }

    }

    /**
     * Display all courses a student is enrolled in
     */
    private void withdrawStudent(){
        System.out.println("\n--- Withdraw Student from Course ---");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        if (studentService.getStudent(studentId) == null) {
            System.out.println("Student not found with ID: " + studentId);
        }

        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();

        if (courseService.getCourse(courseId) == null) {
            System.out.println("Course not found with ID: " + courseId);
        }

        if (enrollmentService.withdrawStudent(studentId, courseId)) {
            System.out.println("Student withdrawn.");
        } else if (!studentService.getStudent(studentId).isEnrolled(courseId)) {
            System.out.println("Student is not enrolled in this course.");
        }

    }

    /**
     * Views all courses a student is enrolled in.
     */
    private void viewStudentCourses() {
        System.out.println("\n--- View Student's Courses ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        Student student = studentService.getStudent(studentId);
        if (student == null) {
            System.out.println("Student not found with ID: " + studentId);
            return;
        }

        Set<String> enrolledCourses = student.getEnrolledCourses();
        if (enrolledCourses.isEmpty()) {
            System.out.println("Student is not enrolled in any courses.");
        } else {
            System.out.println("Total Courses: " + enrolledCourses.size());
            System.out.println("Courses enrolled by " + student.getName() + ":");
            int credits = 0;
            for (String courseId : enrolledCourses) {
                Course course =  courseService.getCourse(courseId);
                System.out.println(course.getTitle());
                credits += course.getCredits();
            }
            System.out.println("Total Credits: " + credits);
        }
    }

    /**
     * Display all students in a course.
     */
    private void viewCourseStudents() {
        System.out.println("\n--- View Course's Students ---");
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();

        Course course = courseService.getCourse(courseId);
        if (course == null) {
            System.out.println("Course not found with ID: " + courseId);
            return;
        }

        Set<String> enrolledStudents = course.getEnrolledStudents();
        if (enrolledStudents.isEmpty()) {
            System.out.println("No students enrolled in this course.");
        } else {
            System.out.println("Total Students: " + enrolledStudents.size());
            System.out.println("Students enrolled in " + course.getTitle() + ":");
            for (String studentId : enrolledStudents) {
                Student student  = studentService.getStudent(studentId);
                System.out.println(student.getName());
            }
        }
    }

}
