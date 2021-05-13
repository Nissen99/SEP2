package client.view.adminView;

import client.core.ViewHandler;
import shared.exception.ServerException;

import java.io.IOException;
import java.sql.SQLException;

public class AdminViewController
{


public void init(){}

  public void back() throws IOException, SQLException, ServerException
  {
  ViewHandler.getInstance().openView("../view/loginView/loginView.fxml");
  }

  public void editMovie() throws IOException, SQLException, ServerException
  {

  ViewHandler.getInstance().openView("../view/adminView/editView/editMovieView.fxml");
  }

  public void editBooking() throws IOException, SQLException, ServerException
  {
  ViewHandler.getInstance().openView("../view/adminView/editView/editBookingView.fxml");
  }
}
