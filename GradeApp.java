package STDgrademanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GradeApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GradeManager manager = new GradeManager();
        boolean using = true;

        while (using) {
            printMenu();
            int choice = getChoice(sc);
            sc.nextLine();

            switch (choice) {
                case 1 -> addStudent(sc, manager);
                case 2 -> addCourse(sc, manager);
                case 3 -> addGrade(sc, manager);
                case 4 -> viewStudentReport(sc, manager);
                case 5 -> viewCourseReport(sc, manager);
                case 6 -> viewAllstudents(sc, manager);
                case 7 -> viewAllCourses(manager);
                case 8 -> findTopStudentInCourse(sc, manager);
                case 9 -> findStudentNeedingHelp(sc, manager);
                case 10 -> {
                    System.out.println("saving data");
                    manager.saveAllData();
                    System.out.println("Thank you");
                    using = false;

                }
                default -> System.out.println("invalid choice");
            }
        }
        sc.close();

    }

    public static void printMenu() {
        System.out.println("\n=============Main Menu==============");
        System.out.println("1: Add new Student");
        System.out.println("2: Add new Course");
        System.out.println("3: add grade");
        System.out.println("4: View Student Report");
        System.out.println("5: View Course Report");
        System.out.println("6: View All Students");
        System.out.println("7: View All Courses");
        System.out.println("8: Find Top Student in Course");
        System.out.println("9: Find Students Needing Help(<40%");
        System.out.println("10: Save and Exit");
        System.out.print("Enter a choice between (1-10): ");

    }

    public static int getChoice(Scanner sc) {
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input");
            sc.next();
            return -1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input");
            return -1;

        }
    }

    public static void addStudent(Scanner sc, GradeManager manager) {
        System.out.print("Enter the Student ID: ");
        String ID = sc.nextLine();
        System.out.print("Enter the student name: ");
        String name = sc.nextLine();
        manager.addStudent(ID, name);
    }

    public static void addCourse(Scanner sc, GradeManager manager) {
        System.out.print("Enter the Course Id:");
        String courseId = sc.nextLine();
        System.out.print("Enter the Course name");
        String coursename = sc.nextLine();
        System.out.print("enter the marks for the course: ");
        int marks = sc.nextInt();
        sc.nextLine();

        manager.addCourse(courseId, coursename, marks);
    }

    public static void addGrade(Scanner sc, GradeManager manager) {
        System.out.print("Enter the Student Id: ");
        String studentID = sc.nextLine();
        System.out.print("Enter the courseId: ");
        String courseId = sc.nextLine();
        System.out.print("Enter the grade: ");
        Integer grade = sc.nextInt();
        sc.nextLine();

        manager.addGrade(studentID, courseId, grade);
    }

    public static void viewStudentReport(Scanner sc, GradeManager manager) {
        System.out.print("Enter the  studentID: ");
        String studentId = sc.nextLine();

        if (!manager.allStudents().containsKey(studentId)) {
            System.out.println("The student doesnt exist");
            return;
        }

        Student std = manager.allStudents().get(studentId);
        System.out.println("Student report for: " + studentId + " :" + std.getName());
        System.out.println("=====Grades======");

        HashMap<String, Integer> grades = std.getAllGrades();
        if (grades.isEmpty()) {
            System.out.println("No grades calculated yet");
        } else {
            HashMap<String, Course> courses = manager.allCourses();
            for (String coursecode : grades.keySet()) {
                Integer grade = grades.get(coursecode);

                Course course = courses.get(coursecode);
                String coursename = course.getCourseName();
                System.out.println(coursecode + " - " + coursename + " : " + grade + " /100");
            }

            System.out.println("Statistics: ");
            double average = std.calculateAverage();
            System.out.println("Overall average is: " + average);
        }
        /*
         * // Option A (your current way):
         * HashMap<String, Course> courses = manager.allCourses();
         * Course course = courses.get(coursecode);
         * String name = course.getCourseName();
         * 
         * // Option B (direct way):
         * Course course = manager.allCourses().get(coursecode);
         * String name = course.getCourseName();
         * 
         * // Option C (one line):
         * String name = manager.allCourses().get(coursecode).getCourseName();
         */

    }

    public static void viewCourseReport(Scanner sc, GradeManager manager) {
        System.out.print("Enter the coursecode: ");
        String coursecode = sc.nextLine();

        HashMap<String, Student> std = manager.allStudents();
        if (!manager.allCourses().containsKey(coursecode)) {
            System.out.println("Course was no Found. ");
            return;
        } else {
            HashMap<String, Course> course = manager.allCourses();
            int enrolledstudent = 0;
            int passedstd = 0;
            for (Student stdcount : std.values()) {
                if (stdcount.hasTakenCourse(coursecode)) {
                    enrolledstudent++;
                }
            }
            if (enrolledstudent <= 0) {
                System.out.println("No students have taken the course");
                return;
            } else {
                System.out.println("Course Report - " + coursecode + " : " + course.get(coursecode).getCourseName());
                System.out.println("Students Enrolled are:(" + enrolledstudent + "):");
            }

            double courseMark = 0;
            for (Student stdId : std.values()) {
                if (stdId.hasTakenCourse(coursecode)) {
                    System.out.println(coursecode + " - " + stdId.getStudentId() + ": " + stdId.getName() + ": " +
                            stdId.getGradeForCourse(coursecode) + "/100");
                    courseMark += stdId.getGradeForCourse(coursecode);
                    Integer grade = stdId.getGradeForCourse(coursecode);
                    if (grade >= 40) {
                        passedstd++;
                    }

                }
            }
            double passrate = (double) passedstd / enrolledstudent * 100;
            System.out.println("Statistics: ");
            System.out.println("CourseAverage" + courseMark / enrolledstudent);
            System.out.println("Top Student: " + manager.findTopStudentInCourse(coursecode));
            System.out.printf("Pass Rate: %.1f%%(%d/%d students)%n", passrate, passedstd, enrolledstudent);
        }

    }

    public static void viewAllstudents(Scanner sc, GradeManager manager) {
        HashMap<String, Student> students = manager.allStudents();
        System.out.println("All the students are: ");
        if (students.isEmpty()) {
            System.out.println("There are no students:");
            return;
        }
        for (String studenId : students.keySet()) {
            System.out.println(studenId + ": " + students.get(studenId).getName());
        }
    }

    public static void viewAllCourses(GradeManager manager) {
        HashMap<String, Course> courses = manager.allCourses();
        if (courses.isEmpty()) {
            System.out.println("There are no courses");
            return;
        }
        for (String coursecode : courses.keySet()) {
            System.out.println(coursecode + ": " + courses.get(coursecode).getCourseName());
        }
    }

    public static void findTopStudentInCourse(Scanner sc, GradeManager manager) {
        System.out.print("Enter the coursecode: ");
        String coursecode = sc.nextLine();
        String topstudent = manager.findTopStudentInCourse(coursecode);
        if (topstudent == null || "null".equals(topstudent)) {
            System.out.println("There is no top student recorded yet");
            return;
        } else {
            System.out.println("The top student is: " + topstudent);
        }
    }

    public static void findStudentNeedingHelp(Scanner sc, GradeManager manager) {
        ArrayList<Student> students = new ArrayList<>();
        System.out.print("Enter the threshold marks: ");
        double threshold = sc.nextDouble();
        students = manager.FindstudentsBelowThreshold(threshold);
        if (students.isEmpty()) {
            System.out.println("There are no struggling students ");
            return;
        }
        for (Student s : students) {
            System.out.println(s.getStudentId() + ": " + s.getName());
        }

    }
}
