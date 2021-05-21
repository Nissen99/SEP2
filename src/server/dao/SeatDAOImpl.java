package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.IHall;
import shared.transferobjects.ISeat;
import shared.transferobjects.Seat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatDAOImpl extends BaseDAO implements SeatDAO
{

  @Override public void create(ISeat seat, IHall hall) throws ServerException
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
      System.out.println(throwables.getMessage());
      throw new ServerException("Database fejl");
    }

  }

  @Override public ISeat getSeatBySeatNo(String seatNo) throws ServerException
  {
    ISeat seat = null;
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
      throw new ServerException("Database fejl");
    }
    return seat;

  }

}