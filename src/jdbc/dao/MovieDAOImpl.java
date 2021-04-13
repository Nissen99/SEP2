package jdbc.dao;

import shared.transferobjects.Booking;
import shared.transferobjects.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDAOImpl extends BaseDAO implements MovieDAO
{
  @Override public Movie create(String movieTitle) throws SQLException
  {
      try(Connection connection = getConnection())
      {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Movie (title) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, movieTitle);
        statement.executeUpdate();
        ResultSet keys = statement.getGeneratedKeys();
        if (keys.next()){
        return new Movie(keys.getInt(1), movieTitle);
      }else {
      throw new SQLException("No keys generated");
      }}
  }
}
