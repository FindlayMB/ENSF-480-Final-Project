package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Seatmap extends JFrame implements ActionListener, MouseListener
{
    // private Aircraft aircraft; // use this to get rows and columns
    // private Flight flight; // use to find classes of each seat 
    private int rows = 10;
    private int columns = 5;
    private JButton[][] seatButtons;

    private ArrayList<Integer> selectedSeats;


    public Seatmap()
    {
        super("Seatmap"); // create a frame
        selectedSeats = new ArrayList<Integer>();
        setupGUI();
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // EventQueue.invokeLater(() -> { // event queue is threading related
        //     new Seatmap().setVisible(true); // makes GUI appear on screen 
        // });
    }
    
    public void setupGUI()
    {
        JPanel seatPanel = new JPanel();
        seatPanel.setLayout(new GridLayout(rows, columns));
        
        // Initialize the seatButtons array
        seatButtons = new JButton[rows][columns];
        // Create buttons for each seat
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seatButtons[i][j] = new JButton("Seat " + (i * columns + j + 1));
                seatButtons[i][j].addActionListener(this);
                seatPanel.add(seatButtons[i][j]);
            }
        }

        JPanel submitPanel = new JPanel();
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(null);
        submitPanel.add(submitButton);

        this.add(seatPanel, BorderLayout.NORTH);
        this.add(submitPanel, BorderLayout.SOUTH);
    }
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> { // event queue is threading related
            new Seatmap().setVisible(true); // makes GUI appear on screen 
        });
    }

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
