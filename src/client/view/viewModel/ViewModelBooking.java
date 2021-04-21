package client.view.viewModel;

import client.core.ModelFactory;
import client.model.Model;
import shared.Seat;
import shared.Showing;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewModelBooking
{

  private  Showing showing;
  private ArrayList<Seat> seatArrayList;
  Model modelManager = ModelFactory.getInstance().getModel();

  public ViewModelBooking(Showing showing, ArrayList<Seat> seatArray){
    this.showing = showing;
    this.seatArrayList = seatArray;
  }

  public void makeBooking(String username) throws SQLException
  {
    for (Seat seat : seatArrayList)
    {
      modelManager.addBooking(showing, username, seat.getSeatNo());
      System.out.println(" seat is " + seat.getSeatNo());
    }

  }

  public Seat getSeat(String seatNo)
  {
    for (Seat seat : seatArrayList)
    {
      if (seat.getSeatNo().equals(seatNo)){
        return seat;
      }
    }
  return null;
  }



}
