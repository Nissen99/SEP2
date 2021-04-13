package jdbc.dao;

import shared.transferobjects.Booking;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.sql.*;
import java.util.ArrayList;

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
        return new Showing( keys.getInt("showingId"),movie, timestamp);
      } else {
        throw new SQLException("No keys generated");
      }
    }
  }

  @Override public ArrayList<Showing> getAllShowings(Movie movie) throws SQLException
  {
    ArrayList<Showing> showingArrayList = new ArrayList<>();
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Showing WHERE movieId = ?");
      statement.setInt(1, movie.getMovieId());
      ResultSet showings = statement.executeQuery();
      while (showings.next()){
        showingArrayList.add(new Showing(showings.getInt("showingId"), movie, showings.getTimestamp("time")));
      }
  return showingArrayList;
    }
  }
}
