package STDgrademanagement;

public class Course {
    private String courseCode;
    private String courseName;
    private int maxMarks;

    public Course(String courseCode, String courseName, int maxMarks) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxMarks = maxMarks;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public void displayInfo() {
        System.out.println("The details of the courses available are:  ");
        System.out.println("The course name is: " + courseName);
        System.out.println("The course code is: " + courseCode);
        System.out.println("The maximum marks in the course is: " + maxMarks);
        System.out.println();
    }
}
