package server.model;

import shared.exception.ServerException;
import shared.transferobjects.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ServerModel
{
  ArrayList<Movie> getMovieList() throws ServerException;
}
