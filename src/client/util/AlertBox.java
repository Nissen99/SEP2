package client.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 * Bruges i Controllere.
 * Primært bruger vi 2 forskellige typer pop up viduer, denne klasse står for
 * at lave vinduerne. Hånteringen af dem sker i controlleren.
 */
public class AlertBox
{

  /**
   * Der er to typer alertboxe - confirmation og information.
   * "confirmation": laver alert med "Yes" og "No" knapper her skal kaldes showAndWait()
   * "information": lave alert med "Ok" knap, kan bruges med showAndWait() eller show()
   *
   * @param type "confirmation" eller "information"
   * @param title titel på vinduet
   * @param context besked i vinduet
   * @return {@link Alert}
   */
  public static Alert makeAlert(String type, String title, String context)
  {
    Alert alert = null;
    if (type.toLowerCase().equals("confirmation"))
    {
      alert = new Alert(Alert.AlertType.CONFIRMATION);
      ButtonType yesButton = new ButtonType("Ja", ButtonBar.ButtonData.YES);
      ButtonType noButton = new ButtonType("Nej", ButtonBar.ButtonData.NO);
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
