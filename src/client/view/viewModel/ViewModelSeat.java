package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelBooking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.exception.ServerException;
import shared.util.PropertyChangeSubject;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ViewModelSeat implements PropertyChangeListener,
    PropertyChangeSubject
{
  private Showing selectedShowing;
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
  private ClientModelBooking clientModel = ModelFactory.getInstance().getModelBooking();
  private ArrayList<Seat> bookingSeatArrayList;
  private ArrayList<Seat> occupiedSeatArrayList;
  ObservableList<Integer> integerObservableList = FXCollections.observableArrayList();
  private int currentNumber = 0;


  public ViewModelSeat(Showing showing)
  {
    this.selectedShowing = showing;
    clientModel.addPropertyChangeListener(this::update);
    bookingSeatArrayList = new ArrayList<>();
  }

  private void update(PropertyChangeEvent propertyChangeEvent)
  {
    propertyChangeSupport.firePropertyChange(propertyChangeEvent);
  }


  public ArrayList<Seat> getOccupiedSeats() throws ServerException
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


  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
    clientModel.removePropertyChangeListener(this);
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

  public void addBooking() throws ServerException
  {
    clientModel.addBooking(selectedShowing, bookingSeatArrayList);
  }


  public void setCurrentNumber(String id)
       {
       currentNumber = Integer.parseInt(id.substring(1));
      ++currentNumber;
    }

  public int getCurrentNumber()
  {
    return currentNumber;
  }

  public void addSeat(String seatNo)
  {
    Seat seat = new Seat();
    seat.setSeatNo(seatNo);
    bookingSeatArrayList.add(seat);

  }

  public void checkIfSeatOccupied(String id) throws ServerException
  {
    for (Seat seat : getOccupiedSeats())
    {
      if (seat.getSeatNo().equals(id))
      {
        throw new IndexOutOfBoundsException(
            "Invalid input - Seat already occupied");
      }
    }
  }

  public boolean seatIsOccupied(String id)
  {
    for (Seat seat : occupiedSeatArrayList)
    {
      if (seat.getSeatNo().equals(id)){
        return true;
      }
    }
    return false;
  }

  public void updateOccupiedSeatsList() throws ServerException
  {
    occupiedSeatArrayList = getOccupiedSeats();
  }
}
