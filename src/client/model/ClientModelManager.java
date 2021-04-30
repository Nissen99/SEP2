package client.model;

import client.network.Client;
import client.network.RMIClient;
import databaseConnection.dao.*;
import shared.transferobjects.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ClientModelManager implements ClientModel
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

  @Override public Booking addBooking(Showing showing, String username,String email,
      ArrayList<Seat> seats) throws RemoteException, SQLException
  {
    System.out.println("Booking in clientModel manager");
    return client.addBooking(showing, username, email, seats);
  }

  @Override public Movie addMovie(Movie movie)
      throws SQLException, RemoteException
  {
    return client.addMovie(movie);
  }

  @Override public Showing addShowing(Showing showing)
      throws SQLException, RemoteException
  {

    return client.addShowing(showing);

  }

  @Override public Hall addHall(Hall hall) throws SQLException, RemoteException
  {
    return client.addHall(hall);
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
