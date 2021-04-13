package jdbc.dao;

import shared.transferobjects.Booking;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.sql.*;

public class ShowingDAOImpl extends BaseDAO implements ShowingDAO
{

  @Override public Showing create(Movie movie, Timestamp timestamp) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      System.out.println("KOMMER VI HER");
      PreparedStatement statement = connection.prepareStatement("INSERT INTO Showing (movieId, time) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setInt(1, movie.getMovieId());
      statement.setTimestamp(2,timestamp);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next()) {
        return new Showing(movie, timestamp, keys.getInt(1));
      } else {
        throw new SQLException("No keys generated");
      }
    }
  }
}
