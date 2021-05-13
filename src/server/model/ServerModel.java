package server.model;

import shared.transferobjects.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ServerModel
{
  ArrayList<Movie> getMovieList() throws SQLException;
}
