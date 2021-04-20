package client.view.bookingView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.viewModel.ViewModelBooking;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class BookingViewController
{
  @FXML public TextField usernameField;

  private ViewModelBooking viewModel = ViewModelFactory.getInstance().getBooking();



  public void makeBooking() throws IOException, SQLException
  {
    viewModel.makeBooking(usernameField.getText());
    System.out.println("You made a booking");
    ViewHandler.getInstance().openView("../view/movieList/movieListView.fxml");

  }

  public void backButtonHit() throws IOException, SQLException
  {

    ViewHandler.getInstance().openView("../view/seatView/seatView.fxml");
  }
}
