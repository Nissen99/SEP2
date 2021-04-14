package DatabaseConnection.dao;

import shared.Hall;
import shared.Movie;
import shared.Showing;

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
      statement.setInt(3, showing.getHall().getHallNo());
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
      //TODO
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Showing right Join Hall ON Showing.hallNo = Hall.hallNo  WHERE movieId = ?");
      statement.setInt(1, movie.getMovieId());
      ResultSet showings = statement.executeQuery();
      while (showings.next()){
        showingArrayList.add(
            new Showing(showings.getInt("showingId"),
            movie,
            showings.getTimestamp("time"),
            new Hall(showings.getInt("hallNo"),
                showings.getInt("maxSeatInRow"),
                showings.getInt("maxRows"))));
      }
  return showingArrayList;
    }
  }
}
