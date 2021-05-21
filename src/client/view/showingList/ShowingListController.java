package client.view.showingList;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.Controller;
import client.view.viewModel.ViewModelShowingList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.exception.ServerException;
import shared.transferobjects.IShowing;
import java.sql.Timestamp;

/**
 * Controller til showingList, står for at læse bruger inputs, dette er gjort
 * gennem et tableView og confirm/back knap.
 */


public class ShowingListController implements Controller
{
  @FXML public TableColumn<Timestamp, String> datoerForFremvisning;
  @FXML public TableColumn<Timestamp, String> ugedagForFremvisning;
  @FXML public TableColumn<Timestamp, String> tidspunktForFremvisning;
  @FXML public Label movieTitle;
  @FXML public TableView<IShowing> tableViewForFilmFremvisninger;

  private ViewModelShowingList viewModel = ViewModelFactory.getInstance()
      .getShowingList();


  public void init()
  {
    movieTitle.textProperty().bind(viewModel.movieTitleProperty());

    setUpTable();
  }

  private void setUpTable()
  {
    try
    {
      tableViewForFilmFremvisninger.setItems(viewModel.getFutureShowings());
      tidspunktForFremvisning.setCellValueFactory(new PropertyValueFactory<>("time"));
      ugedagForFremvisning.setCellValueFactory(new PropertyValueFactory<>("weekDay"));
      datoerForFremvisning.setCellValueFactory(new PropertyValueFactory<>("date"));

    }
    catch (ServerException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!", e.getMessage());
      alert.show();
    }
  }

  public void confirmChoice()
{
    try
    {
      IShowing showing = tableViewForFilmFremvisninger.getSelectionModel().getSelectedItem();
      viewModel.setSelectedShowing(showing);
      ViewHandler.getInstance().openView("Seat");
    }
    catch (NullPointerException e){
      Alert alert = AlertBox.makeAlert("information", "Error!", e.getMessage());
      alert.show();
    }
  }

public void backButton()
{
  ViewHandler.getInstance().openView("Movie List");
}
}
