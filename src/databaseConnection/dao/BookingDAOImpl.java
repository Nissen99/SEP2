package databaseConnection.dao;

import shared.transferobjects.Booking;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;
import shared.transferobjects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDAOImpl extends BaseDAO implements BookingDAO
{

  @Override
  public Booking create(Showing showing, User user, String seatNo) throws
      SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO Booking (showingId, userId, seatNo) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

      statement.setInt(1, showing.getId());

      statement.setInt(2, user.getUserID());
      statement.setString(3, seatNo);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next()) {
        return new Booking(keys.getInt("bookingId"), showing, user, seatNo);
      } else {
        throw new SQLException();
      }
    }
  }

  @Override public ArrayList<Seat> getOccupiedSeats(Showing showing) throws SQLException
  {
    ArrayList<Seat> seatArrayList = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT seatNo FROM booking WHERE showingId = ?");
      statement.setInt(1, showing.getId());
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()){
        Seat seat = new Seat();
        seat.setSeatNo(resultSet.getString("seatNo"));
        seatArrayList.add(seat);
      }
      return seatArrayList;
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
