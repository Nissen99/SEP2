package jdbc.dao;

import shared.transferobjects.Movie;

import java.sql.SQLException;

public interface MovieDAO
{
  Movie create(String movieTitle) throws SQLException;
}
