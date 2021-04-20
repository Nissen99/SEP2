package Client.view.viewModel;

import Client.core.ModelFactory;
import Client.model.Model;
import shared.Seat;
import shared.Showing;

import java.sql.SQLException;

public class ViewModelBooking
{

  private  Showing showing;
  private Seat seat;
  Model modelManager = ModelFactory.getInstance().getModel();

  public ViewModelBooking(Showing showing,Seat seat){
    this.showing = showing;
    this.seat = seat;
  }

  public void makeBooking(String username) throws SQLException
  {
    modelManager.addBooking(showing,username,seat.getSeatNo());
    System.out.println(" seat is " + seat.getSeatNo());
  }

  public Seat getSeat()
  {
    return seat;
  }



}
