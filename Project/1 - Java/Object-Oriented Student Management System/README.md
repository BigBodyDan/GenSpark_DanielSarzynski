# Object-Oriented Student Management System

## Overview
In this project, you will build a console-based Student Management System in Java. The goal is to apply object-oriented principles and use Java Collections to store and manage data. Students will interact with the system through a menu-driven interface.

## Project Requirements
1. Use at least these classes:
    - **Student**: holds student ID, name, date of birth, and a list of enrolled courses.
    - **Course**: holds course ID, title, and a list of enrolled students.
    - **StudentManagementSystem**: main class with a `main` method and menu logic.
2. Use Java Collections to manage lists and maps:
   - Store all students in an `ArrayList<Student>` or `HashMap<String, Student>` (keyed by ID).
   - Store all courses in an `ArrayList<Course>` or `HashMap<String, Course>`.
3. Provide basic operations in the menu:
    - Add, view, update, and delete a student.
    - Add, view, update, and delete a course.
    - Enroll a student in a course.
    - Withdraw a student from a course.
    - List all students or all courses.
    - Search for a student by ID.
4. Implement input validation. Show error messages for invalid input. 
5. Handle exceptions to prevent the program from crashing.

## Detailed Features

### Student Management
- Add new student: prompt for ID, name, and date of birth.
- Update student details by ID.
- Delete student by ID (also remove student from any course list).
- View details of one student or all students.

### Course Management
- Add new course: prompt for course ID and title.
- Update course title by ID.
- Delete a course by ID (also update student enrollments).
- View details of one course or all courses.

### Enrollment
- Enroll student in a course (check that both exist).
- Withdraw student from a course.
- Display all courses a student is enrolled in.
- Display all students in a course.

## Design Decisions:
- `StudentManagementSystem.java` Main application controller
- `Student.java` Model class representing a student
- `Course.java` Model class representing a course
- `FileManager.java` Dedicated file I/O operations
-  Implemented hierarchical menu structure instead of a flat menu
-  Implemented data persistence using .txt files
-  Choose `HashMap` data structure for students and courses
- Data is automatically saved to `.txt` files after each modification

## File Format:

The `students.txt` file stores data in the following format:  
Student ID | Name | DOB | List of CourseIDs
```
1|John Pork|04/20/1969|1,2
2|Tim Cheese|05/05/1955|1
3|Marvin Beak|06/07/1967|1,3
4|Pengu|01/01/2000|2,3
5|Pablo|02/29/2004|none
```

The `courses.txt` file stores data in the following format:  
Course ID | Title | List of StudentsIDs Enrolled
```
1|Archery|1,2,3
2|Cooking|1,4
3|Physics|3,4
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
   javac *.java
   ```

3. **Run the program:**
   ```
   java Main
   ```

### Usage Instructions

When the program starts, you'll see a menu with 4 options  
Enter the number corresponding to your desired action:

    --- Student Management System ---
    1. Student Management Menu
    2. Course Management Menu
    3. Enrollment Management Menu
    0. Exit
    -------------------------------------------

Student Management Menu

    --- Student Management ---
    1. Add Student
    2. Update Student
    3. Delete Student
    4. View Student
    5. List All Students
    0. Back to Main Menu
    -------------------------------------------

Course Management Menu

    --- Course Management ---
    1. Add Course
    2. Update Course
    3. Delete Course
    4. View Course
    5. List All Courses
    0. Back to Main Menu
    -------------------------------------------

Enrollment Management Menu

    --- Enrollment Management ---
    1. Enroll Student
    2. Withdraw Student
    3. View Student's Courses
    4. View Course's Students
    0. Back to Main Menu
    -------------------------------------------

Follow the prompts for each operation  
Data is automatically saved to files after each modification
