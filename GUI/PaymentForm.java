package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaymentForm extends JFrame implements ActionListener, MouseListener
{
    private String name;
    private int creditCardNumber;
    private Date expiryDate;
    private int CSV;

    private JLabel nameLabel;
    private JLabel creditCardNumberLabel;
    private JLabel expiryDateLabel;
    private JLabel CSVLabel;

    private JTextField nameInput;
    private JTextField creditCardNumberInput;
    private JTextField expiryDateInput;
    private JTextField CSVInput;

    public PaymentForm()
    {
        super("Payment"); // create a frame
        setupGUI();
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EventQueue.invokeLater(() -> { // event queue is threading related
            new PaymentForm().setVisible(true); // makes GUI appear on screen 
        });
    }
    
    public void setupGUI()
    {
        nameLabel = new JLabel("Full Name:");
        creditCardNumberLabel = new JLabel("Credit Card Number:");
        expiryDateLabel = new JLabel("Expiry Date:");
        CSVLabel = new JLabel("CSV:");


        nameInput = new JTextField("Enter full name");
        creditCardNumberInput = new JTextField("Enter credit card number");
        expiryDateInput = new JTextField("Enter expiry date:");
        CSVInput = new JTextField("Enter CSV:");

        nameInput.addMouseListener(this);
        creditCardNumberInput.addMouseListener(this);
        expiryDateInput.addMouseListener(this);
        CSVInput.addMouseListener(this);

        JButton payButton = new JButton("Pay");

        payButton.addActionListener(this);


        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
        paymentPanel.add(nameLabel, gbc);

        gbc.gridx = 10;
        paymentPanel.add(nameInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 20;
        paymentPanel.add(creditCardNumberLabel, gbc);

        gbc.gridx = 10;
        paymentPanel.add(creditCardNumberInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 30;
        paymentPanel.add(expiryDateLabel);

        gbc.gridx = 10;
        paymentPanel.add(expiryDateInput);

        gbc.gridx = 0;
        gbc.gridy = 40;
        paymentPanel.add(CSVLabel);

        gbc.gridx = 10;
        paymentPanel.add(CSVInput);

        gbc.gridy = 50;
        gbc.gridwidth = 2; // Make the button span two columns
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make the button horizontally fill the cell
        paymentPanel.add(payButton, gbc);

        this.add(paymentPanel, BorderLayout.NORTH);

    }
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> { // event queue is threading related
            new Login().setVisible(true); // makes GUI appear on screen 
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) // performed for an actionListener
    {
        name = nameInput.getText();
        creditCardNumber = Integer.parseInt(creditCardNumberInput.getText());
        expiryDate = expiryDateInput.getText(); // CONVERT FROM STRING TO DATE OBJECT
        CSV = Integer.parseInt(CSVInput.getText());
        
        if(validatePaymentInfo(name, creditCardNumber, expiryDate, CSV)) // add checks for all user types
        {
            this.dispose();
            SelectFlightPage nextPage = new SelectFlightPage();// navigate to next page
        }
    }

    public boolean validatePaymentInfo(String name, int creditCardNumber, Date expiryDate, int CSV)
    {
        // NEED TO IMPLEMENT VALIDATE PAYMENT INFO
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

    public boolean validateLoginInfo(String username, String password)
    {
        // check username and password

        if (true)
        {
            return(true);
        }

        else
        {
            JOptionPane.showMessageDialog(this, username + " or " + password + "was invalid");
            return(false);
        }
    }
    
}
