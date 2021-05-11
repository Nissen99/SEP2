package shared.networking;

import shared.exception.ServerException;
import shared.transferobjects.*;

import javax.security.auth.login.LoginException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface RMIServer extends Remote
{
  Booking addBooking(Showing showing, User user, ArrayList<Seat> seats)
      throws RemoteException, ServerException;
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
  Hall getHallByNumber(String hallNo) throws RemoteException, SQLException;
  void registerCallback(ClientCallBack client)  throws RemoteException;
  ArrayList<Timestamp> getShowingTimesByHallNoAndDate(String hallNo, Timestamp timestamp) throws RemoteException, SQLException;
  ArrayList<String> getHallNumbers() throws RemoteException, SQLException;
  void createUser(String userName, String email,String password) throws SQLException,RemoteException;
  User login(String userName,String password) throws LoginException,RemoteException;
  void removeShowing(Showing showing) throws SQLException, RemoteException;
}
