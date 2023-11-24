package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener, MouseListener
{
    private String username;
    private String password;

    private JLabel usernameLabel; // text in GUI
    private JLabel passwordLabel;

    private JTextField usernameInput; // text box in GUI
    private JTextField passwordInput;

    
    public Login()
    {
        super("Login"); // create a frame
        setupGUI();
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // EventQueue.invokeLater(() -> { // event queue is threading related
        //     new Login().setVisible(true); // makes GUI appear on screen 
        // });
    }
    
    public void setupGUI()
    {
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");

        usernameInput = new JTextField("Enter Username");
        passwordInput = new JTextField("Enter Password");

        usernameInput.addMouseListener(this);
        passwordInput.addMouseListener(this);

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(this);


        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
        loginPanel.add(usernameLabel, gbc);

        gbc.gridx = 10;
        loginPanel.add(usernameInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 20;
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 10;
        loginPanel.add(passwordInput, gbc);

        gbc.gridy = 30;
        gbc.gridwidth = 2; // Make the button span two columns
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make the button horizontally fill the cell
        loginPanel.add(loginButton, gbc);

        this.add(loginPanel, BorderLayout.NORTH);

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
        username = usernameInput.getText();
        password = passwordInput.getText();

        if(validateLoginInfo(username, password)) // add checks for all user types
        {
            this.dispose();
            SelectFlightPage nextPage = new SelectFlightPage();// navigate to next page
        }
    }

    public void mouseClicked(MouseEvent event) { 

        if (event.getSource().equals(usernameInput))
        {
            usernameInput.setText(""); // clear the default text
        }

        if(event.getSource().equals(passwordInput))
        {
            passwordInput.setText("");
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
        // NEED TO IMPLEMENT check username and password

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

