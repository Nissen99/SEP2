package client.view.seatView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.viewModel.ViewModelSeat;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import server.ServerException;
import shared.transferobjects.Seat;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeatViewController implements PropertyChangeListener
{

  @FXML public ChoiceBox<Integer> numberOfSeats;
  private ArrayList<Pane> paneArrayList = new ArrayList<>();
  private ArrayList<Pane> selectedPane = new ArrayList<>();
  private ArrayList<Seat> occupiedSeatArrayList;
  @FXML private AnchorPane anchorPane;
  private int currentNumber = 0;

  private ViewModelSeat viewModel;


  public void init() throws SQLException, RemoteException
  {

    viewModel = ViewModelFactory.getInstance().getSeatVM();

    setChoiceBox();

    setOccupiedSeats();

    viewModel.addPropertyChangeListener(this);
  }

  private void setOccupiedSeats() throws SQLException, RemoteException
  {
    for (Node node : getAllNodes(anchorPane))
    {

      if (checkPaneAndNotVboxOrHbox(node))
      {
        paneArrayList.add((Pane) node);
      }
    }

    setOccupiedColor();
  }

  private boolean checkPaneAndNotVboxOrHbox(Node node)
  {
    return node instanceof Pane && (!(node instanceof VBox
        || node instanceof HBox));
  }

  private void setChoiceBox()
  {
    numberOfSeats.setItems(viewModel.getChoiceList());
    numberOfSeats.setValue(1);
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
    throw new IndexOutOfBoundsException("Invalid input - Seat out of bounds");
  }

  public void setOccupiedColor() throws SQLException, RemoteException
  {
    occupiedSeatArrayList = viewModel.getOccupiedSeats();


    for (Seat seat : occupiedSeatArrayList)
    {
      for (Pane pane : selectedPane)
      {
        if (pane.idProperty().get().equals(seat.getSeatNo())){
          makeOldPanesTransparent();
          selectedPane.clear();
          break;
        }
      }


      Pane occupiedPane = getPane(seat.getSeatNo());
      occupiedPane.setStyle("-fx-background-color:red;");
      occupiedPane.setDisable(true);


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

  public void onClick(MouseEvent mouseEvent)
  {
    makeOldPanesTransparent();

    Pane pane = (Pane) mouseEvent.getSource();

      selectedPane.clear();

      String id = pane.idProperty().get();



      for (int i = 0; i < numberOfSeats.getValue(); i++)
      {
        try
        {
          id = idCounter(id);
        }
        catch (IndexOutOfBoundsException e){
          JOptionPane.showMessageDialog(null, e.getMessage());
          selectedPane.clear();
          return;
        }
      }
    for (Pane selectedPane : selectedPane)
    {

      selectedPane.setStyle("-fx-background-color:blue;");
    }
      }

  private void makeOldPanesTransparent()
  {
    for (Pane pane : selectedPane)
    {
      pane.setStyle("-fx-background-color:transparent;");
      pane.setStyle("-fx-border-color:black;");
    }
  }

  private String idCounter(String id)
  {

    String[] idSplit = id.split("");
    Pane pane = getPane(id);
    checkIfSeatOccupied(pane);
    selectedPane.add(pane);
    currentNumber = getCurrentNumber(idSplit);

    id = idSplit[0] + currentNumber;
    return id;
  }

  private int getCurrentNumber(String[] idSplit)
  {
    if (idSplit.length > 4)
    {
     currentNumber = Integer.parseInt(
         idSplit[1] + idSplit[2] + idSplit[3] + idSplit[4]);
    } else
    {
       currentNumber = Integer.parseInt(idSplit[1] + idSplit[2] + idSplit[3]);
    }

    return ++currentNumber;
  }


  @FXML void OnConfirmButton() throws IOException, SQLException, ServerException
  {
    if (confirmBookingBox("Are you sure you want to confirm your booking"))
    {

      ArrayList<Seat> seats = new ArrayList<>();
      for (Pane pane : selectedPane)
      {
        Seat seat = new Seat();
        seat.setSeatNo(pane.idProperty().get());
        seats.add(seat);
      }

      viewModel.setSelectedSeats(seats);

      //Når vi skifter view er der ingen grund til vi stadigvæk lytter
      viewModel.removePropertyChangeListener(this);
      viewModel.addBooking();

      JOptionPane.showMessageDialog(null, "You have successfully made a booking. An email has been sent to your mailbox");
      ViewHandler.getInstance().openView("../view/movieList/movieListView.fxml");
    }
  }

  private void checkIfSeatOccupied(Pane pane){
    for (Seat seat : occupiedSeatArrayList)
    {
      if (seat.getSeatNo().equals(pane.idProperty().get())){
        throw new IndexOutOfBoundsException("Invalid input - Seat already occupied");
      }}}

  @FXML void onBackButton() throws IOException, SQLException
  {


    //Når vi skifter view er der ingen grund til vi stadigvæk lytter
      viewModel.removePropertyChangeListener(this);
      ViewHandler.getInstance()
          .openView("../view/showingList/showingListView.fxml");



  }

  private boolean confirmBookingBox(String s)
  {
    return JOptionPane.showConfirmDialog(null, s, "Confirmation", JOptionPane.YES_NO_OPTION)
        == JOptionPane.YES_OPTION;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

      Platform.runLater(() -> {
        try
        {
          System.out.println("PROPERTY CHANGED");
          setOccupiedSeats();
        }
        catch (SQLException | RemoteException throwables)
        {
          throwables.printStackTrace();
        }
      });


  }
}

