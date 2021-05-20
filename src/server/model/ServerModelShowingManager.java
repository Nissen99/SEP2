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
    if (!showingDAO.getAllShowings(showing.getMovie()).contains(showing)){
      throw new ServerException("That Showing does not exist");
    }
    showingDAO.removeShowing(showing);
  }


  @Override public ArrayList<IShowing> getShowingList(
      IMovie movie)
      throws ServerException
  {
    return showingDAO.getAllShowings(movie);
  }

  @Override public void checkIfTimeOverlaps(String hallNo, Timestamp timestamp)
      throws ServerException
  {
    ArrayList<Timestamp> showingTimes = getShowingTimesByHallNoAndDate(hallNo, timestamp);

    for (Timestamp showingTime : showingTimes)
    {
      Timestamp plus3Hours = new Timestamp(showingTime.getTime() + (3 * 3599999));
      Timestamp minus3Hours = new Timestamp(showingTime.getTime() - (3 * 3599999));

      if (!(timestamp.before(minus3Hours) || timestamp.after(plus3Hours))) {
        throw new ServerException("Invalid input - A showing is scheduled at this time");
      }
    }

  }

}
