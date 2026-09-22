UNIFLOW – SMART UNIVERSITY ACADEMIC MANAGEMENT SYSTEM
======================================================

READY-TO-EXECUTE JAVA CONSOLE PROJECT

Requirements
------------
Java JDK 8 or later.

Files
-----
Main.java
UniFlowSystem.java
Student.java
Course.java
Registration.java
Attendance.java
Result.java
UniFlowException.java

How to Run
----------
1. Put all .java files in the same folder.
2. Open Command Prompt/Terminal in that folder.
3. Compile:

   javac *.java

4. Run:

   java Main

IDE
---
You can also create a Java project in Eclipse, IntelliJ IDEA or VS Code,
add all .java files, and run Main.java.

Main Modules
------------
1. Student Admission
2. View Students
3. Course Management
4. Course Registration
5. Attendance Management
6. Examination Management
7. Result Management
8. Academic Reports
9. Exit

Sample Classroom Flow
---------------------
1. Run the program.
2. Select 1 and admit a student.
   Example:
   ID: 101
   Name: Rahul
   Department: CSE
   Mobile: 9876543210
   Email: rahul@gmail.com

3. Select 3 to view the preloaded courses.
4. Select 4 to register Rahul for a course.
5. Select 5 to record attendance.
   Example:
   Total Classes: 40
   Attended: 34
   Attendance = 85%

6. Select 6 to enter examination marks.
   Internal: 35
   External: 50
   Total: 85
   Grade: A

7. Select 7 to view results.
8. Select 8 to generate academic reports.

Teaching Mapping
----------------
Variables/Data Types -> Student and Course data
Operators -> Attendance and marks calculations
if-else -> Validation and eligibility
switch -> Main menu
Loops -> Repeated operations
Arrays/Strings -> Can be introduced as extension activities
Methods -> Modular operations
Classes/Objects -> Student, Course, Attendance, Result
Encapsulation -> Private fields and getters/setters
Collections -> HashMap, ArrayList, HashSet concepts
Exception Handling -> Invalid input and custom UniFlowException
Streams/Lambda -> Academic reports and sorting
JDBC -> Recommended next version for database integration

Important
---------
This version stores data in memory. Data will be lost when the program
is closed. The next project version can add MySQL + JDBC for permanent
storage.

Suggested Student Extensions
----------------------------
1. Add student update/delete.
2. Add email validation.
3. Add course registration credit limit.
4. Add faculty management.
5. Add fee management.
6. Add semester-wise GPA/CGPA.
7. Add file handling.
8. Add MySQL database using JDBC.
9. Add login for Admin, Faculty and Student.
10. Build a GUI/web version after the console version is understood.
