package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelBooking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.exception.ServerException;
import shared.transferobjects.ISeat;
import shared.transferobjects.IShowing;
import shared.transferobjects.Seat;
import shared.util.PropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * ViewModel for seat, her håndteres den læste data fra controlleren,
 * dette sker gennem binding på Property.
 */
public class ViewModelSeat implements PropertyChangeListener,
    PropertyChangeSubject
{
  private IShowing selectedShowing;
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
  private ClientModelBooking clientModel = ModelFactory.getInstance().getModelBooking();
  private ArrayList<ISeat> bookingSeatArrayList = new ArrayList<>();;
  ObservableList<Integer> integerObservableList = FXCollections.observableArrayList();
  private int currentNumber = 0;
  private ArrayList<ISeat> occupiedSeatArrayList;



  public void clearBookingList(){
    bookingSeatArrayList.clear();
  }

  public ArrayList<ISeat> getOccupiedSeats() throws ServerException
  {
    return clientModel.getOccupiedSeats(selectedShowing);
  }

  /**
   * Laver liste fra 1-14, da man kan booke 1-14 sæder af gangen
   * @return liste med integers fra 1 til og med 14
   */
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

  /**
   * Tager et id tæller det 1 op og retunere det
   * @param id original id
   * @return nyt id, original id + 1
   */
  public String setNewId(String id)
  {
    currentNumber = Integer.parseInt(id.substring(1));
    ++currentNumber;
    return id.substring(0, 1) + currentNumber;
  }


  public void addSeat(String seatNo)
  {
    ISeat seat = new Seat();
    seat.setSeatNo(seatNo);
    bookingSeatArrayList.add(seat);
  }

  /**
   * Tjekker om seat er optaget, dette bliver brugt når brugeren er ved at vælge
   * sæder
   * @param id seatNo
   * @throws IndexOutOfBoundsException Hvis seat er optaget
   */
  public void checkIfSeatOccupiedOnClick(String id) throws IndexOutOfBoundsException
  {
    for (ISeat seat : occupiedSeatArrayList)
    {
      if (seat.getSeatNo().equals(id))
      {
        throw new IndexOutOfBoundsException(
            "Ugyldigt input - Sæde er optaget");
      }
    }
  }

  /**
   * Tjekker om seat er optaget, bruges af systemet
   * @param id seatNo
   * @return true hvis optaget false hvis ikke
   */
  public boolean seatIsOccupied(String id)
  {
    for (ISeat seat : occupiedSeatArrayList)
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

  public void setShowing(IShowing selectedShowing)
  {
    this.selectedShowing = selectedShowing;
  }


  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    propertyChangeSupport.firePropertyChange(evt);
  }

  @Override public void addPropertyChangeListener (
      PropertyChangeListener listener) throws ServerException
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
    clientModel.addPropertyChangeListener(this::propertyChange);
  }


  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener) throws ServerException
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
    clientModel.removePropertyChangeListener(this);
  }
}

