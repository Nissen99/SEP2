package server.model;

import server.dao.HallDAO;
import server.dao.HallDAOImpl;
import server.dao.ShowingDAO;
import server.dao.ShowingDAOImpl;
import shared.exception.ServerException;
import shared.transferobjects.IHall;
import shared.transferobjects.IMovie;
import shared.transferobjects.IShowing;

import java.sql.Timestamp;
import java.util.ArrayList;


public class ServerModelShowingManager implements ServerModelShowing
{

  private ShowingDAO showingDAO = new ShowingDAOImpl();
  private HallDAO hallDAO = new HallDAOImpl();

  @Override public IShowing addShowing(IShowing showing) throws ServerException
  {
      return showingDAO.create(showing);
  }

  @Override public IHall getHallByNumber(String hallNo) throws ServerException
  {
    return hallDAO.getHallByNumber(hallNo);
  }


  @Override public ArrayList<Timestamp> getShowingTimesByHallNoAndDate(
      String hallNo, Timestamp timestamp) throws ServerException
  {
    return showingDAO.getShowingTimesByHallNoAndDate(hallNo, timestamp);
  }


  @Override public ArrayList<String> getHallNumbers() throws ServerException
  {
    return hallDAO.getHallNumbers();
  }

  @Override public void removeShowing(IShowing showing) throws ServerException
  {
    showingDAO.removeShowing(showing);
  }


  @Override public ArrayList<IShowing> getShowingList(
      IMovie movie)
      throws ServerException
  {
    return showingDAO.getAllShowings(movie);
  }
}
