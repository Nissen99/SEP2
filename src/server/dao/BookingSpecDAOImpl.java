package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.IBooking;
import shared.transferobjects.ISeat;
import shared.transferobjects.IShowing;
import shared.transferobjects.Seat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingSpecDAOImpl extends BaseDAO implements BookingSpecDAO
{

  @Override public void create(IBooking booking, ISeat seat)
      throws ServerException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO BookingSpec (bookingId, seatNo) VALUES (?, ?)");

      statement.setInt(1, booking.getBookingId());
      statement.setString(2, seat.getSeatNo());
      statement.executeUpdate();

    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
      throw new ServerException("Database Connection Failed");
    }
  }


  /**
   * Alle sæder i BookingSpec er Occupied sæder, så hvis det ligger der er det optaget
   * @param showing den showing der søges efter
   * @return liste af alle bookede sæder
   * @throws ServerException problemer med forbindelse til databasen
   */
  @Override public ArrayList<ISeat> getOccupiedSeats(IShowing showing)
      throws ServerException
  {
    ArrayList<ISeat> seatArrayList = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT seatNo\n" + "FROM BookingSpec b\n"
              + "JOIN Booking on b.bookingId = Booking.bookingId\n"
              + "WHERE showingId = ?");
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
      throw new ServerException("Database fejl");
    }
  }


}
