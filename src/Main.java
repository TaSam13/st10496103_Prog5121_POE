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

            // PART 2: If login successful, show messaging menu
            if (loginResult.contains("Welcome")) {
                showMessagingMenu(scanner, loginSystem);
            }
        }

        scanner.close();
    }

    // PART 2: Messaging Menu after successful login
    private static void showMessagingMenu(Scanner scanner, Login loginSystem) {
        System.out.println("\n=== WELCOME TO LUCKY MOBILE ===");

        // Ask user how many messages they want to send
        System.out.print("How many messages do you wish to send? ");
        int messageCount = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Main menu loop
        boolean running = true;
        int messagesProcessed = 0;

        while (running && messagesProcessed < messageCount) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Send Message");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Send message
                    if (messagesProcessed < messageCount) {
                        sendMessage(scanner, messagesProcessed + 1);
                        messagesProcessed++;
                    } else {
                        System.out.println("You have reached your message limit of " + messageCount);
                    }
                    break;

                case 2:
                    // Show recently sent messages (Coming Soon)
                    System.out.println("Coming Soon - This feature is under development");
                    break;

                case 3:
                    // Quit
                    System.out.println("Thank you for using Lucky Mobile. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }

        // If user sent all messages
        if (messagesProcessed >= messageCount) {
            System.out.println("\nYou have sent all " + messageCount + " messages. Thank you!");
        }
    }

    // ✅ PART 2: Send Message functionality
    private static void sendMessage(Scanner scanner, int messageNumber) {
        System.out.println("\n--- Message " + messageNumber + " ---");

        // Get recipient number
        System.out.print("Enter recipient phone number (+27 followed by 9 digits): ");
        String recipient = scanner.nextLine();

        // Get message content
        System.out.print("Enter your message (max 300 characters): ");
        String message = scanner.nextLine();

        // Validate message length
        if (message.length() > 300) {
            System.out.println("Error: Message exceeds 300 characters. Please shorten your message.");
            return;
        }

        // Show message action options
        System.out.println("\nChoose an action for this message:");
        System.out.println("1. Send Message");
        System.out.println("2. Discard Message");
        System.out.println("3. Store Message to send later");
        System.out.print("Select option: ");

        int action = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (action) {
            case 1:
                System.out.println("Message successfully sent.");
                break;
            case 2:
                System.out.println("Message discarded.");
                break;
            case 3:
                System.out.println("Message successfully stored.");
                break;
            default:
                System.out.println("Invalid option. Message discarded.");
        }

        // Create and use Message object
        Message messageObj = new Message(message, "CurrentUser"); // You might want to pass actual sender
        System.out.println("\nMessage Details:");
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + (messageObj.getContent().length() > 50 ?
                messageObj.getContent().substring(0, 50) + "..." : messageObj.getContent()));
        System.out.println("Sender: " + messageObj.getSender());
        System.out.println("Message ID: MSG" + System.currentTimeMillis());
    }
}