package shared.networking;

import shared.exception.ServerException;
import shared.transferobjects.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface RMIServer extends Remote
{
  void addBooking(IShowing showing, IUser user, ArrayList<ISeat> seats)
      throws ServerException, RemoteException;
  void removeBooking(IBooking booking) throws RemoteException, ServerException;
  IMovie addMovie(IMovie movie)
      throws RemoteException, ServerException;
  void removeMovie(IMovie movie)
      throws RemoteException, ServerException;
  IShowing addShowing(IShowing showing) throws RemoteException, ServerException;
  ArrayList<IMovie> getMovieList() throws RemoteException, ServerException;
  ArrayList<IShowing> getShowingList(IMovie movie)
      throws RemoteException,ServerException;
  ArrayList<IBooking> getBookingList() throws RemoteException, ServerException;
  ArrayList<ISeat> getOccupiedSeats(IShowing showing)
      throws RemoteException, ServerException;
  IHall getHallByNumber(String hallNo)
      throws RemoteException, ServerException;
  void registerCallback(ClientCallBack client)  throws RemoteException;
  ArrayList<Timestamp> getShowingTimesByHallNoAndDate(String hallNo, Timestamp timestamp)
      throws RemoteException, ServerException;
  ArrayList<String> getHallNumbers()
      throws RemoteException,ServerException;
  void createUser(String userName, String email,String password)
      throws RemoteException, ServerException;
  IUser login(String userName,String password)
      throws RemoteException, ServerException;
  void removeShowing(IShowing showing) throws RemoteException, ServerException;
  void removeCallBack(ClientCallBack clientCallBack) throws RemoteException;
}
