import java.io.*;
import java.util.Scanner;

public class StudentManagementSystem {
    private static int[] ids = new int[100];
    private static String[] names = new String[100];
    private static int[] ages = new int[100];
    private static double[] grades = new double[100];
    private static int count = 0;
    private static final String FILENAME = "students.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Load existing data from file on startup
        loadStudentsFromFile();

        int choice;
        do {
            System.out.println("\n--- Student Information Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.println("-------------------------------------------");
            System.out.print("Choose an option: ");

            // Handle non-integer input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                System.out.print("Choose an option: ");
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addStudent(scanner);
                case 2 -> viewStudents();
                case 3 -> searchStudentById(scanner);
                case 4 -> updateStudent(scanner);
                case 5 -> deleteStudentById(scanner);
                case 6 -> System.out.println("Exiting program. Goodbye.");
                default -> System.out.println("Invalid option. Please select 1-6.");
            }
        } while (choice != 6);

        scanner.close();
    }

    /**
     * Add a new student record to the arrays
     * @param scanner Scanner object for user input
     */
    private static void addStudent(Scanner scanner) {
        System.out.println("\n--- Add New Student ---");

        // Get and validate student ID
        int id = 0;
        boolean validId = false;
        while (!validId) {
            System.out.print("Enter ID: ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                scanner.nextLine();

                // Check if ID already exists
                if (findStudentIndexById(id) != -1) {
                    System.out.println("Error: Student ID already exists.");
                    continue;
                }
                if (id <= 0) {
                    System.out.println("Error: ID must be a positive number.");
                    continue;
                }
                validId = true;
            } else {
                System.out.println("Error: ID must be a number.");
                scanner.nextLine();
            }
        }

        // Get student name
        String name = "";
        while (name.trim().isEmpty()) {
            System.out.print("Enter Name: ");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Error: Name cannot be empty.");
            }
        }

        // Get and validate age
        int age = 0;
        boolean validAge = false;
        while (!validAge) {
            System.out.print("Enter Age: ");
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                scanner.nextLine();
                if (age < 0) {
                    System.out.println("Error: Age must be non-negative.");
                } else {
                    validAge = true;
                }
            } else {
                System.out.println("Error: Age must be a number.");
                scanner.nextLine();
            }
        }

        // Get and validate grade
        double grade = 0.0;
        boolean validGrade = false;
        while (!validGrade) {
            System.out.print("Enter Grade (0.0-100.0): ");
            if (scanner.hasNextDouble()) {
                grade = scanner.nextDouble();
                scanner.nextLine();
                if (grade < 0.0 || grade > 100.0) {
                    System.out.println("Error: Grade must be between 0.0 and 100.0");
                } else {
                    validGrade = true;
                }
            } else {
                System.out.println("Error: Grade must be a number");
                scanner.nextLine();
            }
        }

        // Store student data in arrays
        ids[count] = id;
        names[count] = name;
        ages[count] = age;
        grades[count] = grade;
        count++;

        // Save to file
        saveStudentsToFile();

        System.out.println("Student added successfully");
    }

    /**
     * Display all stored student records
     */
    private static void viewStudents() {
        System.out.println("\n--- All Students ---");

        if (count == 0) {
            System.out.println("No students found.");
            return;
        }

        // Print table header
        System.out.printf("%-6s %-15s %-6s %-5s\n", "ID", "Name", "Age", "Grade");
        System.out.println("------------------------------------");

        // Print each student's information
        for (int i = 0; i < count; i++) {
            System.out.printf("%-6d %-15s %-6d %.2f\n",
                    ids[i], names[i], ages[i], grades[i]);
        }

        System.out.println("Total students: " + count);
    }

    /**
     * Find and display a student by ID
     * @param scanner Scanner object for user input
     */
    private static void searchStudentById(Scanner scanner) {
        System.out.println("\n--- Search Student by ID ---");

        if (count == 0) {
            System.out.println("No students to search.");
            return;
        }

        System.out.print("Enter ID to search: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: ID must be a number.");
            scanner.nextLine();
            return;
        }

        int searchId = scanner.nextInt();
        scanner.nextLine();

        int index = findStudentIndexById(searchId);

        if (index == -1) {
            System.out.println("Student with ID " + searchId + " not found.");
        } else {
            displayStudentDetails(index);
        }
    }

    /**
     * Modify an existing student record
     * @param scanner Scanner object for user input
     */
    private static void updateStudent(Scanner scanner) {
        System.out.println("\n--- Update Student ---");

        if (count == 0) {
            System.out.println("No students to update.");
            return;
        }

        System.out.print("Enter ID to update: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: ID must be a number.");
            scanner.nextLine();
            return;
        }

        int updateId = scanner.nextInt();
        scanner.nextLine();

        int index = findStudentIndexById(updateId);

        if (index == -1) {
            System.out.println("Student with ID " + updateId + " not found.");
            return;
        }

        // Display current information
        System.out.println("Current student information:");
        displayStudentDetails(index);

        System.out.println("\nEnter new information (press Enter to keep current value):");

        // Update name
        System.out.print("New Name: ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty()) {
            names[index] = newName;
        }

        // Update age
        System.out.print("New Age: ");
        String ageInput = scanner.nextLine();
        if (!ageInput.trim().isEmpty()) {
            try {
                int newAge = Integer.parseInt(ageInput);
                if (newAge >= 0) {
                    ages[index] = newAge;
                } else {
                    System.out.println("Invalid age. Keeping current value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Keeping current value.");
            }
        }

        // Update grade
        System.out.print("New Grade: ");
        String gradeInput = scanner.nextLine();
        if (!gradeInput.trim().isEmpty()) {
            try {
                double newGrade = Double.parseDouble(gradeInput);
                if (newGrade >= 0.0 && newGrade <= 100.0) {
                    grades[index] = newGrade;
                } else {
                    System.out.println("Invalid grade. Keeping current value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Keeping current value.");
            }
        }

        // Save changes to file
        saveStudentsToFile();

        System.out.println("Student updated successfully.");
        System.out.println("Updated information:");
        displayStudentDetails(index);
    }

    /**
     * Remove a student record by ID (shift array elements)
     * @param scanner Scanner object for user input
     */
    private static void deleteStudentById(Scanner scanner) {
        System.out.println("\n--- Delete Student ---");

        if (count == 0) {
            System.out.println("No students to delete.");
            return;
        }

        System.out.print("Enter ID to delete: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Error: ID must be a number.");
            scanner.nextLine();
            return;
        }

        int deleteId = scanner.nextInt();
        scanner.nextLine();

        int index = findStudentIndexById(deleteId);

        if (index == -1) {
            System.out.println("Student with ID " + deleteId + " not found.");
            return;
        }

        // Display student to be deleted
        System.out.println("Student to be deleted:");
        displayStudentDetails(index);

        // Confirm deletion
        System.out.print("Are you sure you want to delete this student? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Deletion cancelled.");
            return;
        }

        // Shift array elements to remove the student
        for (int i = index; i < count - 1; i++) {
            ids[i] = ids[i + 1];
            names[i] = names[i + 1];
            ages[i] = ages[i + 1];
            grades[i] = grades[i + 1];
        }

        // Clear the last element
        ids[count - 1] = 0;
        names[count - 1] = null;
        ages[count - 1] = 0;
        grades[count - 1] = 0.0;

        count--;

        // Save changes to file
        saveStudentsToFile();

        System.out.println("Student deleted successfully.");
    }

    /**
     * Helper method to find the index of a student by ID
     * @param id Student ID to search for
     * @return Index of the student, or -1 if not found
     */
    private static int findStudentIndexById(int id) {
        for (int i = 0; i < count; i++) {
            if (ids[i] == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Helper method to display a student's details
     * @param index Index of the student in the arrays
     */
    private static void displayStudentDetails(int index) {
        System.out.println("ID: " + ids[index]);
        System.out.println("Name: " + names[index]);
        System.out.println("Age: " + ages[index]);
        System.out.printf("Grade: %.2f\n", grades[index]);
    }

    /**
     * Read each line from the file, parse fields, and load them into the arrays
     */
    private static void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            count = 0;

            while ((line = reader.readLine()) != null && count < ids.length) {
                // Parse each line: id,name,age,grade
                String[] parts = line.split(",");

                if (parts.length == 4) {
                    try {
                        ids[count] = Integer.parseInt(parts[0].trim());
                        names[count] = parts[1].trim();
                        ages[count] = Integer.parseInt(parts[2].trim());
                        grades[count] = Double.parseDouble(parts[3].trim());
                        count++;
                    } catch (NumberFormatException e) {
                        System.out.println("Warning: Skipping invalid line: " + line);
                    }
                }
            }

            System.out.println("Loaded " + count + " student(s) from file.");

        } catch (FileNotFoundException e) {
            System.out.println("No existing data file found.");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    /**
     * Overwrite txt file with the current records
     */
    private static void saveStudentsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            for (int i = 0; i < count; i++) {
                writer.println(ids[i] + "," + names[i] + "," + ages[i] + "," + grades[i]);
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
}