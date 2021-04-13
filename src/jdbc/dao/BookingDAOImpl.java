package jdbc.dao;

import shared.transferobjects.Booking;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;
import shared.transferobjects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingDAOImpl extends BaseDAO implements BookingDAO
{
  @Override
  public Booking create(int bookingId, Showing showing, User user) throws
      SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO Booking (bookingId, showingId, userId) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setInt(1, bookingId);
      statement.setInt(2, showing.getId());
      statement.setString(3, user.getName());
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next()) {
        return new Booking(showing, user, keys.getInt("bookingId"));
      } else {
        throw new SQLException("No keys generated");
      }
    }
  }
}
