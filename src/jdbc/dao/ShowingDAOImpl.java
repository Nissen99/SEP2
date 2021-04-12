package jdbc.dao;

import shared.transferobjects.Booking;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.sql.*;

public class ShowingDAOImpl extends BaseDAO implements ShowingDAO
{

  @Override public Showing create(Movie movie, Timestamp timestamp,
      int showingId) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO Showing (movieId, timestamp, showingId) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, movie.getMovieTitle());
      statement.setTimestamp(2,timestamp);
      statement.setInt(3,showingId);
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
