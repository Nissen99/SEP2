package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.IMovie;
import shared.transferobjects.Movie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MovieDAOImpl extends BaseDAO implements MovieDAO
{
  @Override public IMovie create(String movieTitle) throws ServerException
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
      catch (SQLException throwables)
      {
        throwables.printStackTrace();
        throw new ServerException("Database fejl");
      }
  }


  @Override public ArrayList<IMovie> getAllMovies() throws ServerException
  {
    ArrayList<IMovie> movieArrayList = new ArrayList<>();
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Movie");
      ResultSet movies = statement.executeQuery();

      while (movies.next()){
        movieArrayList.add(new Movie(movies.getInt("movieId"), movies.getString("title")));
      }
      return movieArrayList;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
      throw new ServerException("Database connection failed");
    }

  }

  @Override public void removeMovie(IMovie movie) throws ServerException
  {
    try (Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("DELETE FROM Movie WHERE movieId = ?");
      statement.setInt(1, movie.getMovieId());
      statement.executeUpdate();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
      throw new ServerException("Database connection failed");

    }
  }
}
