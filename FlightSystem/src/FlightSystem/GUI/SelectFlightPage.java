package FlightSystem.GUI;
import javax.swing.*;

import FlightSystem.objects.*;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import FlightSystem.data.*;

public class SelectFlightPage extends JFrame implements ActionListener, MouseListener
{
    private FlightSingleton fs;
    private ArrayList<JButton> selectButtons = new ArrayList<JButton>();

    private JButton nextButton = new JButton("Next"); // need for action listener

    private int flightID;
    private String origin;
    private String destination;
    private LocalDate departure;
    private LocalDate arrival;

    private RegisteredUser signedInUser;

    private HashMap<Integer,Flight> flights;


    public SelectFlightPage(RegisteredUser signedInUser, String destination)
    {
        super("Select Flight"); // create a frame
        fs = FlightSingleton.getOnlyInstance();
        flights = fs.getFlights(destination);
        this.signedInUser = signedInUser;
        setupGUI();
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EventQueue.invokeLater(() -> { // event queue is threading related
            this.setVisible(true); // makes GUI appear on screen 
        });
    }

    public void setupGUI()
    {
        JPanel selectPanel = new JPanel();
        selectPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
        gbc.gridy = 0;

        ArrayList<Flight> flightValues = new ArrayList<Flight>(this.flights.values());
        for (Flight flight : flightValues) 
        {
            // Reset grid position for each flight
            gbc.gridx = 0;
            int tempFlightID = flight.getID();

            JLabel origin = new JLabel("Origin: " + flight.getOrigin().getCode());
            selectPanel.add(origin, gbc);
        
            gbc.gridx++;
            JLabel dest = new JLabel("Destination: " + flight.getDestination().getCode());
            selectPanel.add(dest, gbc);
        
            gbc.gridx++;
            JLabel departure = new JLabel("Departure: " + flight.getDepartureDate().toString());
            selectPanel.add(departure, gbc);
        
            gbc.gridx++;
            JLabel arrival = new JLabel("Arrival: " + flight.getArrivalDate().toString());
            selectPanel.add(arrival, gbc);
        
            gbc.gridx++;
            gbc.gridwidth = 4; // Make the button span all columns in the row
            gbc.fill = GridBagConstraints.HORIZONTAL; // Make the button horizontally fill the cell
        
            JButton selectButton = new JButton("Select");
            selectButtons.add(selectButton);
            selectPanel.add(selectButton, gbc);
        
            selectButton.addActionListener(new ActionListener() {
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
                    

                    for (JButton button : selectButtons) {
                        button.setBackground(null);
                    }
                    JButton clickedButton = (JButton) e.getSource();
                    clickedButton.setBackground(Color.GRAY);
                }
            });
            // increment row each time
            gbc.gridy++;
        }
        
        nextButton.addActionListener(this);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 4; // Make the button span all columns in the row
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make the button horizontally fill the cell
        
        selectPanel.add(nextButton, gbc);
        
        this.add(selectPanel, BorderLayout.NORTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) // performed for an actionListener
    {
        if(e.getSource() == nextButton) // add checks for all user types
        {
            this.dispose();
            Seatmap nextPage = new Seatmap(signedInUser, flights.get(flightID));// navigate to next page
        }
    }

    public void mouseClicked(MouseEvent event) { 

    }

    public void mouseEntered(MouseEvent event) { // must have because implementing mouse listener

    }

    public void mouseExited(MouseEvent event) { // must have because implementing mouse listener

    }

    public void mousePressed(MouseEvent event) { // must have because implementing mouse listener

    }

    public void mouseReleased(MouseEvent event) { // must have because implementing mouse listener

    }

    // public static void main(String[] args)
    // {
    //     EventQueue.invokeLater(() -> { // event queue is threading related
    //         new SelectFlightPage().setVisible(true); // makes GUI appear on screen 
    //     });
    // }
}