package FlightSystem.GUI;
import javax.swing.*;

import FlightSystem.objects.Flight;
import FlightSystem.objects.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import FlightSystem.data.UserSingleton;
import FlightSystem.objects.User;
import FlightSystem.data.FlightSingleton;

public class PerformAdmin extends JFrame {
    private User user;
    private String name;
    public PerformAdmin(User user,String name){
        this.user = user;
        System.out.println("hello");
        this.name=name;
        setupUI(this.name);

    }
    public void setupUI(String name) {
        setTitle("Perform Admin");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    
        JLabel text1 = new JLabel("Text1:");
        JLabel text2 = new JLabel("Text2:");
        JLabel text3 = new JLabel("Text3:");
        JLabel text4 = new JLabel("Text4:");
    
        JTextField text1Field = new JTextField(20);
        JTextField text2Field = new JTextField(20);
        JTextField text3Field = new JTextField(20);
        JTextField text4Field = new JTextField(20);
    
        JButton submitButton = new JButton("Submit");
        //Add goback button
        JButton goBackButton = new JButton("Go Back");
    
        JPanel registerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;  // Align components to the left
    
        // Add components with consistent GridBagConstraints
        addComponent(registerPanel, text1, gbc, 0, 0);
        addComponent(registerPanel, text2, gbc, 0, 1);
        addComponent(registerPanel, text3, gbc, 0, 2);
        addComponent(registerPanel, text4, gbc, 0, 3);
    
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addComponent(registerPanel, text1Field, gbc, 1, 0);
        addComponent(registerPanel, text2Field, gbc, 1, 1);
        addComponent(registerPanel, text3Field, gbc, 1, 2);
        addComponent(registerPanel, text4Field, gbc, 1, 3);
    
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;  // Span across both columns
        addComponent(registerPanel, submitButton, gbc, 0, 4);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;  // Span across both columns
        addComponent(registerPanel, goBackButton, gbc, 0, 5);


    
        add(registerPanel);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminPage adminPage = new AdminPage(user);
                adminPage.setVisible(true);
            }
        });

        if(name =="Browse List Flights"){
            //change title
            setTitle("Browse List Flights");
            //hide text4 and text4Field
            text4.setVisible(false);
            text4Field.setVisible(false);
            //change text1 to "Origin"
            text1.setText("Origin");
            //change text2 to "Destination"
            text2.setText("Destination");
            //change text3 to "Depature Date"
            text3.setText("Depature Date");
            //change submitButton to "Search"   
            submitButton.setText("Search");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String origin = text1Field.getText();
                    String destination = text2Field.getText();
                    String departureDate = text3Field.getText();
                    ArrayList<Flight> flightList = FlightSingleton.getOnlyInstance().getFlights(destination);
                    String message="";
                    for (Flight flight : flightList) {
                        System.out.println(flight.getDestination().getCity()+flight.getOrigin().getCode()+flight.getDepartureDate().toString());
                        if(destination.toUpperCase()==flight.getDestination().getCode().toUpperCase() && origin.toUpperCase()==flight.getOrigin().getCode().toUpperCase() && departureDate==flight.getDepartureDate().toString()){
                            message+="From: "+origin+"To: "+destination+"Departure Time: "+departureDate.toString()+"Arrival time:"+flight.getArrivalDate().toString()+"\n";
                        }
                    }
                    JOptionPane.showMessageDialog(PerformAdmin.this,message, "Flight List", JOptionPane.INFORMATION_MESSAGE);
                }
            });}
        else if(name =="Browse List Crews"){
            setTitle("Browse List Crews");
            //hide text4 and text4Field, text3 and text3Field, text2 and text2Field

            text4.setVisible(false);
            text4Field.setVisible(false);
            //change text1 to "Flight ID"
            text3.setVisible(false);
            text3Field.setVisible(false);
            text2.setVisible(false);
            text2Field.setVisible(false);
   
            text1.setText("Flight ID");
            // text2.setText("Destination");
            submitButton.setText("Search");
            // submitButton.addActionListener( new ActionEvent {
            //     String flightID = text1Field.getText();
            //     String destination = text2Field.getText();
            //     ArrayList<Flight> flightList = FlightSingleton.getOnlyInstance().getFlights(destination);
            //     String message="";
            //     for (Flight flight : flightList) {
            //         if(flight.getID()==flightID){
            //             for (Crew crew : flight.getCrew()) {
            //                 message+=crew.getFirstName()+" "+crew.getLastName()+", ";
            //             }
            //             message+="\n";
            //         }
                    
            //     }
            //     JOptionPane.showMessageDialog(PerformAdmin.this,message, "Flight List", JOptionPane.INFORMATION_MESSAGE);
            // };
            // );
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String flightID = text1Field.getText();
                    // String destination = text2Field.getText();
                    ArrayList<Flight> flightList = FlightSingleton.getOnlyInstance().getFlights();
                    String message="";
                    for (Flight flight : flightList) {
                        if(flight.getID()==Integer.parseInt(flightID)){
                            for (User crew : flight.getCrew()) {
                                message+=crew.getFirstName()+" "+crew.getLastName()+", ";
                            }
                            message+="\n";
                        }
                        
                    }
                    JOptionPane.showMessageDialog(PerformAdmin.this,message, "Flight List", JOptionPane.INFORMATION_MESSAGE);
                }
            });
        }
            


        }
    
    // Helper method to add components with GridBagConstraints
    private void addComponent(JPanel panel, Component component, GridBagConstraints gbc, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(component, gbc);
    }
    


    
}
