package client.view.seatView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.Controller;
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

/**
 * Controller til seatView, står for at læse bruger inputs, dette er gjort
 * gennem Panes (et pane = et sæde) med confrim/back kanp.
 *
 * Vi har 2 arrayLister af Panes
 * paneArrayList er en arrayList med samtlige panes på viewet.
 * sekectedPane er en arrayList af de panes man har valgt.
 */

public class SeatViewController implements PropertyChangeListener, Controller
{

  @FXML public ChoiceBox<Integer> numberOfSeats;
  @FXML private AnchorPane anchorPane;
  private ArrayList<Pane> paneArrayList = new ArrayList<>(); //liste af alle panes
  private ArrayList<Pane> selectedPane = new ArrayList<>(); //Liste af valgte
  private ViewModelSeat viewModel;

  public void init()
  {
    viewModel = ViewModelFactory.getInstance().getSeatVM();

    updateOccupiedSeat();

    setPaneList();

    setOccupiedColor();

    viewModel.addPropertyChangeListener(this);

    setChoiceBox();

   }


  private void updateOccupiedSeat()
  {
    try
    {
      viewModel.updateOccupiedSeatsList();
    }
    catch (ServerException e)
    {
    Alert alert = AlertBox.makeAlert("information", "Error", e.getMessage());
    alert.show();
    }
  }

  /**
   * Her ligges samtlige panes i vores paneArrayList, for at kunne tilgå paneId's
   */
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
        if (viewModel.seatIsOccupiedOnLoad(pane.idProperty().get())){
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
      if (viewModel.seatIsOccupiedOnLoad(pane.idProperty().get()))
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
    viewModel.checkIfSeatOccupiedOnClick(id);
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
      alert2.show();
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
            alert1.show();

            ViewHandler.getInstance().openView("Movie List");
          }
          catch (ServerException e)
          {
            Alert alertException = AlertBox.makeAlert("information", "Error!", e.getMessage());
            alertException.show();
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
        .openView("Showing List");
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
        updateOccupiedSeat();
        selectedPaneTakken();
        setOccupiedColor();

    });
  }
}

