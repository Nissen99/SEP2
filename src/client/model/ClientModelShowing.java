package client.model;

import shared.transferobjects.Hall;
import shared.transferobjects.Showing;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface ClientModelShowing extends ClientModelShowingList
{
  Showing addShowing(Showing showing) throws SQLException, RemoteException;
  Hall getHallByNumber(String hallNo) throws SQLException, RemoteException;
  ArrayList<Timestamp> getShowingTimesByHallNoAndDate(String hallNo, Timestamp timestamp)
      throws RemoteException, SQLException;
  ArrayList<String> getHallNumbers() throws RemoteException, SQLException;
}
