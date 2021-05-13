package server.dao;

import shared.transferobjects.Hall;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;
import java.sql.*;
import java.util.ArrayList;

public class ShowingDAOImpl extends BaseDAO implements ShowingDAO
{

  @Override public Showing create(Showing showing) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO Showing (movieId, time, hallNo) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setInt(1, showing.getMovie().getMovieId());
      statement.setTimestamp(2,showing.getTimestamp());
      statement.setString(3, showing.getHall().getHallNo());
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next()) {
        return new Showing( keys.getInt("showingId"),showing.getMovie(), showing.getTimestamp(), showing.getHall());
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
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Showing right Join Hall ON Showing.hallNo = Hall.hallNo  WHERE movieId = ?");
      statement.setInt(1, movie.getMovieId());
      ResultSet showings = statement.executeQuery();
      while (showings.next()){
        showingArrayList.add(
            new Showing(showings.getInt("showingId"),
            movie,
            showings.getTimestamp("time"),
            new Hall(showings.getString("hallNo"),
                showings.getInt("maxSeatInRow"),
                showings.getInt("maxRows"))));
      }
  return showingArrayList;
    }
  }

  @Override public ArrayList<Timestamp> getShowingTimesByHallNoAndDate(String hallNo, Timestamp timestamp) throws SQLException {
    ArrayList<Timestamp> showingTimes = new ArrayList<>();
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT time FROM Showing WHERE (hallNo = ? AND DATE(time) = ?)");
      Date date = new Date(timestamp.getTime());
      statement.setString(1, hallNo);
      statement.setDate(2, date);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        showingTimes.add(resultSet.getTimestamp("time"));
      }
      return showingTimes;
    }
  }

  @Override public void removeShowing(Showing showing) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM Showing WHERE showingId = ?");
      statement.setInt(1, showing.getId());
      statement.executeUpdate();
    }
  }

}
