package STDgrademanagement;

import java.util.ArrayList;
import java.util.HashMap;

public class GradeManager {

    private HashMap<String, Student> students = new HashMap<>();
    private HashMap<String, Course> courses = new HashMap<>();

    public HashMap<String, Student> allStudents() {
        return new HashMap<>(students);
    }

    public HashMap<String, Course> allCourses() {
        return new HashMap<>(courses);
    }

    public GradeManager() {
        this.courses = FileHandler.loadCourses();
        this.students = FileHandler.loadStudents();
    }

    public void saveAllData() {
        FileHandler.saveCourse(courses);
        FileHandler.saveStudent(students);
    }

    public boolean addStudent(String studentId, String name) {
        if (students.containsKey(studentId)) {
            System.out.println("The student already exists");
            return false;
        }
        Student std = new Student(studentId, name);
        students.put(studentId, std);
        System.out.println("Student added sucessfully of ID: " + studentId);
        return true;
    }

    public void addStudent(Student student) {
        students.put(student.getStudentId(), student);
        System.out.println("Sucessfully added the student: " + student.getStudentId());
    }

    public boolean addCourse(String courseId, String CourseName, int marks) {
        if (courses.containsKey(courseId)) {
            System.out.println("The course already exists");
            return false;
        } else {
            Course course = new Course(courseId, CourseName, marks);
            courses.put(courseId, course);
            System.out.println("Sucessfully added the course: " + courseId);
            return true;
        }
    }

    public void addCourse(Course course) {
        courses.put(course.getCourseCode(), course);
        System.out.println("Sucessfully added the course: " + course.getCourseCode());
    }

    public void addGrade(String studentId, String courseCode, Integer grade) {
        if (!students.containsKey(studentId)) {
            System.out.println("The student is not found");
            return;
        }
        if (!courses.containsKey(courseCode)) {
            System.out.println("The course is not Found");
            return;
        }

        Student std = students.get(studentId);
        std.addGrade(courseCode, grade);
        System.out.println("Grade added sucessfully");
        /*
         * // 1. Build a house (object)
         * Student aliceHouse = new Student("S001", "Alice");
         * // aliceHouse = "123 Main St" (address to the house)
         * 
         * // 2. Create an address book (HashMap)
         * HashMap<String, Student> addressBook = new HashMap<>();
         * 
         * // 3. Save the address in your book
         * addressBook.put("S001", aliceHouse);
         * // You wrote: "For S001, go to 123 Main St"
         * // You DIDN'T build a new house!
         * 
         * HashMap<String, Student> students = new HashMap<>();
         * // KEY (String) → VALUE (Memory Address of Student object)
         * // "S001" → 0x1000 (Alice's object location)
         * // "S002" → 0x2000 (Bob's object location)
         * 
         * 
         */

    }

    public double calculateStudentAverage(String studentId) {
        if (!students.containsKey(studentId)) {
            System.out.println("The student is not Found");
            return 0;
        } else {
            Student std = students.get(studentId);
            double average = std.calculateAverage();
            System.out.println("The total average of the student is: " + average);
            return average;
        }

    }

    public double calculateCourseAverage(String courseCode) {
        if (!courses.containsKey(courseCode)) {
            System.out.println("The course was not Found");
            return 0;
        } else {
            Integer sum = 0;
            double count = 0;
            for (Student std : students.values()) {
                Integer grade = std.getGradeForCourse(courseCode);
                if (grade == null) {
                    sum += 0;
                } else {
                    sum += grade;
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("No students took the course");
                return 0;
            }
            double average = (double) sum / count;
            System.out.println("The average is: " + average);
            return average;

        }
    }

    public String findTopStudentInCourse(String courseCode) {
        if (!courses.containsKey(courseCode)) {
            System.out.println("Course not Found");
            return null;
        } else {
            String topstudent = "null";
            Integer highest = -1;
            for (Student std : students.values()) {
                Integer grade = std.getGradeForCourse(courseCode);
                if (grade != null && grade > highest) {
                    highest = grade;
                    topstudent = std.getName();
                }
            }
            if (topstudent.equals("null")) {
                System.out.println("No grade recorded yet");
            } else {
                System.out.println("The topstudent is: " + topstudent);
            }
            return topstudent;
        }
    }

    public ArrayList<Student> FindstudentsBelowThreshold(double threshold) {
        ArrayList<Student> strugglingstd = new ArrayList<>();
        for (Student std : students.values()) {
            double average = std.calculateAverage();
            if (average != 0.0 && average < threshold) {
                strugglingstd.add(std);
            }
        }
        if (strugglingstd.isEmpty()) {
            System.out.println("There are no students below threshold");
            return strugglingstd;
        } else {
            System.out.println("The struggling students are: ");
            for (Student s : strugglingstd) {
                System.out.println(s.getName() + " -Average is: " + s.calculateAverage());
            }
        }
        return strugglingstd;

    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found");
        } else {
            System.out.println("The students are: ");
            for (Student s : students.values()) {
                System.out.println("StudentID: " + s.getStudentId() + " Name : " + s.getName());
            }
        }
    }

    public void displayAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available");
        } else {
            System.out.println("The courses are: ");
            for (Course c : courses.values()) {
                System.out.println("CourseCode: " + c.getCourseCode() + " courseName: " + c.getCourseName());

            }

        }
    }
}
