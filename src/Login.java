import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Login {
    private String username;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;

    // Constructor
    public Login() {
        this.username = "";
        this.password = "";
        this.phoneNumber = "";
        this.firstName = "";
        this.lastName = "";
    }

    // Check username format
    public boolean checkUserName(String username) {
        // Username must contain underscore and be ≤5 characters
        return username.contains("_") && username.length() <= 5;
    }

    // Check password complexity
    public boolean checkPasswordComplexity(String password) {
        // Password must have: ≥8 chars, capital letter, number, special character
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    // Check phone number format (using regex from ChatGPT)
    public boolean checkCellPhoneNumber(String phoneNumber) {
        // South African number with international code: +27 followed by 9 digits
        String regex = "^\\+27\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    // Register user with validation
    public String registerUser(String username, String password, String phoneNumber,
                               String firstName, String lastName) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        // Store user data
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;

        return  "\nUsername successfully captured.\nPassword successfully captured.\nCell phone number successfully added.\nUser registered successfully!";
    }

    // Login verification
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

    // Return login status message
    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // Getters for testing
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getPhoneNumber() { return phoneNumber; }
}