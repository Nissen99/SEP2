package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;
import shared.transferobjects.Booking;
import shared.transferobjects.Movie;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientModelBookingManager extends ClientModelShowingListManager implements ClientModelBooking
{


  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

  public ClientModelBookingManager(RMIClient client)
  {
    super(client);
    super.getClient().addPropertyChangeListener(this::update);


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
      return super.getClient().addBooking(showing, seats);
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
    super.getClient().removeBooking(booking);
  }

  @Override public ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws RemoteException, SQLException
  {
    return super.getClient().getOccupiedSeats(showing);
  }


  @Override public ArrayList<Booking> getBookingList()
      throws RemoteException, SQLException
  {
    return super.getClient().getBookingList();
  }

  @Override public ArrayList<Showing> getShowingList(Movie movie)
      throws RemoteException, SQLException, ServerException
  {

    return super.getClient().getShowingList(movie);
  }

  @Override public ArrayList<Movie> getMovieList()
      throws RemoteException, SQLException
  {
    return super.getClient().getMovieList();

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
