package client.view.adminView;

import client.core.ViewHandler;
import client.util.AlertBox;
import client.view.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;

/**
 * Controller til adminView, står for at læse bruger inputs, dette er buttons og
 * bliver kun brugt til at skifte view. Derfor har dette view heller ikke en ViewModel
 */

public class AdminViewController implements Controller
{


public void init(){}

  public void back()
  {
    Alert alert = AlertBox.makeAlert("confirmation", "Log ud",
        "Vil du gerne logge ud?");
    alert.showAndWait().ifPresent(type -> {
      if (type.getButtonData() == ButtonBar.ButtonData.YES)
      {
          ViewHandler.getInstance().openView("Login");
      }
    });
  }

  public void editMovie()
  {
  ViewHandler.getInstance().openView("Edit Movie");


  }

  public void editBooking()
  {
  ViewHandler.getInstance().openView("Edit Booking");
  }
}


