package client.view.adminView.editView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.viewModel.ViewModelEditShowing;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.exception.ServerException;
import shared.transferobjects.IShowing;

public class EditShowingController
{

  @FXML public Label filmShowingsErFor;
  @FXML public TableView<IShowing> tableViewForFilmFremvisninger;
  @FXML public TableColumn<IShowing, String> datoerForFremvisning;
  @FXML public TableColumn<IShowing, String> ugedagForFremvisning;
  @FXML public TableColumn<IShowing, String> tidspunktForFremvisning;
  private ViewModelEditShowing viewModel = ViewModelFactory.getInstance()
      .getEditShowing();

  public void init()
  {
    setUpTableView();

    filmShowingsErFor.textProperty().bind(viewModel.movieTitleProperty());
  }
  private void setUpTableView()
  {
    try
    {
      tableViewForFilmFremvisninger.setItems(viewModel.getAllShowings());

      tidspunktForFremvisning.setCellValueFactory(new PropertyValueFactory<>("time"));
      ugedagForFremvisning.setCellValueFactory(new PropertyValueFactory<>("weekDay"));
      datoerForFremvisning.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    catch (ServerException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error", e.getMessage());
      alert.showAndWait();
    }
  }

  public void backButton()
  {
  ViewHandler.getInstance().openView("../view/adminView/editView/editMovieView.fxml");
}

  public void removeShowing()
  {

    try
    {
      IShowing showing = tableViewForFilmFremvisninger.getSelectionModel().getSelectedItem();
      viewModel.removeShowing(showing);
      setUpTableView();
    }
    catch (ServerException | NullPointerException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!", e.getMessage());
      alert.showAndWait();
    }
  }

  public void addShowing()
  {
    ViewHandler.getInstance().openView("../view/adminView/editView/addShowingView.fxml");
  }
}
