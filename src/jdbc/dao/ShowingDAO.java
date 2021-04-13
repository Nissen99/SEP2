package jdbc.dao;

import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface ShowingDAO
{
  Showing create( Movie movie, Timestamp timestamp) throws
      SQLException;

  ArrayList<Showing> getAllShowings(Movie movie) throws SQLException;
}
