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
      throws RemoteException, SQLException, ServerException;

  Booking addBooking(Showing showing,
      ArrayList<Seat> seats) throws ServerException, RemoteException;
  void removeBooking(Booking booking) throws RemoteException, SQLException;
  Movie addMovie(Movie movie)
      throws RemoteException, SQLException, ServerException;
  void removeMovie(Movie movie) throws RemoteException, SQLException;
  Showing addShowing(Showing showing)
      throws RemoteException, SQLException, ServerException;
  ArrayList<Movie> getMovieList() throws RemoteException, SQLException;
  ArrayList<Showing> getShowingList(Movie movie)
      throws RemoteException, SQLException, ServerException;
  ArrayList<Booking> getBookingList() throws RemoteException, SQLException;
  ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws RemoteException, SQLException;
  Hall getHallByNumber(String hallNo)
      throws SQLException, RemoteException, ServerException;
  ArrayList<Timestamp> getShowingTimesByHallNoAndDate(String hallNo,
      Timestamp timestamp) throws RemoteException, SQLException,
      ServerException;
  ArrayList<String> getHallNumbers()
      throws RemoteException, SQLException, ServerException;
  void login(String username, String password)
      throws LoginException, RemoteException, ServerException;
  void removeShowing(Showing showing) throws SQLException;
}
