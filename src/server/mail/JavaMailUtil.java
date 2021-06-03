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

  // En static metode, for at sende mails
  public static void sendMail(String recipient)
      throws MessagingException, IOException
  {
    // Opretter og opsætter Properties
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", true); //Kontrollerer om der er behov for godkendelse til serveren,hvilket er et krav, når der benyttes af Gmail server.
    properties.put("mail.smtp.starttls.enable", true); // tls krypteringsprotokol. for kryptering af internettrafikken. gmail beskytter oplysningerne automatisk, ved at kryptere mailen under levering.
    properties.put("mail.smtp.host", "smtp.gmail.com"); //  opsætning af host. smtp.gmail.com er host for gmail.
    properties.put("mail.smtp.port", "587"); // porten der forbindes til.

    String myAccountEmail = "sepTest2021@gmail.com"; // email-kontoen vi har lavet for at systemet kan sende mails fra.
    String password = "S123456789s"; // password til email-kontoen

    // Her logges ind, ved at benytte vores emailadresse og password.
    Session session = Session.getInstance(properties, new Authenticator()
    {
      @Override protected PasswordAuthentication getPasswordAuthentication()
      {
        return new PasswordAuthentication(myAccountEmail, password);
      }
    });


    // Klaregør en besked, af typen Message. Se link for yderlige info om det eksterne jar-files.
   // https://javaee.github.io/javamail/
    Message message = prepareMessage(session, myAccountEmail, recipient);
    Transport.send(message); // Tager langt tid, kan ikke optimeres grundet ekstern library.
    System.out.println("Mail sent");
  }

  // statisk metode, for klargøreing af en besked
  private static Message prepareMessage(Session session, String myAccountEmail,
      String recipient) throws MessagingException, IOException
  {
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(myAccountEmail));  // fra hvilke mailadresse
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));  // modtageren
    message.setSubject("Biograf Billet"); // emnefeltet

    Multipart multipart = new MimeMultipart(); //Opretter MultiPart objekt

    MimeBodyPart bodyPart = new MimeBodyPart(); //Opretter BodyPart
    File file = new File(path); //Fetcher filen der skal sendes

    bodyPart.attachFile(file); //Tilføjer filen til bodyPart

    multipart.addBodyPart(bodyPart); //Tilføjer bodyPart til multipart

    message.setContent(multipart); //Tilføjer multipart til message objektet

    return message;

  }

}
