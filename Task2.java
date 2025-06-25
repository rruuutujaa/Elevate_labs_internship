// Student.java
class Student {
    private int id;
    private String name;
    private double marks;
    
    // Default constructor
    public Student() {
    }
    
    // Parameterized constructor
    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
    
    // Constructor overloading - with just id and name
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.marks = 0.0;
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
    
    // Override toString for better display
    @Override
    public String toString() {
        return String.format("ID: %-5d | Name: %-20s | Marks: %.2f", id, name, marks);
    }
}

// StudentManager.java
import java.util.*;

class StudentManager {
    private ArrayList<Student> students;
    private static int nextId = 1; // Static variable for auto-incrementing IDs
    
    public StudentManager() {
        students = new ArrayList<>();
        // Add some sample data
        addStudent(new Student(nextId++, "John Doe", 85.5));
        addStudent(new Student(nextId++, "Jane Smith", 92.0));
        addStudent(new Student(nextId++, "Bob Johnson", 78.3));
    }
    
    // Add student
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully!");
    }
    
    // Add student with input validation
    public void addStudent(String name, double marks) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Name cannot be empty!");
            return;
        }
        if (marks < 0 || marks > 100) {
            System.out.println("Error: Marks should be between 0 and 100!");
            return;
        }
        
        Student student = new Student(nextId++, name.trim(), marks);
        addStudent(student);
    }
    
    // View all students
    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    STUDENT RECORDS");
        System.out.println("=".repeat(60));
        
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("=".repeat(60));
        System.out.println("Total Students: " + students.size());
    }
    
    // Find student by ID
    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
    
    // Update student
    public void updateStudent(int id, String newName, double newMarks) {
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student with ID " + id + " not found!");
            return;
        }
        
        if (newName != null && !newName.trim().isEmpty()) {
            student.setName(newName.trim());
        }
        
        if (newMarks >= 0 && newMarks <= 100) {
            student.setMarks(newMarks);
        } else {
            System.out.println("Invalid marks! Marks should be between 0 and 100.");
            return;
        }
        
        System.out.println("Student updated successfully!");
        System.out.println("Updated Record: " + student);
    }
    
    // Delete student
    public void deleteStudent(int id) {
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student with ID " + id + " not found!");
            return;
        }
        
        students.remove(student);
        System.out.println("Student deleted successfully!");
        System.out.println("Deleted Record: " + student);
    }
    
    // Search students by name (partial match)
    public void searchStudentsByName(String searchName) {
        if (searchName == null || searchName.trim().isEmpty()) {
            System.out.println("Please enter a valid search term!");
            return;
        }
        
        List<Student> matches = new ArrayList<>();
        String searchTerm = searchName.toLowerCase().trim();
        
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(searchTerm)) {
                matches.add(student);
            }
        }
        
        if (matches.isEmpty()) {
            System.out.println("No students found with name containing: " + searchName);
        } else {
            System.out.println("\nSearch Results for '" + searchName + "':");
            System.out.println("-".repeat(50));
            for (Student student : matches) {
                System.out.println(student);
            }
        }
    }
    
    // Sort students by marks (descending order)
    public void sortStudentsByMarks() {
        if (students.isEmpty()) {
            System.out.println("No students to sort!");
            return;
        }
        
        // Using Collections.sort with custom comparator
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.getMarks(), s1.getMarks()); // Descending order
            }
        });
        
        System.out.println("Students sorted by marks (highest to lowest):");
        viewAllStudents();
    }
    
    // Get statistics
    public void showStatistics() {
        if (students.isEmpty()) {
            System.out.println("No students found for statistics!");
            return;
        }
        
        double totalMarks = 0;
        double highestMarks = students.get(0).getMarks();
        double lowestMarks = students.get(0).getMarks();
        Student topStudent = students.get(0);
        
        for (Student student : students) {
            double marks = student.getMarks();
            totalMarks += marks;
            
            if (marks > highestMarks) {
                highestMarks = marks;
                topStudent = student;
            }
            
            if (marks < lowestMarks) {
                lowestMarks = marks;
            }
        }
        
        double averageMarks = totalMarks / students.size();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                STATISTICS");
        System.out.println("=".repeat(50));
        System.out.println("Total Students: " + students.size());
        System.out.println("Average Marks: " + String.format("%.2f", averageMarks));
        System.out.println("Highest Marks: " + String.format("%.2f", highestMarks));
        System.out.println("Lowest Marks: " + String.format("%.2f", lowestMarks));
        System.out.println("Top Student: " + topStudent.getName() + " (ID: " + topStudent.getId() + ")");
        System.out.println("=".repeat(50));
    }
}

// Main class - StudentRecordSystem.java
import java.util.Scanner;

public class StudentRecordSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManager manager = new StudentManager();
    
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("        WELCOME TO STUDENT RECORD MANAGEMENT SYSTEM");
        System.out.println("=".repeat(60));
        
        while (true) {
            displayMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    manager.viewAllStudents();
                    break;
                case 3:
                    updateExistingStudent();
                    break;
                case 4:
                    deleteExistingStudent();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    manager.sortStudentsByMarks();
                    break;
                case 7:
                    manager.showStatistics();
                    break;
                case 8:
                    System.out.println("\nThank you for using Student Record Management System!");
                    System.out.println("Goodbye! 👋");
                    System.exit(0);
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please enter a number between 1-8.");
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                    MAIN MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. 📝 Add New Student");
        System.out.println("2. 👀 View All Students");  
        System.out.println("3. ✏️  Update Student Record");
        System.out.println("4. 🗑️  Delete Student Record");
        System.out.println("5. 🔍 Search Student by Name");
        System.out.println("6. 📊 Sort Students by Marks");
        System.out.println("7. 📈 Show Statistics");
        System.out.println("8. 🚪 Exit");
        System.out.println("=".repeat(50));
        System.out.print("Enter your choice (1-8): ");
    }
    
    private static int getChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice;
        } catch (NumberFormatException e) {
            return -1; // Invalid choice
        }
    }
    
    private static void addNewStudent() {
        System.out.println("\n📝 ADD NEW STUDENT");
        System.out.println("-".repeat(30));
        
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter marks (0-100): ");
        try {
            double marks = Double.parseDouble(scanner.nextLine().trim());
            manager.addStudent(name, marks);
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid marks format! Please enter a valid number.");
        }
    }
    
    private static void updateExistingStudent() {
        System.out.println("\n✏️ UPDATE STUDENT RECORD");
        System.out.println("-".repeat(30));
        
        System.out.print("Enter student ID to update: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            Student student = manager.findStudentById(id);
            if (student == null) {
                System.out.println("❌ Student with ID " + id + " not found!");
                return;
            }
            
            System.out.println("Current Record: " + student);
            System.out.println("Enter new details (press Enter to keep current value):");
            
            System.out.print("New name (" + student.getName() + "): ");
            String newName = scanner.nextLine();
            if (newName.trim().isEmpty()) {
                newName = student.getName();
            }
            
            System.out.print("New marks (" + student.getMarks() + "): ");
            String marksInput = scanner.nextLine().trim();
            double newMarks = student.getMarks();
            
            if (!marksInput.isEmpty()) {
                try {
                    newMarks = Double.parseDouble(marksInput);
                } catch (NumberFormatException e) {
                    System.out.println("❌ Invalid marks format! Keeping current marks.");
                    newMarks = student.getMarks();
                }
            }
            
            manager.updateStudent(id, newName, newMarks);
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid ID format! Please enter a valid number.");
        }
    }
    
    private static void deleteExistingStudent() {
        System.out.println("\n🗑️ DELETE STUDENT RECORD");
        System.out.println("-".repeat(30));
        
        System.out.print("Enter student ID to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            Student student = manager.findStudentById(id);
            if (student == null) {
                System.out.println("❌ Student with ID " + id + " not found!");
                return;
            }
            
            System.out.println("Record to delete: " + student);
            System.out.print("Are you sure you want to delete this record? (y/N): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (confirmation.equals("y") || confirmation.equals("yes")) {
                manager.deleteStudent(id);
            } else {
                System.out.println("❌ Deletion cancelled.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid ID format! Please enter a valid number.");
        }
    }
    
    private static void searchStudent() {
        System.out.println("\n🔍 SEARCH STUDENT BY NAME");
        System.out.println("-".repeat(30));
        
        System.out.print("Enter name to search: ");
        String searchName = scanner.nextLine();
        manager.searchStudentsByName(searchName);
    }
}
