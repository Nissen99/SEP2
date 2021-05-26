package client.model;

import client.network.Client;
import shared.exception.ServerException;
import shared.transferobjects.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * Denne klasse extender ClientModelShowingListManager, som extender ClientModelManager
 * hvor har referrnce til Client. Vi bruger super.getClient når Client skal bruges.
 *
 * Denne klasse lytter på Client, dette bliver brugt til dynamisk opdatering af seatView.
 *
 * Denne klasse videresender data som den får fra ViewModel til Client.
 */
public class ClientModelBookingManager extends ClientModelShowingListManager implements ClientModelBooking, PropertyChangeListener
{

  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


  public ClientModelBookingManager(Client client)
  {
    super(client);
  }

  /**
   * @param showing den showing der skal bookes billetter til
   * @param seats de sæder der skal bookes
   * @throws ServerException connection fejl eller anden reservation af de valgte sæder
   */
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

  /**
   * Sørger for at tilføje listener til listen af observers til subject
   *
   * @param listener observer der tilføjes til subject
   * @throws ServerException
   */
  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener) throws ServerException
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
    super.getClient().addPropertyChangeListener(this);
  }

  /**
   * Sørger for at fjerne listener fra listen af observers til subject
   *
   * @param listener observer der fjernes fra subject
   * @throws ServerException
   */
  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener) throws ServerException
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
    super.getClient().removePropertyChangeListener(this);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    propertyChangeSupport.firePropertyChange(evt);

  }
}
