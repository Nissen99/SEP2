package client.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 * Bruges i Controllere.
 * Primørt bruger vi 2 forskellige typer pop up viduer, denne klasse står for
 * at lave vinduerne, hånteringen af dem sker i controlleren.
 */
public class AlertBox
{


  //Når der bruges confimation gøres det i et lambda expresion, her bliver der kaldt
  //alert.showAndWait(), her venter vores system på bruger input.
  //Inputtet håndteres af controllers alt efter valgt.
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
