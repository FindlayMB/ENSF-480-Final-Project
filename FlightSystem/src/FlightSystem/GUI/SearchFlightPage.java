package FlightSystem.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.concurrent.Flow;
import FlightSystem.objects.*;
import FlightSystem.objects.user.RegisteredUser;
public class SearchFlightPage extends JFrame implements ActionListener{

    private JTextField flightSearchInput;
    private RegisteredUser signedInUser;
    private JButton searchButton = new JButton("Search");
    private JButton backButton = new JButton("Back");

    // search flight by destination and move to select flight page
    public SearchFlightPage(RegisteredUser signedInUser)
    {
        super("Search Flight");
        this.signedInUser = signedInUser;
        setupGUI();
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EventQueue.invokeLater(() -> { // event queue is threading related
            this.setVisible(true); // makes GUI appear on screen 
        });
    }

    public void setupGUI()
    {
        JLabel flightSearchLabel = new JLabel("Enter flight destination:");

        flightSearchInput = new JTextField();
        flightSearchInput.setColumns(20); // Set the preferred number of columns


        searchButton.addActionListener(this);
        backButton.addActionListener(this);

        // Create a panel with FlowLayout for label and text field
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(flightSearchLabel);
        searchPanel.add(flightSearchInput);
        searchPanel.add(searchButton);
        searchPanel.add(backButton);
        this.add(searchPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) // Search once search button pushed
    {
        this.dispose();
        if(e.getSource() == searchButton)
        {
            SelectFlightPage nextPage = new SelectFlightPage(signedInUser, flightSearchInput.getText()); // navigate to next page
        }
        else if(e.getSource() == backButton)
        {
            HomePage homePage = new HomePage(signedInUser);
        }
    }

    

    // public static void main(String[] args)
    // {
    //     EventQueue.invokeLater(() -> { // event queue is threading related
    //         new SearchFlightPage().setVisible(true); // makes GUI appear on screen 
    //     });
    // }
}
