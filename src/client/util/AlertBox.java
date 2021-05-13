package client.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class AlertBox
{

  public static Alert makeAlert(String type, String title, String context)
  {
    Alert alert = null;
    if (type.toLowerCase().equals("confirmation"))
    {
      alert = new Alert(Alert.AlertType.CONFIRMATION);
      ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
      ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
      alert.getButtonTypes().setAll(yesButton, noButton);
    }
    else if (type.toLowerCase().equals("information")){
      alert = new Alert(Alert.AlertType.INFORMATION);
    }

    alert.setTitle(title);
    alert.setContentText(context);
    return alert;
  }


}
