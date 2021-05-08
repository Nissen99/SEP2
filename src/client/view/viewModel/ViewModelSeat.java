package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelBooking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.ServerException;
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
  private ClientModelBooking clientModel = ModelFactory.getInstance().getModelBooking();
  private ArrayList<Seat> seatArrayList;
  ObservableList<Integer> integerObservableList = FXCollections.observableArrayList();



  public ViewModelSeat() throws SQLException, RemoteException
  {

    clientModel.addPropertyChangeListener(this::update);


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

  public void setSelectedSeats(ArrayList<Seat> seatArray)
  {
    this.seatArrayList = seatArray;
  }

  public ArrayList<Seat> getOccupiedSeats() throws SQLException, RemoteException
  {
    return clientModel.getOccupiedSeats(selectedShowing);
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
  {
    this.selectedShowing = selectedShowing;
     }

  public ObservableList<Integer> getChoiceList()
  {
    if (integerObservableList.size() < 13){
      integerObservableList.clear();
      for (int i = 1; i <= 14; i++)
      {
        integerObservableList.add(i);
      }
    }

    return integerObservableList;
  }

  public void addBooking() throws ServerException, RemoteException, SQLException
  {
    clientModel.addBooking(selectedShowing,seatArrayList);
  }
}
