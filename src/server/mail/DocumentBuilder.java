package server.mail;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import shared.transferobjects.IBooking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Builder pattern
 */
public class DocumentBuilder
{
  private Document document;
  private final String logoPath = "src/shared/image/emailLogo.jpg";
  private final String path = "src/server/mail/mailOrder.pdf";
  private File file;
  private String movieTitle;
  private String bookingID;
  private String fName;
  private String dateTime;
  private String hallNo;


  public void setBooking(IBooking booking)
  {
    movieTitle = booking.getShowing().getMovie().getMovieTitle();
    bookingID = String.valueOf(booking.getBookingId());
    fName = booking.getUser().getUserName();
    dateTime = booking.getShowing().getDate() + "   " + booking.getShowing().getTime();
    hallNo = booking.getShowing().getHall().getHallNo();
  }


  public DocumentBuilder()
  {
    try
    {
      this.document = new Document();
      file = new File(path);
      PdfWriter.getInstance(document, new FileOutputStream(file));
      document.open();
    }
    catch (DocumentException | FileNotFoundException e)
    {
      e.printStackTrace();
    }

  }


  public void settingUpSeat(String seat) throws DocumentException
  {
    Paragraph pSeat = setupParagraph(20f, "Seat Number: " + seat, 12, true);
    document.add(pSeat);
  }

  public void settingUpDateAndTime()
      throws DocumentException
  {
    Paragraph pDateAndTime = setupParagraph(10f, dateTime, 12, true);
    document.add(pDateAndTime);
  }

  public void settingUpFName() throws DocumentException
  {
    Paragraph pFName = setupParagraph(10f, "Name: " + fName, 10, false);
    document.add(pFName);
  }

  public void SettingUpHallNo() throws DocumentException
  {
    Paragraph pHall = setupParagraph(10f,"Hall: " + hallNo, 20,true);
    pHall.getFont().setStyle(Font.BOLD);
    pHall.setSpacingBefore(69f);
    pHall.setSpacingAfter(20f);
    document.add(pHall);
  }



  public void SettingUpMovieTitle() throws DocumentException
  {
    Paragraph pMovieTitle = setupParagraph(10f, movieTitle,30,true);
    pMovieTitle.setSpacingBefore(30f);
    pMovieTitle.setSpacingAfter(15f);
    document.add(pMovieTitle);
  }



  public void setUpBookingId() throws DocumentException
  {
    Paragraph pBookingID = setupParagraph(10f,"BookingID: " + bookingID, 10,false);
    pBookingID.setSpacingBefore(13f);
    document.add(pBookingID);
  }

  public void setUpImage() throws IOException, DocumentException
  {
    Image image = Image.getInstance(logoPath);
    image.setAlignment(Element.ALIGN_TOP);
    image.setAlignment(Element.ALIGN_CENTER);
    document.add(image);
  }

  private Paragraph setupParagraph(Float linespace, String element, int fontSize, boolean centerAlign)
  {
    Paragraph p = new Paragraph(
        new Phrase(linespace, element,
            FontFactory.getFont(FontFactory.COURIER, fontSize)));
    if (centerAlign)
      p.setAlignment(Element.ALIGN_CENTER);
    return p;
  }


  public void finishBuild()
  {
    document.close();
  }
}
