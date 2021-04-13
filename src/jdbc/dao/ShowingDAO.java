package jdbc.dao;

import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.sql.SQLException;
import java.sql.Timestamp;

public interface ShowingDAO
{
  Showing create( Movie movie, Timestamp timestamp) throws
      SQLException;

}
