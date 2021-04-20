package client.view.viewModel;

import client.model.Model;
import shared.Seat;
import shared.Showing;

public class ViewModelSeat
{
  private Showing selectedShowing;
  private Model modelManger;
  private Seat selectedSeat ;


  public ViewModelSeat(Model modelManger,Showing selectedShowing)
  {
    this.selectedShowing = selectedShowing;
    this.modelManger = modelManger;
  }


  public Seat getSelectedSeat()
  {
    return selectedSeat;
  }

  public void setSelectedSeat(Seat seat)
  {
    this.selectedSeat = seat;
  }
}
