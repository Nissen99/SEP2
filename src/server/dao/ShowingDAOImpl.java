package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.*;
import java.sql.*;
import java.util.ArrayList;


public class ShowingDAOImpl extends BaseDAO implements ShowingDAO
{

  @Override public IShowing create(IShowing showing) throws ServerException
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
        throw new ServerException("No keys generated in database");
      }
    }
    catch (SQLException throwables)
    {
      throw new ServerException("Database fejl");

    }
  }

  @Override public ArrayList<IShowing> getAllShowings(IMovie movie)
      throws ServerException
  {
    ArrayList<IShowing> showingArrayList = new ArrayList<>();
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
    catch (SQLException throwables)
    {
      throw new ServerException("Database fejl");

    }
  }

  @Override public ArrayList<Timestamp> getShowingTimesByHallNoAndDate(String hallNo, Timestamp timestamp)
      throws ServerException
  {
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
    catch (SQLException throwables)
    {
      throw new ServerException("Database fejl");

    }
  }

  @Override public void removeShowing(IShowing showing) throws ServerException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM Showing WHERE showingId = ?");
      statement.setInt(1, showing.getId());
      statement.executeUpdate();
    }
    catch (SQLException throwables)
    {
      throw new ServerException("Database fejl");

    }
  }

}
