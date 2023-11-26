package FlightSystem.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import FlightSystem.objects.*;


public class PaymentPage extends JFrame implements ActionListener, MouseListener
{
    private String name;
    private String creditCardNumber;
    private LocalDate expiryDate;
    private String CSV;

    private JLabel nameLabel;
    private JLabel creditCardNumberLabel;
    private JLabel expiryDateLabel;
    private JLabel CSVLabel;

    private JTextField nameInput;
    private JTextField creditCardNumberInput;
    private JTextField expiryDateInput;
    private JTextField CSVInput;

    public PaymentPage(Flight selectedFlight, ArrayList<Integer> selectedSeats)
    {
        super("Payment"); // create a frame
        setupGUI();
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EventQueue.invokeLater(() -> { // event queue is threading related
            this.setVisible(true); // makes GUI appear on screen 
        });
    }
    
    public void setupGUI()
    {
        nameLabel = new JLabel("Full Name:");
        creditCardNumberLabel = new JLabel("Credit Card Number:");
        expiryDateLabel = new JLabel("Expiry Date:");
        CSVLabel = new JLabel("CSV:");


        nameInput = new JTextField("Enter full name");
        nameInput.setColumns(20); // Set the number of columns (width)

        creditCardNumberInput = new JTextField("Enter 16 digit credit card number");
        creditCardNumberInput.setColumns(20); // Set the number of columns (width)

        expiryDateInput = new JTextField("Enter expiry date:");
        expiryDateInput.setColumns(20); // Set the number of columns (width)

        CSVInput = new JTextField("Enter 3 digit CSV:");
        CSVInput.setColumns(20); // Set the number of columns (width)

        nameInput.addMouseListener(this);
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
        paymentPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;        
        paymentPanel.add(nameInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        paymentPanel.add(creditCardNumberLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;        
        paymentPanel.add(creditCardNumberInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        paymentPanel.add(expiryDateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        paymentPanel.add(expiryDateInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        paymentPanel.add(CSVLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        paymentPanel.add(CSVInput, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
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
        name = nameInput.getText();
        creditCardNumber = (creditCardNumberInput.getText());
        expiryDate = LocalDate.parse(expiryDateInput.getText()); // CONVERT FROM STRING TO DATE OBJECT
        CSV = CSVInput.getText();
        
        if(validatePaymentInfo(name, creditCardNumber, expiryDate, CSV)) // add checks for all user types
        {
            this.dispose();
            HomePage nextPage = new HomePage(); // navigate to next page
        }
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
        // NEED TO IMPLEMENT VALIDATE PAYMENT INFO FOR A COMPNAY CREDIT CARD
        return true;

    }

    public void mouseClicked(MouseEvent event) { 

        if (event.getSource().equals(creditCardNumberInput))
        {
            creditCardNumberInput.setText(""); // clear the default text
        }

        if(event.getSource().equals(nameInput))
        {
            nameInput.setText("");
        }
         if(event.getSource().equals(expiryDateInput))
        {
            expiryDateInput.setText("");
        }
         if(event.getSource().equals(CSVInput))
        {
            CSVInput.setText("");
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