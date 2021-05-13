package server.model;

import server.dao.ShowingDAO;
import server.dao.ShowingDAOImpl;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerModelShowingListManager implements ServerModelShowingList
{
  private ShowingDAO showingDAO = new ShowingDAOImpl();


  @Override public ArrayList<Showing> getShowingList(Movie movie) throws SQLException
  {
    return showingDAO.getAllShowings(movie);
  }
}
