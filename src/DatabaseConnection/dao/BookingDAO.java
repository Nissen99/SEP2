package DatabaseConnection.dao;

import shared.Booking;
import shared.Showing;
import shared.User;

import java.sql.SQLException;

public interface BookingDAO
{
  Booking create(Showing showing, User user) throws SQLException;
  //ArrayList<Booking> getAllBookings() throws SQLException;
}
