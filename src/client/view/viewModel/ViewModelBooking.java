package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModel;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewModelBooking
{

  private  Showing showing;
  private Seat seat;
  ClientModel clientModelManager = ModelFactory.getInstance().getModel();

  public ViewModelBooking(Showing showing,Seat seat){
    this.showing = showing;
    this.seat = seat;
  }

  public void makeBooking(String username) throws SQLException, RemoteException
  {
    clientModelManager.addBooking(showing,username,seat.getSeatNo());
    System.out.println(" seat is " + seat.getSeatNo());
  }

  public Seat getSeat()
  {
    return seat;
  }



}
