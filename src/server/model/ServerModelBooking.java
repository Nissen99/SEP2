package server.model;

import shared.exception.ServerException;
import shared.transferobjects.Booking;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;
import shared.transferobjects.User;
import shared.util.PropertyChangeSubject;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ServerModelBooking extends PropertyChangeSubject
{
  void addBooking(Showing showing, User user, ArrayList<Seat> seats)
      throws ServerException;
  void removeBooking(Booking booking) throws ServerException;
  ArrayList<Seat> getOccupiedSeats(Showing showing) throws ServerException;
  ArrayList<Booking> getBookingList() throws ServerException;
}
