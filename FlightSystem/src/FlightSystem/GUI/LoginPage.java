package FlightSystem.GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import FlightSystem.objects.RegisteredUser;
import FlightSystem.objects.User;
import java.util.*;
import FlightSystem.data.UserSingleton;

public class LoginPage extends JFrame {
    private ArrayList<RegisteredUser> users; // list that only contain users with accounts
    public void setusers(ArrayList<RegisteredUser> users)
    {
        this.users = users;
    }
    public ArrayList<RegisteredUser> getUsers()
    {
        return users;
    }


    public LoginPage() {
        super("Login - Air Canada");
        //setupGUI();
        users = new ArrayList<RegisteredUser>();
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
        JButton registerButton = new JButton("Create Account");

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
                ArrayList<User> tmpListUsers= UserSingleton.getOnlyInstance().getUsers();
                for(User user: tmpListUsers)
                {
                    if(user.getRole().equals("member") || user.getRole().equals("admin") || user.getRole().equals("employee"))
                    {
                        users.add((RegisteredUser)user);
                    }
                }
                for (RegisteredUser user : users) {
                    System.out.println("Username: " + user.getUsername() + " Password: " + user.getPassword());}
                String password = new String(enteredPassword);


                if (isValidUser(enteredUsername, password)!=null) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Login successful!");
                    LoginPage.this.dispose();
                    HomePage homePage = new HomePage(isValidUser(enteredUsername, password));
                    homePage.setVisible(true);
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
            //this.dispose();
            //this.setVisible(false);
            
            RegisterPage registerPage = new RegisterPage();
            registerPage.setVisible(true);
        });
        //

        // Add the login panel to the JFrame
        add(loginPanel);
    }

    private RegisteredUser isValidUser(String username, String password) {
        for (RegisteredUser user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        //print out all the users
        // for (User user : users) {
        //     System.out.println("Username: " + user.getUsername() + " Password: " + user.getPassword());}

        return null;
    }

    // public static void main(String[] args)
    // {
    //     EventQueue.invokeLater(() -> { // event queue is threading related
    //         new LoginPage().setVisible(true); // makes GUI appear on screen 
    //     });
    // }
}
