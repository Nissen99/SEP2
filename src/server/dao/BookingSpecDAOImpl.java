package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.Booking;
import shared.transferobjects.Seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingSpecDAOImpl extends BaseDAO implements BookingSpecDAO
{
  @Override public void create(Booking booking, Seat seat)
      throws ServerException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO BookingSpec (bookingId, seatNo, showingId) VALUES (?, ?, ?)");

      statement.setInt(1, booking.getBookingId());
      statement.setString(2, seat.getSeatNo());
      statement.setInt(3, booking.getShowing().getId());
      statement.executeUpdate();
    }
    catch (SQLException throwables)
    {
      throw new ServerException(throwables.getMessage());
    }
  }
}
