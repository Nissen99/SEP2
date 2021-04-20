package databaseConnection.dao;

import shared.Booking;
import shared.Seat;
import shared.Showing;
import shared.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingDAO
{
  Booking create(Showing showing, User user, String seatNo) throws SQLException;
  //ArrayList<Booking> getAllBookings() throws SQLException;
  ArrayList<Seat> getOccupiedSeats(Showing showing) throws SQLException;
}
