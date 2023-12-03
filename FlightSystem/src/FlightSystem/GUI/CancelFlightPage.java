package FlightSystem.GUI;
import javax.mail.MessagingException;
import javax.swing.*;

import FlightSystem.objects.*;
import FlightSystem.objects.flight.Flight;
import FlightSystem.objects.flight.FlightsSingleton;
import FlightSystem.objects.seats.BusinessSeat;
import FlightSystem.objects.seats.ComfortSeat;
import FlightSystem.objects.seats.RegularSeat;
import FlightSystem.objects.seats.Seat;
import FlightSystem.objects.user.RegisteredUser;
import FlightSystem.objects.user.User;
import FlightSystem.objects.user.UsersSingleton;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import FlightSystem.data.*;


public class CancelFlightPage extends JFrame implements ActionListener{
    private User user;
    private JButton backButton = new JButton("Back"); // need for action listener
    private JButton nextButton = new JButton("Next"); // need for action listener
    private int flightID;
    private ArrayList<JButton> cancelButtons = new ArrayList<JButton>();


    public CancelFlightPage(User user){
        super("Cancel Flight");
        this.user = user;
        setupGUI();
        this.setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EventQueue.invokeLater(() -> { // event queue is threading related
            this.setVisible(true); // makes GUI appear on screen 
        });
    }

    private void setupGUI(){
        JPanel cancelPanel = new JPanel();
        cancelPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
        gbc.gridy = 0;
        gbc.gridx = 0;

        if(user != null) // if user is signed or enters their info in same page then  display their flights
        {
            JLabel welcomeLabel = new JLabel("Welcome " + user.getFirstName() + " " + user.getLastName());
            welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            cancelPanel.add(welcomeLabel);
            gbc.gridy++;
            ArrayList<Flight>userFlights = user.getUserFlights();
            if(userFlights.size() == 0) // if no flights found
            {
                JLabel noFlights = new JLabel("No flights found");
                cancelPanel.add(noFlights, gbc);
                gbc.gridy++;
            }
            else{
                for (Flight flight : userFlights) 
                {
                    // Reset grid position for each flight
                    gbc.gridx = 0;
                    int tempFlightID = flight.getID(); // if select button clicked will set flightID to this

                    JLabel origin = new JLabel("Origin: " + flight.getOrigin().getCode());
                    cancelPanel.add(origin, gbc);
                
                    gbc.gridx++;
                    JLabel dest = new JLabel("Destination: " + flight.getDestination().getCode());
                    cancelPanel.add(dest, gbc);
                
                    gbc.gridx++;
                    JLabel departure = new JLabel("Departure: " + flight.getDepartureDate().toString());
                    cancelPanel.add(departure, gbc);
                
                    gbc.gridx++;
                    JLabel arrival = new JLabel("Arrival: " + flight.getArrivalDate().toString());
                    cancelPanel.add(arrival, gbc);
                
                    gbc.gridx++;
                    gbc.gridwidth = 4; // Make the button span all columns in the row
                    gbc.fill = GridBagConstraints.HORIZONTAL; // Make the button horizontally fill the cell
                
                    JButton cancelButton = new JButton("Cancel");
                    cancelButtons.add(cancelButton);
                    cancelPanel.add(cancelButton, gbc);
                
                    cancelButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // set origin, dest, and dates variables
                            flightID = tempFlightID;

                            String selectedOrigin = origin.getText().substring("Origin: ".length());
                            String selectedDestination = dest.getText().substring("Destination: ".length());
                
                            String departureText = departure.getText().substring("Departure: ".length());
                            LocalDate selectedDeparture = LocalDate.parse(departureText, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                
                            String arrivalText = arrival.getText().substring("Arrival: ".length());
                            LocalDate selectedArrival = LocalDate.parse(arrivalText, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            

                            for (JButton button : cancelButtons) {
                                button.setBackground(null);
                            }
                            JButton clickedButton = (JButton) e.getSource();
                            clickedButton.setBackground(Color.GRAY);
                        }
                    });
                    // increment row each time
                    gbc.gridy++;
                }
            }
            nextButton.addActionListener(this);
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.gridwidth = 4; // Make the button span all columns in the row
            gbc.fill = GridBagConstraints.HORIZONTAL; // Make the button horizontally fill the cell
            cancelPanel.add(nextButton, gbc);
        }
       
        
        
        else
        {
            JLabel welcomeLabel = new JLabel("Welcome Guest");
            welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            cancelPanel.add(welcomeLabel, gbc);

            gbc.gridy++;
            JLabel firstNameLabel = new JLabel("First Name");
            cancelPanel.add(firstNameLabel, gbc);
            gbc.gridx++;
            JTextField firstNameField = new JTextField(20);
            cancelPanel.add(firstNameField, gbc);
            
            gbc.gridx = 0;
            gbc.gridy++;
            JLabel lastNameLabel = new JLabel("Last Name");
            cancelPanel.add(lastNameLabel, gbc);
            gbc.gridx++;
            JTextField lastNameField = new JTextField(20);
            cancelPanel.add(lastNameField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            JLabel emailLabel = new JLabel("Email");
            cancelPanel.add(emailLabel, gbc);
            gbc.gridx++;
            JTextField emailField = new JTextField(20);
            cancelPanel.add(emailField, gbc);

            JButton enterButton = new JButton("Enter");
            enterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    user = UsersSingleton.getInstance().getUserByNameAndEmail(firstNameField.getText(), lastNameField.getText(), emailField.getText()); // set basic user who wants to cancel flight
                    if(user == null)
                    {
                        JOptionPane.showMessageDialog(null, "Guest user not found");
                        return;
                    }
                    CancelFlightPage.this.dispose();
                    CancelFlightPage nextPage = new CancelFlightPage(user);
                }
            });            
            gbc.gridy++;
            gbc.gridx = 0;
            cancelPanel.add(enterButton, gbc);
        }
        
        gbc.gridy++;
        backButton.addActionListener(this);
        cancelPanel.add(backButton, gbc);
        this.add(cancelPanel);

    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String buttonText = clickedButton.getText();
        if(e.getSource() == backButton)
        {
            this.dispose();
            if(user == null || user.getRole().equals("guest"))
            {
                HomePage nextPage = new HomePage(null);
            }
            else
            {
                HomePage nextPage = new HomePage((RegisteredUser)user);
            }
        }
        else if(e.getSource() == nextButton)
        {
            try
            {

                // Send cancellation email to user
                Flight bookedFlight = FlightsSingleton.getInstance().getFlightMap().get(flightID); // get the flight
                ArrayList<Seat> bookedSeats = bookedFlight.getPassengerList().getPassengers(); 
                Seat bookedSeat = null;   
                for (Seat seat : bookedSeats) {
                    if (seat.getPassengerID() == user.getID()) {
                        // Found the seat with the specified passenger ID
                        bookedSeat = seat; // get seat and price of seat
                    }
                }         
                double seatPrice;
    
                // Multiply seat by multiplier to get price of seat
                // if(bookedSeat instanceof RegularSeat)
                // {
                //     RegularSeat regularSeat = (RegularSeat)bookedSeat;
                //     seatPrice = bookedFlight.getBasePrice()*RegularSeat.getPriceMultipler();
                // } 
                // else if(bookedSeat instanceof BusinessSeat)
                // {
                //     BusinessSeat businessSeat = (BusinessSeat)bookedSeat;
                //     seatPrice = bookedFlight.getBasePrice()*BusinessSeat.getPriceMultipler();
                // }                                                   
                // else
                // {
                //     ComfortSeat comfortSeat = (ComfortSeat)bookedSeat;
                //     seatPrice = bookedFlight.getBasePrice()*ComfortSeat.getPriceMultipler();
                // }
                seatPrice = bookedSeat.getPricePaid();
                
                Mail.sendCancellation(user, bookedFlight, bookedSeat.getInsurance(), seatPrice);

                // Remove user from flight
                bookedFlight.removePassenger(bookedSeat);
                
                JOptionPane.showMessageDialog(this, "Flight cancelled");
                this.dispose();
                if(user.getRole().equals("guest"))
                {
                    HomePage nextPage = new HomePage(null);
                }
                else
                {
                    HomePage nextPage = new HomePage((RegisteredUser)user);
                }
            }
            catch (MessagingException ex) {
                    // Handle AddressException and MessagingException here
                    ex.printStackTrace(); // You might want to log this to a proper logging system
                    // Optionally, show an error message to the user
                    JOptionPane.showMessageDialog(this, "Error sending email: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
        
        }
        
        
    }
}