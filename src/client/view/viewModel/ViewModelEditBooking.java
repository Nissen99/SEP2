package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelBooking;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.exception.ServerException;
import shared.transferobjects.IBooking;

/**
 * ViewModel for editBooking, her håndteres den læste data fra controlleren,
 * dette sker gennem en binding på Property.
 */

public class ViewModelEditBooking
{
  private ClientModelBooking clientModel = ModelFactory.getInstance().getModelBooking();
  private ObservableList<IBooking> bookings = FXCollections.observableArrayList();
  private StringProperty search = new SimpleStringProperty("");

  public String getSearch()
  {
    return search.get();
  }

  public StringProperty searchProperty()
  {
    return search;
  }


  public ObservableList<IBooking> getAllBookings() throws ServerException
  {
    bookings.clear();
    bookings.addAll(clientModel.getBookingList());

    return bookings;
  }

  public void removeBooking(IBooking booking) throws ServerException
  {
    if (booking == null)
    {
      throw new NullPointerException();
    }
      clientModel.removeBooking(booking);
  }

  /**
   * Vi parser search til en int, hvis det ikke kan lade sig gøre ved vi at
   * brugeren ikke har indtastet et gyldigt id
   * @return booking med id der matcher search felt
   * @throws IllegalArgumentException hvis input ikke kan parses til Integer
   */
  public IBooking getBookingById() throws IllegalArgumentException{
    try{
      int searchNumber = Integer.parseInt(search.get());
      for (IBooking booking : bookings)
      {
        if (booking.getBookingId() == searchNumber)
        {
          return booking;
        }
      }
      throw new NullPointerException();
    }catch (NumberFormatException e){
      throw new IllegalArgumentException();
    }
  }

}
