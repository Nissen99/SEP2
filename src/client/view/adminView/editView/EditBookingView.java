package client.view.adminView.editView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.util.AlertBox;
import client.view.viewModel.ViewModelEditBooking;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import shared.exception.ServerException;
import shared.transferobjects.Booking;

import javax.swing.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

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

  public void init() throws RemoteException, SQLException
  {
    viewModel = ViewModelFactory.getInstance()
        .getEditBooking();
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
    catch (RemoteException | SQLException  | NullPointerException e)
    {
      e.printStackTrace();
      System.out.println("Error setting up the booking list");
    }
  }

  public void searchByBookingId(){
    String search="";
    try
    {
      search = searchBox.getText();
      Booking booking = viewModel.getBookingById(search);
      bookingTable.getSelectionModel().select(booking);
      bookingTable.requestFocus();
      bookingTable.scrollTo(booking);
    }catch (IllegalArgumentException e)
    {
      Alert alert = AlertBox.makeAlert("information", "Error!", "Invalid input");
      alert.showAndWait();
    }catch (NullPointerException e){
      Alert alert = AlertBox.makeAlert("information", "Error!", "No booking found, Id: " + search);
      alert.showAndWait();
    }
  }

  public void deleteBooking() throws RemoteException, SQLException
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
          System.out.println("Yes pressed");
          try
          {
            viewModel.removeBooking(booking);
          }
          catch (RemoteException | SQLException e)
          {
            e.printStackTrace();
          }
        }
      });
    }
    setupTable();
  }

  public void back() throws IOException, SQLException, ServerException
  {
    ViewHandler.getInstance().openView("../view/adminView/adminView.fxml");
  }



  }

