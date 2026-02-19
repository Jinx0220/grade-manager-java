package STDgrademanagement;

import java.util.HashMap;

public class Student {
    private String studentId;
    private String name;
    private HashMap<String, Integer> grades = new HashMap<>();

    public Student(String studentID, String name) {
        this.studentId = studentID;
        this.name = name;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void addGrade(String courseCode, Integer grade) {
        grades.put(courseCode, grade);
    }

    public double calculateAverage() {
        if (grades.isEmpty()) {
            System.out.println("There are no grades available");
            return 0.0;
        } else {
            double sum = 0;
            for (Integer values : grades.values()) {
                sum += values;
            }
            return sum / grades.size();
        }
    }

    public Integer getGradeForCourse(String courseCode) {
        if (!grades.containsKey(courseCode)) {
            return null;
        }
        return grades.get(courseCode);
    }

    public boolean hasTakenCourse(String courseCode) {
        return grades.containsKey(courseCode);
    }

    public void displayStudentInfo() {
        System.out.println("The student's information are: ");
        System.out.println("StudentID: " + studentId);
        System.out.println("Student Name: " + name);
        if (grades.isEmpty()) {
            System.out.println("No grades recorded yet: ");
        } else {
            for (String course : grades.keySet()) {
                System.out.println("" + course + ": " + grades.get(course));
            }
            System.out.println("Average is: " + calculateAverage());
        }

    }

    public HashMap<String, Integer> getAllGrades() {
        return new HashMap<>(grades);

        // This getAllGrades() method returns a hashmap copy of a Hashmap named grades.
    }

}
