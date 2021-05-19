package server.mail;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import shared.transferobjects.IBooking;
import shared.transferobjects.ISeat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler
{
  private final String path = "src/server/mail/mailOrder.pdf";
  private File file;
  private Document document;

  String[] seatNos;

  public FileHandler()
  {
    file = new File(path);
    document = new Document();
  }

  public void createPDF(IBooking booking, ArrayList<ISeat> seats)
  {
    setSeats(seats);
    for (int i = 0; i < seats.size(); i++)
    {
      seatNos[i] = seats.get(i).getSeatNo();
    }
    try
    {
      PdfWriter.getInstance(document, new FileOutputStream(file));
      document.open();

      DocumentBuilder documentBuilder = new DocumentBuilder(document);
      documentBuilder.setBooking(booking);

      documentBuilder.setUpImage();

      documentBuilder.setUpBookingId();

      documentBuilder.settingUpFName();

      documentBuilder.SettingUpMovieTitle();

      documentBuilder.settingUpDateAndTime();

      documentBuilder.SettingUpHallNo();

      for (String seat : seatNos)
      {
        documentBuilder.settingUpSeat(seat);
      }

      document = documentBuilder.getDocument();
      document.close();
    }
    catch (DocumentException | IOException e)
    {
      e.printStackTrace();
    }
  }

  private void setSeats(ArrayList<ISeat> seats)
  {
    seatNos = new String[seats.size()];
  }


}
