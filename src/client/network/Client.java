package client.network;

import shared.exception.ServerException;
import shared.transferobjects.*;


import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Implementeres af RMIClient
 *
 */
public interface Client
{
  void startClient();
  void createUser(String userName, String email,String password)
      throws ServerException;

  void addBooking(IShowing showing,
      ArrayList<ISeat> seats) throws ServerException;
  void removeBooking(IBooking booking)
      throws ServerException;
  IMovie addMovie(IMovie movie)
      throws ServerException;
  void removeMovie(IMovie movie)
      throws ServerException;
  IShowing addShowing(IShowing showing)
      throws ServerException;
  ArrayList<IMovie> getMovieList()
      throws ServerException;
  ArrayList<IShowing> getShowingList(IMovie movie)
      throws ServerException;
  ArrayList<IBooking> getBookingList()
      throws ServerException;
  ArrayList<ISeat> getOccupiedSeats(IShowing showing)
      throws ServerException;
  IHall getHallByNumber(String hallNo)
      throws ServerException;
  ArrayList<Timestamp> getShowingTimesByHallNoAndDate(String hallNo,
      Timestamp timestamp) throws ServerException;
  ArrayList<String> getHallNumbers()
      throws ServerException;
  void login(String username, String password)
      throws ServerException;
  void removeShowing(IShowing showing) throws ServerException;
  void checkIfTimeOverlaps(String hallNo, Timestamp inputTimestamp)
      throws ServerException;
}
