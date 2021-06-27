package client.view.adminView;

import client.core.ViewHandler;
import client.util.AlertBox;
import client.view.Controller;
import client.view.adminView.editView.EditBookingView;
import client.view.adminView.editView.EditMovieController;
import client.view.loginView.LoginViewController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;

/**
 * Controller til adminView, står for at læse bruger inputs, dette er buttons og
 * bliver kun brugt til at skifte view. Derfor har dette view heller ikke en ViewModel
 */

public class AdminViewController implements Controller
{

  private String path = "adminView/admin";
  private String viewTitle = "Admin View";


public void init(){}

  public String getPath()
  {
    return path;
  }

  @Override public String getTitle()
  {
    return viewTitle;
  }

  public void back()
  {
    Alert alert = AlertBox.makeAlert("confirmation", "Log ud",
        "Vil du gerne logge ud?");
    alert.showAndWait().ifPresent(type -> {
      if (type.getButtonData() == ButtonBar.ButtonData.YES)
      {
        ViewHandler.getInstance().setState(new LoginViewController());
        ViewHandler.getInstance().openView();
          //ViewHandler.getInstance().openView("Login");
      }
    });
  }

  public void editMovie()
  {
    ViewHandler.getInstance().setState(new EditMovieController());
    ViewHandler.getInstance().openView();
  //ViewHandler.getInstance().openView("Edit Movie");


  }

  public void editBooking()
  {
    ViewHandler.getInstance().setState(new EditBookingView());
    ViewHandler.getInstance().openView();
  //ViewHandler.getInstance().openView("Edit Booking");
  }
}


