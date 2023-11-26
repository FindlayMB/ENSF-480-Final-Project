package GUI;
import javax.swing.*;

import FlightSystem.objects.Flight;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SelectFlightPage extends JFrame implements ActionListener, MouseListener
{
    flightSingleton fs = getOnlyInstance();

    private JButton nextButton = new JButton("Next"); // need for action listener
    private JButton selectButton = new JButton("Select");

    private String origin;
    private String destination;
    private Date departure;
    private Date arrival;

    private ArrayList<Flight> flights;


    public SelectFlightPage()
    {
        super("Select Flight"); // create a frame
        setupGUI();
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EventQueue.invokeLater(() -> { // event queue is threading related
            new SelectFlightPage().setVisible(true); // makes GUI appear on screen 
        });

    }

    public void setupGUI()
    {
        JPanel selectPanel = new JPanel();
        selectPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        int gbcY = 0;
        int gbcX = 0;
        
        for(Flight flights:flights)
        {
            JLabel origin = new JLabel("Origin: " + flight.origin);
            selectPanel.add(origin, gbc);

            gbcX+=10;
            JLabel dest = new JLabel("Destination: " + flight.destination);
            selectPanel.add(dest, gbc);

            gbcX+=10;
            JLabel departure = new Jlabel("Departure: " + flight.departure);
            selectPanel.add(departure, gbc);

            gbcX+=10;
            JLabel arrival = new JLabel("Arrival: " + flight.arrival);
            selectPanel.add(arrival, gbc);

            gbcX+=10;
            gbc.gridwidth = 2; // Make the button span two columns
            gbc.fill = GridBagConstraints.HORIZONTAL; // Make the button horizontally fill the cell
            selectPanel.add(selectButton, gbc);
             selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // set origin, dest and dates variables
                String selectedOrigin = origin.getText().substring("Origin: ".length());
                String selectedDestination = dest.getText().substring("Destination: ".length());
                Date selectedDeparture = departure.getText().substring("Departure: ".length());
                Date selectedArrival = arrival.getText().substring("Arrival: ".length());
            }
        });

            gbcY+=10;
            gbcX = 0;
        }


        nextButton.addActionListener(this);

        gbc.gridx = gbcX;
        gbc.gridy = gbcY;
        gbc.gridwidth = 2; // Make the button span two columns
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
            Seatmap nextPage = new Seatmap();// navigate to next page
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

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> { // event queue is threading related
            new SelectFlightPage().setVisible(true); // makes GUI appear on screen 
        });
    }
}