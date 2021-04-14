package Client.view.seatView;

import Client.core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeatViewController
{

  @FXML private Pane S101, S102, S103;
 // private Pane[] seats = new Pane[3];
  private Pane[] seats={S101,S102,S103};
  Pane lastClicked = null;

  @FXML private Button backButton;
  @FXML private Button confirmButton;




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

  @FXML
  void OnConfirmButtom(ActionEvent event) {

  }

  @FXML
  void onBackButton(ActionEvent event) throws IOException, SQLException
  {

    ViewHandler.getInstance().openView("../view/showingList/showingListView.fxml");

  }

}

