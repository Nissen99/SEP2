package server.model;

import shared.exception.ServerException;
import shared.transferobjects.*;

import java.util.ArrayList;

public interface ServerModel
{
  ArrayList<IMovie> getMovieList() throws ServerException;
}
