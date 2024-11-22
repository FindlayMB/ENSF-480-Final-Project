package FlightSystem.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import FlightSystem.objects.user.RegisteredUser;
import FlightSystem.objects.user.User;


public class ProfilePage {
    private RegisteredUser user;
    public ProfilePage(RegisteredUser User) {
        this.user = User;
        setupGUI();
    }
    public void setupGUI(){
        JFrame frame = new JFrame("Profile");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Username: " + user.getUsername());
        JLabel emailLabel = new JLabel("Email: " + user.getEmail());
        JLabel firstNameLabel = new JLabel("First Name: " + user.getFirstName());
        JLabel lastNameLabel = new JLabel("Last Name: " + user.getLastName());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                

                frame.dispose();
                HomePage homePage = new HomePage(user);
                homePage.setVisible(true);

            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(emailLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(firstNameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lastNameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(backButton, gbc);

        frame.add(panel);



    }




    
    
}
