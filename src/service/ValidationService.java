package service;

import javax.swing.*;

public class ValidationService {

	 // Generic method to check if a field is filled
    public static boolean isFieldFilled(String fieldValue) {
        if (fieldValue == null || fieldValue.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    // Method to validate CIN (or ID)
    public static boolean validateNumericField(String field, String fieldName) {
        if (!isFieldFilled(field)) return false;
        if (!field.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, fieldName + " must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }


    // Method to validate a phone number (e.g., must be numeric)
    public static boolean validatePhoneNumber(String phoneNumber) {
    	if (!isFieldFilled(phoneNumber)) return false;
        if (!phoneNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Phone number must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // Method to validate a name (should only contain alphabetic characters)
    public static boolean validateName(String name) {
    	if (!isFieldFilled(name)) return false;
        if (!name.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Name must only contain alphabetic characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // Method to validate a surname (same rules as name)
    public static boolean validateSurname(String surname) {
    	if (!isFieldFilled(surname)) return false;
        if (!surname.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Surname must only contain alphabetic characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
