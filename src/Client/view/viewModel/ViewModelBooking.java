package Client.view.viewModel;

import Client.core.ModelFactory;
import Client.model.Model;
import shared.Showing;

import java.sql.SQLException;

public class ViewModelBooking
{

  private  Showing showing;
  private String seatNo;
  Model modelManager = ModelFactory.getInstance().getModel();

  public ViewModelBooking(Showing showing){
    this.showing = showing;
  }

  public void makeBooking(String username) throws SQLException
  {
    modelManager.addBooking(showing, username, seatNo);
  }


}
