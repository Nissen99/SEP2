package databaseConnection.dao;


import shared.transferobjects.Hall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

  @Override public Hall getHallByNumber(String hallNo) throws SQLException
  {
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("SELECT * from Hall where hallNo = ?");
      statement.setString(1, hallNo);
      ResultSet resultSet = statement.executeQuery();
      Hall hall = null;
      if (resultSet.next()){
        hall = new Hall(
            resultSet.getString("hallNo"),
            resultSet.getInt("maxSeatInRow"),
            resultSet.getInt("maxRows")
        );

      }
      return hall;
    }
  }
}
