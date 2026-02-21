# Grade Manager 📊

A console-based Java application for managing student grades, calculating statistics, and generating academic reports with file-based storage.

## 📋 Features

### Student Management
- Add new students with unique ID, name.
- View all registered students
- Search students by ID or name
- Update student information

### Course Management
- Add courses with course code, title, and credit hours
- View all available courses
- Search courses by code
- Track course roster

### Grade Management
- Record grades for students in different courses
- Calculate weighted averages based on credit hours
- Assign letter grades (A, B, C, D, F)
- Calculate GPA for each student
- View complete grade history

### Statistics & Reports
- Calculate class averages per course
- Generate student transcripts
- View grade distribution
- Identify top performers

### Data Persistence
- All data saved to text files in /data folder
- Students saved to: students.txt
- Courses saved to: courses.txt
- Grades saved to: grades.txt
- Automatic loading on startup
- Automatic saving after each operation

### Input Validation
- Prevents duplicate student/course IDs
- Validates grade entries (0-100 range)
- Checks student/course existence before operations
- Handles invalid menu choices

## 🛠️ Technologies Used

- Java (Version 17)
- Collections Framework (HashMap, ArrayList, TreeSet for sorting)
- File I/O (java.nio.file)
- Exception Handling
- OOP Principles
- Scanner for user input

## 📁 Project Structure
GradeManager/
│
├── data/
│ ├── students.txt # Student records (ID|Name|Email)
│ ├── courses.txt # Course records (Code|Title|Credits)
│ └── grades.txt # Grade records (StudentID|CourseCode|Score)
│
├── Student.java # Student model class
├── Course.java # Course model class
├── Grade.java # Grade record class
├── GradeCalculator.java # Statistics and GPA calculations
├── DataManager.java # File I/O operations
└── GradeApp.java # Main menu & user interface


## 🚀 How to Run

1. **Clone the repository**
   ```bash
  git clone https://github.com/YOUR-USERNAME/grade-manager-java.git

2.Open in your favorite Java IDE
3.Run GradeApp.java
4.Follow the menu prompts to manage students, courses, and grades


🎯 Sample Usage
=== GRADE MANAGER SYSTEM ===
1. Add Student
2. Add Course
3. Record Grade
4. View Student Transcript
5. View Course Statistics
6. View All Students
7. Exit

Enter choice: 1

Enter Student ID: S001
Enter Student Name: Alice Johnson
Enter Email: alice@email.com
✓ Student added successfully!

Enter choice: 3
Enter Student ID: S001
Enter Course Code: CS101
Enter Grade (0-100): 85.5
✓ Grade recorded for Alice Johnson in CS101: 85.5 (B+)

Enter choice: 4

TRANSCRIPT: Alice Johnson (S001)
----------------------------------------
Course      Credits   Grade   Points
CS101       3         85.5    12.0
MATH201     4         92.0    16.0
----------------------------------------
GPA: 3.50
Total Credits: 7
