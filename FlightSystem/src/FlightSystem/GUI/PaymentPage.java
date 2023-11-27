package FlightSystem.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import FlightSystem.data.FlightSingleton;
import FlightSystem.data.UserSingleton;
import FlightSystem.objects.*;


public class PaymentPage extends JFrame implements ActionListener, MouseListener
{
    private RegisteredUser signedInUser;
    private UserSingleton us = UserSingleton.getOnlyInstance();
    private FlightSingleton fs = FlightSingleton.getOnlyInstance();
    private Flight selectedFlight;
    private HashMap<Integer, Color> selectedSeats;

    private String firstName;
    private String lastName;
    private String email;
    private String creditCardNumber;
    private LocalDate expiryDate;
    private String CSV;

    private JLabel fnameLabel;
    private JLabel lnameLabel;
    private JLabel emailLabel;
    private JLabel creditCardNumberLabel;
    private JLabel expiryDateLabel;
    private JLabel CSVLabel;

    private JTextField fnameInput;
    private JTextField lnameInput;
    private JTextField emailInput;
    private JTextField creditCardNumberInput;
    private JTextField expiryDateInput;
    private JTextField CSVInput;

    private JCheckBox insuranceCheckBox;

    public PaymentPage(RegisteredUser signedInUser, Flight selectedFlight, HashMap<Integer, Color> selectedSeats)
    {
        super("Payment"); // create a frame
        this.signedInUser = signedInUser;
        this.selectedFlight = selectedFlight;
        this.selectedSeats = selectedSeats;
        setupGUI();
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EventQueue.invokeLater(() -> { // event queue is threading related
            this.setVisible(true); // makes GUI appear on screen 
        });
    }
    
    public void setupGUI()
    {
        fnameLabel = new JLabel("First Name:");
        lnameLabel = new JLabel("Last Name:");
        emailLabel = new JLabel("Email:");
        creditCardNumberLabel = new JLabel("Credit Card Number:");
        expiryDateLabel = new JLabel("Expiry Date:");
        CSVLabel = new JLabel("CSV:");


        fnameInput = new JTextField("Enter first name");
        fnameInput.setColumns(20); // Set the number of columns (width)

        lnameInput = new JTextField("Enter first name");
        lnameInput.setColumns(20); 

        emailInput = new JTextField("Enter email");
        emailInput.setColumns(20); // Set the number of columns (width)

        creditCardNumberInput = new JTextField("Enter 16 digit credit card number");
        creditCardNumberInput.setColumns(20); // Set the number of columns (width)

        expiryDateInput = new JTextField("Enter expiry date:");
        expiryDateInput.setColumns(20); // Set the number of columns (width)

        CSVInput = new JTextField("Enter 3 digit CSV:");
        CSVInput.setColumns(20); // Set the number of columns (width)

        insuranceCheckBox = new JCheckBox("Insurance");

        fnameInput.addMouseListener(this);
        lnameInput.addMouseListener(this);
        emailInput.addMouseListener(this);
        creditCardNumberInput.addMouseListener(this);
        expiryDateInput.addMouseListener(this);
        CSVInput.addMouseListener(this);

        JButton payButton = new JButton("Book");

        payButton.addActionListener(this);


        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
        paymentPanel.add(fnameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;        
        paymentPanel.add(fnameInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        paymentPanel.add(lnameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;        
        paymentPanel.add(lnameInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        paymentPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        paymentPanel.add(emailInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        paymentPanel.add(creditCardNumberLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;        
        paymentPanel.add(creditCardNumberInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        paymentPanel.add(expiryDateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        paymentPanel.add(expiryDateInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        paymentPanel.add(CSVLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        paymentPanel.add(CSVInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        paymentPanel.add(insuranceCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2; // Make the button span two columns
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make the button horizontally fill the cell
        paymentPanel.add(payButton, gbc);

        this.add(paymentPanel, BorderLayout.NORTH);

    }
    // public static void main(String[] args)
    // {
    //     EventQueue.invokeLater(() -> { // event queue is threading related
    //         new PaymentPage().setVisible(true); // makes GUI appear on screen 
    //     });
    // }

    @Override
    public void actionPerformed(ActionEvent e) // performed for an actionListener
    {
        firstName = fnameInput.getText().trim();
        lastName = lnameInput.getText().trim();
        email = emailInput.getText().trim();
        creditCardNumber = creditCardNumberInput.getText().trim();
        expiryDate = LocalDate.parse(expiryDateInput.getText().trim()); // CONVERT FROM STRING TO DATE OBJECT
        CSV = CSVInput.getText();
        
        if(validatePaymentInfo(firstName, creditCardNumber, expiryDate, CSV)) // add checks for all user types
        {
            try {
                    boolean hasInsurance = insuranceCheckBox.isSelected();
                    addUserToFlight(hasInsurance);
                    this.dispose();
                    HomePage nextPage = new HomePage(signedInUser);
                
            } 
            catch (SQLException ex) {
                // Handle the SQLException or log it
                ex.printStackTrace(); // You might want to log this to a proper logging system
                // Optionally, show an error message to the user
                JOptionPane.showMessageDialog(this, "Error adding user to flight: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void addUserToFlight(boolean hasInsurance) throws SQLException
    {
            if(signedInUser == null) // user not signed and not in DB must add to DB
            {
                User newUser = addUserToDBAndSingleton(firstName, lastName, email, "basic");
                // Add user to passenger list of flight
                addPassengerToDB(selectedFlight,newUser, selectedSeats, hasInsurance);
                // add this as a purchase to the user who booked
                us.setPurchase(newUser, selectedFlight, selectedSeats.keySet(), hasInsurance, creditCardNumber, expiryDate, CSV);
            }
            else // user is signed in
            {
                // Add user to passenger list of flight
                addPassengerToDB(selectedFlight, signedInUser, selectedSeats, hasInsurance);
                us.setPurchase(signedInUser, selectedFlight, selectedSeats.keySet(), hasInsurance, creditCardNumber, expiryDate, CSV);
            }
            
    }
    
    public void addPassengerToDB(Flight selectedFlight, User signedInUser, HashMap<Integer, Color> selectedSeats, boolean hasInsurance) throws SQLException
    {
        try
        {
            fs.addPassenger(selectedFlight,signedInUser, selectedSeats, hasInsurance);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    
public User addUserToDBAndSingleton(String firstName, String lastName, String email, String userType) throws SQLException {
    User newUser = null;
    try {
        newUser = us.addUserwithFields(firstName, lastName, email, userType);
    } catch (SQLException e) {
        System.out.println(e);
        // Optionally, log the exception or handle it in another way
    }
    return newUser;
}

    public boolean validatePaymentInfo(String name, String creditCardNumber, LocalDate expiryDate, String CSV)
    {
        if(creditCardNumber.length() != 16)
        {
            JOptionPane.showMessageDialog(this, "Credit card number must be 16 digits");
            return false;
        }
        else if(CSV.length() != 3)
        {
            JOptionPane.showMessageDialog(this, "CSV must be 3 digits");
            return false;
        }
        else if(expiryDate.isBefore(LocalDate.now()))
        {
            JOptionPane.showMessageDialog(this, "Expiry date must be in the future");
            return false;
        }
        // NEED TO IMPLEMENT VALIDATE PAYMENT INFO FOR A COMPANY CREDIT CARD
        return true;

    }

    public void mouseClicked(MouseEvent event) { 

        if (event.getSource().equals(creditCardNumberInput))
        {
            creditCardNumberInput.setText(""); // clear the default text
        }

        if(event.getSource().equals(fnameInput))
        {
            fnameInput.setText("");
        }
        if(event.getSource().equals(lnameInput))
        {
            lnameInput.setText("");
        }
         if(event.getSource().equals(expiryDateInput))
        {
            expiryDateInput.setText("");
        }
         if(event.getSource().equals(CSVInput))
        {
            CSVInput.setText("");
        }
        if(event.getSource().equals(emailInput))
        {
            emailInput.setText("");
        }

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