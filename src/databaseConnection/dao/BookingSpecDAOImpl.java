package databaseConnection.dao;

import shared.transferobjects.Booking;
import shared.transferobjects.Seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingSpecDAOImpl extends BaseDAO implements BookingSpecDAO
{
  @Override public void create(Booking booking, Seat seat)
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO BookingSpec (bookingId, seatNo) VALUES (?, ?)");

      statement.setInt(1, booking.getBookingId());
      statement.setString(2, seat.getSeatNo());
      statement.executeUpdate();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }
}
