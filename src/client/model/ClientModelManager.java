package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;
import shared.transferobjects.*;

import javax.security.auth.login.LoginException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ClientModelManager implements ClientModelBooking, ClientModelMovie, ClientModelShowing,ClientModelCreateUser,ClientModelLogin
{
  private RMIClient client;
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


  public ClientModelManager(RMIClient client)
  {
    this.client = client;
    client.addPropertyChangeListener(this::update);
  }

  private void update(PropertyChangeEvent propertyChangeEvent)
  {
    System.out.println("FIRE 3");
    propertyChangeSupport.firePropertyChange(propertyChangeEvent);
  }

  @Override public Booking addBooking(Showing showing,
      ArrayList<Seat> seats)
      throws ServerException
  {
    System.out.println("Booking in clientModel manager");
    try
    {
      return client.addBooking(showing, seats);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;

  }

  @Override public void removeBooking(Booking booking)
      throws RemoteException, SQLException
  {
    client.removeBooking(booking);
  }

  @Override public Movie addMovie(Movie movie)
      throws SQLException, RemoteException
  {
    return client.addMovie(movie);
  }

  @Override public void removeMovie(Movie movie)
      throws RemoteException, SQLException
  {
    client.removeMovie(movie);
  }

  @Override public Showing addShowing(Showing showing)
      throws SQLException, RemoteException
  {

    return client.addShowing(showing);

  }

  @Override public ArrayList<Movie> getMovieList()
      throws RemoteException, SQLException
  {
      return client.getMovieList();

  }

  @Override public ArrayList<Showing> getShowingList(Movie movie)
      throws RemoteException, SQLException
  {

    return client.getShowingList(movie);
  }

  @Override public ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws RemoteException, SQLException
  {
    return client.getOccupiedSeats(showing);
  }

  @Override public ArrayList<Booking> getBookingList()
      throws RemoteException, SQLException
  {
    return client.getBookingList();
  }

  @Override public Hall getHallByNumber(String hallNo)
      throws SQLException, RemoteException
  {
    return client.getHallByNumber(hallNo);
  }

  @Override public ArrayList<Timestamp> getShowingTimesByHallNoAndDate(
      String hallNo, Timestamp timestamp) throws RemoteException, SQLException
  {
    return client.getShowingTimesByHallNoAndDate(hallNo, timestamp);
  }

  @Override public ArrayList<String> getHallNumbers()
      throws RemoteException, SQLException
  {
    return client.getHallNumbers();
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

  @Override public void createUser(String userName,String email, String password)
      throws RemoteException, SQLException
  {
    client.crateUser(userName,email,password);
  }

  @Override public void login(String userName, String password)
      throws LoginException, RemoteException
  {
    client.login(userName,password);
  }
}
