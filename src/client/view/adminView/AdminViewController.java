package client.view.adminView;

import client.core.ViewHandler;
import client.view.Controller;

/**
 * Controller til adminView, står for at læse bruger inputs, dette er buttons og
 * bliver kun brugt til at skifte view. Derfor har dette view heller ikke en ViewModel
 */

public class AdminViewController implements Controller
{


public void init(){}

  public void back()
  {
  ViewHandler.getInstance().openView("Login");
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
