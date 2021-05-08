package client.network;

import server.ServerException;
import shared.transferobjects.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface Client
{
  void startClient();
  Booking addBooking(Showing showing, String username,String email, ArrayList<Seat> seats)
      throws ServerException, RemoteException;
  void removeBooking(Booking booking) throws RemoteException, SQLException;
  Movie addMovie(Movie movie) throws RemoteException, SQLException;
  void removeMovie(Movie movie) throws RemoteException, SQLException;
  Showing addShowing(Showing showing) throws RemoteException, SQLException;
  Hall addHall(Hall hall) throws RemoteException, SQLException;
  ArrayList<Movie> getMovieList() throws RemoteException, SQLException;
  ArrayList<Showing> getShowingList(Movie movie)
      throws RemoteException, SQLException;
  ArrayList<Booking> getBookingList() throws RemoteException, SQLException;
  ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws RemoteException, SQLException;
  Hall getHallByNumber(String hallNo) throws SQLException, RemoteException;
  ArrayList<Timestamp> getShowingTimesByHallNoAndDate(String hallNo, Timestamp timestamp)
      throws RemoteException, SQLException;
  ArrayList<String> getHallNumbers() throws RemoteException, SQLException;
}
