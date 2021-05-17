package client.network;

import shared.exception.ServerException;
import shared.transferobjects.*;

import javax.security.auth.login.LoginException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface Client
{
  void startClient();
  void createUser(String userName, String email,String password)
      throws ServerException;

  void addBooking(Showing showing,
      ArrayList<Seat> seats) throws ServerException;
  void removeBooking(Booking booking)
      throws ServerException;
  Movie addMovie(Movie movie)
      throws ServerException;
  void removeMovie(Movie movie)
      throws ServerException;
  Showing addShowing(Showing showing)
      throws ServerException;
  ArrayList<Movie> getMovieList()
      throws ServerException;
  ArrayList<Showing> getShowingList(Movie movie)
      throws ServerException;
  ArrayList<Booking> getBookingList()
      throws ServerException;
  ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws ServerException;
  Hall getHallByNumber(String hallNo)
      throws ServerException;
  ArrayList<Timestamp> getShowingTimesByHallNoAndDate(String hallNo,
      Timestamp timestamp) throws ServerException;
  ArrayList<String> getHallNumbers()
      throws ServerException;
  void login(String username, String password)
      throws ServerException;
  void removeShowing(Showing showing) throws ServerException;
}
