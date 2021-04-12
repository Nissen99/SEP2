package jdbc.dao;

import shared.transferobjects.Movie;

import java.sql.SQLException;

public interface MovieDAO
{
  Movie create(int movieId, String movieTitle) throws SQLException;
}
