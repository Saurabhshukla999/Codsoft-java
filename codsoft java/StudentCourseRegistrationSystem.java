import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;
    int enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public boolean isFull() {
        return enrolledStudents >= capacity;
    }

    public void enrollStudent() {
        if (!isFull()) {
            enrolledStudents++;
        }
    }

    public void dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        }
    }
}

class Student {
    String studentID;
    String name;
    ArrayList<Course> registeredCourses = new ArrayList<>();

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public void registerCourse(Course course) {
        if (!course.isFull()) {
            registeredCourses.add(course);
            course.enrollStudent();
            System.out.println("Registered for course: " + course.title);
        } else {
            System.out.println("Course is full!");
        }
    }

    public void dropCourse(String courseCode) {
        for (Course course : registeredCourses) {
            if (course.courseCode.equals(courseCode)) {
                registeredCourses.remove(course);
                course.dropStudent();
                System.out.println("Dropped course: " + course.title);
                return;
            }
        }
        System.out.println("Course not found!");
    }

    public void listRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No registered courses.");
        } else {
            System.out.println("Registered Courses:");
            for (Course course : registeredCourses) {
                System.out.println(course.courseCode + ": " + course.title);
            }
        }
    }
}

public class StudentCourseRegistrationSystem {
    static HashMap<String, Course> courseDatabase = new HashMap<>();
    static HashMap<String, Student> studentDatabase = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Sample data
        courseDatabase.put("CS101", new Course("CS101", "Intro to Computer Science", "Basic concepts of computer science", 3, "MWF 10-11 AM"));
        courseDatabase.put("MATH123", new Course("MATH123", "Calculus I", "Differential calculus", 2, "TTh 1-2:30 PM"));

        studentDatabase.put("S001", new Student("S001", "Alice"));
        studentDatabase.put("S002", new Student("S002", "Bob"));

        while (true) {
            System.out.println("\n1. List Courses\n2. Register for Course\n3. Drop Course\n4. List Registered Courses\n5. Exit");
            int choice = getValidChoice();

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    listRegisteredCourses();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static int getValidChoice() {
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    static void listCourses() {
        for (Course course : courseDatabase.values()) {
            System.out.println("Course Code: " + course.courseCode);
            System.out.println("Title: " + course.title);
            System.out.println("Description: " + course.description);
            System.out.println("Capacity: " + course.capacity);
            System.out.println("Enrolled: " + course.enrolledStudents);
            System.out.println("Schedule: " + course.schedule);
            System.out.println();
        }
    }

    static void registerCourse() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Student student = studentDatabase.get(studentID);
        Course course = courseDatabase.get(courseCode);
        if (student != null && course != null) {
            student.registerCourse(course);
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    static void dropCourse() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Student student = studentDatabase.get(studentID);
        if (student != null) {
            student.dropCourse(courseCode);
        } else {
            System.out.println("Invalid student ID.");
        }
    }

    static void listRegisteredCourses() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = studentDatabase.get(studentID);
        if (student != null) {
            student.listRegisteredCourses();
        } else {
            System.out.println("Invalid student ID.");
        }
    }
}
