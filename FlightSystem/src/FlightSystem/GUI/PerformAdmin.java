package FlightSystem.GUI;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;

import FlightSystem.data.DatabaseSingleton;
import FlightSystem.objects.airport.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import FlightSystem.objects.user.*;
import FlightSystem.objects.flight.*;
import FlightSystem.objects.plane.*;

public class PerformAdmin extends JFrame {
    private RegisteredUser user;
    private String name;
    ArrayList<Flight> flightList = FlightsSingleton.getInstance().getFlightList();

    public PerformAdmin(RegisteredUser user, String name) {
        // dispose the previous frame
        dispose();
        this.user = user;
        System.out.println("hello");
        this.name = name;
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
        JLabel text5 = new JLabel("Text5:");
        JLabel text6 = new JLabel("Text6:");
        JLabel text7 = new JLabel("Text7:");
        JLabel text8 = new JLabel("Text8:");
        JLabel text9 = new JLabel("Text9:");
        JLabel text10 = new JLabel("Text10:");

        JTextField text1Field = new JTextField(20);
        JTextField text2Field = new JTextField(20);
        JTextField text3Field = new JTextField(20);
        JTextField text4Field = new JTextField(20);
        JTextField text5Field = new JTextField(20);
        JTextField text6Field = new JTextField(20);
        JTextField text7Field = new JTextField(20);
        JTextField text8Field = new JTextField(20);
        JTextField text9Field = new JTextField(20);
        JTextField text10Field = new JTextField(20);

        JButton submitButton = new JButton("Submit");
        // Add goback button
        JButton goBackButton = new JButton("Go Back");

        JPanel registerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // gbc.insets = new Insets(5, 5, 5, 5);
        // gbc.anchor = GridBagConstraints.WEST; // Align components to the left
        // gbc.fill = GridBagConstraints.HORIZONTAL; // Allow horizontal resizing
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow horizontal resizing

        // Add components with consistent GridBagConstraints
        addComponent(registerPanel, text1, gbc, 0, 0);
        addComponent(registerPanel, text2, gbc, 0, 1);
        addComponent(registerPanel, text3, gbc, 0, 2);
        addComponent(registerPanel, text4, gbc, 0, 3);
        addComponent(registerPanel, text5, gbc, 0, 4);
        addComponent(registerPanel, text6, gbc, 0, 5);
        addComponent(registerPanel, text7, gbc, 0, 6);
        addComponent(registerPanel, text8, gbc, 0, 7);
        addComponent(registerPanel, text9, gbc, 0, 8);
        addComponent(registerPanel, text10, gbc, 0, 9);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        addComponent(registerPanel, text1Field, gbc, 1, 0);
        addComponent(registerPanel, text2Field, gbc, 1, 1);
        addComponent(registerPanel, text3Field, gbc, 1, 2);
        addComponent(registerPanel, text4Field, gbc, 1, 3);
        addComponent(registerPanel, text5Field, gbc, 1, 4);
        addComponent(registerPanel, text6Field, gbc, 1, 5);
        addComponent(registerPanel, text7Field, gbc, 1, 6);
        addComponent(registerPanel, text8Field, gbc, 1, 7);
        addComponent(registerPanel, text9Field, gbc, 1, 8);
        addComponent(registerPanel, text10Field, gbc, 1, 9);
        // hide text5 to 10 and text5Field to 10Field
        text5.setVisible(false);
        text5Field.setVisible(false);
        text6.setVisible(false);
        text6Field.setVisible(false);
        text7.setVisible(false);
        text7Field.setVisible(false);
        text8.setVisible(false);
        text8Field.setVisible(false);
        text9.setVisible(false);
        text9Field.setVisible(false);
        text10.setVisible(false);
        text10Field.setVisible(false);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Span across both columns
        addComponent(registerPanel, submitButton, gbc, 0, 10);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Span across both columns
        addComponent(registerPanel, goBackButton, gbc, 0, 11);

        add(registerPanel);
        // goBackButton.addActionListener(new ActionListener() {
        // //dispose the current frame and open the previous frame
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // dispose();
        // AdminPage adminPage = new AdminPage(user);
        // adminPage.setVisible(true);
        // }
        // });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and make visible the new AdminPage frame first
                // AdminPage adminPage = new AdminPage(user);
                // adminPage.setVisible(true);

                // Then dispose of the current PerformAdmin frame
                dispose();
            }
        });

        if (name == "Browse List Flights") {

            // change title
            setTitle("Browse List Flights");
            // hide text4 and text4Field
            text4.setVisible(false);
            text4Field.setVisible(false);
            // change text1 to "Origin"
            text1.setText("Origin");
            // change text2 to "Destination"
            text2.setText("Destination");
            // change text3 to "Depature Date"
            text3.setText("Depature Date");
            text3.setVisible(false);
            text3Field.setVisible(false);
            // change submitButton to "Search"
            submitButton.setText("Search");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String origin = text1Field.getText();
                    String destination = text2Field.getText();
                    // String departureDate = text3Field.getText();
                    
                        //print the list of flights
                    String message = "";
                    if (origin.equals("") && destination.equals("")){
                        for (Flight flight : flightList) {
                        message += "From: " + flight.getOrigin().getCode() + "To: " + flight.getDestination().getCode() + "Departure Time: "
                                + flight.getDepartureDate().toString() + "Arrival time:" + flight.getArrivalDate().toString()
                                + "\n";
                         }
                        System.out.println(message);
                    

                    
                        JOptionPane.showMessageDialog(PerformAdmin.this, message, "Flight List",
                            JOptionPane.INFORMATION_MESSAGE);

                    }
                    else{
                        for (Flight flight : flightList) {
                            if (flight.getOrigin().getCode().equals(origin) && flight.getDestination().getCode().equals(destination)) {
                                message += "From: " + flight.getOrigin().getCode() + "To: " + flight.getDestination().getCode() + "Departure Time: "
                                    + flight.getDepartureDate().toString() + "Arrival time:" + flight.getArrivalDate().toString()
                                    + "\n";
                            }
                        }
                        JOptionPane.showMessageDialog(PerformAdmin.this, message, "Flight List",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                }
            });
          

        } else if (name == "Browse List Crews") {

            setTitle("Browse List Crews");
            // hide text4 and text4Field, text3 and text3Field, text2 and text2Field

            text4.setVisible(false);
            text4Field.setVisible(false);
            // change text1 to "Flight ID"
            text3.setVisible(false);
            text3Field.setVisible(false);
            text2.setVisible(false);
            text2Field.setVisible(false);

            text1.setText("Flight ID");
            // text2.setText("Destination");
            submitButton.setText("Search");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String flightID = text1Field.getText();
                    // String destination = text2Field.getText();
                    Flight flight = FlightsSingleton.getInstance().getFlight(Integer.parseInt(flightID));
                    ArrayList<RegisteredUser> crews = flight.getCrew().getCrewMembers();
                    String message = "";
                    for (RegisteredUser crewmember : crews) {
                        message += "Crew ID: " + crewmember.getID() + "Crew Name: " + crewmember.getFirstName() + " "
                                + crewmember.getLastName() + "\n";
                    }
                    JOptionPane.showMessageDialog(PerformAdmin.this, message, "Crew List",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });

        } else if (name == "Add Aircraft") {
            dispose();
            setTitle("Add AirCraft");
            // hide text4 and text4Field, text3 and text3Field, text2 and text2Field

            text4.setText("Total bussiness seat");
            // change text1 to "Flight ID"
            text3.setText("Total Comfort seat");
            text2.setText("Total Regular seat");

            text1.setText("AirCraft Type");
            // text2.setText("Destination");
            submitButton.setText("Add Aircraft");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String aircraftType = text1Field.getText();
                    int totalRegularSeat = Integer.parseInt(text2Field.getText());
                    int totalComfortSeat = Integer.parseInt(text3Field.getText());
                    int totalBussinessSeat = Integer.parseInt(text4Field.getText());

                    boolean addedPlane = PlaneSingleton.getInstance().addPlane(
                            new Plane(0, aircraftType, totalRegularSeat, totalComfortSeat, totalBussinessSeat));

                    if (addedPlane) {
                        System.out.println("Added plane!");
                    } else {
                        System.out.println("Failed to add plane!");
                    }
                    // need to add to database and singleton
                    PlaneSingleton.getInstance().addPlane(new Plane(0,aircraftType, (totalRegularSeat),
                            (totalComfortSeat), (totalBussinessSeat)));
                    System.out.println("Aircraft Type: " + aircraftType + "Total Regular Seat: " + totalRegularSeat
                            + "Total Comfort Seat: " + totalComfortSeat + "Total Bussiness Seat: "
                            + totalBussinessSeat);
                    JOptionPane.showMessageDialog(PerformAdmin.this, "Aircraft has been added", "Aircraft Added",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                //Show that the aircraft has been added and ask user to go back
                
            });
            goBackButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Create and make visible the new AdminPage frame first
                    // AdminPage adminPage = new AdminPage(user);
                    // adminPage.setVisible(true);

                    // Then dispose of the current PerformAdmin frame
                    dispose();
                }
            });
        } else if (name == "Remove Aircraft") {
            setTitle("Remove AirCraft");
            // hide text4 and text4Field, text3 and text3Field, text2 and text2Field

            // text4.setText("Total bussiness seat");
            // //change text1 to "Flight ID"
            // text3.setText("Total Comfort seat");
            // text2.setText("Total Regular seat");
            // set text2,3,4 to invisible
            text2.setVisible(false);
            text2Field.setVisible(false);
            text3.setVisible(false);
            text3Field.setVisible(false);
            text4.setVisible(false);
            text4Field.setVisible(false);


            text1.setText("AirCraftID to remove:");
            // text2.setText("Destination");
            submitButton.setText("Remove Aircraft");
            
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int aircraftID = Integer.parseInt(text1Field.getText());
                    //Search for the aircraft in the database and remove it
                    // need to add to database and singleton
                    PlaneSingleton.getInstance().removePlane(PlaneSingleton.getInstance().getPlane(aircraftID));
                    //if the aircraft is not found, show error message
                    //if the aircraft is found, show that the aircraft has been removed and ask user to go back
                    try {
                        PlaneSingleton.getInstance().removePlane(PlaneSingleton.getInstance().getPlane(aircraftID));
                        JOptionPane.showMessageDialog(PerformAdmin.this, "Aircraft has been removed", "Aircraft Removed",
                            JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(PerformAdmin.this, "Aircraft not found", "Aircraft not found",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });

        } else if (name == "Add Crew") {

            setTitle("Add Crew");
            text1.setText("Crew ID:");
            text2.setText("Crew Member ID: ");
            text3.setText("Crew job: ");

            text4.setVisible(false);
            text4Field.setVisible(false);
            submitButton.setText("Add Crew to the flight");

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int crewID = Integer.parseInt(text1Field.getText());
                    int crewMemberID = Integer.parseInt(text2Field.getText());
                    String job = text3Field.getText();
                    try {
                        DatabaseSingleton.getInstance().addCrewMember(crewID, crewMemberID, job);
                        FlightsSingleton.getInstance().getFlightList().forEach((f) -> {
                            if (f.getCrew().getCrewID() == crewID) {
                                f.addCrewMember(UsersSingleton.getInstance().getRegisteredUser(crewMemberID), job);
                            }
                        });
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("Failed to add crew member!");
                    }
                    System.out.println("CrewID: " + crewID);
                    System.out.println("CrewMemberID: " + crewMemberID);
                    System.out.println("Job: " + job);

                    FlightsSingleton.getInstance().getFlightList().forEach((f) -> {
                        System.out.println(f);
                        f.getCrew().getCrewMembers().forEach((cm) -> {
                            System.out.println(cm);
                        });
                        System.out.println();
                    });


                }
            });
        } else if (name == "Remove Crew") {
            setTitle("Remove Crew");
            text1.setText("Crew ID:");
            text2.setText("Flight ID: ");

            text3.setVisible(false);
            text3Field.setVisible(false);
            text4.setVisible(false);
            text4Field.setVisible(false);
            submitButton.setText("Remove Crew from the flight");

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int crewID = Integer.parseInt(text1Field.getText());
                    // int flightID = Integer.parseInt(text2Field.getText());

                    ArrayList<Flight> toRemove = new ArrayList<Flight>();
                    FlightsSingleton.getInstance().getFlightList().forEach((f) -> {
                        if (f.getCrew().getCrewID() == crewID) {

                            toRemove.add(f);
                        }
                    });
                    FlightsSingleton.getInstance().removeFlight(toRemove);

                }

            });
        }

        else if (name == "Add Flight Destination") {
            setTitle("Add Flight Destination");
            text1.setText("Airport Name:");
            text2.setText("Airport Code: ");
            text3.setText("City: ");
            text4.setText("Country:");
            submitButton.setText("Add Destination");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String airportName = text1Field.getText();
                    String airportCode = text2Field.getText();
                    String city = text3Field.getText();
                    String country = text4Field.getText();

                    AirportsSingleton.getInstance().addAirport(new Airport(airportCode, name, city, country));
                    System.out.println("Airport Name: " + airportName + "Airport Code: " + airportCode + "City: " + city
                            + "Country: " + country);
                    try{
                        AirportsSingleton.getInstance().addAirport(new Airport(airportCode,airportName , city, country));
                        JOptionPane.showMessageDialog(PerformAdmin.this, "Airport has been added", "Airport Added",
                            JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(PerformAdmin.this, "Airport Code already exists", "Airport Code already exists",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                }
            });

        } else if (name == "Remove Flight Destination") {
            setTitle("Remove Flight Destination");
            text1.setText("Flight Destination Code");
            text2.setVisible(false);
            text2Field.setVisible(false);
            text3.setVisible(false);
            text3Field.setVisible(false);
            text4.setVisible(false);
            text4Field.setVisible(false);
            submitButton.setText("Remove Destination");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String AirportCode = text1Field.getText();

                    AirportsSingleton.getInstance().removeAirport(AirportCode);
                    System.out.println("Airport Code removed: " + AirportCode);
                    try{
                        AirportsSingleton.getInstance().removeAirport(AirportsSingleton.getInstance().getAirport(AirportCode));
                        JOptionPane.showMessageDialog(PerformAdmin.this, "Airport has been removed", "Airport Removed",
                            JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(PerformAdmin.this, "Airport Code not found", "Airport Code not found",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
        }

        else if (name == "Add Flight Info") {
            // set all the text to visible
            text1.setVisible(false);
            text1Field.setVisible(false);
            text5.setVisible(true);
            text5Field.setVisible(true);
            text6.setVisible(true);
            text6Field.setVisible(true);
            text7.setVisible(true);
            text7Field.setVisible(true);
            text8.setVisible(true);
            text8Field.setVisible(true);
            text9.setVisible(true);
            text9Field.setVisible(true);
            text10.setVisible(true);
            text10Field.setVisible(true);
            // (FlightID, Destination, ArrivalTime, ArrivalDate, Origin, DepartureTime,
            // DepartureDate, CrewID, PlaneID, BasePrice
            setTitle("Add Flight Info");
            // text1.setText("Flight ID");
            text2.setText("Destination");
            text3.setText("Arrival Time");
            text4.setText("Arrival Date");
            text5.setText("Origin");
            text6.setText("Departure Time");
            text7.setText("Departure Date");
            text8.setText("Crew ID");
            text9.setText("Plane ID");
            text10.setText("Base Price");
            submitButton.setText("Add Flight");
            text1.setVisible(false);
            text1Field.setVisible(false);

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String destination = text2Field.getText();
                    LocalTime arrivalTime = LocalTime.parse(text3Field.getText().trim());
                    LocalDate arrivalDate = LocalDate.parse(text4Field.getText().trim());
                    String origin = text5Field.getText();

                    LocalTime departureTime = LocalTime.parse(text6Field.getText());
                    LocalDate departureDate = LocalDate.parse(text7Field.getText());
                    int crewID = Integer.parseInt(text8Field.getText());
                    int planeID = Integer.parseInt(text9Field.getText());
                    float basePrice = Float.parseFloat(text10Field.getText());

                    System.out.println("Destination: " + destination);
                    System.out.println("Arrival Time: " + arrivalTime);
                    System.out.println("Arrival Date: " + arrivalDate);
                    System.out.println("Origin: " + origin);
                    System.out.println("Departure Time: " + departureTime);
                    System.out.println("Departure Date: " + departureDate);
                    System.out.println("Crew ID: " + crewID);
                    System.out.println("Plane ID: " + planeID);
                    System.out.println("Base Price: " + basePrice);
                    FlightsSingleton.getInstance().addFlight(
                            new Flight(1,
                                    AirportsSingleton.getInstance().getAirport(destination), arrivalTime, arrivalDate,
                                    AirportsSingleton.getInstance().getAirport(origin), departureTime, departureDate,
                                    crewID,
                                    basePrice,
                                    PlaneSingleton.getInstance().getPlane(planeID)));

                    FlightsSingleton.getInstance().getFlightList().forEach((f) -> {
                        System.out.println(f);
                    });
                }
            });

        } else if (name == "Remove Flight Info") {
            setTitle("Remove Flight Info");
            text1.setText("Flight ID");
            text2.setVisible(false);
            text2Field.setVisible(false);
            text3.setVisible(false);
            text3Field.setVisible(false);
            text4.setVisible(false);
            text4Field.setVisible(false);
            submitButton.setText("Remove Flight Info:");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int flightID = Integer.parseInt(text1Field.getText());
                    // need to add to database and singleton
                    FlightsSingleton.getInstance().removeFlight(FlightsSingleton.getInstance().getFlight(flightID));
                    System.out.println("Flight ID: " + flightID);
                    try {
                        FlightsSingleton.getInstance().removeFlight(FlightsSingleton.getInstance().getFlight(Integer.parseInt(flightID)));
                        JOptionPane.showMessageDialog(PerformAdmin.this, "Flight has been removed", "Flight Removed",
                            JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(PerformAdmin.this, "Flight ID not found", "Flight ID not found",
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    }

                    //TODO
                }
            });

        } else if (name == "Modify Flight") {
            // set all the text to visible

            text5.setVisible(true);
            text5Field.setVisible(true);
            text6.setVisible(true);
            text6Field.setVisible(true);
            text7.setVisible(true);
            text7Field.setVisible(true);
            text8.setVisible(true);
            text8Field.setVisible(true);
            text9.setVisible(true);
            text9Field.setVisible(true);
            text10.setVisible(true);
            text10Field.setVisible(true);

            setTitle("Modify Flight");
            text1.setText("Flight ID");
            text2.setText("Destination");
            text3.setText("Arrival Time");
            text4.setText("Arrival Date");
            text5.setText("Origin");
            text6.setText("Departure Time");
            text7.setText("Departure Date");
            text8.setText("Crew ID");
            text9.setText("Plane ID");
            text10.setText("Base Price");
            submitButton.setText("Modify Flight");

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int flightID = Integer.parseInt(text1Field.getText());
                    String destination = text2Field.getText();
                    LocalTime arrivalTime = LocalTime.parse(text3Field.getText());
                    LocalDate arrivalDate = LocalDate.parse(text4Field.getText());
                    String origin = text5Field.getText();
                    LocalTime departureTime = LocalTime.parse(text6Field.getText());
                    LocalDate departureDate = LocalDate.parse(text7Field.getText());
                    int crewID = Integer.parseInt(text8Field.getText());
                    int planeID = Integer.parseInt(text9Field.getText());
                    float basePrice = Float.parseFloat(text10Field.getText());

                    FlightsSingleton.getInstance().updateFlight(new Flight(flightID,
                            AirportsSingleton.getInstance().getAirport(destination), arrivalTime, arrivalDate,
                            AirportsSingleton.getInstance().getAirport(origin), departureTime, departureDate,
                            crewID,
                            basePrice,
                            PlaneSingleton.getInstance().getPlane(planeID)));

                    System.out.println("Flight ID: " + flightID);
                    System.out.println("Destination: " + destination);
                    System.out.println("Arrival Time: " + arrivalTime);
                    System.out.println("Arrival Date: " + arrivalDate);
                    System.out.println("Origin: " + origin);
                    System.out.println("Departure Time: " + departureTime);
                    System.out.println("Departure Date: " + departureDate);
                    System.out.println("Crew ID: " + crewID);
                    System.out.println("Plane ID: " + planeID);
                    System.out.println("Base Price: " + basePrice);
                    //TODO
                }
            });
        }

        else if(name == "Manage Promo")
        {
            setTitle("Manage Promo"); 
            text1.setText("Enter News:");
            text2.setText("Promo Code:");
            text3.setText("Discount:");
            text4.setVisible(false);
            text4Field.setVisible(false);
            submitButton.setText("Send Promo");

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String news = text1Field.getText();
                    String promoCode = text2Field.getText();
                    float discount = Float.parseFloat(text3Field.getText());

                    // check discount is between 0 and 1
                    if(discount>1 || discount<0)
                    {
                        JOptionPane.showMessageDialog(PerformAdmin.this, "Discount must be between 0 and 1", "Error",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                 
                    // need to add to database and singleton
                    System.out.println("News: " + news);
                    System.out.println("Promo: " + promoCode);
                    System.out.println("Discount: " + discount);
                    Mail.sendPromo(news, promoCode, discount);
                         // Dispose of the current JFrame
                    PerformAdmin.this.dispose();

                    // Open the next page
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