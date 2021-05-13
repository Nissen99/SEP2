package client.model;

import shared.exception.ServerException;
import shared.transferobjects.Hall;
import shared.transferobjects.Showing;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface ClientModelShowing extends ClientModelShowingList
{
  Showing addShowing(Showing showing)
      throws SQLException, RemoteException, ServerException;
  Hall getHallByNumber(String hallNo)
      throws SQLException, RemoteException, ServerException;
  ArrayList<Timestamp> getShowingTimesByHallNoAndDate(String hallNo, Timestamp timestamp)
      throws RemoteException, SQLException, ServerException;
  ArrayList<String> getHallNumbers()
      throws RemoteException, SQLException, ServerException;
  void removeShowing(Showing showing) throws SQLException;
}
