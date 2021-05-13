package server.model;

import server.dao.HallDAO;
import server.dao.HallDAOImpl;
import server.dao.ShowingDAO;
import server.dao.ShowingDAOImpl;
import shared.transferobjects.Hall;
import shared.transferobjects.Showing;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ServerModelShowingManager implements ServerModelShowing
{

  private ShowingDAO showingDAO = new ShowingDAOImpl();
  private HallDAO hallDAO = new HallDAOImpl();

  @Override public Showing addShowing(Showing showing)
  {

    try
    {
      return showingDAO.create(showing);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
      System.out.println("Catch in addShowing");
    }
    return null;

  }

  @Override public Hall getHallByNumber(String hallNo) throws SQLException
  {
    return hallDAO.getHallByNumber(hallNo);
  }


  @Override public ArrayList<Timestamp> getShowingTimesByHallNoAndDate(
      String hallNo, Timestamp timestamp) throws SQLException
  {
    return showingDAO.getShowingTimesByHallNoAndDate(hallNo, timestamp);
  }


  @Override public ArrayList<String> getHallNumbers() throws SQLException
  {
    return hallDAO.getHallNumbers();
  }

  @Override public void removeShowing(Showing showing) throws SQLException
  {
    showingDAO.removeShowing(showing);
  }
}
