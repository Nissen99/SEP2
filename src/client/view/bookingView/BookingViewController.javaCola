package client.view.bookingView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.viewModel.ViewModelBooking;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TextField;
import shared.transferobjects.Showing;

import javax.swing.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class BookingViewController
{
  @FXML public TextField usernameField;
  @FXML public TextField emailField;

  private ViewModelBooking viewModel = ViewModelFactory.getInstance()
      .getBooking();

  public void init()
  {
    usernameField.textProperty()
        .bindBidirectional(viewModel.userNameFieldProperty());
    emailField.textProperty().bindBidirectional(viewModel.emailFieldProperty());
  }

  public void makeBooking() throws IOException, SQLException
  {

    Alert alert = AlertBox.makeAlert("confirmation", "Make Booking",
        "Do you want to confirm your booking?");
    alert.showAndWait().ifPresent(type -> {
      if (type.getButtonData() == ButtonBar.ButtonData.YES)
      {
        System.out.println("Yes pressed");
        try
        {
          viewModel.makeBooking();
        }
        catch (RemoteException e)
        {
          e.printStackTrace();
        }
      }
    });
    ViewHandler.getInstance().openView("../view/movieList/movieListView.fxml");

  }

  public void backButtonHit() throws IOException, SQLException
  {

    ViewHandler.getInstance().openView("../view/seatView/seatView.fxml");

  }
}
