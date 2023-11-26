package FlightSystem.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage extends JFrame {

    public RegisterPage() {
        setTitle("Register - Air Canada");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel nameLabel = new JLabel("Name:");
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordRepeatLabel = new JLabel("Repeat Password:");      
        JLabel dobLabel = new JLabel("Date of Birth (YYYY-MM-DD):");

        JLabel streetNameLabel = new JLabel("Street Name:");
        JLabel streetNumberLabel = new JLabel("Street Number:");
        JLabel postalCodeLabel = new JLabel("Postal Code:");
        JLabel cityLabel = new JLabel("City:");
        JLabel provinceLabel = new JLabel("Province/State:");
        JLabel countryLabel = new JLabel("Country:");

        JTextField nameField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField passwordRepeatField = new JPasswordField();
        //Check if the password is the same
        // if(passwordField != passwordRepeatField){
        //     JOptionPane.showMessageDialog(RegisterPage.this, "Password is not the same");
        // }
        JTextField emailField = new JTextField();
        JTextField dobField = new JTextField();

        JTextField streetNameField = new JTextField();
        JTextField streetNumberField = new JTextField();
        JTextField postalCodeField = new JTextField();
        JTextField cityField = new JTextField();
        JTextField provinceField = new JTextField();
        JTextField countryField = new JTextField();

        JButton registerButton = new JButton("Register");

        // Create a panel for register components using GridBagLayout
        JPanel registerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add name label and text field
        gbc.gridx = 0;
        gbc.gridy = 0;
        registerPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(nameField, gbc);

        // Add username label and text field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(usernameField, gbc);

        // Add password label and password field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(passwordField, gbc);
        
        // Add password repeat label and password repeat field
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(passwordRepeatLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(passwordRepeatField, gbc);

        // Add email label and text field
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(emailField, gbc);

        // Add dob label and text field
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(dobLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(dobField, gbc);

        // Add street name label and text field
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(streetNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(streetNameField, gbc);
        
        // Add street number label and text field
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(streetNumberLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(streetNumberField, gbc);

        // Add postal code label and text field
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(postalCodeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(postalCodeField, gbc);

        // Add city label and text field
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(cityLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(cityField, gbc);

        // Add province label and text field
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(provinceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(provinceField, gbc);

        // Add country label and text field
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(countryLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(countryField, gbc);

        // Add register button
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(registerButton, gbc);




        // Add action listener to the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the entered information
                String name = nameField.getText();
                String username = usernameField.getText();
                char[] enteredPassword = passwordField.getPassword();
                String password = new String(enteredPassword);
                String email = emailField.getText();
                String dob = dobField.getText();

                // Get address information
                String streetName = streetNameField.getText();
                String streetNumber = streetNumberField.getText();
                String postalCode = postalCodeField.getText();
                String city = cityField.getText();
                String province = provinceField.getText();
                String country = countryField.getText();
                // Check if the password is the same
                if(passwordField != passwordRepeatField){
                    JOptionPane.showMessageDialog(RegisterPage.this, "Password is not the same");
                }else{

                    // Perform registration logic (you can replace this with your registration logic)
                    if (registerUser(name, username, password, email, dob, streetName, streetNumber, postalCode, city, province, country)) {
                        JOptionPane.showMessageDialog(RegisterPage.this, "Registration successful!");
                        System.out.println("Register button clicked");
                        // You can add additional actions after successful registration
                    } else {
                        JOptionPane.showMessageDialog(RegisterPage.this, "Registration failed. Please try again.");
                    }

                    // Clear sensitive fields for security
                    passwordField.setText("");

                }

            }
        });

        // Add the register panel to the JFrame
        add(registerPanel);
    }

    private boolean registerUser(String name, String username, String password, String email, String dob,
                                 String streetName, String streetNumber, String postalCode, String city, String province, String country) {
        // Add your registration logic here
        // For simplicity, this example always returns true
        return true;
    }
}
