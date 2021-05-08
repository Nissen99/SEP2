package databaseConnection.dao;

import shared.transferobjects.*;

import java.awt.print.Book;
import java.sql.*;
import java.util.ArrayList;

public class BookingDAOImpl extends BaseDAO implements BookingDAO
{

  @Override public Booking create(Showing showing, User user)
      throws SQLException
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
  }

  @Override public ArrayList<Booking> getAllBookings() throws SQLException
  {
    ArrayList<Booking> bookingArrayList = new ArrayList<>();
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT s.movieId, title, b.userId, username, email,password, hallNo, b.showingId, time, bookingId\n"
          + "FROM Booking b\n" + "JOIN User_ u on b.userId = u.userId\n"
          + "JOIN Showing s on b.showingId = s.showingId\n"
          + "Join Movie m on s.movieId = m.movieId;");
      ResultSet bookings = statement.executeQuery();
      while (bookings.next()){

        Movie movie = new Movie(bookings.getInt("movieId"),
            bookings.getString("title"));
        User user = new User(bookings.getInt("userId"),
            bookings.getString("username"),
            bookings.getString("email"),"password");
        Hall hall = new Hall(bookings.getString("hallNo"),16,14);
        Showing showing = new Showing(bookings.getInt("showingId"),
            movie,
            bookings.getTimestamp("time"), hall);
        Booking booking = new Booking(bookings.getInt("bookingId"),
            showing,user);

        bookingArrayList.add(booking);
      }
    }
    return bookingArrayList;
  }

  @Override public void removeBooking(Booking booking) throws SQLException
  {
    try (Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("DELETE FROM Booking WHERE bookingId = ?");
      statement.setInt(1, booking.getBookingId());
      statement.executeUpdate();
    }
  }

  @Override public ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws SQLException
  {
    ArrayList<Seat> seatArrayList = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT seatNo FROM BookingSpec\n"
              + "JOIN Booking B ON B.bookingId = BookingSpec.bookingId\n"
              + "WHERE showingId = ?;");
      statement.setInt(1, showing.getId());
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next())
      {
        Seat seat = new Seat();
        seat.setSeatNo(resultSet.getString("seatNo"));
        seatArrayList.add(seat);
      }
      return seatArrayList;
    }
  }
/*
  @Override public Booking getBookingByID(int bookingId) throws SQLException
  {
    try (Connection connection = getConnection())
    {

      PreparedStatement statement = connection.prepareStatement(
          "SELECT b.bookingId,fName,lName,email,time,hallNo,title,b.userId\n"
              + "FROM Booking b\n"
              + "join Showing s on b.showingId = s.showingId\n"
              + "join Movie M on s.movieId = M.movieId\n"
              + "join User_ u on u.userId = b.userId\n"
              + "where b.bookingId = ?");
      statement.setInt(1, bookingId);
      ResultSet resultSet = statement.executeQuery();
      Booking booking = null;
      User user = null;
      Showing showing = null;
      Movie movie = null;
      Timestamp timestamp = null;
      Hall hall = null;

      if (resultSet.next())
      {
        hall = new Hall(resultSet.getString("hallNo"),resultSet.get)
        user = new User(resultSet.getInt("userId"),resultSet.getString("fName"),resultSet.getString("email"));
        showing = new Showing(movie,timestamp,hall);
        booking = new Booking(resultSet.getInt("bookingId"),showing,user);
      }
      System.out.println(booking);
      return booking;
    }

  }

 */
}
