package server.mail;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import shared.transferobjects.IBooking;
import shared.transferobjects.ISeat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Diretor for Document builder
 */
public class DocumentDirector
{
  private DocumentBuilder documentBuilder;

  private String[] seatNos;

  public DocumentDirector()
  {

      documentBuilder = new DocumentBuilder();
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

    }
    catch (DocumentException | IOException e)
    {
      e.printStackTrace();
    } finally
    {
      documentBuilder.finishBuild();
    }
  }

  private void setSeats(ArrayList<ISeat> seats)
  {
    seatNos = new String[seats.size()];
  }


}
