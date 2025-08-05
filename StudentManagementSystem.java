import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks;
    }
}

public class StudentManagementSystem {

    private static ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void showMenu() {
        System.out.println("\n--- Student Record Management System ---");
        System.out.println("1. Add a new student");
        System.out.println("2. View all students");
        System.out.println("3. Update student details");
        System.out.println("4. Delete a student");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student marks: ");
        double marks = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Student newStudent = new Student(nextId++, name, marks);
        studentList.add(newStudent);
        System.out.println("Student added successfully with ID: " + newStudent.getId());
    }

    private static void viewAllStudents() {
        System.out.println("\n--- All Student Records ---");
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }

    private static void updateStudent() {
        System.out.println("\n--- Update Student Details ---");
        System.out.print("Enter the ID of the student to update: ");
        int idToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student studentToUpdate = findStudentById(idToUpdate);
        if (studentToUpdate == null) {
            System.out.println("Student with ID " + idToUpdate + " not found.");
            return;
        }

        System.out.println("Student found: " + studentToUpdate);
        System.out.print("Enter new name (press Enter to keep current: '" + studentToUpdate.getName() + "'): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            studentToUpdate.setName(newName);
        }

        System.out.print("Enter new marks (press Enter to keep current: " + studentToUpdate.getMarks() + "): ");
        String newMarksStr = scanner.nextLine();
        if (!newMarksStr.isEmpty()) {
            try {
                double newMarks = Double.parseDouble(newMarksStr);
                studentToUpdate.setMarks(newMarks);
            } catch (NumberFormatException e) {
                System.out.println("Invalid marks format. Marks not updated.");
            }
        }

        System.out.println("Student record updated successfully.");
    }

    private static void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        System.out.print("Enter the ID of the student to delete: ");
        int idToDelete = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student studentToDelete = findStudentById(idToDelete);
        if (studentToDelete == null) {
            System.out.println("Student with ID " + idToDelete + " not found.");
            return;
        }

        studentList.remove(studentToDelete);
        System.out.println("Student with ID " + idToDelete + " deleted successfully.");
    }

    private static Student findStudentById(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}