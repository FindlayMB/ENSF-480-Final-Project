package FlightSystem.GUI;

import java.time.LocalDate;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import FlightSystem.objects.flight.Flight;

import javax.mail.PasswordAuthentication;

public class Mail {
    private static Session newSession;
    private static MimeMessage mimeMessage;
    private static String fromUser = "group17airline@gmail.com";
    private final static String emailHost = "smtp.gmail.com";
  

    public static void emailTicket(String firstName, String lastName, String emailRecipientString, String creditCardNumber, LocalDate expiryDate, String CSV, Flight selectedFlight, Integer selectedSeatNum, int totalPrice) throws AddressException, MessagingException 
    {
        setupServerProperties();
        String emailSubjectString = "Reciept and Ticket for Flight";
        String emailBodyString = "Dear " + firstName + " " + lastName + ",\n\n" + "Thank you for booking with us. Please" 
        + "find your ticket and reciept below:\n\n" + "Ticket:\n" + "Name: " + firstName + " " + lastName + "\n" + "Flight Info:\n" 
        + "From: " + selectedFlight.getDestination() + "\nTo: " + selectedFlight.getDestination() + "\nSeat number: " + selectedSeatNum 
        + "\n\n" + "Reciept\n" + "Cardholder: " + firstName + " " + lastName + "\n" + "Card Number: " + creditCardNumber
        + "\n" + "Expiry Date: " + expiryDate + "\n" + "CSV: " + CSV + "\n\n" + "Total Price: " + totalPrice +"$" +"\n\n" + 
        "Kind Regards,\n\n" + "Group 17 Airline";
        mimeMessage = new MimeMessage(newSession);
        mimeMessage.setFrom(new InternetAddress(fromUser));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipientString));
        mimeMessage.setSubject(emailSubjectString);
        mimeMessage.setText(emailBodyString);
        sendEmail();
    }

    public static void sendCancellation(String firstName, String lastName, String emailRecipientString, String creditCardNumber, LocalDate expiryDate, String CSV, Flight selectedFlight, Integer selectedSeatNum, int totalPrice) throws AddressException, MessagingException 
    {
        setupServerProperties();
        String emailSubjectString = "Reciept and Ticket for Flight";
        String emailBodyString = "Dear " + firstName + " " + lastName + ",\n\n" + "Thank you for booking with us. Please " 
        + "find your ticket and reciept below:\n\n" + "Ticket:\n" + "Name: " + firstName + " " + lastName + "\n" + "Flight Info:\n" 
        + "From: " + selectedFlight.getDestination() + "\nTo: " + selectedFlight.getDestination() + "\nSeat number: " + selectedSeatNum 
        + "\n\n" + "Reciept:\n" + "Cardholder: " + firstName + " " + lastName + "\n" + "Card Number: " + creditCardNumber
        + "\n" + "Expiry Date: " + expiryDate + "\n" + "CSV: " + CSV + "\n\n" + "Total Price: " + totalPrice +"$" +"\n\n" + 
        "Kind Regards,\n\n" + "Group 17 Airline Company";
        mimeMessage = new MimeMessage(newSession);
        mimeMessage.setFrom(new InternetAddress(fromUser));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipientString));
        mimeMessage.setSubject(emailSubjectString);
        mimeMessage.setText(emailBodyString);
        sendEmail();
    }


    // public static void main(String args[]) throws AddressException,
    // MessagingException {
    // Mail mail = new Mail();
    // mail.setupServerProperties();
    // mail.draftEmail();
    // mail.sendEmail();
    // }
    private static void sendEmail() throws MessagingException {

        // Transport transport = newSession.getTransport("smtp");
        // transport.connect(emailHost, fromUser, fromUserPassword);
        Transport.send(mimeMessage);
        // Transport.close();
        System.out.println("Email sent successfully.");

    }

    // private static MimeMessage draftEmail() throws AddressException, MessagingException {
    //     String emailSubjectString = "Reciept and Ticket for Flight";
    //     String emailBodyString = "Dear " + firstName + " " + lastName + ",\n\n" + "Thank you for booking with us. Please" 
    //     + "find your ticket and reciept below:\n\n" + "Ticket:\n" + "Name: " + firstName + " " + lastName + "\n" + "Flight Info:\n" 
    //     + "From: " + flight.getDestination() + "\nTo: " + flight.getDestination() + "\nSeat number: " + selectedSeatNum 
    //     + "\n\n" + "Reciept\n" + "Cardholder: " + firstName + " " + lastName + "\n" + "Card Number: " + creditCardNumber
    //     + "\n" + "Expiry Date: " + expiryDate + "\n" + "CSV: " + CSV + "\n\n" + "Total Price: " + totalPrice +"$" +"\n\n" + 
    //     "Kind Regards,\n\n" + "Group 17 Airline";
    //     mimeMessage = new MimeMessage(newSession);
    //     mimeMessage.setFrom(new InternetAddress(fromUser));
    //     mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipientString));
    //     mimeMessage.setSubject(emailSubjectString);
     
    //     mimeMessage.setText(emailBodyString);

    //     return mimeMessage;
    // }

    private static boolean setupServerProperties() {
        Properties properties = new Properties(); // properties object contains host information
        properties.put("mail.smtp.port", "587"); // or 587
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", emailHost);
        newSession = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromUser, "megr cpcl qesw lpms");
            }
        });
        return true;
    }
}
