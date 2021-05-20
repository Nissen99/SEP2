package server.model;

import shared.exception.ServerException;
import shared.transferobjects.IHall;
import shared.transferobjects.IMovie;
import shared.transferobjects.IShowing;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Implementeres af ServerModelShowingManager
 */
public interface ServerModelShowing
{
  IShowing addShowing(IShowing showing) throws ServerException;
  IHall getHallByNumber(String hallNo) throws ServerException;
  ArrayList<Timestamp> getShowingTimesByHallNoAndDate(String hallNo, Timestamp timestamp)
      throws ServerException;
  ArrayList<String> getHallNumbers() throws ServerException;
  void removeShowing(IShowing showing) throws ServerException;
  ArrayList<IShowing> getShowingList(IMovie movie) throws ServerException;
  void checkIfTimeOverlaps(String hallNo, Timestamp timestamp)
      throws ServerException;

}
