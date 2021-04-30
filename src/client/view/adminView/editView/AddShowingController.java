package client.view.adminView.editView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.viewModel.ViewModelAddShowing;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import shared.transferobjects.Showing;

import javax.swing.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class AddShowingController
{

  @FXML public JFXTimePicker timePicker;
  @FXML public JFXDatePicker datePicker;
  @FXML public JFXTextField hallNo;
  private ViewModelAddShowing viewModel = ViewModelFactory.getInstance().getAddShowing();

  public void init(){
    hallNo.textProperty().bindBidirectional(viewModel.hallNoProperty());
  }

  public void back() throws IOException, SQLException
  {
    ViewHandler.getInstance().openView("../view/adminView/editView/editShowingView.fxml");
  }

  public void confirm() throws SQLException, IOException
  {
    try
    {
      LocalDate date = datePicker.getValue();
      LocalTime time = timePicker.getValue();
      Timestamp timestamp = new Timestamp(date.getYear() - 1900, date.getMonthValue() - 1,
          date.getDayOfMonth(), time.getHour(), time.getMinute(), time.getSecond(),
          0);
      viewModel.addShowing(timestamp);
      back();
    } catch (NullPointerException e) {
      JOptionPane.showMessageDialog(null, "Invalid input - Time and Date needs to be filled");
    }
    catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
    }

  }


}
