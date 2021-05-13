package server.model;

import server.dao.BookingDAO;
import server.dao.BookingDAOImpl;
import server.dao.BookingSpecDAO;
import server.dao.BookingSpecDAOImpl;
import server.mail.FileHandler;
import server.mail.JavaMailUtil;
import shared.exception.ServerException;
import shared.transferobjects.Booking;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;
import shared.transferobjects.User;
import shared.util.ENUM;
import javax.mail.MessagingException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;

public class ServerModelBookingManager implements ServerModelBooking
{
  private BookingDAO bookingDAO = new BookingDAOImpl();
  private BookingSpecDAO bookingSpecDAO = new BookingSpecDAOImpl();
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


  @Override public void addBooking(Showing showing, User user,
      ArrayList<Seat> seats) throws ServerException
  {
    Booking booking;
    try
    {
      booking = bookingDAO.create(showing,user);
      for (Seat seat : seats)
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

  @Override public void removeBooking(Booking booking) throws ServerException

  {
    bookingDAO.removeBooking(booking);

  }

  @Override public ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws ServerException

  {
    return bookingDAO.getOccupiedSeats(showing);
  }

  @Override public ArrayList<Booking> getBookingList()
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
