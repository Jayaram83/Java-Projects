public class Attendance {
    private int studentId;
    private String courseId;
    private int totalClasses;
    private int attendedClasses;

    public Attendance(int studentId, String courseId,
                      int totalClasses, int attendedClasses) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.totalClasses = totalClasses;
        this.attendedClasses = attendedClasses;
    }

    public int getStudentId() { return studentId; }
    public String getCourseId() { return courseId; }

    public double getPercentage() {
        return (attendedClasses * 100.0) / totalClasses;
    }

    public boolean isEligible() {
        return getPercentage() >= 75.0;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId +
               " | Course: " + courseId +
               " | Classes: " + attendedClasses + "/" + totalClasses +
               " | Attendance: " + String.format("%.2f", getPercentage()) + "%";
    }
}
