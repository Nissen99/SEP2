package client.view.viewModel;

import client.model.Model;
import shared.Seat;
import shared.Showing;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewModelSeat
{
  private Showing selectedShowing;
  private Model modelManger;
  private Seat selectedSeat ;
  private ArrayList<Seat> seatArrayList;



  public ViewModelSeat(Model modelManger,Showing selectedShowing)
      throws SQLException

  {
    this.selectedShowing = selectedShowing;
    this.modelManger = modelManger;
    seatArrayList = modelManger.getOccupiedSeats(selectedShowing);
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

  public ArrayList<Seat> getOccupiedSeats() throws SQLException
  {
    return modelManger.getOccupiedSeats(selectedShowing);
  }

}
