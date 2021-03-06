package client.network;

import shared.exception.ServerException;
import shared.transferobjects.*;
import shared.util.PropertyChangeSubject;
import shared.util.ENUM;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
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

/**
 * Dette er forbindelsen til serveren, RMI er anvendt til håndtering af forbindelsen.
 * Denne klasse har til ansvar at komminikere med serveren.
 * Den videresender data som den får fra modellen.
 *
 * Vi vil ikke have at der kastes Remote- og SQLExceptions gennem vores system.
 * Derfor har vi lavet vores egen Exception - ServerException.
 * Denne kastes når der fanges en Remote- eller SQLException.
 * ServerExceptions kan fanges i controllere, hvilket giver brugeren besked.
 */
public class RMIClient implements Client, ClientCallBack
{
  private RMIServer rmiServer;
  private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
  private IUser user;

  /**
   * Lokaliserer serveren på port 1099 og forbinder Clienten hertil.
   * Herefter registreres en client callback,
   * således at der kan sendes data fra client til server.
   */
  @Override
  public void startClient() {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      rmiServer = (RMIServer) registry.lookup(String.valueOf(ENUM.BIOSERVER));
      //rmiServer.registerCallback(this);
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * @throws ServerException Hvis RemoteException sker eller ikke valid input
   */
  @Override public void createUser(String userName, String email,String password)
      throws ServerException
  {
    try
    {
      rmiServer.createUser(userName,email,password);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException("Connection to server failed");
    }
  }

  /**
   * Når man logger ind gemmer vi hvilken User der er logget ind.
   * User sendes med i addBooking metoden.
   *
   * @param showing den showing der bliver booket til
   * @param seats de sæder der bliver booket
   * @throws ServerException hvis RemoteException sker
   */
  @Override public void addBooking(IShowing showing,
      ArrayList<ISeat> seats) throws ServerException
  {
    try
    {
      rmiServer.addBooking(showing,user,seats);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException("Connection to server failed");
    }
  }

  @Override public void removeBooking(IBooking booking)
      throws ServerException
  {
    try
    {
      rmiServer.removeBooking(booking);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException("Connection to server failed");
    }
  }

  @Override public IMovie addMovie(IMovie movie)
      throws ServerException
  {
    try
    {
      return rmiServer.addMovie(movie);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException("Connection to server failed");
    }
  }

  @Override public void removeMovie(IMovie movie)
      throws ServerException
  {
    try
    {
      rmiServer.removeMovie(movie);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException("Connection to server failed");

    }
  }

  @Override public IShowing addShowing(IShowing showing)
      throws ServerException
  {
    try
    {
      return rmiServer.addShowing(showing);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException("Connection to server failed");

    }
  }

  @Override public ArrayList<IMovie> getMovieList() throws ServerException
  {
    try
    {
      return rmiServer.getMovieList();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException("Connection to server failed");
    }
  }

  @Override public ArrayList<IShowing> getShowingList(IMovie movie)
      throws ServerException
  {
    try
    {
      return rmiServer.getShowingList(movie);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException("Connection to server failed");
    }
  }

  @Override public ArrayList<IBooking> getBookingList() throws ServerException
  {
    try
    {
      return rmiServer.getBookingList();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException("Connection to server failed");

    }

  }

  @Override public ArrayList<ISeat> getOccupiedSeats(IShowing showing)
      throws ServerException
  {
    try
    {
      return rmiServer.getOccupiedSeats(showing);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException("Connection to server failed");

    }
  }

  @Override public IHall getHallByNumber(String hallNo)
      throws ServerException
  {
    try
    {
      return rmiServer.getHallByNumber(hallNo);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
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
      e.printStackTrace();
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
      e.printStackTrace();
      throw new ServerException("Connection to server failed");

    }
  }

  /**
   * Login sender username og password til serveren og gemmer brugeren som feltvariabelet user.
   * User bliver brugt i {@link #addBooking}
   *
   * @throws ServerException hvis der ikke findes et username + password match i databasen
   */
  @Override public void login(String username, String password) throws ServerException
  {
    try
    {
      user = rmiServer.login(username,password);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException("Connection to server failed");

    }

  }

  @Override public void removeShowing(IShowing showing) throws ServerException
  {
    try
    {
      rmiServer.removeShowing(showing);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException("Connection to server failed");
    }
  }

  @Override public void checkIfTimeOverlaps(String hallNo,
      Timestamp inputTimestamp) throws ServerException
  {
    try
    {
      rmiServer.checkIfTimeOverlaps(hallNo, inputTimestamp);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException("Connection to server failed");
    }
  }

  @Override public void update(PropertyChangeEvent evt)
  {
    propertyChangeSupport.firePropertyChange(evt);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener) throws ServerException
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
    try
    {
      rmiServer.registerCallback(this);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new ServerException("Connection to server failed");
    }

  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener) throws ServerException
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
