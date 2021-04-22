package client.view.loginView;

import client.core.ViewHandler;

import java.io.IOException;
import java.sql.SQLException;

public class LoginViewController
{

  public void init(){}
  public void adminButtonPressed() throws IOException, SQLException
  {
    ViewHandler.getInstance().openView("../view/adminView/adminView.fxml");
  }

  public void userButtonPressed() throws IOException, SQLException
  {

    ViewHandler.getInstance().openView("../view/movieList/movieListView.fxml");
  }
}
