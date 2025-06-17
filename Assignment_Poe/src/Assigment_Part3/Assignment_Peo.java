/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Assigment_Part3;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_lab
 */
public class Assignment_Peo {

    public static String phone;
    public static String firstName;
    public static String lastName;
    public static String password;
    public static String  username;
    
    public static void main(String[] args) {
      // REGISTRATION SECTION
        firstName = JOptionPane.showInputDialog(null, "Enter your firstname:", "REGISTRATION", JOptionPane.QUESTION_MESSAGE);
        lastName = JOptionPane.showInputDialog(null, "Enter your lastname:", "REGISTRATION", JOptionPane.QUESTION_MESSAGE);
        username = JOptionPane.showInputDialog(null, "Enter your username:", "REGISTRATION", JOptionPane.QUESTION_MESSAGE);
        password = JOptionPane.showInputDialog(null, "Enter your password:", "REGISTRATION", JOptionPane.QUESTION_MESSAGE);
        phone = JOptionPane.showInputDialog(null, "Enter Phone Number (starting with +27):", "REGISTRATION", JOptionPane.QUESTION_MESSAGE);

        Login login = new Login();

        boolean isPhoneValid = login.checkCellPhoneNumber(phone);
        boolean isUsernameValid = login.checkUserName(username);
        boolean isPasswordValid = login.checkPasswordComplexity(password);

        if (isUsernameValid) {
            JOptionPane.showMessageDialog(null, "Username successfully captured.");
        } else {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted. It must contain an underscore and be no more than 5 characters.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (isPasswordValid) {
            JOptionPane.showMessageDialog(null, "Password successfully captured.");
        } else {
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted. It must be at least 8 characters and contain uppercase, lowercase, a number, and a special character.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (isPhoneValid) {
            JOptionPane.showMessageDialog(null, "Cell phone number successfully added.");
        } else {
            JOptionPane.showMessageDialog(null, "Phone number format is incorrect. Must start with +27 and only contain digits.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (!(isUsernameValid && isPasswordValid && isPhoneValid)) {
            JOptionPane.showMessageDialog(null, "Account registration was unsuccessful", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, "You have registered successfully!");

     
        boolean loggedIn = false;
        for (int i = 0; i < 3; i++) {
            String loginUser = JOptionPane.showInputDialog(null, "Enter your Username:", "LOGIN", JOptionPane.QUESTION_MESSAGE);
            String loginPass = JOptionPane.showInputDialog(null, "Enter your Password:", "LOGIN", JOptionPane.QUESTION_MESSAGE);

            if (loginUser == null || loginPass == null) {
                JOptionPane.showMessageDialog(null, "Login cancelled.");
                return;
            }

            if (loginUser.equals(username) && loginPass.equals(password)) {
                JOptionPane.showMessageDialog(null, "Welcome, " + lastName + " " + firstName + ", it is nice to see you again.");
                loggedIn = true;
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Username or Password. Attempts remaining: " + (2 - i));
            }
        }

        if (!loggedIn) {
            JOptionPane.showMessageDialog(null, "Login failed. Exiting application.");
            return;
        }


        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");
       

        Message message = new Message(
            "dummyID",  
            phone,
            "dummyMessage",    username,"ffd"   );
        message.sentMessage();
        
        }
    }
    



