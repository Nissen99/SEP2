package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.*;

import java.sql.*;
import java.util.ArrayList;

public class BookingDAOImpl extends BaseDAO implements BookingDAO
{

  @Override public IBooking create(IShowing showing, IUser user)
      throws ServerException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO Booking (showingId, userId) VALUES (?, ?)",
          PreparedStatement.RETURN_GENERATED_KEYS);

      statement.setInt(1, showing.getId());

      statement.setInt(2, user.getUserID());
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        return new Booking(keys.getInt("bookingId"), showing, user);
      }
      else
      {
        throw new SQLException();
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
      throw new ServerException("Database connection failed");
    }
  }

  @Override public ArrayList<IBooking> getAllBookings() throws ServerException
  {
    ArrayList<IBooking> bookingArrayList = new ArrayList<>();
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT s.movieId, title, b.userId, username, email,password, hallNo, b.showingId, time, bookingId\n"
          + "FROM Booking b\n" + "JOIN User_ u on b.userId = u.userId\n"
          + "JOIN Showing s on b.showingId = s.showingId\n"
          + "Join Movie m on s.movieId = m.movieId;");
      ResultSet bookings = statement.executeQuery();
      while (bookings.next()){


        IMovie movie = new Movie(bookings.getInt("movieId"),
            bookings.getString("title"));
        IUser user = new User(bookings.getInt("userId"),
            bookings.getString("username"),
            bookings.getString("email"),"password");
        IHall hall = new Hall(bookings.getString("hallNo"),16,14);
        IShowing showing = new Showing(bookings.getInt("showingId"),
            movie,
            bookings.getTimestamp("time"), hall);
        IBooking booking = new Booking(bookings.getInt("bookingId"),
            showing,user);

        bookingArrayList.add(booking);
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
      throw new ServerException("Database connection failed");

    }
    return bookingArrayList;
  }

  @Override public void removeBooking(IBooking booking) throws ServerException
  {
    try (Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("DELETE FROM Booking WHERE bookingId = ?");
      statement.setInt(1, booking.getBookingId());
      statement.executeUpdate();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
      throw new ServerException("Database connection failed");

    }
  }

  @Override public ArrayList<ISeat> getOccupiedSeats(IShowing showing)
      throws ServerException

  {
    ArrayList<ISeat> seatArrayList = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT seatNo FROM BookingSpec WHERE showingId = ?;");
      statement.setInt(1, showing.getId());
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next())
      {
        ISeat seat = new Seat();
        seat.setSeatNo(resultSet.getString("seatNo"));
        seatArrayList.add(seat);
      }
      return seatArrayList;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
      throw new ServerException("Database connection failed");

    }
  }

}
