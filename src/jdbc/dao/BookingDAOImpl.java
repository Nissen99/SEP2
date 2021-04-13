package jdbc.dao;

import shared.transferobjects.Booking;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;
import shared.transferobjects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDAOImpl extends BaseDAO implements BookingDAO
{
  private UserDAO userDAO = new UserDAOImpl();

  @Override
  public Booking create(Showing showing, User user) throws
      SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO Booking (showingId, userId) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

      statement.setInt(1, showing.getId());

      statement.setInt(2, user.getUserID());
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next()) {
        return new Booking(showing, user, keys.getInt("bookingId"));
      } else {
        throw new SQLException("No keys generated");
      }
    }
  }

//  @Override public ArrayList<Booking> getAllBookings() throws SQLException
//  {
//    ArrayList<Booking> bookingArrayList = new ArrayList<>();
//    try(Connection connection = getConnection()){
//      PreparedStatement statement = connection.prepareStatement("SELECT * From Booking");
//      ResultSet bookings = statement.executeQuery();
//      while (bookings.next()){
//      //TODO OPret booking og tilføj til listen
//      }
//    }
//    return null;
//  }
}
