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

public class ServerModelBookingManager implements ServerModelBooking
{
  private BookingDAO bookingDAO = new BookingDAOImpl();
  private BookingSpecDAO bookingSpecDAO = new BookingSpecDAOImpl();
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


  @Override public void addBooking(IShowing showing, IUser user,
      ArrayList<ISeat> seats) throws ServerException
  {
    if (seats.isEmpty()){
      throw new ServerException("Sæderne du havde valgt blev optaget");
    }
    IBooking booking;
    try
    {
      booking = bookingDAO.create(showing,user);
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
    bookingDAO.removeBooking(booking);

  }

  @Override public ArrayList<ISeat> getOccupiedSeats(IShowing showing)
      throws ServerException

  {
    return bookingDAO.getOccupiedSeats(showing);
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
