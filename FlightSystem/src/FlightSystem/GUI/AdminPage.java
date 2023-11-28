


package FlightSystem.GUI;

import javax.swing.*;

import FlightSystem.objects.user.User;
import FlightSystem.objects.user.UserSingleton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class AdminPage extends JFrame implements ActionListener {
    private User user;
    private JButton[][] adminButtons;

    public AdminPage(User user) {
        this.user = user;
        setupGUI();
    }

    public void setupGUI() {
        JFrame frame = new JFrame("Admin Page");
        frame.setSize(500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Create a 2D array of buttons
        adminButtons = new JButton[3][4];

        // Create buttons and add them to the array
        ArrayList<String> buttonList = new ArrayList<>(Arrays.asList("Browse List Flights", "Browse List Crews", "Browse List Aircrafts",
            "Print Users", "Add Crew", "Add Aircraft", "Add Flight Destination",
            "Add Flight Info", "Remove Crew", "Remove Aircraft",
            "Remove Flight Destination", "Remove Flight Info"
        ));
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


        // Add the button panel to the frame
        //frame.add(buttonPanel, BorderLayout.CENTER);
        JPanel southPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        southPanel.setVisible(false);
        JLabel label1 = new JLabel("Text 1:");
        JLabel label2 = new JLabel("Text 2:");
        JLabel label3 = new JLabel("Text 3:");
        JLabel label4 = new JLabel("Text 4:");
        JLabel label5 = new JLabel("Text 5:");

        // TextFields for the second column
        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();
        JTextField textField3 = new JTextField();
        JTextField textField4 = new JTextField();
        JTextField textField5 = new JTextField();
        

        // Add labels and text fields to the panel
        southPanel.add(label1);
        southPanel.add(textField1);
        southPanel.add(label2);
        southPanel.add(textField2);
        southPanel.add(label3);
        southPanel.add(textField3);
        southPanel.add(label4);
        southPanel.add(textField4);
        southPanel.add(label5);
        southPanel.add(textField5);




        // Add the panel to the frame
        // frame.add(southPanel, BorderLayout.SOUTH);
        buttonPanel.add(southPanel);
        frame.add(buttonPanel, BorderLayout.CENTER);
        //Add the go back button at the bottom
        JButton backButton = new JButton("Go Back");
        backButton.setPreferredSize(new Dimension(100, 50));
        //borderless and hover color
        backButton.setBorder(null);
        backButton.setContentAreaFilled(false);//Make the button transparent
        backButton.setForeground(Color.BLACK);
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    backButton.setForeground(Color.BLUE); // Change text color on hover
                    //add underline
                
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setForeground(Color.BLACK); // Reset text color on exit
            }
        });
        frame.add(backButton, BorderLayout.PAGE_END);
        // Create text fields
        // 2 coloms and 5 rows with first colum dislplay the label text1, text2, text3, text4, text 5 and second colum display the text field

       


        //All title to the top
        JLabel headerLabel = new JLabel("Welcome to the Administator Page "+user.getFirstName() + " " + user.getLastName());
        headerLabel.setFont(new Font("Serif", Font.BOLD, 25));
        
        frame.add(headerLabel, BorderLayout.NORTH);
        //Add action listener to the back button
        backButton.addActionListener(e -> {
            frame.dispose();
            HomePage homePage = new HomePage(user);
            homePage.setVisible(true);});
        //     //add action listener to the buttons
        //hide the text fields and its label




  





        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        JButton clickedButton = (JButton) e.getSource();
        String buttonText = clickedButton.getText();
        if(buttonText=="Print Users"){
            //make a function call handle print users
            disableButton(clickedButton);
            printUsers();
            enableButton();

        }
        else if(buttonText=="Browse List Flights"){
            disableButton(clickedButton);
            //make a function call handle browse list flights
            //browseListFlights();
            enableButton();
        }
        
        // else if(buttonText=="Browse List Crews"){
        //     //make a function call handle browse list crews
        //     browseListCrews();
        // }
        // else if(buttonText=="Browse List Aircrafts"){
        //     //make a function call handle browse list aircrafts
        //     browseListAircrafts();
        // }
        // else if(buttonText=="Add Crew"){
        //     //make a function call handle add crew
        //     addCrew();
        // }
        // else if(buttonText=="Add Aircraft"){
        //     //make a function call handle add aircraft
        //     addAircraft();
        // }
        // else if(buttonText=="Add Flight Destination"){
        //     //make a function call handle add flight destination
        //     addFlightDestination();
        // }
        // else if(buttonText=="Add Flight Info"){
        //     //make a function call handle add flight info
        //     addFlightInfo();
        // }
        // else if(buttonText=="Remove Crew"){
        //     //make a function call handle remove crew
        //     removeCrew();
        // }
        // else if(buttonText=="Remove Aircraft"){
        //     //make a function call handle remove aircraft
        //     removeAircraft();
        // }
        // else if(buttonText=="Remove Flight Destination"){
        //     //make a function call handle remove flight destination
        //     removeFlightDestination();
        // }
        // else if(buttonText=="Remove Flight Info"){
        //     //make a function call handle remove flight info
        //     removeFlightInfo();
        // }

    }
    //make a function that only one button is function when user click on it and the rest disable and make it transparent
    public void disableButton(JButton notHideButton){
        //disable all the buttons

        for (int i = 0; i < adminButtons.length; i++) {
            for (int j = 0; j < adminButtons[i].length; j++) {
                if(adminButtons[i][j]!=notHideButton){
                    adminButtons[i][j].setEnabled(false);
                    adminButtons[i][j].setOpaque(false);
                    adminButtons[i][j].setContentAreaFilled(false);
                    adminButtons[i][j].setBorderPainted(false);
                }
            }
        }
    }
    //make a function that enable all the buttons
    public void enableButton(){
        //enable all the buttons
        for (int i = 0; i < adminButtons.length; i++) {
            for (int j = 0; j < adminButtons[i].length; j++) {
                adminButtons[i][j].setEnabled(true);
                adminButtons[i][j].setOpaque(true);
                adminButtons[i][j].setContentAreaFilled(true);
                adminButtons[i][j].setBorderPainted(true);
            }
        }
    }
    public void printUsers(){
        ArrayList<User> users = UserSingleton.getOnlyInstance().getUsers();

        //print out all the users
        String userlist="";
        for (User user : users) {  

            userlist =userlist + "Username: " + user.getUsername() + " FirstName " + user.getFirstName() + " LastName " + user.getLastName() + "Email " + user.getEmail()+"\n";}
        
        JOptionPane.showMessageDialog(AdminPage.this, userlist,"Registered Users",JOptionPane.PLAIN_MESSAGE);



    }

    // public void browseListFlights({
    //     //Disable Text4, Text5 and its label
    //     //Change Text1 to Destination
    //     //Change Text2 to Departure Date
    //     //Change Text3 to Departure Time
    // })

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminPage(null);
        });
    }
}

