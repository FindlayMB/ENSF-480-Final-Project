package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {

    public LoginPage() {
        setTitle("Login - Air Canada");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register If you don't have an account");

        // Create a panel for login components using GridBagLayout
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add username label and text field
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginPanel.add(usernameField, gbc);

        // Add password label and password field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginPanel.add(passwordField, gbc);

        // Add login button
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        loginPanel.add(loginButton, gbc);

        // Add register button
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        loginPanel.add(registerButton, gbc);
        

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check the username and password (you can replace this with your authentication logic)
                String enteredUsername = usernameField.getText();
                char[] enteredPassword = passwordField.getPassword();
                String password = new String(enteredPassword);

                if (isValidUser(enteredUsername, password)) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Login successful!");
                    // Add code to open the main application window or perform other actions upon successful login
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Invalid username or password. Please try again.");
                }

                // Clear the password field for security
                passwordField.setText("");
            }
        });
        registerButton.addActionListener((e) -> {
            System.out.println("Register button clicked");
            RegisterPage registerPage = new RegisterPage();
            registerPage.setVisible(true);
        });

        // Add the login panel to the JFrame
        add(loginPanel);
    }

    private boolean isValidUser(String username, String password) {
        // Add your authentication logic here
        // For simplicity, this example always returns true
        return true;
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> { // event queue is threading related
            new LoginPage().setVisible(true); // makes GUI appear on screen 
        });
    }
}
