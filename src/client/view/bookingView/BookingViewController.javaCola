package client.view.bookingView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.viewModel.ViewModelBooking;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import shared.transferobjects.Showing;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class BookingViewController
{
  @FXML public TextField usernameField;
  @FXML public TextField emailField;

  private ViewModelBooking viewModel = ViewModelFactory.getInstance().getBooking();

  public void init(){
    usernameField.textProperty().bindBidirectional(viewModel.userNameFieldProperty());
    emailField.textProperty().bindBidirectional(viewModel.emailFieldProperty());
  }

  public void makeBooking() throws IOException, SQLException
  {
    if (confirmBookingBox("Do you confirm your booking"))
    {

      viewModel.makeBooking();

      JOptionPane.showMessageDialog(null, "You have successfully made a booking");

      ViewHandler.getInstance().openView("../view/movieList/movieListView.fxml");

    }

  }

  private boolean confirmBookingBox(String s)
  {
    return JOptionPane
        .showConfirmDialog(null, s, "Confirmation", JOptionPane.YES_NO_OPTION)
        == JOptionPane.YES_OPTION;
  }

  public void backButtonHit() throws IOException, SQLException
  {

    if (confirmBookingBox("Do you want to go back to seat view"))
    {
      ViewHandler.getInstance().openView("../view/seatView/seatView.fxml");

    }
     }
}
