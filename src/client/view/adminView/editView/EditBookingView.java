package client.view.adminView.editView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.viewModel.ViewModelEditBooking;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import shared.exception.ServerException;
import shared.transferobjects.Booking;


public class EditBookingView
{
  @FXML private TextField searchBox;
  @FXML private TableView<Booking> bookingTable;
  @FXML private TableColumn<Booking, String> bookingId;
  @FXML private TableColumn<Booking, String> name;
  @FXML private TableColumn<Booking, String> email;
  @FXML private TableColumn<Booking, String> title;
  @FXML private TableColumn<Booking, String> time;

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

  public void searchByBookingId(){

    try
    {
      Booking booking = viewModel.getBookingById();
      bookingTable.getSelectionModel().select(booking);
      bookingTable.requestFocus();
      bookingTable.scrollTo(booking);
    }catch (IllegalArgumentException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!", "Invalid input");
      alert.showAndWait();
    }catch (NullPointerException e){
      Alert alert = AlertBox.makeAlert("information", "Error!", "No booking found, Id: " + viewModel.getSearch());
      alert.showAndWait();
    }
  }

  public void deleteBooking()
  {
    Booking booking = bookingTable.getSelectionModel().getSelectedItem();
    if (booking == null){
      Alert alert = AlertBox.makeAlert("information","Delete Booking", "No booking selected");
      alert.showAndWait();
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
            serverAlert.showAndWait();
           }
        }
      });
    }
  }

  public void back()
  {
    ViewHandler.getInstance().openView("../view/adminView/adminView.fxml");
  }
}

