package client.view.seatView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.viewModel.ViewModelSeat;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import shared.exception.ServerException;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class SeatViewController implements PropertyChangeListener
{

  @FXML public ChoiceBox<Integer> numberOfSeats;
  @FXML private AnchorPane anchorPane;
  private ArrayList<Pane> paneArrayList = new ArrayList<>();
  private ArrayList<Pane> selectedPane = new ArrayList<>();
  private ViewModelSeat viewModel;

  public void init()
  {

    viewModel = ViewModelFactory.getInstance().getSeatVM();

    updateOccupiedSeatCatch();

    setPaneList();

    setOccupiedColor();

    viewModel.addPropertyChangeListener(this);

    setChoiceBox();

   }

  private void updateOccupiedSeatCatch()
  {
    try
    {
      viewModel.updateOccupiedSeatsList();
    }
    catch (ServerException e)
    {
    Alert alert = AlertBox.makeAlert("information", "Error", e.getMessage());
    alert.showAndWait();
    }
  }

  private void setPaneList()
  {
    for (Node node : getAllNodes(anchorPane))
    {
      if (checkPaneAndNotVboxOrHbox(node))
      {
       paneArrayList.add((Pane) node);
      }
    }

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

  public void setOccupiedColor()
  {

      for (Pane pane : paneArrayList)
      {
        if (viewModel.seatIsOccupied(pane.idProperty().get())){
          Pane occupiedPane = getPane(pane.idProperty().get());
          occupiedPane.setStyle("-fx-background-color:red;");
          occupiedPane.setDisable(true);
        }
      }

  }

  private void selectedPaneTakken()
  {
    for (Pane pane : selectedPane)
    {
      if (viewModel.seatIsOccupied(pane.idProperty().get()))
      {
        makeOldPanesTransparent();
        selectedPane.clear();
        break;
      }
    }
  }

  private ArrayList<Node> getAllNodes(Parent root)
  {
    ArrayList<Node> nodes = new ArrayList<>();
    addAllDescendents(root, nodes);
    return nodes;
  }



  private void addAllDescendents(Parent parent, ArrayList<Node> nodes)
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
      catch (IndexOutOfBoundsException | ServerException e)
      {
        Alert alert = AlertBox
            .makeAlert("information", "Error!", e.getMessage());
        alert.showAndWait();
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

  private String idCounter(String id) throws ServerException
  {
    Pane pane = getPane(id);
    viewModel.checkIfSeatOccupied(id);
    selectedPane.add(pane);
    viewModel.setCurrentNumber(id);

    id = id.substring(0,1) + viewModel.getCurrentNumber();
    return id;
  }


  @FXML void OnConfirmButton()
  {
    if (selectedPane.size() == 0)
    {
      Alert alert2 = AlertBox.makeAlert("Information","Error","No seats has been selected");
      alert2.showAndWait();
    }
    else
    {
      Alert alert = AlertBox.makeAlert("confirmation", "Make Booking",
          "Do you want to confirm your booking?");
      alert.showAndWait().ifPresent(type -> {
        if (type.getButtonData() == ButtonBar.ButtonData.YES)
        {
          try
          {

            for (Pane pane : selectedPane)
            {
              viewModel.addSeat(pane.idProperty().get());
            }

            viewModel.addBooking();
            //Når vi skifter view er der ingen grund til vi stadigvæk lytter
            viewModel.removePropertyChangeListener(this);

            Alert alert1 = AlertBox.makeAlert("Information", "Booking made",
                "You have successfully made a booking. An email has been sent to your mailbox ");
            alert1.showAndWait();

            ViewHandler.getInstance().openView("../view/movieList/movieListView.fxml");
          }
          catch (ServerException e)
          {
            Alert alertException = AlertBox.makeAlert("information", "Error!", e.getMessage());
            alertException.showAndWait();
          }
        }
      });
    }
  }


  @FXML void onBackButton()
  {
    //Når vi skifter view er der ingen grund til vi stadigvæk lytter
    viewModel.removePropertyChangeListener(this);
    ViewHandler.getInstance()
        .openView("../view/showingList/showingListView.fxml");
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
        updateOccupiedSeatCatch();
        selectedPaneTakken();
        setOccupiedColor();

    });
  }
}

