# Student Information Management System

## Overview
In this assignment, you will build a console-based Student Information Management System in Java. You will practice basic programming constructs, file I/O, and menu-driven interfaces. This assignment uses primitive arrays; we will cover ArrayList and OOP in later assignments.

## Functional Requirements
1. Store up to 100 student records using parallel arrays:
   - `int[] ids`
   - `String[] names`
   - `int[] ages`
   - `double[] grades`
   - Use an `int count` variable to track the current number of students.
2. Implement static methods in a single `Main` class (or a utility class) to:
   - `addStudent(...)`: Add a new student record to the arrays.
   - `viewStudents(...)`: Display all stored student records.
   - `searchStudentById(...)`: Find and display a student by ID.
   - `updateStudent(...)`: Modify an existing student record.
   - `deleteStudent(...)`: Remove a student record by ID (shift array elements).
3. Persist student data in a text file named `students.txt`:
   - On startup, read each line from the file, parse fields, and load them into the arrays.
   - After each add, update, or delete operation, overwrite `students.txt` with the current records.
4. Build a console menu in the `main` method that lets the user choose actions: Add, View, Search, Update, Delete, or Exit.
5. Handle invalid input and file errors gracefully. Display meaningful messages and allow the user to retry or return to the menu.

## Technical Requirements
 - Use basic Java features: arrays, loops, conditionals, and methods.
 - Do not use `ArrayList` or custom classes (no `Student` class).
 - Use `Scanner` and `PrintWriter` or `BufferedReader` and `BufferedWriter` for file I/O.
 - Include comments explaining key parts of your code.

## File Format:

The students.txt file stores data in CSV format:
```
1,Daniel,23,100.0
2,Jane Doe,35,95.46
3,John Pork,56,72.0
4,Bob Smith,21,92.0
```

## Compilation and Execution

### Steps to Run

1. **Extract the ZIP file**:
   ```
   unzip StudentManagementSystem.zip
   cd StudentManagementSystem
    ```
   
2. **Compile the Java program:**
    ```
   javac StudentManagementSystem.java
   ```
   
3. **Run the program:**
   ```
   java StudentManagementSystem
   ```
   
### Usage Instructions

When the program starts, you'll see a menu with 6 options  
Enter the number corresponding to your desired action:  

    1. Add Student
    2. View All Students
    3. Search Student by ID
    4. Update Student
    5. Delete Student
    6. Exit

Follow the prompts for each operation  
Data is automatically saved to `students.txt` after each modification