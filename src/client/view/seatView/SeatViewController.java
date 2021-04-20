package client.view.seatView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.viewModel.ViewModelSeat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import shared.Seat;

import java.io.IOException;
import java.sql.SQLException;

public class SeatViewController
{

  @FXML private Pane S101, S102, S103;
 // private Pane[] seats = new Pane[3];
  private Pane[] seats={S101,S102,S103};
  Pane selectedSeat = null;
  private Seat seat = new Seat();

  @FXML private Button backButton;
  @FXML private Button confirmButton;

  private ViewModelSeat viewModel = ViewModelFactory.getInstance().getSeatVM();





  private int id;

  public void init() throws SQLException
  {

  }

  public void onClick(MouseEvent e)
  {
    Pane pane = (Pane) e.getSource();

    if (!(selectedSeat == null))
    {
      selectedSeat.setStyle("-fx-border-color:black;");
    }
    pane.setStyle("-fx-background-color:red;");
    selectedSeat = pane;


  }

  @FXML
  void OnConfirmButtom(ActionEvent event) throws IOException, SQLException

  {
    System.out.println("Køre den her??");
    seat.setSeatNo(selectedSeat.idProperty().get());
    viewModel.setSelectedSeat(seat);
    System.out.println(" Hvilket id ??" + selectedSeat.idProperty().get());

    ViewHandler.getInstance().openView("../view/bookingView/bookingView.fxml");




  }

  @FXML
  void onBackButton(ActionEvent event) throws IOException, SQLException
  {

    ViewHandler.getInstance().openView("../view/showingList/showingListView.fxml");

  }

}

