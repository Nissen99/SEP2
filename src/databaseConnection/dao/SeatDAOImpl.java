package databaseConnection.dao;

import shared.transferobjects.Hall;
import shared.transferobjects.Seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatDAOImpl extends BaseDAO implements SeatDAO
{
  @Override public void create(Seat seat, Hall hall)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO Seat(seatNo, hallNo) VALUES (?, ?)");
      statement.setString(1, seat.getSeatNo());
      statement.setString(2, hall.getHallNo());
      statement.executeUpdate();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }

  }

  @Override public Seat getSeatBySeatNo(String seatNo)
  {
    Seat seat = null;
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Seat WHERE seatNo = ?");
      statement.setString(1, seatNo);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next())
      {
        seat = new Seat();
        seat.setSeatNo(resultSet.getString("seatNo"));
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return seat;

  }

}