package server.model;

import server.dao.BookingDAO;
import server.dao.BookingDAOImpl;
import server.dao.BookingSpecDAO;
import server.dao.BookingSpecDAOImpl;
import server.mail.FileHandler;
import server.mail.JavaMailUtil;
import shared.exception.ServerException;
import shared.transferobjects.*;
import shared.util.ENUM;
import javax.mail.MessagingException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Klasse der kalder videre til BookingDAO, sender mail ved booking og er
 * PropertyChangeSubject der fire ved ny booking
 */
public class ServerModelBookingManager implements ServerModelBooking
{
  private BookingDAO bookingDAO = new BookingDAOImpl();
  private BookingSpecDAO bookingSpecDAO = new BookingSpecDAOImpl();
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

  /**
   * Tilføjer booking gennem kald til databasen
   * @param showing den showing der skal bookes billetter til
   * @param user den brugers navn billetterne skal bookes i
   * @param seats de sæder der skal bookes
   * @throws ServerException connection fejl
   */
  @Override public synchronized void addBooking(IShowing showing, IUser user,
      ArrayList<ISeat> seats) throws ServerException
  {
    try
    {
      IBooking booking = bookingDAO.create(showing,user);
      for (ISeat seat : seats)
      {
        bookingSpecDAO.create(booking, seat);
      }
      FileHandler fileHandler = new FileHandler();
      fileHandler.createPDF(booking,seats);
      JavaMailUtil.sendMail(user.getEmail());
    }
    catch (MessagingException | IOException e)
    {
      e.printStackTrace();
    }
    propertyChangeSupport.firePropertyChange(String.valueOf(ENUM.ADDBOOKING), null, "booking");
  }

  @Override public void removeBooking(IBooking booking) throws ServerException
  {

    if (!bookingDAO.getAllBookings().contains(booking)){
      throw new ServerException("Bookingen eksiterer ikke");
    }
    bookingDAO.removeBooking(booking);
  }

  @Override public ArrayList<ISeat> getOccupiedSeats(IShowing showing)
      throws ServerException
  {
    return bookingSpecDAO.getOccupiedSeats(showing);
  }

  @Override public ArrayList<IBooking> getBookingList()
      throws ServerException
  {
    return bookingDAO.getAllBookings();

  }


  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }


  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }


}
