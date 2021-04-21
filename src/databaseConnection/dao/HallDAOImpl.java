package databaseConnection.dao;


import shared.transferobjects.Hall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HallDAOImpl extends BaseDAO implements HallDAO
{
  @Override public Hall create(Hall hall) throws SQLException
  {
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("INSERT INTO Hall(hallNo, maxSeatInRow, maxRows) VALUES (?, ?, ?)");
      statement.setString(1, hall.getHallNo());
      statement.setInt(2, hall.getMaxSeatsInRow());
      statement.setInt(3, hall.getMaxRows());
      statement.executeUpdate();
      return hall;
    }

  }
}
