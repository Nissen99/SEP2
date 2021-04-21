package client.view.viewModel;

import client.model.ClientModel;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewModelSeat
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
    for (Seat occupiedSeat : seatArrayList )
    {
     String id = occupiedSeat.getSeatNo();


    }
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

}
