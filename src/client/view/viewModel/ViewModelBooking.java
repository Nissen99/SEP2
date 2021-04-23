package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModel;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewModelBooking
{

  private Showing showing;
  private ArrayList<Seat> seatArrayList;
  ClientModel clientModelManager = ModelFactory.getInstance().getModel();

  public ViewModelBooking(Showing showing, ArrayList<Seat> seatArray){
    this.showing = showing;
    this.seatArrayList = seatArray;

  }

  public void makeBooking(String username) throws SQLException, RemoteException
  {
//    for (Seat seat : seatArrayList)
//    {
//      clientModelManager.addBooking(showing, username, seat.getSeatNo());
//      System.out.println(" seat is " + seat.getSeatNo());
//    }
    clientModelManager.addBooking(showing, username, seatArrayList);

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
