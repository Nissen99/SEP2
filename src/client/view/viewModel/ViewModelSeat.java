package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModel;
import server.model.PropertyChangeSubject;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewModelSeat implements PropertyChangeListener,
    PropertyChangeSubject
{
  private Showing selectedShowing;
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
  private ClientModel clientModelManger = ModelFactory.getInstance().getModel();
  private Seat selectedSeat ;
  private ArrayList<Seat> seatArrayList;


  public ViewModelSeat() throws SQLException, RemoteException
  {

    clientModelManger.addPropertyChangeListener(this::update);


  }

  private void update(PropertyChangeEvent propertyChangeEvent)
  {
    System.out.println("FIRE 4");
    propertyChangeSupport.firePropertyChange(propertyChangeEvent);
  }

  public ArrayList<Seat> getSelectedSeat()
  {
    return seatArrayList;
  }

  public void setSelectedSeat(ArrayList<Seat> seatArray)
  {
    this.seatArrayList = seatArray;
  }

  public ArrayList<Seat> getOccupiedSeats() throws SQLException, RemoteException
  {
    return clientModelManger.getOccupiedSeats(selectedShowing);
  }

  public void removeListen(){
    clientModelManger.removePropertyChangeListener(this);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    update(evt);
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

  public void setSelectedShowing(Showing selectedShowing)
      throws SQLException, RemoteException
  {
    this.selectedShowing = selectedShowing;
     }
}
