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
import java.util.ArrayList;

/**
 * Denne klasse extender ClientModelShowingListManager, som extender ClientModelManager
 * hvor en referance til vores Client ligger, vi bruger super.getClient når den skal bruges
 *
 * Denne klasse lytter på Client, dette bliver brugt til at dynamisk at opdatere
 * seatView.
 *
 */
public class ClientModelBookingManager extends ClientModelShowingListManager implements ClientModelBooking
{


  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

  public ClientModelBookingManager(RMIClient client)
  {
    super(client);
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


  //Når vores View tilføjer eller fjerner sig som listener, tilføjer eller
  // fjerner denne model sig som listener på client
  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
    super.getClient().addPropertyChangeListener(this::update);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
    super.getClient().removePropertyChangeListener(this::update);
  }


}
