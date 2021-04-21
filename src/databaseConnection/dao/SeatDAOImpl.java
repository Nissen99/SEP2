package databaseConnection.dao;

import shared.transferobjects.Hall;
import shared.transferobjects.Seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SeatDAOImpl extends BaseDAO implements SeatDAO
{
  @Override public void create(Seat seat, Hall hall)
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO Seat(seatNo, isOccupied, hallNo) VALUES (?, ?, ?)");
      statement.setString(1, seat.getSeatNo());
      statement.setBoolean(2, seat.IsOccupied());
      statement.setString(3, hall.getHallNo());
      statement.executeUpdate();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }

  }

  @Override public void setSeatOccupiedStatus(Seat seat, boolean b)
  {
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("UPDATE seat set isOccupied = ?");
      statement.setBoolean(1, b);
      statement.executeUpdate();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }
}
