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
 * Vi har 2 arrayLister af Panes
 * paneArrayList er en arrayList med samtlige panes på viewet.
 * selectedPane er en arrayList af de panes man har valgt.
 */

public class SeatViewController implements PropertyChangeListener, Controller
{

  @FXML public ChoiceBox<Integer> numberOfSeats;
  @FXML private AnchorPane anchorPane;
  private ArrayList<Pane> paneArrayList = new ArrayList<>(); //liste af alle panes
  private ArrayList<Pane> selectedPane = new ArrayList<>(); //Liste af valgte sæder
  private ViewModelSeat viewModel;

  public void init()
  {
    viewModel = ViewModelFactory.getInstance().getSeatVM();

    try
    {
      updateOccupiedSeatList();

      setPaneList();

      setOccupiedColor();

      viewModel.addPropertyChangeListener(this);
    }
    catch (ServerException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error", e.getMessage());
      alert.show();
    }

    setChoiceBox();
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

  /**
   * id tjekkes mod paneArrayList for at se om det pane eksiterer
   *
   * @param id id der bliver tjekket
   * @return Pane med det specifikke parameters id
   * @throws IndexOutOfBoundsException Hvis der ikke eksisterer pane med given id
   */
  public Pane getPane(String id) throws IndexOutOfBoundsException
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

  /**
   * Tjekker alle panes, hvis et panes id er optaget farves det pane
   * rødt og bliver "disabled"
   */
  public void setOccupiedColor()
  {
    for (Pane pane : paneArrayList)
    {
      if (viewModel.seatIsOccupied(pane.idProperty().get()))
      {
        pane.setStyle("-fx-background-color:red;");
        pane.setDisable(true);
      }
    }
  }

  /**
   * Denne metode bliver kaldt i propertyChange
   *
   * Tjekker om en anden client har booket de sæder der er valgt.
   * Hvis dette sker, gøres de valgte panes transparente og de bliver fravalgt (unselected)
   */
  private void selectedPaneTaken()
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

  /**
   * Tager fat i rooten(AnchorPane)
   * kalder {@link #addAllDescendants}
   *
   * @param root AnchorPane
   * @return liste af alle nodes på rooten
   */
  private ArrayList<Node> getAllNodes(Parent root)
  {
    ArrayList<Node> nodes = new ArrayList<>();
    addAllDescendants(root, nodes);
    return nodes;
  }

  /**
   * Tilføjer alle nodes fra en parent til en arrayList og hvis de node selv er
   * parents tilføjes deres children også
   *
   * @param parent root
   * @param nodes arrayList af nodes man ønsker tilføjelser til
   */
  private void addAllDescendants(Parent parent, ArrayList<Node> nodes)
  {
    for (Node node : parent.getChildrenUnmodifiable())
    {
      nodes.add(node);
      if (node instanceof Parent)
        addAllDescendants((Parent) node, nodes);
    }
  }

  /**
   * Gør valgte panes transperante
   * Tager fat i det pane der er clikket på, tjekker om ens valgte sæder er optaget
   * Hvis de ikke er optaget farves de blå og bliver lagt i selectedPane listen
   *
   * @param mouseEvent Sker når man trykker på et aktivt pane
   */
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
      catch (IndexOutOfBoundsException e)
      {
        Alert alert = AlertBox
            .makeAlert("information", "Error!", e.getMessage());
        alert.show();
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

  /**
   * Tager et pane ud fra id, tjekker gennem viewModel om det er optaget
   * Ligger det pane i selectedPane listen og tæller id'et op med 1 (1 plads til højre)
   *
   * @param id bruges til at få et bestemt pane
   * @return id'et for sædet til højre
   * @throws IndexOutOfBoundsException hvis det valgte sæde er optaget
   */
  private String idCounter(String id) throws IndexOutOfBoundsException
  {
    Pane pane = getPane(id);
    viewModel.checkIfSeatOccupiedOnClick(id);
    selectedPane.add(pane);
    id = viewModel.setNewId(id);

    return id;
  }

  private void updateOccupiedSeatList()
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
   * Ligger valgte sæder i liste på viewModellen, kalder addBooking og fjerner
   * sig selv som listener
   */
  @FXML void OnConfirmButton()
  {
    if (selectedPane.size() == 0)
    {
      Alert alert2 = AlertBox
          .makeAlert("Information", "Error", "No seats has been selected");
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
            Alert alertException = AlertBox
                .makeAlert("information", "Error!", e.getMessage());
            alertException.show();
          } finally
          {
            viewModel.clearBookingList(); //Vi vil være sikker på listen bliver clearet
          }
        }
      });
    }
  }

  @FXML void onBackButton()
  {
    //Når vi skifter view er der ingen grund til vi stadigvæk lytter
    try
    {
      viewModel.removePropertyChangeListener(this);
      ViewHandler.getInstance().openView("Showing List");
    }
    catch (ServerException ignored)
    {}
  }

  /**
   * @param evt event sendt fra viewModellen
   */
  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {

        updateOccupiedSeatList();
        selectedPaneTaken();
        setOccupiedColor();


    });
  }
}

