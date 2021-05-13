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
  Booking addBooking(Showing showing, User user, ArrayList<Seat> seats)
      throws ServerException;
  void removeBooking(Booking booking) throws SQLException;
  ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws SQLException;
  ArrayList<Booking> getBookingList() throws SQLException;
}
