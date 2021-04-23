package client.view.seatView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.viewModel.ViewModelSeat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import shared.transferobjects.Seat;

import javax.swing.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeatViewController
{

  @FXML public ChoiceBox antalSeats;
  private ArrayList<Pane> paneArrayList = new ArrayList<>();
  private ArrayList<Seat> seatArrayList;

  private ArrayList<Pane> selectedPane = new ArrayList<>();

  @FXML private AnchorPane anchorPane;

  private ViewModelSeat viewModel = ViewModelFactory.getInstance().getSeatVM();

  public SeatViewController() throws SQLException, RemoteException
  {
  }

  public void init() throws SQLException, RemoteException
  {
    setChoiceBox();
    seatArrayList = viewModel.getOccupiedSeats();
    for (Node node : getAllNodes(anchorPane))
    {

      if (node instanceof Pane && (!(node instanceof VBox
          || node instanceof HBox)))
      {
        paneArrayList.add((Pane) node);
      }
    }
    setOccupiedColor();

  }

  private void setChoiceBox()
  {
    ArrayList<Integer> integerArrayList = new ArrayList<>();
    for (int i = 1; i <= 14; i++)
    {
      integerArrayList.add(i);
    }
    ObservableList<Integer> integerObservableList = FXCollections.observableList(integerArrayList);
    antalSeats.setItems(integerObservableList);
    antalSeats.setValue(1);
  }

  public Pane getPane(String id)
  {
    for (Pane pane : paneArrayList)
    {
      if (pane.idProperty().get().equals(id))
      {
        return pane;
      }
    }
    return null;

  }

  public void setOccupiedColor()
  {
    for (Seat seat : seatArrayList)
    {
      Pane d = getPane(seat.getSeatNo());
      d.setStyle("-fx-background-color:red;");
      d.setDisable(true);
    }
  }

  public static ArrayList<Node> getAllNodes(Parent root)
  {
    ArrayList<Node> nodes = new ArrayList<>();
    addAllDescendents(root, nodes);
    return nodes;
  }

  private static void addAllDescendents(Parent parent, ArrayList<Node> nodes)
  {
    for (Node node : parent.getChildrenUnmodifiable())
    {
      nodes.add(node);
      if (node instanceof Parent)
        addAllDescendents((Parent) node, nodes);
    }
  }

  public void onClick(MouseEvent e)
  {
    for (Pane pane : paneArrayList)
    {
      if (!pane.isDisabled()){
      pane.setStyle("-fx-background-color:transparent;");
      pane.setStyle("-fx-border-color:black;");
    }}

    Pane pane = (Pane) e.getSource();

    selectedPane = new ArrayList<>();
      String id = pane.idProperty().get();
      String[] idSplit = id.split("");
      System.out.println(idSplit);
    int currentNumber = 0;

      for (int i = 0; i < (int) antalSeats.getValue(); i++)
      {

          pane = getPane(id);
          selectedPane.add(pane);
          System.out.println(id);

          pane.setStyle("-fx-background-color:blue;");

        currentNumber = getCurrentNumber(idSplit, currentNumber);
        currentNumber++;
          id = idSplit[0] + currentNumber;
          idSplit = id.split("");
      }

      }

  private int getCurrentNumber(String[] idSplit, int currentNumber)
  {
    if (idSplit.length > 4)
    {
     currentNumber = Integer.parseInt(
         idSplit[1] + idSplit[2] + idSplit[3] + idSplit[4]);
    } else if (idSplit.length < 5)
    {
       currentNumber = Integer.parseInt(idSplit[1] + idSplit[2] + idSplit[3]);
    }
    return currentNumber;
  }


  @FXML void OnConfirmButtom(ActionEvent event) throws IOException, SQLException
  {
    ArrayList<Seat> seats = new ArrayList<>();
    for (Pane pane : selectedPane)
    {
      Seat seat = new Seat();
      seat.setSeatNo(pane.idProperty().get());
      seats.add(seat);
    }

      viewModel.setSelectedSeat(seats);
      ViewHandler.getInstance().openView("../view/bookingView/bookingView.fxml");
      viewModel.removeListen();
  }

  @FXML void onBackButton(ActionEvent event) throws IOException, SQLException
  {
//    if (JOptionPane
//        .showConfirmDialog(null, "Do you want to cancel?",
//            "Cancel", JOptionPane.YES_NO_OPTION)
//        == JOptionPane.YES_OPTION)
//    {
//
    //    }
    //    else
    //    {
    //      // do nothing
    //    }


      ViewHandler.getInstance()
          .openView("../view/showingList/showingListView.fxml");



  }

}

