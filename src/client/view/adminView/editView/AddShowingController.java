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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddShowingController
{

  @FXML public JFXTimePicker timePicker;
  @FXML public JFXDatePicker datePicker;
  @FXML public ChoiceBox<String> hallNo;
  private ViewModelAddShowing viewModel = ViewModelFactory.getInstance().getAddShowing();

  public void init()
  {
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
      LocalDate date = datePicker.getValue();
      LocalTime time = timePicker.getValue();
      Timestamp timestamp = new Timestamp(date.getYear() - 1900, date.getMonthValue() - 1,
          date.getDayOfMonth(), time.getHour(), time.getMinute(), time.getSecond(),
          0);
      viewModel.addShowing(timestamp, hallNo.getValue());
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
      e.printStackTrace();
    }
  }

}
