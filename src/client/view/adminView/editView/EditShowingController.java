package client.view.adminView.editView;

import client.core.ViewHandler;

import java.io.IOException;
import java.sql.SQLException;

public class EditShowingController
{

public void init(){}

public void backButton() throws IOException, SQLException
{
  ViewHandler.getInstance().openView("../view/adminView/editView/editMovieView.fxml");
}


  public void removeShowing(){}

  public void addShowing(){


  }
}
