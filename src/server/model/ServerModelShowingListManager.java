package server.model;

import server.dao.ShowingDAO;
import server.dao.ShowingDAOImpl;
import shared.exception.ServerException;
import shared.transferobjects.IMovie;
import shared.transferobjects.IShowing;

import java.util.ArrayList;

public class ServerModelShowingListManager implements ServerModelShowingList
{
  private ShowingDAO showingDAO = new ShowingDAOImpl();


  @Override public ArrayList<IShowing> getShowingList(
      IMovie movie)
      throws ServerException
  {
    return showingDAO.getAllShowings(movie);
  }
}
