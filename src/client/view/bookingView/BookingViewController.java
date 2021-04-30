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



  public void makeBooking() throws IOException, SQLException
  {
    if (JOptionPane
        .showConfirmDialog(null, "Do you confirm your booking",
            "Confirmation", JOptionPane.YES_NO_OPTION)
        == JOptionPane.YES_OPTION)
    {
      System.out.println(emailField.getText()+"Vi er i controlleren");
      viewModel.makeBooking(usernameField.getText(),emailField.getText());
      JOptionPane.showMessageDialog(null, "You have successfully made a booking");
      ViewHandler.getInstance().openView("../view/movieList/movieListView.fxml");

    }
    else
    {
      // do nothing
    }



  }

  public void backButtonHit() throws IOException, SQLException
  {

    if (JOptionPane
        .showConfirmDialog(null, "Do you want to go back to seat view",
            "Confirmation", JOptionPane.YES_NO_OPTION)
        == JOptionPane.YES_OPTION)
    {
      ViewHandler.getInstance().openView("../view/seatView/seatView.fxml");

    }
    else
    {
      // do nothing
    }

  }
}
