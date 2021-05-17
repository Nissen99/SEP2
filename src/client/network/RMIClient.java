package client.network;

import shared.exception.ServerException;
import shared.util.PropertyChangeSubject;
import shared.util.ENUM;
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
import java.sql.Timestamp;
import java.util.ArrayList;

public class RMIClient implements Client, ClientCallBack, PropertyChangeSubject
{
  private RMIServer rmiServer;
  private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
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
      throws ServerException
  {
    try
    {
      rmiServer.createUser(userName,email,password);
    }
    catch (RemoteException e)
    {
      throw new ServerException("Connection to server failed");
    }
  }


  //Når man logger ind gemmer vi hvilken User der er logget ind
  //Den sender vi med i addBooking
  @Override public void addBooking(Showing showing,
      ArrayList<Seat> seats) throws ServerException
  {
    try
    {
      System.out.println("RMI CLient " + user);
      rmiServer.addBooking(showing,user,seats);
    }
    catch (RemoteException e)
    {
      throw new ServerException("Connection to server failed");
    }
  }

  @Override public void removeBooking(Booking booking)
      throws ServerException
  {
    try
    {
      rmiServer.removeBooking(booking);
    }
    catch (RemoteException e)
    {
      throw new ServerException("Connection to server failed");
    }
  }

  @Override public Movie addMovie(Movie movie)
      throws ServerException
  {
    try
    {
      return rmiServer.addMovie(movie);
    }
    catch (RemoteException e)
    {
      throw new ServerException("Connection to server failed");
    }
  }

  @Override public void removeMovie(Movie movie)
      throws ServerException
  {
    try
    {
      rmiServer.removeMovie(movie);
    }
    catch (RemoteException e)
    {
      throw new ServerException("Connection to server failed");

    }
  }

  @Override public Showing addShowing(Showing showing)
      throws ServerException
  {
    try
    {
      return rmiServer.addShowing(showing);
    }
    catch (RemoteException e)
    {
      throw new ServerException("Connection to server failed");

    }
  }

  @Override public ArrayList<Movie> getMovieList() throws ServerException
  {
    try
    {
      return rmiServer.getMovieList();
    }
    catch (RemoteException e)
    {
      throw new ServerException("Connection to server failed");
    }
  }

  @Override public ArrayList<Showing> getShowingList(Movie movie)
      throws ServerException
  {
    try
    {
      return rmiServer.getShowingList(movie);
    }
    catch (RemoteException e)
    {
      throw new ServerException("Connection to server failed");
    }
  }

  @Override public ArrayList<Booking> getBookingList() throws ServerException
  {
    try
    {
      return rmiServer.getBookingList();
    }
    catch (RemoteException e)
    {
      throw new ServerException("Connection to server failed");

    }

  }

  @Override public ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws ServerException
  {
    try
    {
      return rmiServer.getOccupiedSeats(showing);
    }
    catch (RemoteException e)
    {
      throw new ServerException("Connection to server failed");

    }
  }

  @Override public Hall getHallByNumber(String hallNo)
      throws ServerException
  {
    try
    {
      return rmiServer.getHallByNumber(hallNo);
    }
    catch (RemoteException e)
    {
      throw new ServerException("Connection to server failed");
    }
  }

  @Override public ArrayList<Timestamp> getShowingTimesByHallNoAndDate(
      String hallNo, Timestamp timestamp)
      throws ServerException
  {
    try
    {
      return rmiServer.getShowingTimesByHallNoAndDate(hallNo, timestamp);
    }
    catch (RemoteException e)
    {
      throw new ServerException("Connection to server failed");

    }
  }

  @Override public ArrayList<String> getHallNumbers()
      throws ServerException
  {
    try
    {
      return rmiServer.getHallNumbers();
    }
    catch (RemoteException e)
    {
      throw new ServerException("Connection to server failed");

    }
  }

  @Override public void login(String username, String password)
      throws ServerException
  {
    try
    {
      System.out.println("RMI CLient Login " + user + "Before");
      user = rmiServer.login(username,password);
      System.out.println("RMI CLient Login " + user + "After");
    }
    catch (RemoteException e)
    {
      throw new ServerException("Connection to server failed");

    }

  }

  @Override public void removeShowing(Showing showing) throws ServerException
  {
    try
    {
      rmiServer.removeShowing(showing);
    }
    catch (RemoteException e)
    {
      throw new ServerException("Connection to server failed");
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
    try
    {
      rmiServer.registerCallback(this);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
    try
    {
      rmiServer.removeCallBack(this);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

}
