public class Registration {
    private int studentId;
    private String courseId;

    public Registration(int studentId, String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getStudentId() { return studentId; }
    public String getCourseId() { return courseId; }
}
