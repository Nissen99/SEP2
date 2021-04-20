package Client.view.viewModel;

import Client.model.Model;
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


  public Seat getSelectedSeat()
  {
    return selectedSeat;
  }

  public void setSelectedSeat(Seat seat)
  {
    this.selectedSeat = seat;
  }

  public ArrayList<Seat> getOccupiedSeats() throws SQLException
  {
    return modelManger.getOccupiedSeats(selectedShowing);
  }
}
