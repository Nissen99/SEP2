package client.view.showingList;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.Controller;
import client.view.movieList.MovieListController;
import client.view.seatView.SeatViewController;
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

  private String path = "showingList/showingList";
  private String viewTitle = "Showing List";
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

  @Override public String getPath()
  {
    return path;
  }

  @Override public String getTitle()
  {
    return viewTitle;
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
      ViewHandler.getInstance().setState(new SeatViewController());
      ViewHandler.getInstance().openView();
      //ViewHandler.getInstance().openView("Seat");
    }
    catch (NullPointerException e){
      Alert alert = AlertBox.makeAlert("information", "Error!", e.getMessage());
      alert.show();
    }
  }

public void backButton()
{
  ViewHandler.getInstance().setState(new MovieListController());
  ViewHandler.getInstance().openView();
  //ViewHandler.getInstance().openView("Movie List");
}
}
