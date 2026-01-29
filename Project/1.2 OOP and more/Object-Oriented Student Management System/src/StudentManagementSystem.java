import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentManagementSystem {
    private HashMap<String, Student> students;
    private HashMap<String, Course> courses;
    private Scanner scanner;

    public StudentManagementSystem() {
        students = new HashMap<>();
        courses = new HashMap<>();
        scanner = new Scanner(System.in);
        loadData();
    }

    private void loadData(){
        courses = FileManager.loadCourses();
        students = FileManager.loadStudents();
        System.out.println("Students loaded: " + students.size());
        System.out.println("Courses loaded: " + courses.size());
    }

    public void run() {
        boolean running = true;

        while (running) {
            displayMainMenu();
            // Handle non-integer input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                System.out.print("Choose an option: ");
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> studentMenu();
                case 2 -> courseMenu();
                case 3 -> enrollmentMenu();
                case 0 -> running = false;
                default -> System.out.println("Invalid option. ");
            }
        }
        System.out.println("Exiting program.");
        scanner.close();
    }

    private void studentMenu(){
        boolean inStudentMenu = true;

        while (inStudentMenu) {
            displayStudentMenu();

            // Handle non-integer input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                System.out.print("Choose an option: ");
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> deleteStudent();
                case 4 -> viewStudent();
                case 5 -> listStudents();
                case 0 -> inStudentMenu =  false;
                default -> System.out.println("Invalid option. ");
            }
        }
    }

    private void courseMenu(){
        boolean inCourseMenu = true;

        while (inCourseMenu) {
            displayCourseMenu();

            // Handle non-integer input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                System.out.print("Choose an option: ");
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

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
            displayEnrollmentMenu();

            // Handle non-integer input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                System.out.print("Choose an option: ");
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

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

    private void displayMainMenu(){
        System.out.println("\n--- Student Management System ---");
        System.out.println("1. Student Management Menu");
        System.out.println("2. Course Management Menu");
        System.out.println("3. Enrollment Management Menu");
        System.out.println("0. Exit");
        System.out.println("-------------------------------------------");
        System.out.print("Choose an option: ");
    }

    private void displayStudentMenu(){
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. View Student");
        System.out.println("5. List All Students");
        System.out.println("0. Back to Main Menu");
        System.out.println("-------------------------------------------");
        System.out.print("Choose an option: ");

    }

    private void displayCourseMenu(){
        System.out.println("\n--- Course Management ---");
        System.out.println("1. Add Course");
        System.out.println("2. Update Course");
        System.out.println("3. Delete Course");
        System.out.println("4. View Course");
        System.out.println("5. List All Courses");
        System.out.println("0. Back to Main Menu");
        System.out.println("-------------------------------------------");
        System.out.print("Choose an option: ");
    }

    private void displayEnrollmentMenu(){
        System.out.println("\n--- Enrollment Management ---");
        System.out.println("1. Enroll Student");
        System.out.println("2. Withdraw Student");
        System.out.println("3. View Student's Courses");
        System.out.println("4. View Course's Students");
        System.out.println("0. Back to Main Menu");
        System.out.println("-------------------------------------------");
        System.out.print("Choose an option: ");
    }


    // -------------------- STUDENT --------------------
    /**
     * Add new student: prompt for ID, name, and date of birth
     */
    private void addStudent(){
        System.out.println("\n--- Add New Student ---");

        String studentId;
        while (true) {
            System.out.print("Enter Student ID: ");
            studentId = scanner.nextLine().trim();

            if (studentId.isEmpty()) {
                System.out.println("Student ID cannot be empty.");
            } else if (students.containsKey(studentId)) {
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

            if (isValidDate(dateOfBirth)) {
                break;
            } else {
                System.out.println("Invalid date. Please use MM/dd/yyyy");
            }
        }

        Student student = new Student(studentId, name, dateOfBirth);
        students.put(studentId, student);

        // Auto-save
        FileManager.saveStudents(students);
        System.out.println("Student added.");
    }

    /**
     * Update student details by ID
     */
    private void updateStudent(){
        System.out.println("\n--- Update Student ---");
        System.out.print("Enter Student ID to update: ");
        String studentId = scanner.nextLine().trim();

        Student student = students.get(studentId);
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
            }

            if (isValidDate(newDateOfBirth)) {
                student.setDateOfBirth(newDateOfBirth);
                break;
            } else {
                System.out.println("Invalid date. Please use MM/dd/yyyy");
            }
        }

        // Auto-save
        FileManager.saveStudents(students);
        System.out.println("Student updated.");
    }

    /**
     * Delete student by ID (also remove student from any course list)
     */
    private void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        System.out.print("Enter Student ID to delete: ");
        String studentId = scanner.nextLine().trim();

        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found with ID: " + studentId);
            return;
        }

        // Remove student from all courses
        for (Course course : courses.values()) {
            if (course.hasStudent(studentId)) {
                course.withdrawStudent(studentId);
            }
        }

        students.remove(studentId);

        // Save both files
        FileManager.saveStudents(students);
        FileManager.saveCourses(courses);
        System.out.println("Student deleted.");
    }

    /**
     * View details of one student
     */
    private void viewStudent(){
        System.out.println("\n--- View Student ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        Student student = students.get(studentId);
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

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("Total Students: " + students.size());
        for (Student student : students.values()) {
            System.out.println(student);
        }
    }

    // -------------------- COURSE --------------------
    /**
     * Add new course: prompt for course ID and title
     */
    private void addCourse(){
        System.out.println("\n--- Add New Course ---");

        String courseId;
        while (true) {
            System.out.print("Enter Course ID: ");
            courseId = scanner.nextLine().trim();

            if (courseId.isEmpty()) {
                System.out.println("Course ID cannot be empty.");
            } else if (courses.containsKey(courseId)) {
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

        Course course = new Course(courseId, title);
        courses.put(courseId, course);

        // Auto-save
        FileManager.saveCourses(courses);
        System.out.println("Course added.");
    }

    /**
     * Update course title by ID
     */
    private void updateCourse(){
        System.out.println("\n--- Update Course ---");
        System.out.print("Enter Course ID to update: ");
        String courseId = scanner.nextLine().trim();

        Course course = courses.get(courseId);
        if (course == null) {
            System.out.println("Course not found with ID: " + courseId);
            return;
        }

        System.out.println("Current details:");
        System.out.println("Course ID: " + course.getCourseId());
        System.out.println("Title: " + course.getTitle());

        System.out.print("Enter new title (press Enter to keep current): ");
        String newTitle = scanner.nextLine().trim();
        if (!newTitle.isEmpty()) {
            course.setTitle(newTitle);
        }

        // Auto-save
        FileManager.saveCourses(courses);
        System.out.println("Course updated and saved successfully!");
    }

    /**
     * Delete a course by ID (also update student enrollments)
     */
    private void deleteCourse(){
        System.out.println("\n--- Delete Course ---");
        System.out.print("Enter Course ID to delete: ");
        String courseId = scanner.nextLine().trim();

        Course course = courses.get(courseId);
        if (course == null) {
            System.out.println("Course not found with ID: " + courseId);
            return;
        }

        // Remove course from all students
        for (Student student : students.values()) {
            if (student.isEnrolled(courseId)) {
                student.withdrawCourse(courseId);
            }
        }

        courses.remove(courseId);

        // Save both files
        FileManager.saveStudents(students);
        FileManager.saveCourses(courses);
        System.out.println("Course deleted.");
    }

    /**
     * View details of one course
     */
    private void viewCourse(){
        System.out.println("\n--- View Course ---");
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();

        Course course = courses.get(courseId);
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

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println("Total Courses: " + courses.size());
        for (Course course : courses.values()) {
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

        Student student = students.get(studentId);
        Course course = courses.get(courseId);

        if (student == null) {
            System.out.println("Student not found with ID: " + studentId);
            return;
        }

        if (course == null) {
            System.out.println("Course not found with ID: " + courseId);
            return;
        }

        if (student.isEnrolled(courseId)) {
            System.out.println("Student is already enrolled in this course.");
            return;
        }

        student.enrollCourse(courseId);
        course.enrollStudent(studentId);

        // Save both files
        FileManager.saveStudents(students);
        FileManager.saveCourses(courses);
        System.out.println("Student enrolled");
    }

    /**
     * Display all courses a student is enrolled in
     */
    private void withdrawStudent(){
        System.out.println("\n--- Withdraw Student from Course ---");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();

        Student student = students.get(studentId);
        Course course = courses.get(courseId);

        if (student == null) {
            System.out.println("Student not found with ID: " + studentId);
            return;
        }

        if (course == null) {
            System.out.println("Course not found with ID: " + courseId);
            return;
        }

        if (!student.isEnrolled(courseId)) {
            System.out.println("Student is not enrolled in this course.");
            return;
        }

        student.withdrawCourse(courseId);
        course.withdrawStudent(studentId);

        // Save both files
        FileManager.saveStudents(students);
        FileManager.saveCourses(courses);
        System.out.println("Student withdrawn.");
    }

    /**
     * Views all courses a student is enrolled in.
     */
    private void viewStudentCourses() {
        System.out.println("\n--- View Student's Courses ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found with ID: " + studentId);
            return;
        }

        Set<String> enrolledCourses = student.getEnrolledCourses();
        if (enrolledCourses.isEmpty()) {
            System.out.println("Student is not enrolled in any courses.");
        } else {
            System.out.println("\nCourses enrolled by " + student.getName() + ":");
            for (String courseId : enrolledCourses) {
                Course course =  courses.get(courseId);
                System.out.println(course.getTitle());
            }
        }
    }

    /**
     * Display all students in a course.
     */
    private void viewCourseStudents() {
        System.out.println("\n--- View Course's Students ---");
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine().trim();

        Course course = courses.get(courseId);
        if (course == null) {
            System.out.println("Course not found with ID: " + courseId);
            return;
        }

        Set<String> enrolledStudents = course.getEnrolledStudents();
        if (enrolledStudents.isEmpty()) {
            System.out.println("No students enrolled in this course.");
        } else {
            System.out.println("\nStudents enrolled in " + course.getTitle() + ":");
            for (String studentId : enrolledStudents) {
                Student student  = students.get(studentId);
                System.out.println(student.getName());
            }
        }
    }

    // -------------------- HELPER --------------------
    private static boolean isValidDate(String date) {
        try{
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return true;
        } catch (ParseException e){
            return false;
        }
    }

}
