package client.view.adminView.editView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.viewModel.ViewModelEditShowing;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class EditShowingController
{

  public Label filmShowingsErFor;
  @FXML public TableView tableViewForFilmFremvisninger;
  @FXML public TableColumn datoerForFremvisning;
  @FXML public TableColumn ugedagForFremvisning;
  @FXML public TableColumn tidspunktForFremvisning;
  private ViewModelEditShowing viewModel = ViewModelFactory.getInstance()
      .getEditShowing();

  public void init() throws SQLException, RemoteException
  {

    tableViewForFilmFremvisninger.setItems(viewModel.getAllShowings());

    tidspunktForFremvisning.setCellValueFactory(new PropertyValueFactory("time"));
    ugedagForFremvisning.setCellValueFactory(new PropertyValueFactory("weekDay"));
    datoerForFremvisning.setCellValueFactory(new PropertyValueFactory("date"));

    filmShowingsErFor.textProperty().bind(viewModel.movieTitleProperty());

  }

public void backButton() throws IOException, SQLException
{
  ViewHandler.getInstance().openView("../view/adminView/editView/editMovieView.fxml");
}


  public void removeShowing(){

  }

  public void addShowing() throws IOException, SQLException
  {
    ViewHandler.getInstance().openView("../view/adminView/editView/addShowingView.fxml");



  }
}
