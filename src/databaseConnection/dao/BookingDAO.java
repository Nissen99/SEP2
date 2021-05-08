package databaseConnection.dao;

import shared.transferobjects.Booking;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;
import shared.transferobjects.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingDAO
{
  Booking create(Showing showing, User user) throws SQLException;
  public ArrayList<Booking> getAllBookings() throws SQLException;
  void removeBooking(Booking booking) throws SQLException;
  ArrayList<Seat> getOccupiedSeats(Showing showing) throws SQLException;
  //Booking getBookingByID(int bookingId) throws SQLException;
}
