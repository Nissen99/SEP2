package client.view.adminView.editView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.viewModel.ViewModelAddShowing;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import shared.exception.ServerException;

/**
 * Controller til addShowingView, står for at læse bruger inputs, dette er gjort
 * gennem bindings med viewModellen.
 *
 */
public class AddShowingController
{

  @FXML public JFXTimePicker timePicker;
  @FXML public JFXDatePicker datePicker;
  @FXML public ChoiceBox<String> hallNo;
  private ViewModelAddShowing viewModel = ViewModelFactory.getInstance().getAddShowing();

  public void init()
  {
  datePicker.valueProperty().bindBidirectional(viewModel.localDatePropertyProperty());
  timePicker.valueProperty().bindBidirectional(viewModel.localTimePropertyProperty());
  hallNo.valueProperty().bindBidirectional(viewModel.hallNoProperty());
    setChoiceBox();

  }

  public void back()
  {
    ViewHandler.getInstance().openView("../view/adminView/editView/editShowingView.fxml");
  }

  public void confirm()
  {
    try
    {
      viewModel.addShowing();
      back();
    } catch (NullPointerException e) {
      Alert alert = AlertBox.makeAlert("information", "Error!","Invalid input - Time and Date needs to be filled");
      alert.showAndWait();
    }
    catch (IllegalArgumentException | ServerException e) {
      Alert alert = AlertBox.makeAlert("information", "Error!", e.getMessage());
      alert.showAndWait();
    }
  }

  private void setChoiceBox()
  {
    try
    {
      hallNo.setItems(viewModel.getChoiceList());
      hallNo.setValue(viewModel.getChoiceList().get(0));
    }
    catch (ServerException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!", e.getMessage());
      alert.showAndWait();
    }
  }

}
