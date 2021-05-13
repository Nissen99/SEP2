package server.dao;


import shared.transferobjects.Hall;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

  @Override public ArrayList<String> getHallNumbers() throws SQLException
  {
    ArrayList<String> stringArrayList = new ArrayList<>();
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT hallNo FROM hall;");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()){
        stringArrayList.add(resultSet.getString("hallNo"));
      }
      return stringArrayList;
    }
  }
}
