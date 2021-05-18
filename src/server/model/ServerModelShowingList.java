package server.model;

import shared.exception.ServerException;
import shared.transferobjects.IMovie;
import shared.transferobjects.IShowing;

import java.util.ArrayList;

public interface ServerModelShowingList
{
  ArrayList<IShowing> getShowingList(IMovie movie) throws ServerException;
}
