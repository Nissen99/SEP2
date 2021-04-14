package Client.view.seatView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.sql.SQLException;
import java.util.ArrayList;

public class SeatViewController
{

  @FXML private Pane S101, S102, S103;
 // private Pane[] seats = new Pane[3];
  private Pane[] seats={S101,S102,S103};
  Pane lastClicked = null;




  private int id;

  public void init() throws SQLException
  {

  }

  public void onClick(MouseEvent e)
  {
    Pane pane = (Pane) e.getSource();

    if (!(lastClicked == null))
    {
      lastClicked.setStyle("-fx-border-color:black;");
    }
    pane.setStyle("-fx-background-color:red;");
    lastClicked = pane;



  }

}

