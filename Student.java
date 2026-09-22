public class Student {
    private int studentId;
    private String studentName;
    private String department;
    private String mobile;
    private String email;

    public Student(int studentId, String studentName, String department,
                   String mobile, String email) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.department = department;
        this.mobile = mobile;
        this.email = email;
    }

    public int getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public String getDepartment() { return department; }
    public String getMobile() { return mobile; }
    public String getEmail() { return email; }

    public void setStudentName(String studentName) { this.studentName = studentName; }
    public void setDepartment(String department) { this.department = department; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "ID: " + studentId +
               " | Name: " + studentName +
               " | Department: " + department +
               " | Mobile: " + mobile +
               " | Email: " + email;
    }
}
