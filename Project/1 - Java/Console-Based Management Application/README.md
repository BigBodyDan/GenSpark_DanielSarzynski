# Console-Based Management Application

## Overview
The Student Information Management System is a console application designed to manage student records, course information, and enrollments. This project showcases professional software engineering practices including proper package organization, encapsulation, inheritance, polymorphism, and comprehensive error handling.

## Project Requirements
1. Multiple classes and objects
2. Proper use of constructors and encapsulation
3. Inheritance and polymorphism
4. Clear package organization
5. Clean, readable code

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
- Encapsulation
  - All Fields are private
  - Public getters and setters for controlled access

- Inheritance
  - `Student` extends `Person` abstract class
  - `Student` demonstrates "is-a" relationship

- Polymorphism
  - Abstract `getRole()` method in Person
  - Overridden in Student class

- Package Organization
  - `model` package for entity classes
  - `service` package for business logic
  - `dao` package for data storage and access
  - `util` package for utility classes

- Multiple Classes and Objects
  - Distinct classes with clear responsibilities
  - Each class has a single responsibility

- Data is automatically saved to `.txt` files after each modification

## File Format:

The `students.txt` file stores data in the following format:  
ID | Name | DOB | Major | Email | List of CourseIDs
```
1|John Pork|04/20/1969|Computer Science|John.Pork@gmail.com|1,5
2|Tim Cheese|5/5/1955|Engineering|tim@cheese.net|1,2
3|Marvin Beak|1/2/2003|Aerospace|marvinbeak@boeing.com|2,3
4|Pengu|1/1/2000|Culinary Arts|pengu@yahoo.com|1,4
5|Pablo|4/5/1999|Music|pablo@gmail.com|1,2,3,4,5
6|Jane Doe|2/16/2000|Criminal Justice|jane.doe@neps.gov|none
```

The `courses.txt` file stores data in the following format:  
Course ID | Title | Credits | List of StudentsIDs Enrolled
```
1|Calculus|3|1,2,4,5
2|Chemistry|3|2,3,5
3|Aerodynamics|4|3,5
4|Cooking|2|4,5
5|Java|3|1,5
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
   javac -d . src/dao/*.java src/model/*.java src/service/*.java src/util/*.java *.java
   ```

3. **Run the program:**
   ```
   java Application
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