package client.network;

import server.model.PropertyChangeSubject;
import shared.ENUM;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.transferobjects.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class RMIClient implements Client, ClientCallBack, PropertyChangeSubject
{
  private RMIServer rmiServer;
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

  @Override
  public void startClient() {
    System.out.println("Hallo");
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      rmiServer = (RMIServer) registry.lookup(String.valueOf(ENUM.BIOSERVER));
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }

  }

  @Override public Booking addBooking(Showing showing, String username,
      ArrayList<Seat> seats) throws RemoteException, SQLException
  {
    return rmiServer.addBooking(showing, username, seats);
  }

  @Override public Movie addMovie(Movie movie)
      throws RemoteException, SQLException
  {
    return rmiServer.addMovie(movie);
  }

  @Override public Showing addShowing(Showing showing)
      throws RemoteException, SQLException
  {
    return rmiServer.addShowing(showing);
  }

  @Override public Hall addHall(Hall hall) throws RemoteException, SQLException
  {
    return rmiServer.addHall(hall);
  }

  @Override public ArrayList<Movie> getMovieList()
      throws RemoteException, SQLException
  {
    return rmiServer.getMovieList();
  }

  @Override public ArrayList<Showing> getShowingList(Movie movie)
      throws RemoteException, SQLException
  {
    return rmiServer.getShowingList(movie);
  }

  @Override public ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws RemoteException, SQLException
  {
    return rmiServer.getOccupiedSeats(showing);
  }

  @Override public Hall getHallByNumber(String hallNo)
      throws SQLException, RemoteException
  {
    return rmiServer.getHallByNumber(hallNo);
  }

  @Override public void update(PropertyChangeEvent evt) throws RemoteException
  {
    propertyChangeSupport.firePropertyChange(evt);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  @Override public void addPropertyChangeListener(String eventName,
      PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String eventName,
      PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(eventName, listener);
  }
}
