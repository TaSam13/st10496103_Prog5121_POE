import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {

    private Login login;

    @Before
    public void setUp() {
        login = new Login();
    }

    // ---------- Username ----------
    @Test
    public void testValidUserName() {
        assertTrue("Valid username should return true", login.checkUserName("Sam_"));
    }

    @Test
    public void testInvalidUserName_NoUnderscore() {
        assertFalse("Username without underscore should return false", login.checkUserName("Sam1"));
    }

    @Test
    public void testInvalidUserName_TooLong() {
        assertFalse("Username longer than 5 chars should return false", login.checkUserName("Sam_12"));
    }

    // ---------- Password ----------
    @Test
    public void testValidPassword() {
        assertTrue("Valid password should return true", login.checkPasswordComplexity("Passw0rd!"));
    }

    @Test
    public void testInvalidPassword_NoCapital() {
        assertFalse("No capital letter", login.checkPasswordComplexity("password1!"));
    }

    @Test
    public void testInvalidPassword_NoNumber() {
        assertFalse("No number", login.checkPasswordComplexity("Password!"));
    }

    @Test
    public void testInvalidPassword_NoSpecialChar() {
        assertFalse("No special char", login.checkPasswordComplexity("Password1"));
    }

    @Test
    public void testInvalidPassword_TooShort() {
        assertFalse("Too short", login.checkPasswordComplexity("P1!a"));
    }

    // ---------- Phone Number ----------
    @Test
    public void testValidPhoneNumber() {
        assertTrue("Valid SA phone number should return true", login.checkCellPhoneNumber("+27831234567"));
    }

    @Test
    public void testInvalidPhoneNumber_MissingPlus() {
        assertFalse("Missing + sign", login.checkCellPhoneNumber("27831234567"));
    }

    @Test
    public void testInvalidPhoneNumber_WrongLength() {
        assertFalse("Too short", login.checkCellPhoneNumber("+27831"));
    }

    // ---------- Registration ----------
    @Test
    public void testRegisterUser_Success() {
        String result = login.registerUser("Sam_", "Passw0rd!", "+27831234567", "Sam", "Damas");
        assertTrue("Should contain success message", result.contains("User registered successfully!"));
    }

    @Test
    public void testRegisterUser_InvalidUsername() {
        String result = login.registerUser("Sam123", "Passw0rd!", "+27831234567", "Sam", "Damas");
        assertTrue("Should contain username error", result.contains("Username is not correctly formatted"));
    }

    @Test
    public void testRegisterUser_InvalidPassword() {
        String result = login.registerUser("Sam_", "pass", "+27831234567", "Sam", "Damas");
        assertTrue("Should contain password error", result.contains("Password is not correctly formatted"));
    }

    @Test
    public void testRegisterUser_InvalidPhoneNumber() {
        String result = login.registerUser("Sam_", "Passw0rd!", "0831234567", "Sam", "Damas");
        assertTrue("Should contain phone error", result.contains("Cell phone number incorrectly formatted"));
    }

    // ---------- Login ----------
    @Test
    public void testLoginUser_Successful() {
        login.registerUser("Sam_", "Passw0rd!", "+27831234567", "Sam", "Damas");
        assertTrue("Correct credentials should return true", login.loginUser("Sam_", "Passw0rd!"));
    }

    @Test
    public void testLoginUser_Fail() {
        login.registerUser("Sam_", "Passw0rd!", "+27831234567", "Sam", "Damas");
        assertFalse("Wrong password should return false", login.loginUser("Sam_", "WrongPass"));
    }

    // ---------- Login Status ----------
    @Test
    public void testReturnLoginStatus_Successful() {
        login.registerUser("Sam_", "Passw0rd!", "+27831234567", "Sam", "Damas");
        String result = login.returnLoginStatus("Sam_", "Passw0rd!");
        assertTrue("Successful login should return welcome message", result.contains("Welcome Sam Damas"));
    }

    @Test
    public void testReturnLoginStatus_Fail() {
        login.registerUser("Sam_", "Passw0rd!", "+27831234567", "Sam", "Damas");
        String result = login.returnLoginStatus("Sam_", "WrongPass");
        assertEquals("Username or password incorrect, please try again.", result);
    }
}
