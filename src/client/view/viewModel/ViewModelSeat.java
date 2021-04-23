package client.view.viewModel;

import client.model.ClientModel;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewModelSeat implements PropertyChangeListener
{
  private Showing selectedShowing;
  private ClientModel clientModelManger;
  private Seat selectedSeat ;
  private ArrayList<Seat> seatArrayList;


  public ViewModelSeat(ClientModel clientModelManger,Showing selectedShowing)
      throws SQLException, RemoteException

  {
    this.selectedShowing = selectedShowing;
    this.clientModelManger = clientModelManger;
    seatArrayList = clientModelManger.getOccupiedSeats(selectedShowing);
    clientModelManger.addPropertyChangeListener(this::update);
    for (Seat occupiedSeat : seatArrayList )
    {
     String id = occupiedSeat.getSeatNo();
    }
  }

  private void update(PropertyChangeEvent propertyChangeEvent)
  {

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

  }
}
