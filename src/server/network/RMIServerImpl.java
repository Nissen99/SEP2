package server.network;

import server.ServerException;
import server.model.ServerModelManager;
import shared.ENUM;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.transferobjects.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class RMIServerImpl implements RMIServer, PropertyChangeListener
{
  private ServerModelManager model;
  private ArrayList<ClientCallBack> clientCallBackArrayList = new ArrayList<>();

  public RMIServerImpl(ServerModelManager model) throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    this.model = model;
    model.addPropertyChangeListener(this::propertyChange);
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind(String.valueOf(ENUM.BIOSERVER), this);
  }

  @Override public Booking addBooking(Showing showing, String username, String email,
      ArrayList<Seat> seats) throws ServerException
  {
    return model.addBooking(showing, username,email, seats);

  }

  @Override public void removeBooking(Booking booking)
      throws RemoteException, SQLException
  {
    model.removeBooking(booking);
  }

  @Override public Movie addMovie(Movie movie) throws SQLException
  {
    return model.addMovie(movie);
  }

  @Override public void removeMovie(Movie movie)
      throws RemoteException, SQLException
  {
    model.removeMovie(movie);
  }

  @Override public Showing addShowing(Showing showing) throws SQLException
  {
    return model.addShowing(showing);
  }

  @Override public Hall addHall(Hall hall) throws SQLException
  {
    return model.addHall(hall);
  }

  @Override public ArrayList<Movie> getMovieList() throws SQLException
  {
    return model.getMovieList();
  }

  @Override public ArrayList<Showing> getShowingList(Movie movie)
      throws SQLException
  {
    return model.getShowingList(movie);
  }

  @Override public ArrayList<Booking> getBookingList()
      throws RemoteException, SQLException
  {
    return model.getBookingList();
  }

  @Override public ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws SQLException
  {
    return model.getOccupiedSeats(showing);
  }

  @Override public Hall getHallByNumber(String hallNo) throws SQLException
  {
    return model.getHallByNumber(hallNo);
  }

  @Override public void registerCallback(ClientCallBack client)
  {
    clientCallBackArrayList.add(client);
    System.out.println("Added Client to call back list");
  }

  @Override public ArrayList<Timestamp> getShowingTimesByHallNoAndDate(
      String hallNo, Timestamp timestamp) throws RemoteException, SQLException
  {
    return model.getShowingTimesByHallNoAndDate(hallNo, timestamp);
  }

  @Override public ArrayList<String> getHallNumbers() throws SQLException
  {
    return model.getHallNumbers();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    for (ClientCallBack clientCallBack : clientCallBackArrayList)
    {
      try
      {
        System.out.println("Call back client fires before");
        clientCallBack.update(evt);
        System.out.println("Call back client fires after");
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }
  }
}
