package util;

public class MenuUtil {

    public static void displayMainMenu(){
        System.out.println("\n--- Student Management System ---");
        System.out.println("1. Student Management Menu");
        System.out.println("2. Course Management Menu");
        System.out.println("3. Enrollment Management Menu");
        System.out.println("0. Exit");
        System.out.println("-------------------------------------------");
        System.out.print("Choose an option: ");
    }

    public static void displayStudentMenu(){
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

    public static void displayCourseMenu(){
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

    public static void displayEnrollmentMenu(){
        System.out.println("\n--- Enrollment Management ---");
        System.out.println("1. Enroll Student");
        System.out.println("2. Withdraw Student");
        System.out.println("3. View Student's Courses");
        System.out.println("4. View Course's Students");
        System.out.println("0. Back to Main Menu");
        System.out.println("-------------------------------------------");
        System.out.print("Choose an option: ");
    }
}
