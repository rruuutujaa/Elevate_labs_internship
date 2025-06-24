import java.util.Scanner;

public class ConsoleCalculator {
    private Scanner scanner;
    
    public ConsoleCalculator() {
        scanner = new Scanner(System.in);
    }
    
    // Method for addition
    public double add(double num1, double num2) {
        return num1 + num2;
    }
    
    // Method for subtraction
    public double subtract(double num1, double num2) {
        return num1 - num2;
    }
    
    // Method for multiplication
    public double multiply(double num1, double num2) {
        return num1 * num2;
    }
    
    // Method for division with divide-by-zero handling
    public double divide(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Error: Division by zero is not allowed!");
            return Double.NaN; // Return NaN to indicate invalid result
        }
        return num1 / num2;
    }
    
    // Method to get user input safely
    public double getUserInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input! Please enter a valid number.");
            System.out.print(prompt);
            scanner.next(); // Clear invalid input
        }
        return scanner.nextDouble();
    }
    
    // Method to display menu
    public void displayMenu() {
        System.out.println("\n=== Java Console Calculator ===");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.println("5. Exit");
        System.out.print("Choose an operation (1-5): ");
    }
    
    // Main calculator loop
    public void run() {
        System.out.println("Welcome to Java Console Calculator!");
        System.out.println("This calculator supports basic arithmetic operations.");
        
        boolean continueCalculating = true;
        
        while (continueCalculating) {
            displayMenu();
            
            int choice = 0;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Invalid choice! Please select 1-5.");
                scanner.next(); // Clear invalid input
                continue;
            }
            
            if (choice == 5) {
                continueCalculating = false;
                System.out.println("Thank you for using Java Console Calculator!");
                break;
            }
            
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice! Please select 1-5.");
                continue;
            }
            
            // Get numbers from user
            double num1 = getUserInput("Enter first number: ");
            double num2 = getUserInput("Enter second number: ");
            double result = 0;
            String operation = "";
            
            // Perform calculation based on choice
            switch (choice) {
                case 1:
                    result = add(num1, num2);
                    operation = "+";
                    break;
                case 2:
                    result = subtract(num1, num2);
                    operation = "-";
                    break;
                case 3:
                    result = multiply(num1, num2);
                    operation = "*";
                    break;
                case 4:
                    result = divide(num1, num2);
                    operation = "/";
                    if (Double.isNaN(result)) {
                        continue; // Skip displaying result for division by zero
                    }
                    break;
            }
            
            // Display result
            System.out.printf("Result: %.2f %s %.2f = %.2f%n", num1, operation, num2, result);
            
            // Ask if user wants to continue
            System.out.print("\nDo you want to perform another calculation? (y/n): ");
            String continueChoice = scanner.next().toLowerCase();
            
            if (!continueChoice.equals("y") && !continueChoice.equals("yes")) {
                continueCalculating = false;
                System.out.println("Thank you for using Java Console Calculator!");
            }
        }
        
        scanner.close();
    }
    
    // Main method - entry point of the program
    public static void main(String[] args) {
        ConsoleCalculator calculator = new ConsoleCalculator();
        calculator.run();
    }
}
