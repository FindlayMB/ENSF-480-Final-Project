package FlightSystem.GUI;
import javax.swing.*;

import FlightSystem.data.*;
import FlightSystem.objects.*;
import FlightSystem.objects.flight.Flight;
import FlightSystem.objects.flight.FlightsSingleton;
import FlightSystem.objects.seats.BusinessSeat;
import FlightSystem.objects.seats.ComfortSeat;
import FlightSystem.objects.seats.RegularSeat;
import FlightSystem.objects.seats.Seat;
import FlightSystem.objects.user.RegisteredUser;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Seatmap extends JFrame implements ActionListener, MouseListener
{
    // private Aircraft aircraft; // use this to get rows and columns
    // private Flight flight; // use to find classes of each seat 


 
    private JButton[][] seatButtons;

    private int columns = 7;

    private int numSeats;

    private HashMap<Integer, Color> selectedSeatHash; // select a seat and record the color of the seat

    private Flight selectFlight;
    private RegisteredUser signedInUser;
    private ArrayList<Seat> bookedSeats;
    private int numBusiness;
    private int numComfort;
    private int numRegular;


    public Seatmap(RegisteredUser signedInUser, Flight selectFlight)
    {
        super("Seatmap"); // create a frame
        this.selectFlight = selectFlight;
        this.signedInUser = signedInUser;
        this.bookedSeats = selectFlight.getPassengerList().getPassengers();
        this.numBusiness = selectFlight.getPlane().getBusinessSeatAmt();
        this.numComfort = selectFlight.getPlane().getComfortSeatAmt();
        this.numRegular = selectFlight.getPlane().getRegularSeatAmt();
        numSeats = numBusiness + numComfort + numRegular;
        selectedSeatHash = new HashMap<Integer,Color>();
        setupGUI();
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EventQueue.invokeLater(() -> { // event queue is threading related
            this.setVisible(true); // makes GUI appear on screen 
        });
    }
    
    public void setupGUI()
    {
        // CODE FOR ADDING LEGEND OF SEAT TYPES
        JPanel legendPanel = new JPanel();
        
        JLabel selectedSeatLabel = new JLabel("Select Seat");
        legendPanel.add(selectedSeatLabel);
        JButton selectedSeat = new JButton();
        selectedSeat.setBackground(Color.RED);
        selectedSeat.setPreferredSize(new Dimension(50, 20));
        legendPanel.add(selectedSeat);
        
        JLabel bookedSeatLabel = new JLabel("Booked Seat");
        legendPanel.add(bookedSeatLabel);
        JButton bookedSeat = new JButton();
        bookedSeat.setBackground(Color.GRAY);
        bookedSeat.setPreferredSize(new Dimension(50, 20));
        legendPanel.add(bookedSeat);

        JLabel ordinarySeatLabel = new JLabel("Ordinary Seat");
        legendPanel.add(ordinarySeatLabel);
        JButton ordinarySeat = new JButton();
        ordinarySeat.setBackground(Color.GREEN);
        ordinarySeat.setPreferredSize(new Dimension(50, 20));
        legendPanel.add(ordinarySeat);

        JLabel comfortSeatLabel = new JLabel("Comfort Seat");
        legendPanel.add(comfortSeatLabel);
        JButton comfortSeat = new JButton();
        comfortSeat.setBackground(new Color(173, 216, 230)); // Light Blue
        comfortSeat.setPreferredSize(new Dimension(50, 20));
        legendPanel.add(comfortSeat);

        JLabel businessSeatLabel = new JLabel("Buisness Seat");
        legendPanel.add(businessSeatLabel);
        JButton businessSeat = new JButton();
        businessSeat.setBackground(Color.YELLOW);
        businessSeat.setPreferredSize(new Dimension(50, 20));
        legendPanel.add(businessSeat);
        

        // CODE FOR ADDING SEATS TO SEAT MAP

        // Calculate the number of rows (rounding up)
        int rows = (int) Math.ceil((double) numSeats / 6);        
        // Initialize the seatButtons array
        seatButtons = new JButton[rows][columns];

        //create panel for seats
        JPanel seatPanel = new JPanel();
        seatPanel.setLayout(new GridLayout(rows, columns));

        // Create buttons for each seat
        int seatnum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(j == 3 || seatnum>=numSeats) // add aisle
                {
                    seatButtons[i][j] = new JButton();
                    seatButtons[i][j].setBackground(Color.WHITE);
                    seatPanel.add(seatButtons[i][j]);
                }

                else  // add seat
                {
                    seatnum++;
                    seatButtons[i][j] = new JButton("Seat " + seatnum);
                    boolean isSeatnumBooked = false;
                    for (Seat seat : bookedSeats) {
                        if (seat.getSeatNumber() == seatnum) {
                            isSeatnumBooked = true;
                            break; // No need to continue checking if already found
                        }
                    }
                    
                    if (numBusiness > 0) {
                        seatButtons[i][j].setBackground(Color.YELLOW);
                        numBusiness--;
                        seatButtons[i][j].addActionListener(this);
                    } 
                    else if (numComfort > 0) {
                        seatButtons[i][j].setBackground(new Color(173, 216, 230));
                        numComfort--;
                        seatButtons[i][j].addActionListener(this);

                    } 
                    else if (numRegular > 0) {
                        seatButtons[i][j].setBackground(Color.GREEN);
                        numRegular--;
                        seatButtons[i][j].addActionListener(this);

                    }
                    if(isSeatnumBooked)
                    {
                        seatButtons[i][j].setBackground(Color.GRAY);
                    }
                    seatPanel.add(seatButtons[i][j]);
                }
            }
        }

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.X_AXIS));
          JButton backButton = new JButton("Back"); // need for action listener
        backButton.addActionListener((e) -> {
            this.dispose();
            this.setVisible(false);
            SelectFlightPage flightPage = new SelectFlightPage(signedInUser, selectFlight.getDestination().getCode());
            flightPage.setVisible(true);
        });
        submitPanel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // set origin, dest, and dates variables
                if(selectedSeatHash.size() == 0)
                {
                    JOptionPane.showMessageDialog(null, "You have not selected any seats");
                    return;
                }
                ArrayList<Integer> seatNumList = new ArrayList<>(selectedSeatHash.keySet());
                Integer seatnum = seatNumList.get(0);
                ArrayList<Color> seatColorList = new ArrayList<>(selectedSeatHash.values());
                Color seatColor = seatColorList.get(0);
                
                PaymentPage nextPage = new PaymentPage(signedInUser, selectFlight, seatnum, seatColor);
                dispose();

            }
        });
        submitPanel.add(submitButton);

      



        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(legendPanel);
        mainPanel.add(Box.createVerticalStrut(10)); // Add spacing between panels
        mainPanel.add(seatPanel);
        mainPanel.add(Box.createVerticalStrut(10)); // Add spacing between panels
        mainPanel.add(submitPanel);

        this.add(mainPanel);

    }
    // public static void main(String[] args)
    // {
    //     EventQueue.invokeLater(() -> { // event queue is threading related
    //         new Seatmap(4).setVisible(true); // makes GUI appear on screen 
    //     });
    // }

    @Override
    public void actionPerformed(ActionEvent e) // performed for an actionListener
    {
        JButton clickedButton = (JButton) e.getSource();
        
        // Use regular expression to find the first number
        String seatNum = clickedButton.getText().replaceAll("[^0-9]+", "");

        if(clickedButton.getBackground().equals(Color.GRAY))
        {
            JOptionPane.showMessageDialog(this, "This seat is already booked");
        }
       
        else if(selectedSeatHash.keySet().contains(Integer.parseInt(seatNum))) // seat is already select
        {
            int result = JOptionPane.showConfirmDialog(this,
                "Do you want to unselect this seat?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);
        
            if (result == JOptionPane.YES_OPTION) {
                // User clicked "Yes"
                clickedButton.setBackground(selectedSeatHash.get(Integer.parseInt(seatNum)));
                selectedSeatHash.remove(Integer.parseInt(seatNum));
                JOptionPane.showMessageDialog(this, "You have unreserved seat " + seatNum);
            } 
            else {
                // User clicked "No" or closed the dialog
                JOptionPane.showMessageDialog(this, "You clicked No or closed the dialog");
            }
        }

        else if(selectedSeatHash.size()>0){ // try to book multiple seats
            JOptionPane.showMessageDialog(this, "You have already selected a seat");
        } 

     
        else if(clickedButton.getBackground() != Color.RED) // seat is not selected
        {
            int result = JOptionPane.showConfirmDialog(this,
                "Do you want to select this seat?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);
        
            if (result == JOptionPane.YES_OPTION) {
                // User clicked "Yes"
                selectedSeatHash.put(Integer.parseInt(seatNum), clickedButton.getBackground());
                JOptionPane.showMessageDialog(this, "You have reserved seat " + seatNum);
                clickedButton.setBackground(Color.RED);

            } 
            else {
                // User clicked "No" or closed the dialog
                JOptionPane.showMessageDialog(this, "You clicked No or closed the dialog");
            }
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

}