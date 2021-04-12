package jdbc.dao;

import shared.transferobjects.Booking;
import shared.transferobjects.Showing;
import shared.transferobjects.User;

import java.sql.SQLException;

public interface BookingDAO
{
  Booking create(int bookingId, Showing showing, User user) throws SQLException;
}
