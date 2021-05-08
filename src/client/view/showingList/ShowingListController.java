package client.view.showingList;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.viewModel.ViewModelShowingList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.Showing;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ShowingListController
{
  @FXML public TableColumn<Timestamp, String> datoerForFremvisning;
  @FXML public TableColumn<Timestamp, String> ugedagForFremvisning;
  @FXML public TableColumn<Timestamp, String> tidspunktForFremvisning;
  @FXML public Label movieTitle;
  @FXML public TableView<Showing> tableViewForFilmFremvisninger;

  private ViewModelShowingList viewModel = ViewModelFactory.getInstance()
      .getShowingList();


  public void init() throws SQLException, RemoteException
  {
    movieTitle.textProperty().bindBidirectional(viewModel.movieTitleProperty());

    tableViewForFilmFremvisninger.setItems(viewModel.getAllShowings());

    tidspunktForFremvisning.setCellValueFactory(new PropertyValueFactory<>("time"));

    ugedagForFremvisning.setCellValueFactory(new PropertyValueFactory<>("weekDay"));

   datoerForFremvisning.setCellValueFactory(new PropertyValueFactory<>("date"));

  }

public void confirmChoice() throws IOException, SQLException
{
    Showing showing = tableViewForFilmFremvisninger.getSelectionModel().getSelectedItem();
    if (showing == null){
        Alert alert = AlertBox
            .makeAlert("information","Showings", "No showing selected");
        alert.showAndWait();
    }else
    {
      viewModel.setSelectedShowing(showing);
      ViewHandler.getInstance().openView("../view/seatView/seatView.fxml");
    }
  }


public void backButton() throws IOException, SQLException
{
  ViewHandler.getInstance().openView("../view/movieList/movieListView.fxml");

}

}
