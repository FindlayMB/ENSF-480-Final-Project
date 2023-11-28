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
import javax.mail.PasswordAuthentication;


public class Mail {
    private static Session newSession;
    private static MimeMessage mimeMessage;
    private static String fromUser = "group17airline@gmail.com";
    private static String emailRecipientString = "theo444hoang@gmail.com";
    private final static String emailHost = "smtp.gmail.com";
    private static String firstName;
    private static String lastName;
    private static String email;
    private static String creditCardNumber;
    private static LocalDate expiryDate;
    private static String CSV;

    public Mail(String firstName, String lastName,String email, creditCardNumber, expiryDate, CSV) throws AddressException, MessagingException {

        setupServerProperties();
        draftEmail();
        sendEmail();

    }
   
    // public static void main(String args[]) throws AddressException, MessagingException {
    //     Mail mail = new Mail();
    //     mail.setupServerProperties();
    //     mail.draftEmail();
    //     mail.sendEmail();
    // }
    private static void sendEmail() throws MessagingException{
        
        // Transport  transport = newSession.getTransport("smtp");
        // transport.connect(emailHost, fromUser, fromUserPassword);
        Transport.send(mimeMessage);
        // Transport.close();
        System.out.println("Email sent successfully.");

    }
    private static MimeMessage draftEmail() throws AddressException, MessagingException{
        String emailSubjectString = "Reciept and Ticket for Flight";
        String emailBodyString = "This is the email body";
        mimeMessage = new MimeMessage(newSession);
        mimeMessage.setFrom(new InternetAddress(fromUser));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipientString));
        mimeMessage.setSubject(emailSubjectString);
     
        mimeMessage.setText("This is the email body");

        return mimeMessage;
    }
    private static boolean setupServerProperties() {
        Properties properties = new Properties(); // properties object contains host information
        properties.put("mail.smtp.port", "587"); //or 587
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


