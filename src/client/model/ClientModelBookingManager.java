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
    propertyChangeSupport.firePropertyChange(propertyChangeEvent);
  }

  @Override public void addBooking(Showing showing,
      ArrayList<Seat> seats)
      throws ServerException
  {

    super.getClient().addBooking(showing, seats);



  }


  @Override public void removeBooking(Booking booking) throws ServerException
  {
    super.getClient().removeBooking(booking);
  }

  @Override public ArrayList<Seat> getOccupiedSeats(Showing showing)
      throws ServerException

  {
    return super.getClient().getOccupiedSeats(showing);
  }


  @Override public ArrayList<Booking> getBookingList() throws ServerException

  {
    return super.getClient().getBookingList();
  }

  @Override public ArrayList<Showing> getShowingList(Movie movie)
      throws ServerException
  {

    return super.getClient().getShowingList(movie);
  }

  @Override public ArrayList<Movie> getMovieList() throws ServerException

  {
    return super.getClient().getMovieList();

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
    super.getClient().removePropertyChangeListener(this::update);
  }


}
