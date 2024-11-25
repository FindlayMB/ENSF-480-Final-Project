package FlightSystem.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.channels.SeekableByteChannel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Flow;
import FlightSystem.objects.*;
import FlightSystem.objects.flight.Flight;
import FlightSystem.objects.flight.FlightsSingleton;
import FlightSystem.objects.flight.PassengerList;
import FlightSystem.objects.seats.Seat;
import FlightSystem.objects.user.RegisteredUser;
import FlightSystem.objects.user.User;
import FlightSystem.objects.user.UsersSingleton;

public class viewPassengerListPage extends JFrame implements ActionListener{
    private FlightsSingleton fs;
    private ArrayList<JButton> selectButtons = new ArrayList<JButton>();

    private JButton nextButton = new JButton("Next"); // need for action listener
    private JButton backButton = new JButton("Back"); // need for action listener

    private int flightID;
    private String origin;
    private String destination;
    private LocalDate departure;
    private LocalDate arrival;

    private RegisteredUser signedInUser;

    private HashMap<Integer, Flight> employeeFlights = new HashMap<>(); // list of flights the employee is a crew member for


    public viewPassengerListPage(RegisteredUser signedInUser)
    {
        super("Passenger List"); // create a frame

        // get flights they are a crew member for
        fs = FlightsSingleton.getInstance();
        HashMap<Integer, Flight> flights = fs.getFlightMap();
        for(Flight flight : flights.values())
        {
            ArrayList<RegisteredUser> crewMembers = flight.getCrew().getCrewMembers();
            for(RegisteredUser crewMember : crewMembers)
            {
                if(crewMember.getID() == signedInUser.getID())
                {
                    employeeFlights.put(flight.getID(), flight);
                }
            }
        }

        this.signedInUser = signedInUser;
        setupGUI();
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EventQueue.invokeLater(() -> { // event queue is threading related
            this.setVisible(true); // makes GUI appear on screen
        });
    }

    public void setupGUI() {
        JPanel selectPanel = new JPanel();
        selectPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
        gbc.gridy = 0;

        if (employeeFlights.size() == 0) // if no flights found
        {
            JLabel noFlights = new JLabel("No flights found");
            selectPanel.add(noFlights, gbc);
            gbc.gridy++;
        }

        else {
            for (Flight flight : employeeFlights.values()) {
                // Reset grid position for each flight
                gbc.gridx = 0;
                int tempFlightID = flight.getID(); // if select button clicked will set flightID to this

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
                        LocalDate selectedDeparture = LocalDate.parse(departureText,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        String arrivalText = arrival.getText().substring("Arrival: ".length());
                        LocalDate selectedArrival = LocalDate.parse(arrivalText,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd"));

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

        }
        gbc.gridy++;
        backButton.addActionListener(this);
        selectPanel.add(backButton, gbc);

        this.add(selectPanel, BorderLayout.NORTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) // performed for an actionListener
    {
        if (e.getSource() == nextButton) // add checks for all user types
        {
            showPassengerListPopup();

            // display popup with passenger list


        } else if (e.getSource() == backButton) {
            this.dispose();
            HomePage nextPage = new HomePage(signedInUser);
        } else {
            System.out.println("Error in viewPassengerListPage");
        }
    }

    private void showPassengerListPopup() {
        // Retrieve the passenger list based on the selected flightID
        // Replace the following line with your logic to get the passengerList
        ArrayList<Seat> passengerList = employeeFlights.get(flightID).getPassengerList().getPassengers();
        HashMap<Integer, User> users =  UsersSingleton.getInstance().getUsersMap();
        HashMap<Integer, RegisteredUser> registeredUsers = UsersSingleton.getInstance().getRegisteredUsersMap();

        DefaultListModel<String> userModel = new DefaultListModel<>();

        for(Seat seat : passengerList) // get rid of users that aren't in flight
        {
            if(users.containsKey(seat.getPassengerID()))
            {
                userModel.addElement((users.get(seat.getPassengerID())).getFirstName() + " " + users.get((seat.getPassengerID())).getLastName() + " - Seat: " + seat.getSeatNumber());
            }

            else if(registeredUsers.containsKey(seat.getPassengerID()))
            {
                userModel.addElement((registeredUsers.get(seat.getPassengerID())).getFirstName() + " " + registeredUsers.get((seat.getPassengerID())).getLastName() + " - Seat: " + seat.getSeatNumber());
            }
        }
        JList<String> userList = new JList<>(userModel);

        JPanel panel = new JPanel(new GridLayout(1, 1));
        panel.add(new JScrollPane(userList));

        JOptionPane.showMessageDialog(this, panel, "Passenger List", JOptionPane.PLAIN_MESSAGE);

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
    // EventQueue.invokeLater(() -> { // event queue is threading related
    // new SelectFlightPage().setVisible(true); // makes GUI appear on screen
    // });
    // }
}