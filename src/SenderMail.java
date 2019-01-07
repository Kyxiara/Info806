import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class SenderMail {

    // Replace sender@example.com with your "From" address.
    // This address must be verified.
    private String FROM = "kelly73street@gmail.com";
    private String FROMNAME = "Kelly";

    // Replace recipient@example.com with a "To" address. If your account
    // is still in the sandbox, this address must be verified.
    private String TO;

    // Replace smtp_username with your Amazon SES SMTP user name.
    private String SMTP_USERNAME = "kelly73street@gmail.com";

    // The name of the Configuration Set to use for this message.
    // If you comment out or remove this variable, you will also need to
    // comment out or remove the header below.
    private String CONFIGSET = "ConfigSet";

    // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
    // See https://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
    // for more information.
    private String HOST = "smtp.gmail.com";

    // The port you will connect to on the Amazon SES SMTP endpoint.
    private int PORT = 587;

    private String SUBJECT = "Anniversaire";

    private String BODY = String.join(
            System.getProperty("line.separator"),
            "<h1>Joyeux anniversaire</h1>",
            "<p>Kelly vous souhaite un très joyeux anniversaire !</a>."
    );

    private String pw;
    private Person person;

    public SenderMail(String pw, Person person){
        this.pw = pw;
        this.person = person;
        TO = person.getEmail();
    }

    public void send() throws UnsupportedEncodingException, MessagingException {

        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.smtp.host", HOST);
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Create a Session object to represent a mail session with the specified properties.
        Session session = Session.getDefaultInstance(props);

        // Create a message with the specified information.
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM,FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY,"text/html");

        // Add a configuration set header. Comment or delete the
        // next line if you are not using a configuration set
        msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

        // Create a transport.
        Transport transport = session.getTransport();

        // Send the message.
        try
        {
            System.out.println("Sending...");

            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, pw);

            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
        finally
        {
            // Close and terminate the connection.
            transport.close();
        }
    }
}
