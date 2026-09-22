public class Result {
    private int studentId;
    private String courseId;
    private int internal;
    private int external;
    private int total;
    private String grade;
    private boolean passed;

    public Result(int studentId, String courseId, int internal,
                  int external, int total, String grade, boolean passed) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.internal = internal;
        this.external = external;
        this.total = total;
        this.grade = grade;
        this.passed = passed;
    }

    public int getStudentId() { return studentId; }
    public String getCourseId() { return courseId; }
    public int getTotal() { return total; }
    public String getGrade() { return grade; }
    public boolean isPassed() { return passed; }

    @Override
    public String toString() {
        return "Student ID: " + studentId +
               " | Course: " + courseId +
               " | Internal: " + internal +
               " | External: " + external +
               " | Total: " + total +
               " | Grade: " + grade +
               " | Status: " + (passed ? "PASS" : "FAIL");
    }
}
