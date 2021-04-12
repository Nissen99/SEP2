package Client.view.viewModel;

import Client.core.ModelFactory;
import Client.model.Model;
import shared.transferobjects.Booking;
import shared.transferobjects.User;

public class ViewModelBooking
{

  Model model = ModelFactory.getInstance().getModel();

  public void makeBooking(String username)
  {
    model.addBooking(new Booking(model.getSelectedShowing(), new User(username)));
  }
}
