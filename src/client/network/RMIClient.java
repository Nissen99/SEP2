package client.network;

import shared.exception.ServerException;
import shared.util.PropertyChangeSubject;
import shared.util.ENUM;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.transferobjects.*;
import javax.security.auth.login.LoginException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class RMIClient implements Client, ClientCallBack, PropertyChangeSubject
{
  private RMIServer rmiServer;
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
  private User user;

  @Override
  public void startClient() {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);

      rmiServer = (RMIServer) registry.lookup(String.valueOf(ENUM.BIOSERVER));
      rmiServer.registerCallback(this);
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }


  @Override public void createUser(String userName, String email,String password)
      throws RemoteException, SQLException, ServerException
  {
    rmiServer.createUser(userName,email,password);
  }


  //Når man logger ind gemmer vi hvilken User der er logget ind
  //Den sender vi med i addBooking
  @Override public Booking addBooking(Showing showing,
      ArrayList<Seat> seats) throws ServerException, RemoteException
  {
    return rmiServer.addBooking(showing,user,seats);
  }

  @Override public void removeBooking(Booking booking)
      throws RemoteException, SQLException
  {
    rmiServer.removeBooking(booking);
  }

  @Override public Movie addMovie(Movie movie)
      throws RemoteException, SQLException, ServerException
  {
    return rmiServer.addMovie(movie);
  }

  @Override public void removeMovie(Movie movie)
      throws RemoteException, SQLException
  {
    rmiServer.removeMovie(movie);
  }

  @Override public Showing addShowing(Showing showing)
      throws RemoteException, SQLException, ServerException
  {
    return rmiServer.addShowing(showing);
  }

  @Override public ArrayList<Movie> getMovieList()
      throws RemoteException, SQLException
  {
    return rmiServer.getMovieList();
  }

  @Override public ArrayList<Showing> getShowingList(Movie movie)
      throws RemoteException, SQLException, ServerException
  {
    return rmiServer.getShowingList(movie);
  }

  @Override public ArrayList<Booking> getBookingList()
      throws RemoteException, SQLException
  {
    return rmiServer.getBookingList();
  }

  @Override public ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws RemoteException, SQLException
  {
    return rmiServer.getOccupiedSeats(showing);
  }

  @Override public Hall getHallByNumber(String hallNo)
      throws SQLException, RemoteException, ServerException
  {
    return rmiServer.getHallByNumber(hallNo);
  }

  @Override public ArrayList<Timestamp> getShowingTimesByHallNoAndDate(
      String hallNo, Timestamp timestamp)
      throws RemoteException, SQLException, ServerException
  {
    return rmiServer.getShowingTimesByHallNoAndDate(hallNo, timestamp);
  }

  @Override public ArrayList<String> getHallNumbers()
      throws RemoteException, SQLException, ServerException
  {
    return rmiServer.getHallNumbers();
  }

  @Override public void login(String username, String password)
      throws LoginException, RemoteException, ServerException
  {
    user = rmiServer.login(username,password);

  }

  @Override public void removeShowing(Showing showing) throws SQLException
  {
    try
    {
      rmiServer.removeShowing(showing);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void update(PropertyChangeEvent evt)
  {
    propertyChangeSupport.firePropertyChange(evt);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

}
