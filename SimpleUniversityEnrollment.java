import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class EnrollmentException extends Exception {
    public EnrollmentException(String message) {
        super(message);
    }
}

class Course {
    private String name;
    private String prerequisite;
    private int enrolled;
    private int capacity;

    public Course(String name, String prerequisite, int capacity) {
        this.name = name;
        this.prerequisite = prerequisite;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    public void enroll(Map<String, Boolean> completedCourses) throws EnrollmentException {
        if (enrolled >= capacity) {
            throw new EnrollmentException("Course is full. Maximum enrollment reached.");
        }
        if (prerequisite != null && !completedCourses.getOrDefault(prerequisite, false)) {
            throw new EnrollmentException("Prerequisite not met. Complete " + prerequisite + " first.");
        }
        enrolled++;
        System.out.println("Enrollment successful in " + name);
    }
}

public class SimpleUniversityEnrollment {
    public static void main(String[] args) {
        Map<String, Course> courses = new HashMap<>();
        courses.put("Core Java", new Course("Core Java", null, 3));
        courses.put("Advanced Java", new Course("Advanced Java", "Core Java", 2));

        Map<String, Boolean> completedCourses = new HashMap<>();
        completedCourses.put("Core Java", true);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enroll in Course (or 'exit'): ");
            String courseName = scanner.nextLine();
            if (courseName.equalsIgnoreCase("exit")) break;

            Course course = courses.get(courseName);
            if (course == null) {
                System.out.println("Error: Course not found.");
                continue;
            }

            try {
                course.enroll(completedCourses);
            } catch (EnrollmentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
