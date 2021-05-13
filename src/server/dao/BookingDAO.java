package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.Booking;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;
import shared.transferobjects.User;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingDAO
{
  Booking create(Showing showing, User user) throws ServerException;
  ArrayList<Booking> getAllBookings() throws ServerException;
  void removeBooking(Booking booking) throws ServerException;
  ArrayList<Seat> getOccupiedSeats(Showing showing) throws ServerException;

}
