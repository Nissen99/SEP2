package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import server.ServerException;
import shared.transferobjects.Seat;
import shared.transferobjects.Showing;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ViewModelBooking
{

  private Showing showing;
  private StringProperty userNameField = new SimpleStringProperty();


  private StringProperty emailField = new SimpleStringProperty();

  private ArrayList<Seat> seatArrayList;
  ClientModel clientModelManager = ModelFactory.getInstance().getModel();


  public void makeBooking() throws  RemoteException
  {
    System.out.println("Make booking i viewModel ? " + getEmailField());

    try
    {
      clientModelManager.addBooking(showing, getUserNameField(), getEmailField(), seatArrayList);
    }
    catch (ServerException serverException)
    {
      serverException.printStackTrace();
    }

  }

  public void setShowing(Showing selectedShowing)
  {
  this.showing = selectedShowing;
  }

  public void setSelectedSeats(ArrayList<Seat> selectedSeats)
  {
    this.seatArrayList = selectedSeats;

  }

  public String getUserNameField()
  {
    return userNameField.get();
  }

  public StringProperty userNameFieldProperty()
  {
    return userNameField;
  }

  public String getEmailField()
  {
    return emailField.get();
  }

  public StringProperty emailFieldProperty()
  {
    return emailField;
  }

}
