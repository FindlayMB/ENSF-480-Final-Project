package FlightSystem.GUI;

import javax.swing.*;
import javax.xml.crypto.Data;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import FlightSystem.objects.user.RegisteredUser;
import FlightSystem.objects.user.User;
import FlightSystem.objects.user.UsersSingleton;

import java.util.*;

import FlightSystem.data.DatabaseSingleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class RegisterPage extends JFrame {
    private ArrayList<RegisteredUser> users;
    public void setusers(ArrayList<RegisteredUser> users)
    {
        this.users = users;
    }
    public ArrayList<RegisteredUser> getUsers()
    {
        return users;
    }

    public RegisterPage() {
        setTitle("Register - Air Canada");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel email = new JLabel("Email:");
        JLabel firstName = new JLabel("First Name:");
        JLabel lastName = new JLabel("Last Name:");
        JLabel username = new JLabel("Username:");
        JLabel password = new JLabel("Password:");
        JLabel passwordRepeat = new JLabel("Repeat Password:");


        JTextField emailField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField passwordRepeatField = new JPasswordField();

        JButton registerButton = new JButton("Register");

        // Create a panel for register components using GridBagLayout
        JPanel registerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add email label and text field
        gbc.gridx = 0;
        gbc.gridy = 0;
        registerPanel.add(email, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(emailField, gbc);

        // Add first name label and text field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(firstName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(firstNameField, gbc);

        // Add last name label and text field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(lastName, gbc);


        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(lastNameField, gbc);

        // Add username label and text field
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(username, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(usernameField, gbc);

        // Add password label and password field
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(password, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;   
        registerPanel.add(passwordField, gbc);

        // Add password repeat label and password repeat field
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(passwordRepeat, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(passwordRepeatField, gbc);

        // Add register button
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        registerPanel.add(registerButton, gbc);



        // Add action listener to the register button
        registerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // setusers(UserSingleton.getOnlyInstance().getUsers());

                String fname = firstNameField.getText();
                String lname = lastNameField.getText();
                String username = usernameField.getText();
                String email = emailField.getText();
                char[] enteredPassword = passwordField.getPassword();
                String password = new String(enteredPassword);
                char[] enteredPasswordRepeat = passwordRepeatField.getPassword();
                String passwordRepeat = new String(enteredPasswordRepeat);
                //Check username is same as
                
                //Check if email is valid{

                //Print passord and passwordRepeat
                //System.out.println(password);
                //System.out.println(passwordRepeat);

                if(!password.equals(passwordRepeat)){
                    JOptionPane.showMessageDialog(RegisterPage.this, "Password is not the same");
                    
                }

                //System.out.println(email);

                //Check passWords is repeated, username is not taken, email is not taken. if satisfied, add user to database
                if (registerUser(fname, lname, username, password, email)) {
                    DatabaseSingleton dbConnection = DatabaseSingleton.getInstance();
                    try {
                        dbConnection.addUserWithFields(username,password,fname, lname, email,LocalDate.now().toString(),"0","member");
                    } 
                    catch (Exception e1) {
                        System.out.println(e1);
                        System.out.println("Failed to add user to database!");
                    }

                    users = UsersSingleton.getInstance().getRegisteredUsersList();
                    
                    RegisteredUser newUser = new RegisteredUser(users.size()+1,username,password,fname,lname,email,LocalDate.now(), null); // FINS FUNCION GOES HERE
                    
                    // public RegisteredUser(User user, String username, String password,
                    // LocalDate signUpDate, String job, CreditCard creditCard, 
                    // ArrayList<Integer> onFlights) {
                    
                    UsersSingleton.getInstance().addRegisteredUser(newUser); // FINS FUNCTION GOES HERE
                    
                    
                    for (User user : users) {
                        if (user instanceof RegisteredUser) {
                            RegisteredUser registeredUser = (RegisteredUser) user;
                            System.out.println(registeredUser.getUsername() + " " + registeredUser.getPassword());
                        }
                    }
                    
                 //   System.out.println("Here is the new user"+ newUser.getUsername());






                    JOptionPane.showMessageDialog(RegisterPage.this, "Registration successful!");
                    //close the register page and open the home page
                    //RegisterPage.this.dispose();
                    //new HomePage();

                    RegisterPage.this.dispose();
                    HomePage h =new HomePage(newUser);
                    h.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(RegisterPage.this, "Registration failed!");
                }

            }});

        // Add the register panel to the JFrame
        add(registerPanel);
    }

    private boolean registerUser(String fname, String lname, String username, String password, String email) {
        for (RegisteredUser user : users) {
            System.out.println(user.getUsername());
            if(user.getUsername().equals(username)){
                System.out.println(user.getUsername());
                JOptionPane.showMessageDialog(RegisterPage.this, "Username is already taken");
                return false;
            }
        }
        for (User user : users) {
            if(user.getEmail().equals(email)){
                JOptionPane.showMessageDialog(RegisterPage.this, "Email is already taken");
                return false;
            }
        }

        return true;
    }
}
