package Client.view.showingList;

import Client.core.ViewHandler;
import Client.core.ViewModelFactory;
import Client.view.viewModel.ViewModelShowingList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.io.IOException;
import java.sql.Timestamp;

public class ShowingListController
{
  @FXML public TableColumn<Timestamp, String> datoerForFremvisning;
  @FXML public TableColumn<Timestamp, String> ugedagForFremvisning;
  @FXML public TableColumn<Timestamp, String> tidspunktForFremvisning;
  @FXML public Label filmShowingsErFor;
  @FXML public TableView<Showing> tableViewForFilmFremvisninger;

  private ViewModelShowingList viewModel = ViewModelFactory.getInstance()
      .getViewModelShowingList();


  public void init(){

    tableViewForFilmFremvisninger.setItems(viewModel.getAllShowings());

    tidspunktForFremvisning.setCellValueFactory(new PropertyValueFactory("time"));

    ugedagForFremvisning.setCellValueFactory(new PropertyValueFactory("weekDay"));

   datoerForFremvisning.setCellValueFactory(new PropertyValueFactory("date"));

    filmShowingsErFor.setText(viewModel.getMovie());
  }

public void confirmChoice() throws IOException
{

  Showing showing = tableViewForFilmFremvisninger.getSelectionModel().getSelectedItem();

  viewModel.setSelectedShowing(showing);

  ViewHandler.getInstance().openView("../view/bookingView/bookingView.fxml");
}

}
