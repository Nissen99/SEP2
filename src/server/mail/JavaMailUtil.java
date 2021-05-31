package server.mail;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class JavaMailUtil
{
  private static String path = "src/server/mail/mailOrder.pdf";

  // The static method, for sending mail
  public static void sendMail(String recipient)
      throws MessagingException, IOException
  {
    // setting up the properties
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", true); //checks whether authentication is needed for the server. When using g-mail server it requires authentication
    properties.put("mail.smtp.starttls.enable", true); // tls encryption protocol. for encrypting the web traffic. gmail is protecting your information automatically, by encrypting the email.  ( turning the information in mail into code during delivery)
    properties.put("mail.smtp.host", "smtp.gmail.com"); // setting up the host. smtp.gmail.com is the host for gmail
    properties.put("mail.smtp.port", "587"); // the port it is connecting to.

    String myAccountEmail = "sepTest2021@gmail.com"; // the email account we have made, which we are using to send mails from
    String password = "S123456789s"; // the password for the email account.

    // the part where we login, using our mail information,
    Session session = Session.getInstance(properties, new Authenticator()
    {
      @Override protected PasswordAuthentication getPasswordAuthentication()
      {
        return new PasswordAuthentication(myAccountEmail, password);
      }
    });

    // prepareing a message in the format of a Message. This is a part of the JavaMail API
   // The JavaMail API provides a platform-independent and protocol-independent framework to build mail and messaging applications.
   // https://javaee.github.io/javamail/
    Message message = prepareMessage(session, myAccountEmail, recipient);
    Transport.send(message); // Tager langt tid, kan ikke optimeres grundet ekstern library.
    System.out.println("Mail sent");
  }

  // The static method for preparing the message.
  private static Message prepareMessage(Session session, String myAccountEmail,
      String recipient) throws MessagingException, IOException
  {
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(myAccountEmail));  // from which email-adresse
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));  // the recipient
    message.setSubject("Biograf Billet"); // the subject of the mail

    MimeBodyPart bodyPart = new MimeBodyPart();
    File file = new File(path); // creating a new file, and adding the path of our pdf-file

    bodyPart.attachFile(file);

    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(bodyPart);

    message.setContent(multipart);

    return message;

  }

}
