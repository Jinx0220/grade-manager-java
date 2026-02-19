package STDgrademanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class FileHandler {

    private static final String Coursefile = "STDgrademanagement\\data\\Course.txt";
    private static final String Studentfile = "STDgrademanagement\\data\\Student.txt";

    public static void saveStudent(HashMap<String, Student> students) {
        try (FileWriter fw = new FileWriter(Studentfile)) {
            for (Student std : students.values()) {
                StringBuilder line = new StringBuilder();
                line.append(std.getStudentId()).append(",");
                line.append(std.getName()).append("|");

                HashMap<String, Integer> grades = std.getAllGrades();
                boolean firstgrade = true;
                for (String coursecode : grades.keySet()) {
                    if (!firstgrade) {
                        line.append(",");
                    }

                    line.append(coursecode).append(":").append(grades.get(coursecode));
                    firstgrade = false;
                }
                fw.write(line.toString() + "\n");
            }
            System.out.println("Saved sucessfully to file");
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        } catch (IOException e) {
            System.out.println("error saving files");
        }
    }

    public static HashMap<String, Student> loadStudents() {
        HashMap<String, Student> students = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Studentfile))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 1) {
                    String[] studentInfo = parts[0].split(",");
                    if (studentInfo.length >= 2) {
                        String studentId = studentInfo[0];
                        String name = studentInfo[1];

                        Student std = new Student(studentId, name);
                        if (parts.length == 2) {
                            String[] grades = parts[1].split(",");
                            for (String grade : grades) {
                                String[] gradeInfo = grade.split(":");
                                if (gradeInfo.length == 2) {
                                    String coursecode = gradeInfo[0];
                                    int mark = Integer.parseInt(gradeInfo[1]);
                                    std.addGrade(coursecode, mark);

                                }

                            }
                        }
                        students.put(studentId, std);
                        count++;

                    } else {
                        System.out.println("Invalid Student");

                    }

                }

            }
            System.out.println("Loaded the students sucessfully");
            System.out.println("The total students are: " + count);

        } catch (FileNotFoundException e) {
            System.out.println("File was not Found");
        } catch (IOException e) {
            System.out.println("Erro while loading" + e.getMessage());
        }
        return students;
    }

    public static void saveCourse(HashMap<String, Course> courses) {
        try (FileWriter fw = new FileWriter(Coursefile)) {
            for (Course course : courses.values()) {
                fw.write(course.getCourseCode() + "," +
                        course.getCourseName() + "," +
                        course.getMaxMarks() + "\n");
            }
            System.out.println("Saved courses sucessfully");
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        } catch (IOException e) {
            System.out.println("Error something went wrong " + e.getMessage());

        }
    }

    public static HashMap<String, Course> loadCourses() {
        HashMap<String, Course> courses = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(Coursefile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String coursecode = parts[0];
                    String courseName = parts[1];
                    int marks = Integer.parseInt(parts[2]);

                    Course course = new Course(coursecode, courseName, marks);
                    courses.put(coursecode, course);
                }
            }
            System.out.println("Loaded the  courses sucessfully");
            System.out.println("The total courses are" + courses.size());

        } catch (FileNotFoundException e) {
            System.out.println("File was not Found");
        } catch (IOException e) {
            System.out.println("Error loading courses" + e.getMessage());
        }
        return courses;
    }

}