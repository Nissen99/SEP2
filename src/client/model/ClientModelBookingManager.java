package client.model;

import client.network.RMIClient;
import shared.exception.ServerException;
import shared.transferobjects.*;

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
 * Denne klasse sender videre fra ViewModel Til Client, har ikke i sig selv noget logik
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

  @Override public void addBooking(IShowing showing,
      ArrayList<ISeat> seats)
      throws ServerException
  {
    super.getClient().addBooking(showing, seats);
  }


  @Override public void removeBooking(IBooking booking) throws ServerException
  {
    super.getClient().removeBooking(booking);
  }

  @Override public ArrayList<ISeat> getOccupiedSeats(IShowing showing)
      throws ServerException
  {
    return super.getClient().getOccupiedSeats(showing);
  }


  @Override public ArrayList<IBooking> getBookingList() throws ServerException
  {
    return super.getClient().getBookingList();
  }


  @Override public ArrayList<IShowing> getShowingList(IMovie movie)
      throws ServerException
  {
    return super.getClient().getShowingList(movie);
  }

  @Override public ArrayList<IMovie> getMovieList() throws ServerException

  {
    return super.getClient().getMovieList();

  }


  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener) throws ServerException
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
    super.getClient().addPropertyChangeListener(this::update);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener) throws ServerException
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
    super.getClient().removePropertyChangeListener(this::update);
  }


}
