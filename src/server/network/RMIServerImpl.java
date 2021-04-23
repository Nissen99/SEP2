package server.network;

import server.model.PropertyChangeSubject;
import server.model.ServerModelManager;
import shared.ENUM;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.transferobjects.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class RMIServerImpl implements RMIServer, PropertyChangeListener
{
  private ServerModelManager model;
  private ArrayList<ClientCallBack> clientCallBackArrayList = new ArrayList<>();

  public RMIServerImpl(ServerModelManager model) throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    this.model = model;
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind(String.valueOf(ENUM.BIOSERVER), this);
  }

  @Override public Booking addBooking(Showing showing, String username,
      ArrayList<Seat> seats) throws SQLException
  {
    return model.addBooking(showing, username, seats);

  }

  @Override public Movie addMovie(Movie movie) throws SQLException
  {
    return model.addMovie(movie);
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

  @Override public ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws SQLException
  {
    return model.getOccupiedSeats(showing);
  }

  @Override public Hall getHallByNumber(String hallNo) throws SQLException
  {
    return model.getHallByNumber(hallNo);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    for (ClientCallBack clientCallBack : clientCallBackArrayList)
    {
      try
      {
        clientCallBack.update(evt);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }
  }
}
