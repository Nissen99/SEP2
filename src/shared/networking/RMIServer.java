package shared.networking;

import client.network.RMIClient;
import shared.exception.ServerException;
import shared.transferobjects.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface RMIServer extends Remote
{
  void addBooking(Showing showing, User user, ArrayList<Seat> seats)
      throws ServerException, RemoteException;
  void removeBooking(Booking booking) throws RemoteException, ServerException;
  Movie addMovie(Movie movie)
      throws RemoteException, ServerException;
  void removeMovie(Movie movie)
      throws RemoteException, ServerException;
  Showing addShowing(Showing showing) throws RemoteException, ServerException;
  ArrayList<Movie> getMovieList() throws RemoteException, ServerException;
  ArrayList<Showing> getShowingList(Movie movie)
      throws RemoteException,ServerException;
  ArrayList<Booking> getBookingList() throws RemoteException, ServerException;
  ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws RemoteException, ServerException;
  Hall getHallByNumber(String hallNo)
      throws RemoteException, ServerException;
  void registerCallback(ClientCallBack client)  throws RemoteException;
  ArrayList<Timestamp> getShowingTimesByHallNoAndDate(String hallNo, Timestamp timestamp)
      throws RemoteException, ServerException;
  ArrayList<String> getHallNumbers()
      throws RemoteException,ServerException;
  void createUser(String userName, String email,String password)
      throws RemoteException, ServerException;
  User login(String userName,String password)
      throws RemoteException, ServerException;
  void removeShowing(Showing showing) throws RemoteException, ServerException;
  void removeCallBack(ClientCallBack clientCallBack) throws RemoteException;
}
