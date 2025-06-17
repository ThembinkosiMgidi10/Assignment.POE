package Assigment_Part3;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {
    
    private Login login = new Login();
    
    // Test cases for checkUserName
    @Test
    public void testCheckUserNameValid() {
        assertTrue(login.checkUserName("user_"));
        assertTrue(login.checkUserName("a_b"));
    }
    
    @Test
    public void testCheckUserNameInvalidNoUnderscore() {
        assertFalse(login.checkUserName("username"));
        assertFalse(login.checkUserName("user"));
    }
    
    @Test
    public void testCheckUserNameInvalidTooLong() {
        assertFalse(login.checkUserName("user_name"));
        assertFalse(login.checkUserName("long_username"));
    }
    
    // Test cases for checkPasswordComplexity
    @Test
    public void testCheckPasswordComplexityValid() {
        assertTrue(login.checkPasswordComplexity("Passw0rd!"));
        assertTrue(login.checkPasswordComplexity("Complex1#"));
    }
    
    @Test
    public void testCheckPasswordComplexityInvalidTooShort() {
        assertFalse(login.checkPasswordComplexity("Short1!"));
    }
    
    @Test
    public void testCheckPasswordComplexityInvalidNoCapital() {
        assertFalse(login.checkPasswordComplexity("password1!"));
    }
    
    @Test
    public void testCheckPasswordComplexityInvalidNoSmall() {
        assertFalse(login.checkPasswordComplexity("PASSWORD1!"));
    }
    
    @Test
    public void testCheckPasswordComplexityInvalidNoDigit() {
        assertFalse(login.checkPasswordComplexity("Password!"));
    }
    
    @Test
    public void testCheckPasswordComplexityInvalidNoSpecial() {
        assertFalse(login.checkPasswordComplexity("Password1"));
    }
    
    // Test cases for checkCellPhoneNumber
    @Test
    public void testCheckCellPhoneNumberValid() {
        assertTrue(login.checkCellPhoneNumber("+27612345678")); // 6
        assertTrue(login.checkCellPhoneNumber("+27712345678")); // 7
        assertTrue(login.checkCellPhoneNumber("+27812345678")); // 8
    }
    
    @Test
    public void testCheckCellPhoneNumberInvalidPrefix() {
        assertFalse(login.checkCellPhoneNumber("+25712345678"));
        assertFalse(login.checkCellPhoneNumber("27123456789"));
    }
    
    @Test
    public void testCheckCellPhoneNumberInvalidFourthDigit() {
        assertFalse(login.checkCellPhoneNumber("+27512345678")); // 5
        assertFalse(login.checkCellPhoneNumber("+27912345678")); // 9
    }
    
    @Test
    public void testCheckCellPhoneNumberInvalidLength() {
        assertFalse(login.checkCellPhoneNumber("+276123456789")); // too long
        assertFalse(login.checkCellPhoneNumber("+27612345")); // too short
    }
    
    // Test cases for registerUser
    @Test
    public void testRegisterUserSuccess() {
        String result = login.registerUser("user_", "Passw0rd!", "+27612345678");
        assertEquals("User is successfully registered", result);
    }
    
    @Test
    public void testRegisterUserFailure() {
        String result = login.registerUser("username", "password", "+25512345678");
        assertEquals("User registration failed!!!!!", result);
    }
    
    // Test cases for loginUser
    @Test
    public void testLoginUserSuccess() {
        assertTrue(login.loginUser("user_", "Passw0rd!"));
    }
    
    @Test
    public void testLoginUserFailure() {
        assertFalse(login.loginUser("username", "password"));
    }
    
    // Test cases for returnLoginStatus
    @Test
    public void testReturnLoginStatusSuccess() {
        String result = login.returnLoginStatus("user_", "Passw0rd!");
        assertEquals("A successful login", result);
    }
    
    @Test
    public void testReturnLoginStatusFailure() {
        String result = login.returnLoginStatus("username", "password");
        assertEquals("A failed login", result);
    }
}