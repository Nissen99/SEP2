package server.dao;

import shared.exception.ServerException;
import shared.transferobjects.IMovie;
import shared.transferobjects.IShowing;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Implementeres af ShowingDAOImpl
 */
public interface ShowingDAO
{


  ArrayList<IShowing> getAllShowings(IMovie movie) throws ServerException;
  IShowing create(IShowing showing) throws ServerException;
  ArrayList<Timestamp> getShowingTimesByHallNoAndDate(String hallNo, Timestamp timestamp)
      throws ServerException;
  void removeShowing(IShowing showing) throws ServerException;
}
