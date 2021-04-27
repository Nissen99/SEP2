package shared.networking;

import shared.transferobjects.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RMIServer extends Remote
{
  Booking addBooking(Showing showing, String username, ArrayList<Seat> seats)
      throws RemoteException, SQLException;
  Movie addMovie(Movie movie) throws RemoteException, SQLException;
  Showing addShowing(Showing showing) throws RemoteException, SQLException;
  Hall addHall(Hall hall) throws RemoteException, SQLException;
  ArrayList<Movie> getMovieList() throws RemoteException, SQLException;
  ArrayList<Showing> getShowingList(Movie movie)
      throws RemoteException, SQLException;
  ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws RemoteException, SQLException;
  Hall getHallByNumber(String hallNo) throws RemoteException, SQLException;
  void registerCallback(ClientCallBack client)  throws RemoteException;
}
