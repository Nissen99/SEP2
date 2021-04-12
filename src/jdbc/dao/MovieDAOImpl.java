package jdbc.dao;

import shared.transferobjects.Booking;
import shared.transferobjects.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDAOImpl extends BaseDAO implements MovieDAO
{
  @Override public Movie create(int movieId, String movieTitle) throws SQLException
  {
      try(Connection connection = getConnection())
      {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Movie (movieId, title) VALUES (?, ?)");
        statement.setInt(1, movieId);
        statement.setString(2, movieTitle);
        statement.executeUpdate();
        return new Movie(movieId, movieTitle);
      }
  }
}
