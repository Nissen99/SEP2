package client.view.adminView.editView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.Controller;
import client.view.adminView.AdminViewController;
import client.view.viewModel.ViewModelEditBooking;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.exception.ServerException;
import shared.transferobjects.IBooking;


/**
 * Controller til editBookingView, står for at læse bruger inputs, dette er gjort
 * gennem bindings med viewModellen og et tableView.
 */

public class EditBookingView implements Controller
{
  @FXML private TextField searchBox;
  @FXML private TableView<IBooking> bookingTable;
  @FXML private TableColumn<IBooking, Integer> bookingId;
  @FXML private TableColumn<IBooking, String> name;
  @FXML private TableColumn<IBooking, String> email;
  @FXML private TableColumn<IBooking, String> title;
  @FXML private TableColumn<IBooking, String> time;

  private ViewModelEditBooking viewModel;

  private String path = "adminView/editView/editBooking";

  public void init()
  {
    viewModel = ViewModelFactory.getInstance()
        .getEditBooking();

    searchBox.textProperty().bindBidirectional(viewModel.searchProperty());

    setupTable();
  }

  @Override public String getPath()
  {
    return path;
  }

  /**
   * Fylder table med booking objekter.
   * En booking består af en int og 2 objekter
   * De data der ligger i objekter bliver udledt og sat i en celle
   */
  private void setupTable()
  {
    try
    {
      bookingTable.setItems(viewModel.getAllBookings());

      bookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));

      name.setCellValueFactory(cellData -> new SimpleStringProperty(
          cellData.getValue().getUser().getUserName()));

      email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getEmail()));

      title.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getShowing().getMovie().getMovieTitle()));

      time.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getShowing().getTime()));

    }
    catch (ServerException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!", e.getMessage() +". Prøv at genstarte applikationen");
      alert.show();
    }
  }

  /**
   * Vi søger efter det givne bookingId i viewModel, findes denne booking kaldes
   * select på tableviewet og fokus bliver sat på tableViewet.
   * tableViewet ruller ned til booking, som er selected
   */
  public void searchByBookingId(){
    try
    {
      IBooking booking = viewModel.getBookingById();
      bookingTable.getSelectionModel().select(booking);
      bookingTable.requestFocus();
      bookingTable.scrollTo(booking);
    }catch (IllegalArgumentException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!", "Ikke gyldigt input");
      alert.show();
    }catch (NullPointerException e){
      Alert alert = AlertBox.makeAlert("information", "Error!", "Ingen booking findes med id: " + viewModel.getSearch());
      alert.show();
    }
    searchBox.clear();
  }


  public void deleteBooking()
  {
    IBooking booking = bookingTable.getSelectionModel().getSelectedItem();
    if (booking != null)
    {
        Alert alert = AlertBox.makeAlert("confirmation", "Slet Booking",
            "Er du sikker på du vil slette denne booking?");

        alert.showAndWait().ifPresent(type -> {
          if (type.getButtonData() == ButtonBar.ButtonData.YES)
          {
            try
            {
              viewModel.removeBooking(booking);
              setupTable();
            }
            catch (ServerException e)
            {
              Alert serverAlert = AlertBox.makeAlert("information", "Error!", e.getMessage());
              serverAlert.show();
            }
          }
        });
      }
    }


  public void back()
  {
    ViewHandler.getInstance().setState(new AdminViewController());
    ViewHandler.getInstance().openView();
    //ViewHandler.getInstance().openView("Admin");
  }
}

