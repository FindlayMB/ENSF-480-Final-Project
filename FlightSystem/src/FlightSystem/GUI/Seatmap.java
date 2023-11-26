package FlightSystem.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Seatmap extends JFrame implements ActionListener, MouseListener
{
    // private Aircraft aircraft; // use this to get rows and columns
    // private Flight flight; // use to find classes of each seat 
 
    private JButton[][] seatButtons;

    private int columns = 7;

    private int numSeats = 12;

    private ArrayList<Integer> selectedSeats;


    public Seatmap(int flightID)
    {
        super("Seatmap"); // create a frame
        selectedSeats = new ArrayList<Integer>();
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

        JLabel businessSeatLabel = new JLabel("Booked Seat");
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
                    seatButtons[i][j].addActionListener(this);
                    seatPanel.add(seatButtons[i][j]);
                }
            }
        }

        JPanel submitPanel = new JPanel();
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(null);
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
    //         new Seatmap().setVisible(true); // makes GUI appear on screen 
    //     });
    // }

    @Override
    public void actionPerformed(ActionEvent e) // performed for an actionListener
    {
        JButton clickedButton = (JButton) e.getSource();
        
        // Use regular expression to find the first number
        String seatNum = clickedButton.getText().replaceAll("[^0-9]+", "");

        if(selectedSeats.contains(Integer.parseInt(seatNum))) // seat is already select
        {
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "Do you want to proceed?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);
        
        
        if (result == JOptionPane.YES_OPTION) {
            // User clicked "Yes"
            selectedSeats.add(Integer.parseInt(seatNum));
            JOptionPane.showMessageDialog(this, "You have reserved seat " + seatNum);
            clickedButton.setBackground(Color.RED);

        } else {
            // User clicked "No" or closed the dialog
            JOptionPane.showMessageDialog(this, "You clicked No or closed the dialog");
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