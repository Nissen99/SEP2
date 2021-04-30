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
  private static String path = "C:\\Users\\mathi\\IdeaProjects\\SEP2\\src\\server\\mail\\mailOrder.pdf";


  public static void sendMail(String recepient)
      throws MessagingException, IOException
  {
    Properties properties = new Properties();

    System.out.println("Gør klar til at sende mail");

    properties.put("mail.smtp.auth", true);
    properties.put("mail.smtp.starttls.enable", true);
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");

    String myAccountEmail = "sepTest2021@gmail.com";
    String password = "S123456789s";

    Session session = Session.getInstance(properties, new Authenticator()
    {
      @Override protected PasswordAuthentication getPasswordAuthentication()
      {
        return new PasswordAuthentication(myAccountEmail, password);
      }
    });

    Message message = prepareMessage(session, myAccountEmail, recepient);
    //Transport.send(message);
    System.out.println("Beskeden er sendt succesfuldt");
  }

  private static Message prepareMessage(Session session, String myAccountEmail,
      String recepient) throws MessagingException, IOException
  {
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(myAccountEmail));
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
    message.setSubject("Min første mail");

    MimeBodyPart bodyPart = new MimeBodyPart();
    File file = new File(path);
    System.out.println(file.exists() + " hvad?");

    bodyPart.attachFile(file);

    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(bodyPart);

    message.setContent(multipart);

    return message;

  }

}
