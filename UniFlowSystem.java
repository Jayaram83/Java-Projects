import java.util.*;
import java.util.stream.Collectors;

public class UniFlowSystem {
    private final Map<Integer, Student> students = new HashMap<>();
    private final Map<String, Course> courses = new HashMap<>();
    private final List<Registration> registrations = new ArrayList<>();
    private final Map<String, Attendance> attendanceRecords = new HashMap<>();
    private final Map<String, Result> results = new HashMap<>();

    public void displayMenu() {
        System.out.println("\n--------------- UNIFLOW MENU ---------------");
        System.out.println("1. Student Admission");
        System.out.println("2. View Students");
        System.out.println("3. Course Management");
        System.out.println("4. Course Registration");
        System.out.println("5. Attendance Management");
        System.out.println("6. Examination Management");
        System.out.println("7. Result Management");
        System.out.println("8. Academic Reports");
        System.out.println("9. Exit");
        System.out.println("--------------------------------------------");
    }

    public void admitStudent(Scanner sc) {
        System.out.println("\n===== STUDENT ADMISSION =====");

        int id = readInt(sc, "Enter Student ID: ");
        if (students.containsKey(id)) {
            throw new UniFlowException("Student ID already exists.");
        }

        String name = readNonEmpty(sc, "Enter Student Name: ");
        String department = readNonEmpty(sc, "Enter Department: ");
        String mobile = readMobile(sc);
        String email = readNonEmpty(sc, "Enter Email: ");

        Student student = new Student(id, name, department, mobile, email);
        students.put(id, student);

        System.out.println("\nAdmission Successful!");
        System.out.println(student);
    }

    public void displayStudents() {
        System.out.println("\n===== STUDENT LIST =====");

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        students.values().stream()
                .sorted(Comparator.comparingInt(Student::getStudentId))
                .forEach(System.out::println);
    }

    public void courseManagement(Scanner sc) {
        System.out.println("\n===== COURSE MANAGEMENT =====");
        System.out.println("1. Add Course");
        System.out.println("2. View Courses");
        System.out.println("3. Search Course");
        System.out.println("4. Delete Course");

        int choice = readInt(sc, "Enter choice: ");

        switch (choice) {
            case 1:
                String id = readNonEmpty(sc, "Enter Course ID: ");
                if (courses.containsKey(id)) {
                    throw new UniFlowException("Course ID already exists.");
                }
                String name = readNonEmpty(sc, "Enter Course Name: ");
                int credits = readInt(sc, "Enter Credits: ");
                if (credits <= 0) {
                    throw new UniFlowException("Credits must be positive.");
                }
                addCourse(new Course(id, name, credits));
                System.out.println("Course added successfully.");
                break;

            case 2:
                displayCourses();
                break;

            case 3:
                String searchId = readNonEmpty(sc, "Enter Course ID: ");
                Course course = courses.get(searchId);
                if (course == null) {
                    throw new UniFlowException("Course not found.");
                }
                System.out.println(course);
                break;

            case 4:
                String deleteId = readNonEmpty(sc, "Enter Course ID: ");
                if (courses.remove(deleteId) == null) {
                    throw new UniFlowException("Course not found.");
                }
                System.out.println("Course deleted successfully.");
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    public void addCourse(Course course) {
        courses.put(course.getCourseId(), course);
    }

    private void displayCourses() {
        System.out.println("\n===== COURSE LIST =====");
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        courses.values().stream()
                .sorted(Comparator.comparing(Course::getCourseId))
                .forEach(System.out::println);
    }

    public void registerCourse(Scanner sc) {
        System.out.println("\n===== COURSE REGISTRATION =====");

        int studentId = readInt(sc, "Enter Student ID: ");
        Student student = students.get(studentId);
        if (student == null) {
            throw new UniFlowException("Student not found.");
        }

        displayCourses();
        String courseId = readNonEmpty(sc, "Enter Course ID to register: ");

        if (!courses.containsKey(courseId)) {
            throw new UniFlowException("Course not found.");
        }

        boolean alreadyRegistered = registrations.stream()
                .anyMatch(r -> r.getStudentId() == studentId &&
                              r.getCourseId().equals(courseId));

        if (alreadyRegistered) {
            throw new UniFlowException("Student is already registered for this course.");
        }

        registrations.add(new Registration(studentId, courseId));
        System.out.println("Course registration successful.");
    }

    public void attendanceManagement(Scanner sc) {
        System.out.println("\n===== ATTENDANCE MANAGEMENT =====");
        System.out.println("1. Record Attendance");
        System.out.println("2. View Attendance");

        int choice = readInt(sc, "Enter choice: ");

        switch (choice) {
            case 1:
                int studentId = readInt(sc, "Enter Student ID: ");
                if (!students.containsKey(studentId)) {
                    throw new UniFlowException("Student not found.");
                }

                String courseId = readNonEmpty(sc, "Enter Course ID: ");
                if (!courses.containsKey(courseId)) {
                    throw new UniFlowException("Course not found.");
                }

                int total = readInt(sc, "Enter Total Classes: ");
                int attended = readInt(sc, "Enter Attended Classes: ");

                if (total <= 0 || attended < 0 || attended > total) {
                    throw new UniFlowException("Invalid attendance values.");
                }

                String key = attendanceKey(studentId, courseId);
                attendanceRecords.put(key,
                        new Attendance(studentId, courseId, total, attended));

                System.out.printf("Attendance recorded: %.2f%%%n",
                        attendanceRecords.get(key).getPercentage());
                break;

            case 2:
                int sid = readInt(sc, "Enter Student ID: ");
                String cid = readNonEmpty(sc, "Enter Course ID: ");
                Attendance record = attendanceRecords.get(attendanceKey(sid, cid));

                if (record == null) {
                    throw new UniFlowException("Attendance record not found.");
                }

                System.out.println(record);
                System.out.println(record.isEligible()
                        ? "Status: Exam Eligible"
                        : "Status: Not Eligible");
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    public void examinationManagement(Scanner sc) {
        System.out.println("\n===== EXAMINATION MANAGEMENT =====");

        int studentId = readInt(sc, "Enter Student ID: ");
        if (!students.containsKey(studentId)) {
            throw new UniFlowException("Student not found.");
        }

        String courseId = readNonEmpty(sc, "Enter Course ID: ");
        if (!courses.containsKey(courseId)) {
            throw new UniFlowException("Course not found.");
        }

        Attendance attendance = attendanceRecords.get(attendanceKey(studentId, courseId));
        if (attendance != null && !attendance.isEligible()) {
            throw new UniFlowException("Student is not eligible because attendance is below 75%.");
        }

        int internal = readInt(sc, "Enter Internal Marks (0-40): ");
        int external = readInt(sc, "Enter External Marks (0-60): ");

        if (internal < 0 || internal > 40 || external < 0 || external > 60) {
            throw new UniFlowException("Marks out of valid range.");
        }

        int total = internal + external;
        String grade = calculateGrade(total);
        boolean passed = total >= 40;

        Result result = new Result(studentId, courseId, internal, external, total, grade, passed);
        results.put(resultKey(studentId, courseId), result);

        System.out.println("\nExamination Result Saved.");
        System.out.println(result);
    }

    public void displayResults() {
        System.out.println("\n===== RESULT MANAGEMENT =====");

        if (results.isEmpty()) {
            System.out.println("No results available.");
            return;
        }

        results.values().stream()
                .sorted(Comparator.comparingInt(Result::getStudentId))
                .forEach(System.out::println);
    }

    public void generateReports() {
        System.out.println("\n===== ACADEMIC REPORTS =====");

        System.out.println("\n1. Department-wise Student Count");
        Map<String, Long> departmentCount = students.values().stream()
                .collect(Collectors.groupingBy(Student::getDepartment, TreeMap::new, Collectors.counting()));

        if (departmentCount.isEmpty()) {
            System.out.println("No student data available.");
        } else {
            departmentCount.forEach((dept, count) ->
                    System.out.println(dept + " : " + count));
        }

        System.out.println("\n2. Students Below 75% Attendance");
        boolean found = false;
        for (Attendance a : attendanceRecords.values()) {
            if (!a.isEligible()) {
                Student s = students.get(a.getStudentId());
                System.out.printf("%d - %s - %s - %.2f%%%n",
                        a.getStudentId(),
                        s != null ? s.getStudentName() : "Unknown",
                        a.getCourseId(),
                        a.getPercentage());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No students below 75% attendance.");
        }

        System.out.println("\n3. Top Performers");
        List<Result> topResults = results.values().stream()
                .sorted(Comparator.comparingInt(Result::getTotal).reversed())
                .limit(5)
                .collect(Collectors.toList());

        if (topResults.isEmpty()) {
            System.out.println("No result data available.");
        } else {
            for (Result r : topResults) {
                Student s = students.get(r.getStudentId());
                System.out.printf("%d - %s - %s - %d marks - Grade %s%n",
                        r.getStudentId(),
                        s != null ? s.getStudentName() : "Unknown",
                        r.getCourseId(),
                        r.getTotal(),
                        r.getGrade());
            }
        }
    }

    private String calculateGrade(int total) {
        if (total >= 90) return "A+";
        if (total >= 80) return "A";
        if (total >= 70) return "B+";
        if (total >= 60) return "B";
        if (total >= 50) return "C";
        if (total >= 40) return "D";
        return "F";
    }

    private String attendanceKey(int studentId, String courseId) {
        return studentId + "_" + courseId;
    }

    private String resultKey(int studentId, String courseId) {
        return studentId + "_" + courseId;
    }

    private int readInt(Scanner sc, String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private String readNonEmpty(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String value = sc.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("This field cannot be empty.");
        }
    }

    private String readMobile(Scanner sc) {
        while (true) {
            System.out.print("Enter Mobile Number: ");
            String mobile = sc.nextLine().trim();

            if (mobile.matches("\\d{10}")) {
                return mobile;
            }

            System.out.println("Mobile number must contain exactly 10 digits.");
        }
    }
}
