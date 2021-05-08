package server.mail;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import shared.transferobjects.Booking;
import shared.transferobjects.Seat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class FileHandler
{
  private final String path = "src/server/mail/mailOrder.pdf";
  private final String logoPath = "src/shared/image/nyt.jpg";
  private File file;
  private Document document;

  public FileHandler()
  {
    file = new File(path);
    System.out.println("Creating file at: " + file.getAbsolutePath());
    document = new Document();

  }

  public void createPDF(Booking booking, ArrayList<Seat> seats)
  {
    String movieTitle = booking.getShowing().getMovie().getMovieTitle();
    String bookingID = String.valueOf(booking.getBookingId());
    String fName = booking.getUser().getName();
    String dateTime = booking.getShowing().getDate() + "   " + booking.getShowing().getTime();
    String hallNo = booking.getShowing().getHall().getHallNo();
    String[] seatNos = new String[seats.size()];
    for (int i = 0; i < seats.size(); i++)
    {
      seatNos[i] = seats.get(i).getSeatNo();

    }
    try
    {
      PdfWriter.getInstance(document, new FileOutputStream(file));
      document.open();

      Image image = Image.getInstance(logoPath);
      image.setAlignment(Element.ALIGN_TOP);
      image.setAlignment(Element.ALIGN_CENTER);
      document.add(image);

      //Setting up BookingId
      Paragraph pBookingID = setupParagraph(10f,"BookingID: " + bookingID, 10,false);
      pBookingID.setSpacingBefore(13f);
      document.add(pBookingID);


      //Setting up fName
      Paragraph pFName = setupParagraph(10f,"Name: " + fName, 10,false);
      document.add(pFName);

      //Setting up Movie Title
      Paragraph pMovieTitle = setupParagraph(10f,movieTitle,30,true);
      pMovieTitle.setSpacingBefore(30f);
      pMovieTitle.setSpacingAfter(15f);
      document.add(pMovieTitle);

      //Setting up Date & Time
      Paragraph pDateTime = setupParagraph(10f,dateTime,12,true);
      document.add(pDateTime);

      //Setting up HallNo
      Paragraph pHall = setupParagraph(10f,"Hall: " + hallNo, 20,true);
      pHall.getFont().setStyle(Font.BOLD);
      pHall.setSpacingBefore(69f);
      pHall.setSpacingAfter(20f);
      document.add(pHall);

      //Setting up seats
      for (String seat : seatNos)
      {
        Paragraph pSeat = setupParagraph(20f,"Seat Number: " +seat,12,true);

        document.add(pSeat);
      }



      document.close();
    }
    catch (DocumentException | IOException e)
    {
      e.printStackTrace();
    }
  }

  public Paragraph setupParagraph(Float linespace, String element, int fontSize, boolean centerAlign)
      throws DocumentException
  {
    Paragraph p = new Paragraph(
        new Phrase(linespace, element,
            FontFactory.getFont(FontFactory.COURIER, fontSize)));
    if (centerAlign)
      p.setAlignment(Element.ALIGN_CENTER);
    return p;
  }

}
