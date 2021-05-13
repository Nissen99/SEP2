package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModel;
import client.model.ClientModelShowing;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.exception.ServerException;
import shared.transferobjects.Hall;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class ViewModelAddShowing
{
  private Movie selectedMovie;
  private ClientModelShowing clientModel = ModelFactory.getInstance().getModelShowing();
  private ObservableList<String> observableList = FXCollections.observableArrayList();





/**Vi laver nogle checks der checker om man er ved at oprette en valid Showing
  *Vi har besluttet at der skal være 3 timer mellem hver film, så den kan vises
  *og der kan gøres rent. derfor tjekker vi 3 timer før og efter det angivede tidspunkt
 * Vi mener også at showings der forgår i fortiden ikke giver mening, derfor skal
 * det være efter currentTime
 */
  public void addShowing(Timestamp timestamp, String hallNo)
      throws SQLException, RemoteException, ServerException
  {
    Timestamp currentTime = new Timestamp(System.currentTimeMillis());

    ArrayList<Timestamp> showingTimes = clientModel.getShowingTimesByHallNoAndDate(hallNo, timestamp);

    for (Timestamp showingTime : showingTimes)
    {
      Timestamp plus3Hours = new Timestamp(showingTime.getTime() + (3 * 3599999));
      Timestamp minus3Hours = new Timestamp(showingTime.getTime() - (3 * 3599999));

      if (!(timestamp.before(minus3Hours) || timestamp.after(plus3Hours))) {
        throw new IllegalArgumentException("Invalid input - A showing is scheduled at this time");
      }
    }
    if (0 < timestamp.compareTo(currentTime))
    {
      Showing showing = new Showing(selectedMovie, timestamp, getHallByNumber(hallNo));
      clientModel.addShowing(showing);
    }
    else {
      throw new IllegalArgumentException("Invalid input - Timestamp is before current time.");
    }
  }


  public Hall getHallByNumber(String hallNo)
      throws SQLException, RemoteException, ServerException
  {

    return clientModel.getHallByNumber(hallNo);
  }

  public void setSelectedMovie(Movie selectedMovie)
  {
    this.selectedMovie = selectedMovie;
  }

  public ObservableList<String> getChoiceList()
      throws RemoteException, SQLException, ServerException
  {
    observableList.addAll(clientModel.getHallNumbers());
    return observableList;
  }
}
