
package FlightSystem.GUI;

import javax.swing.*;

import FlightSystem.objects.plane.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import FlightSystem.objects.flight.FlightsSingleton;
import FlightSystem.objects.user.*;

public class AdminPage extends JFrame implements ActionListener {
    private RegisteredUser user;
    private JButton[][] adminButtons;

    public AdminPage(RegisteredUser user) {
        super("Admin Page");
        this.user = user;
        this.setSize(500, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setupGUI();
        EventQueue.invokeLater(() -> { // event queue is threading related
                this.setVisible(true); // makes GUI appear on screen 
            });
    }

    public void setupGUI() {
        

        // Create a 2D array of buttons
        adminButtons = new JButton[3][4];

        //Create buttons and add them to the array
        ArrayList<String> buttonList = new ArrayList<>(
                Arrays.asList("Browse List Flights", "Browse List Crews", "Browse List Aircrafts",
                        "Print Users", "Add Crew", "Add Aircraft", "Add Flight Destination",
                        "Add Flight Info", "Remove Crew", "Remove Aircraft",
                        "Remove Flight Destination", "Remove Flight Info"));
        // ArrayList<String> buttonList = new ArrayList<>(
        //         Arrays.asList("Browse List Flights", "Browse List Crews", "Browse List Aircrafts",
        //                 "Print Users", "Add Crew", "Add Aircraft", "Add Flight Info", "Remove Crew", "Remove Aircraft",
        //                 "Remove Flight Info"));
        for (int i = 0; i < adminButtons.length; i++) {
            for (int j = 0; j < adminButtons[i].length; j++) {
                adminButtons[i][j] = new JButton();
                adminButtons[i][j].setText(buttonList.get(i * 4 + j));
                adminButtons[i][j].setPreferredSize(new Dimension(150, 50));
                adminButtons[i][j].addActionListener(this);
            }
        }

        


        // Create a panel for the buttons using FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Add buttons to the panel
        for (int i = 0; i < adminButtons.length; i++) {
            for (int j = 0; j < adminButtons[i].length; j++) {
                buttonPanel.add(adminButtons[i][j]);
            }
        }

        JButton modifyFlightButton = new JButton("Modify Flight");
        modifyFlightButton.setPreferredSize(new Dimension(150, 50));
        modifyFlightButton.addActionListener(this);
        buttonPanel.add(modifyFlightButton);

        JButton managePromoButton = new JButton("Manage Promo");
        managePromoButton.setPreferredSize(new Dimension(150, 50));
        managePromoButton.addActionListener(this);
        buttonPanel.add(managePromoButton);

        this.add(buttonPanel, BorderLayout.CENTER);
        // buttonPanel.add(southPanel);
        // Add the go back button at the bottom
        JButton backButton = new JButton("Go Back");
        backButton.setPreferredSize(new Dimension(100, 50));
        // borderless and hover color
        backButton.setBorder(null);
        backButton.setContentAreaFilled(false);// Make the button transparent
        backButton.setForeground(Color.BLACK);
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setForeground(Color.BLUE); // Change text color on hover
                // add underline

            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setForeground(Color.BLACK); // Reset text color on exit
            }
        });
        this.add(backButton, BorderLayout.PAGE_END);

        // All title to the top
        JLabel headerLabel = new JLabel(
                "Welcome to the Administator Page " + user.getFirstName() + " " + user.getLastName());
        headerLabel.setFont(new Font("Serif", Font.BOLD, 25));

        this.add(headerLabel, BorderLayout.NORTH);
        // frame.add(backButton, BorderLayout.NORTH);

        // Add action listener to the back button
        backButton.addActionListener(e -> {
            // frame.dispose();
            // dispose();

            this.dispose();
            this.setVisible(false);
            HomePage homePage = new HomePage(user);
            homePage.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String buttonText = clickedButton.getText();
        if (buttonText == "Print Users") {
            // make a function call handle print users
            disableButton(clickedButton);
            printUsers();
            enableButton();

        } else if (buttonText == "Browse List Flights") {
            disableButton(clickedButton);
            System.out.println("Browse List Flights button clicked");
            // make a call to new page PerForm Admin
            PerformAdmin a = new PerformAdmin(this.user, "Browse List Flights");
            a.setVisible(true);
            enableButton();
        }

        else if (buttonText == "Browse List Crews") {
            // make a function call handle browse list crews
            disableButton(clickedButton);
            System.out.println("Browse List Flights button clicked");
            // make a call to new page PerForm Admin
            PerformAdmin a = new PerformAdmin(this.user, "Browse List Crews");
            a.setVisible(true);
            enableButton();
        } else if (buttonText == "Browse List Aircrafts") {
            // make a function call handle browse list aircrafts
            disableButton(clickedButton);
            printPlane();

            enableButton();
        } else if (buttonText == "Add Crew") {
            // dispose the admin page
            // make a function call handle add crew

            disableButton(clickedButton);
            System.out.println("Add Crew button clicked");
            // make a call to new page PerForm Admin
            PerformAdmin a = new PerformAdmin(this.user, "Add Crew");
            a.setVisible(true);
            enableButton();
            dispose();

        } else if (buttonText == "Add Aircraft") {
            disableButton(clickedButton);
            System.out.println("Add Aircraft button clicked");
            // make a call to new page PerForm Admin
            PerformAdmin a = new PerformAdmin(this.user, "Add Aircraft");
            a.setVisible(true);
            enableButton();
            dispose();

        } else if (buttonText == "Add Flight Destination") {
            //make a function call handle add flight destination
            disableButton(clickedButton);
            System.out.println("Add Flight Destination button clicked");
            // make a call to new page PerForm Admin
            PerformAdmin a = new PerformAdmin(this.user, "Add Flight Destination");
            a.setVisible(true);
            enableButton();
        } else if (buttonText == "Add Flight Info") {
            // make a function call handle add flight info
            disableButton(clickedButton);
            System.out.println("Add Flight Info button clicked");
            // make a call to new page PerForm Admin
            PerformAdmin a = new PerformAdmin(this.user, "Add Flight Info");
            a.setVisible(true);
            enableButton();

        } else if (buttonText == "Remove Crew") {
            // make a function call handle remove crew
            disableButton(clickedButton);
            System.out.println("Remove Crew button clicked");
            // make a call to new page PerForm Admin
            PerformAdmin a = new PerformAdmin(this.user, "Remove Crew");
            a.setVisible(true);
            enableButton();
        }

        else if (buttonText == "Remove Aircraft") {
            // make a function call handle remove crew
            disableButton(clickedButton);
            System.out.println("Remove Aircaft button clicked");
            // make a call to new page PerForm Admin
            PerformAdmin a = new PerformAdmin(this.user, "Remove Aircraft");
            a.setVisible(true);
            enableButton();
        } else if (buttonText == "Remove Flight Destination") {
            // make a function call handle remove crew
            disableButton(clickedButton);
            System.out.println("Remove Flight button clicked");
            // make a call to new page PerForm Admin
            PerformAdmin a = new PerformAdmin(this.user, "Remove Flight Destination");
            a.setVisible(true);
            enableButton();
            // dispose();
        } else if (buttonText == "Remove Flight Info") {
            // make a function call handle remove crew
            disableButton(clickedButton);
            System.out.println("Remove Flight Info button clicked");
            // make a call to new page PerForm Admin
            PerformAdmin a = new PerformAdmin(this.user, "Remove Flight Info");
            a.setVisible(true);
            enableButton();
        } else if (buttonText == "Modify Flight") {
            // make a function call handle remove crew
            disableButton(clickedButton);
            System.out.println("Modify Flight button clicked");
            // make a call to new page PerForm Admin
            PerformAdmin a = new PerformAdmin(this.user, "Modify Flight");
            a.setVisible(true);
            enableButton();
        } 
        else if(buttonText == "Manage Promo"){
            disableButton(clickedButton);
            System.out.println("Manage Promo button clicked");
            // make a call to new page PerForm Admin
            PerformAdmin a = new PerformAdmin(this.user, "Manage Promo");
            a.setVisible(true);
            enableButton();
        }

        else if (buttonText == "Go Back") {
            // make a function call handle remove crew
            disableButton(clickedButton);
            System.out.println("Go Back button clicked");
            // make a call to new page PerForm Admin
            HomePage homePage = new HomePage(user);
            homePage.setVisible(true);
            enableButton();
            dispose();
        } else {
            System.out.println("Error");
        }

    }

    // make a function that only one button is function when user click on it and
    // the rest disable and make it transparent
    public void disableButton(JButton notHideButton) {
        // disable all the buttons

        for (int i = 0; i < adminButtons.length; i++) {
            for (int j = 0; j < adminButtons[i].length; j++) {
                if (adminButtons[i][j] != notHideButton) {
                    adminButtons[i][j].setEnabled(false);
                    adminButtons[i][j].setOpaque(false);
                    adminButtons[i][j].setContentAreaFilled(false);
                    adminButtons[i][j].setBorderPainted(false);
                }
            }
        }
    }

    // make a function that enable all the buttons
    public void enableButton() {
        // enable all the buttons
        for (int i = 0; i < adminButtons.length; i++) {
            for (int j = 0; j < adminButtons[i].length; j++) {
                adminButtons[i][j].setEnabled(true);
                adminButtons[i][j].setOpaque(true);
                adminButtons[i][j].setContentAreaFilled(true);
                adminButtons[i][j].setBorderPainted(true);
            }
        }
    }

    public void printUsers() {
        ArrayList<RegisteredUser> users = UsersSingleton.getInstance().getRegisteredUsersList();

        // print out all the users
        String userlist = "";
        for (RegisteredUser user : users) {

            userlist = userlist + "Username: " + user.getUsername() + " FirstName " + user.getFirstName() + " LastName "
                    + user.getLastName() + "Email " + user.getEmail() + "\n";
        }

        JOptionPane.showMessageDialog(AdminPage.this, userlist, "Registered Users", JOptionPane.PLAIN_MESSAGE);

    }

    public void printPlane() {
        // print out all the planes
        ArrayList<Plane> planes = PlaneSingleton.getInstance().getPlaneList();
        String planeList = "";
        for (Plane plane : planes) {
            planeList = planeList + "Plane ID: " + plane.getID() + " Plane Type: " + plane.getType() + "\n";
        }
        JOptionPane.showMessageDialog(AdminPage.this, planeList, "Registered Planes", JOptionPane.PLAIN_MESSAGE);
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> {
    //         new AdminPage(null);
    //     });
    // }

}
