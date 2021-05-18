package client.view.adminView.editView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.Controller;
import client.view.viewModel.ViewModelEditBooking;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import shared.exception.ServerException;
import shared.transferobjects.IBooking;


/**
 * Controller til editBookingView, står for at læse bruger inputs, dette er gjort
 * gennem bindings med viewModellen.
 */

public class EditBookingView implements Controller
{
  @FXML private TextField searchBox;
  @FXML private TableView<IBooking> bookingTable;
  @FXML private TableColumn<IBooking, String> bookingId;
  @FXML private TableColumn<IBooking, String> name;
  @FXML private TableColumn<IBooking, String> email;
  @FXML private TableColumn<IBooking, String> title;
  @FXML private TableColumn<IBooking, String> time;

  private ViewModelEditBooking viewModel;

  public void init()
  {
    viewModel = ViewModelFactory.getInstance()
        .getEditBooking();

    searchBox.textProperty().bindBidirectional(viewModel.searchProperty());

    setupTable();
  }


  private void setupTable()
  {
    try
    {
      bookingTable.setItems(viewModel.getAllBookings());

      bookingId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getBookingId())));
      name.setCellValueFactory(cellData -> new SimpleStringProperty(
          cellData.getValue().getUser().getUserName()));
      email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getEmail()));
      title.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getShowing().getMovie().getMovieTitle()));
      time.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getShowing().getTime()));
    }
    catch (ServerException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!", e.getMessage() +". Try restarting the app");
      alert.showAndWait();
    }
  }

  //Vi søger efter det givne bookingId i viewModel, findes denne booking kaldes
  // select på tableviewet og fokus bliver sat på tableViewet.
  //tableViewet ruller ned til booking, som er selected
  public void searchByBookingId(){
    try
    {
      IBooking booking = viewModel.getBookingById();
      bookingTable.getSelectionModel().select(booking);
      bookingTable.requestFocus();
      bookingTable.scrollTo(booking);
    }catch (IllegalArgumentException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!", "Invalid input");
      alert.show();
    }catch (NullPointerException e){
      Alert alert = AlertBox.makeAlert("information", "Error!", "No booking found, Id: " + viewModel.getSearch());
      alert.show();
    }
    searchBox.clear();
  }


  public void deleteBooking()
  {
    IBooking booking = bookingTable.getSelectionModel().getSelectedItem();
    if (booking == null){
      Alert alert = AlertBox.makeAlert("information","Delete Booking", "No booking selected");
      alert.show();
    }else
    {
      Alert alert = AlertBox.makeAlert("confirmation", "Deleting Booking",
          "Are you sure you want to delete this booking?");

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
    ViewHandler.getInstance().openView("Admin");
  }
}

