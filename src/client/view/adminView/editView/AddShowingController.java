package client.view.adminView.editView;

import client.core.ViewModelFactory;
import client.view.viewModel.ViewModelAddShowing;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.Calendar;

public class AddShowingController
{

  @FXML public DatePicker datePicker;
  @FXML public TextField hallNo;
  private ViewModelAddShowing viewModel = ViewModelFactory.getInstance().getAddShowing();

  public void init(){
    hallNo.textProperty().bind(viewModel.hallNoProperty());
  }





}
