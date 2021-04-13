package Client.view.viewModel;

import Client.core.ModelFactory;
import Client.model.Model;
import shared.transferobjects.Booking;
import shared.transferobjects.User;

import java.sql.SQLException;

public class ViewModelBooking
{

  Model model = ModelFactory.getInstance().getModel();

  public void makeBooking(String username) throws SQLException
  {
    model.addBooking(model.getSelectedShowing(),username);
  }


}
