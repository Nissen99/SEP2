package client.view.adminView.editView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.viewModel.ViewModelEditShowing;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class EditShowingController
{

  public Label filmShowingsErFor;
  @FXML public TableView<Showing> tableViewForFilmFremvisninger;
  @FXML public TableColumn<Showing, String> datoerForFremvisning;
  @FXML public TableColumn<Showing, String> ugedagForFremvisning;
  @FXML public TableColumn<Showing, String> tidspunktForFremvisning;
  private ViewModelEditShowing viewModel = ViewModelFactory.getInstance()
      .getEditShowing();

  public void init() throws SQLException, RemoteException
  {

    setUpTableView();

    filmShowingsErFor.textProperty().bind(viewModel.movieTitleProperty());

  }

  private void setUpTableView() throws SQLException, RemoteException
  {
    tableViewForFilmFremvisninger.setItems(viewModel.getAllShowings());

    tidspunktForFremvisning.setCellValueFactory(new PropertyValueFactory("time"));
    ugedagForFremvisning.setCellValueFactory(new PropertyValueFactory("weekDay"));
    datoerForFremvisning.setCellValueFactory(new PropertyValueFactory("date"));
  }

  public void backButton() throws IOException, SQLException
{
  ViewHandler.getInstance().openView("../view/adminView/editView/editMovieView.fxml");
}


  public void removeShowing() throws SQLException, RemoteException
  {

    Showing showing = tableViewForFilmFremvisninger.getSelectionModel().getSelectedItem();
    viewModel.removeShowing(showing);
    setUpTableView();
  }

  public void addShowing() throws IOException, SQLException
  {
    ViewHandler.getInstance().openView("../view/adminView/editView/addShowingView.fxml");

  }



}
