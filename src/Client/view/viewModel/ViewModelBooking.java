package Client.view.viewModel;

import Client.core.ModelFactory;
import Client.model.Model;

import java.sql.SQLException;

public class ViewModelBooking
{

  Model modelManager = ModelFactory.getInstance().getModel();

  public void makeBooking(String username) throws SQLException
  {
    modelManager.addBooking(modelManager.getSelectedShowing(),username);
  }


}
