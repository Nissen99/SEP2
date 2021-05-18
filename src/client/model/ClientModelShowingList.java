package client.model;

import shared.exception.ServerException;
import shared.transferobjects.IMovie;
import shared.transferobjects.IShowing;


import java.util.ArrayList;

/**
 * Implementeres af ClientModelShowingListManager
 */

public interface ClientModelShowingList extends ClientModel
{
  ArrayList<IShowing> getShowingList(IMovie movie)
      throws ServerException;
}
