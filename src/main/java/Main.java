import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        Login loginSystem = new Login();

        // === REGISTRATION ===
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat App!", "QuickChat App", JOptionPane.INFORMATION_MESSAGE);

        String firstName = JOptionPane.showInputDialog(null, "Enter your first name:", "QuickChat App", JOptionPane.QUESTION_MESSAGE);
        String lastName = JOptionPane.showInputDialog(null, "Enter your last name:", "QuickChat App", JOptionPane.QUESTION_MESSAGE);
        String username = JOptionPane.showInputDialog(null, "Enter username (must contain _ and be ≤5 chars):", "QuickChat App", JOptionPane.QUESTION_MESSAGE);
        String password = JOptionPane.showInputDialog(null, "Enter password (≥8 chars, capital, number, special char):", "QuickChat App", JOptionPane.QUESTION_MESSAGE);
        String phone = JOptionPane.showInputDialog(null, "Enter phone number (+27 followed by 9 digits):", "QuickChat App", JOptionPane.QUESTION_MESSAGE);

        String registrationResult = loginSystem.registerUser(username, password, phone, firstName, lastName);
        JOptionPane.showMessageDialog(null, registrationResult, "QuickChat App", JOptionPane.INFORMATION_MESSAGE);

        if (registrationResult.contains("User registered successfully")) {
            // === LOGIN ===
            String loginUsername = JOptionPane.showInputDialog(null, "Enter username to log in:", "QuickChat App", JOptionPane.QUESTION_MESSAGE);
            String loginPassword = JOptionPane.showInputDialog(null, "Enter password:", "QuickChat App", JOptionPane.QUESTION_MESSAGE);

            String loginResult = loginSystem.returnLoginStatus(loginUsername, loginPassword);
            JOptionPane.showMessageDialog(null, loginResult, "QuickChat App", JOptionPane.INFORMATION_MESSAGE);

            if (loginResult.contains("Welcome")) {
                showMessagingMenu(loginSystem);
            }
        }
    }

    private static void showMessagingMenu(Login loginSystem) {
        int messageCount = Integer.parseInt(JOptionPane.showInputDialog(null, "How many messages do you wish to send?", "QuickChat App", JOptionPane.QUESTION_MESSAGE));
        int messagesProcessed = 0;

        while (messagesProcessed < messageCount) {
            String[] options = {"Send Message", "Show Sent Messages", "Quit"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Choose an option:",
                    "QuickChat App",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch (choice) {
                case 0:
                    sendMessage(messagesProcessed + 1);
                    messagesProcessed++;
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Coming soon - feature under development.", "QuickChat App", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 2:
                case JOptionPane.CLOSED_OPTION:
                    JOptionPane.showMessageDialog(null, "Thank you for using QuickChat App. Goodbye!", "QuickChat App", JOptionPane.INFORMATION_MESSAGE);
                    return;
            }
        }

        JOptionPane.showMessageDialog(null, "You have sent all " + messageCount + " messages.\nThank you!", "QuickChat App", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void sendMessage(int messageNumber) {
        String recipient = JOptionPane.showInputDialog(null, "Enter recipient phone number (+27 followed by 9 digits):", "QuickChat App", JOptionPane.QUESTION_MESSAGE);
        String content = JOptionPane.showInputDialog(null, "Enter your message (max 250 characters):", "QuickChat App", JOptionPane.QUESTION_MESSAGE); // CHANGED TO 250

        Message message = new Message(recipient, content, messageNumber);

        // UPDATED VALIDATION - CHECK FOR SPECIFIC SUCCESS MESSAGES
        String recipientValidation = message.validateRecipientNumber();
        if (!recipientValidation.equals("Cell phone number successfully captured.")) {
            JOptionPane.showMessageDialog(null, recipientValidation, "QuickChat App", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String lengthValidation = message.validateMessageLength();
        if (!lengthValidation.equals("Message ready to send.")) {
            JOptionPane.showMessageDialog(null, lengthValidation, "QuickChat App", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] actions = {"Send", "Discard", "Store"};
        int action = JOptionPane.showOptionDialog(null,
                "Choose what to do with this message:",
                "QuickChat App",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                actions,
                actions[0]);

        message.setStatus(action + 1); // REMOVED String.valueOf()

        switch (action) {
            case 0:
                MessageStorage.storeMessage(message);
                JOptionPane.showMessageDialog(null, "Message successfully sent.", "QuickChat App", JOptionPane.INFORMATION_MESSAGE); // UPDATED MESSAGE
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Press 0 to delete message.", "QuickChat App", JOptionPane.WARNING_MESSAGE); // UPDATED MESSAGE
                break;
            case 2:
                MessageStorage.storeMessage(message);
                JOptionPane.showMessageDialog(null, "Message successfully stored.", "QuickChat App", JOptionPane.INFORMATION_MESSAGE); // UPDATED MESSAGE
                break;
            default:
                JOptionPane.showMessageDialog(null, "No action selected.", "QuickChat App", JOptionPane.WARNING_MESSAGE);
        }

        // Show message details popup
        JOptionPane.showMessageDialog(null,
                "=== Message Details ===\n" + message.getMessageDetails(),
                "QuickChat App",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
