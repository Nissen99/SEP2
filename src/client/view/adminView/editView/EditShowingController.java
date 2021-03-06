package client.view.adminView.editView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.Controller;
import client.view.viewModel.ViewModelEditShowing;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.exception.ServerException;
import shared.transferobjects.IShowing;

/**
 * Controller til editShowingView, står for at læse bruger inputs, dette er gjort
 * gennem et tableview.
 */

public class EditShowingController implements Controller
{

  private String path = "adminView/editView/editShowing";
  private String viewTitle = "Edit Showing";
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

  @Override public String getPath()
  {
    return path;
  }

  @Override public String getTitle()
  {
    return viewTitle;
  }

  private void setUpTableView()
  {
    try
    {
      tableViewForFilmFremvisninger.setItems(viewModel.getAllShowings());

      tidspunktForFremvisning
          .setCellValueFactory(new PropertyValueFactory<>("time"));
      ugedagForFremvisning
          .setCellValueFactory(new PropertyValueFactory<>("weekDay"));
      datoerForFremvisning
          .setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    catch (ServerException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error", e.getMessage());
      alert.show();
    }
  }

  public void backButton()
  {
    ViewHandler.getInstance().setState(new EditMovieController());
    ViewHandler.getInstance().openView();
    //ViewHandler.getInstance().openView("Edit Movie");
  }

  public void removeShowing()
  {
    IShowing showing = tableViewForFilmFremvisninger.getSelectionModel().getSelectedItem();
    if (showing != null)
    {
      Alert alert = AlertBox.makeAlert("confirmation", "Slet Filmfremvisning",
          "Vil du gerne slette den valgte filmfremvisning?");
      alert.showAndWait().ifPresent(type -> {
        if (type.getButtonData() == ButtonBar.ButtonData.YES)
        {
          try
          {
            viewModel.removeShowing(showing);
            setUpTableView();
          }
          catch (ServerException e)
          {
            Alert alert2 = AlertBox.makeAlert("information", "Error!", e.getMessage());
            alert2.show();
          }
        }
      });
    }
  }

  public void addShowing()
  {
    ViewHandler.getInstance().setState(new AddShowingController());
    ViewHandler.getInstance().openView();
    //ViewHandler.getInstance().openView("Add Showing");
  }
}
