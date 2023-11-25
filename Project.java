
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Project extends JFrame{

    public Project() {
        setTitle("Air Canada");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel headerLabel = new JLabel("Welcome to Air Company");
        JButton bookFlightButton = new JButton("Search");
        JButton flightStatusButton = new JButton("Sign In");

        // Set font for the title
        headerLabel.setFont(new Font("Serif", Font.BOLD, 30));

        // Set smaller size for buttons
        Dimension buttonSize = new Dimension(100, 30);
        bookFlightButton.setPreferredSize(buttonSize);
        flightStatusButton.setPreferredSize(buttonSize);

        // Create a panel for title and buttons using GridBagLayout
        JPanel titleAndButtonsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add title to the left
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;  // Align to the left
        gbc.insets = new Insets(10, 10, 10, 10);  // Insets for the title
        titleAndButtonsPanel.add(headerLabel, gbc);

        // Create a space between title and buttons
        gbc.gridx = 1;
        gbc.weightx = 1.0;  // Make this cell take any extra horizontal space
        titleAndButtonsPanel.add(new JPanel(), gbc);

        // Add buttons to the right
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;  // Align to the right
        gbc.weightx = 0.0;  // Reset the weight
        gbc.insets = new Insets(10, 0, 10, 10);  // Insets for the buttons
        titleAndButtonsPanel.add(bookFlightButton, gbc);

        gbc.gridx = 3;
        titleAndButtonsPanel.add(flightStatusButton, gbc);

        // Add promotion logo
        ImageIcon originalIcon = new ImageIcon("promotionlogo.png");
        Image originalImage = originalIcon.getImage();
         
        // Scale the image proportionally to fill the width
        int newWidth = getWidth() - 20;  // Adjust as needed
        int newHeight = (int) ((double) newWidth / originalIcon.getIconWidth() * originalIcon.getIconHeight());
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
         
        // Create a new ImageIcon from the scaled image
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);

        // Create buttons for the middle panel
        JButton searchFlightButton = new JButton("Search Flight");
        JButton bookFlightButton2 = new JButton("Book Flight");
        JButton myBookingsButton = new JButton("Flight Status");
        JButton cancelButton = new JButton("Cancel Flight");

        // Create a panel for the middle buttons
        JPanel middleButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        middleButtonPanel.add(searchFlightButton);
        middleButtonPanel.add(bookFlightButton2);
        middleButtonPanel.add(myBookingsButton);
        middleButtonPanel.add(cancelButton);

        // Create a panel for the south buttons
        JButton sButton = new JButton("South Button");
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(sButton);

        // Create a panel for main content using BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(titleAndButtonsPanel, BorderLayout.NORTH);
        mainPanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.add(middleButtonPanel, BorderLayout.SOUTH);

        // Add the main panel to the JFrame
        add(mainPanel);
        add(southPanel, BorderLayout.PAGE_END);
        flightStatusButton.addActionListener(e -> {
            System.out.println("Sign In button clicked");
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Project gui = new Project();
            gui.setVisible(true);
        });
    }
}

