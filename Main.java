import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UniFlowSystem system = new UniFlowSystem();

        // Sample data for classroom demonstration
        system.addCourse(new Course("C101", "Java Programming", 4));
        system.addCourse(new Course("C102", "Data Structures", 4));
        system.addCourse(new Course("C103", "Database Management", 3));
        system.addCourse(new Course("C104", "Computer Networks", 3));

        System.out.println("========================================");
        System.out.println("       UNIFLOW SMART UNIVERSITY");
        System.out.println("        ACADEMIC MANAGEMENT SYSTEM");
        System.out.println("========================================");

        boolean running = true;

        while (running) {
            system.displayMenu();
            int choice = readInt(sc, "Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        system.admitStudent(sc);
                        break;
                    case 2:
                        system.displayStudents();
                        break;
                    case 3:
                        system.courseManagement(sc);
                        break;
                    case 4:
                        system.registerCourse(sc);
                        break;
                    case 5:
                        system.attendanceManagement(sc);
                        break;
                    case 6:
                        system.examinationManagement(sc);
                        break;
                    case 7:
                        system.displayResults();
                        break;
                    case 8:
                        system.generateReports();
                        break;
                    case 9:
                        running = false;
                        System.out.println("Thank you for using UniFlow.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 1-9.");
                }
            } catch (UniFlowException e) {
                System.out.println("Operation failed: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }

        sc.close();
    }

    private static int readInt(Scanner sc, String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
