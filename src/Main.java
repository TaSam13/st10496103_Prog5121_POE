import java.util.Scanner;

public class Main  {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login loginSystem = new Login();

        System.out.println("=== REGISTRATION SYSTEM ===");

        // Get user details
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter username (must contain _ and be ≤5 chars): ");
        String username = scanner.nextLine();

        System.out.print("Enter password (≥8 chars, capital, number, special char): ");
        String password = scanner.nextLine();

        System.out.print("Enter phone number (+27 followed by 9 digits): ");
        String phone = scanner.nextLine();

        // Register user
        String registrationResult = loginSystem.registerUser(username, password, phone, firstName, lastName);
        System.out.println(registrationResult);

        // If registration successful, proceed to login
        if (registrationResult.equals("\nUsername successfully captured.\nPassword successfully captured.\nCell phone number successfully added.\nUser registered successfully!")) {
            System.out.println("\n=== LOGIN ===");

            System.out.print("Enter username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter password: ");
            String loginPassword = scanner.nextLine();

            // Attempt login
            String loginResult = loginSystem.returnLoginStatus(loginUsername, loginPassword);
            System.out.println(loginResult);
        }

        scanner.close();
    }
}