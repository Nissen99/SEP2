package server.mail;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import shared.transferobjects.Booking;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler
{
  private final String path = "C:\\Users\\Mikkel\\IdeaProjects\\SEP2Kode\\src\\server\\mail\\mailOrder.pdf";
  /** HUSK AT ÆNDRE PATH **/
  private File file;
  private Document document;

  public FileHandler()
  {
    file = new File(path);
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

      //Setting up BookingId
      Paragraph pBookingID = new Paragraph(
          new Phrase(10f, "BookingID: " + bookingID,
              FontFactory.getFont(FontFactory.COURIER, 10)));
      document.add(pBookingID);

      //Setting up fName
      Paragraph pFName = new Paragraph(new Phrase(10f, "Name: " + fName,
          FontFactory.getFont(FontFactory.COURIER, 10)));
      document.add(pFName);

      //Setting up Movie Title
      Paragraph pMovieTitle = new Paragraph(new Phrase(10f, movieTitle,
          FontFactory.getFont(FontFactory.COURIER, 30)));
      pMovieTitle.setAlignment(Element.ALIGN_CENTER);
      pMovieTitle.setSpacingBefore(30f);
      pMovieTitle.setSpacingAfter(15f);
      document.add(pMovieTitle);

      //Setting up Date & Time
      Paragraph pDateTime = new Paragraph(new Phrase(10f, dateTime,
          FontFactory.getFont(FontFactory.COURIER, 12)));
      pDateTime.setAlignment(Element.ALIGN_CENTER);
      document.add(pDateTime);

      //Setting up HallNo
      Paragraph pHall = new Paragraph(new Phrase(10f, "Hall: " +hallNo,
          FontFactory.getFont(FontFactory.COURIER_BOLD, 20)));
      pHall.setAlignment(Element.ALIGN_CENTER);
      pHall.setSpacingBefore(150f);
      pHall.setSpacingAfter(20f);
      document.add(pHall);

      //Setting up seats
      for (String seat : seatNos)
      {
        Paragraph pSeat = new Paragraph(new Phrase(30f, "Seat Number: " +seat,
            FontFactory.getFont(FontFactory.TIMES, 12)));
        pSeat.setAlignment(Element.ALIGN_CENTER);
        document.add(pSeat);
      }

      document.close();
    }
    catch (DocumentException | FileNotFoundException e)
    {
      e.printStackTrace();
    }
  }

}
