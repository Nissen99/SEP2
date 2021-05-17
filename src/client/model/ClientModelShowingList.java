package client.model;

import shared.exception.ServerException;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementeres af ClientModelShowingListManager
 */

public interface ClientModelShowingList extends ClientModel
{
  ArrayList<Showing> getShowingList(Movie movie)
      throws ServerException;
}
