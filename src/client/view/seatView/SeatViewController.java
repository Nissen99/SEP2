package client.view.seatView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.viewModel.ViewModelSeat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import shared.transferobjects.Seat;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeatViewController
{

  private ArrayList<Pane> paneArrayList = new ArrayList<>();
  private ArrayList<Seat> seatArrayList;

  private Seat selectedSeat = new Seat();
  private Pane selectedPane = null;

  @FXML private AnchorPane anchorPane;

  private ViewModelSeat viewModel = ViewModelFactory.getInstance().getSeatVM();

  public SeatViewController() throws SQLException, RemoteException
  {
  }

  public void init() throws SQLException, RemoteException
  {

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
    Pane pane = (Pane) e.getSource();

    if (!(selectedPane == null))
    {
      selectedPane.setStyle("-fx-border-color:black;");
    }
    pane.setStyle("-fx-background-color:blue;");
    selectedPane = pane;

  }

  @FXML void OnConfirmButtom(ActionEvent event) throws IOException, SQLException

  {
    System.out.println("Køre den her??");
    selectedSeat.setSeatNo(selectedPane.idProperty().get());
    viewModel.setSelectedSeat(selectedSeat);
    System.out.println(" Hvilket id ??" + selectedPane.idProperty().get());

    ViewHandler.getInstance().openView("../view/bookingView/bookingView.fxml");

  }

  @FXML void onBackButton(ActionEvent event) throws IOException, SQLException
  {

    ViewHandler.getInstance()
        .openView("../view/showingList/showingListView.fxml");

  }

}

