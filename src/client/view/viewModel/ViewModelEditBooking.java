package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelBooking;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.Booking;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewModelEditBooking
{
  private ClientModelBooking clientModel = ModelFactory.getInstance().getModelBooking();
  private ObservableList<Booking> bookings = FXCollections.observableArrayList();
  private StringProperty search = new SimpleStringProperty("");

  public String getSearch()
  {
    return search.get();
  }

  public StringProperty searchProperty()
  {
    return search;
  }

  public ViewModelEditBooking(){}


  public ObservableList<Booking> getAllBookings()
      throws RemoteException, SQLException
  {
    bookings.removeAll(bookings);
    bookings.addAll(clientModel.getBookingList());

    return bookings;
  }

  public void removeBooking(Booking booking)
      throws RemoteException, SQLException
  {
    if (booking == null)
      throw new NullPointerException();
    clientModel.removeBooking(booking);
  }

  public Booking getBookingById(){
    try{
      int x = Integer.parseInt(search.get());
      for (Booking b : bookings)
      {
        if (b.getBookingId() == x)
        {
          Booking booking = b;
          return booking;
        }
      }
      throw new NullPointerException();
    }catch (NumberFormatException e){
      throw new IllegalArgumentException();
    }
  }



}
