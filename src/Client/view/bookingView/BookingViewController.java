package Client.view.bookingView;

import Client.core.ViewHandler;
import Client.core.ViewModelFactory;
import Client.view.viewModel.ViewModelBooking;
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

  public void backButtonHit() throws IOException
  {

    ViewHandler.getInstance().openView("../view/showingList/showingListView.fxml");
  }
}
