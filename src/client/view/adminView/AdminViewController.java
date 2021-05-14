package client.view.adminView;

import client.core.ViewHandler;


public class AdminViewController
{


public void init(){}

  public void back()
  {
  ViewHandler.getInstance().openView("../view/loginView/loginView.fxml");
  }

  public void editMovie()
  {
  ViewHandler.getInstance().openView("../view/adminView/editView/editMovieView.fxml");
  }

  public void editBooking()
  {
  ViewHandler.getInstance().openView("../view/adminView/editView/editBookingView.fxml");
  }
}
