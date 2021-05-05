package client.model;

import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ClientModelShowingList extends ClientModel
{
  ArrayList<Showing> getShowingList(Movie movie)
      throws SQLException, RemoteException;
}
