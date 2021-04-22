package client.view.adminView.editView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.viewModel.ViewModelEditMovie;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import shared.transferobjects.Movie;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class EditMovieController
{
  @FXML public TextField movieTitleTextField;
  private ViewModelEditMovie viewModel;

  public void init(){
   viewModel = ViewModelFactory.getInstance().getEditMovie();
}

  public void back() throws IOException, SQLException
  {
    ViewHandler.getInstance().openView("../view/adminView/adminView.fxml");
  }

  public void addMovie() throws SQLException, RemoteException
  {
    if (!movieTitleTextField.getText().equals(""))
    {
      Movie movie = new Movie(movieTitleTextField.getText());
      viewModel.addMovie(movie);
      movieTitleTextField.clear();
    }
  }

  public void removeMovie(){
    //TODO
  }


}
