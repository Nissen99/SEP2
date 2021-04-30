package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Hall;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class ViewModelAddShowing
{
  private StringProperty hallNo = new SimpleStringProperty("");

  private Movie selectedMovie;
  private Timestamp timestamp = new Timestamp(new Date().getTime());
  private ClientModel clientModel = ModelFactory.getInstance().getModel();




  public void addShowing(Timestamp timestamp) throws SQLException, RemoteException
  {
    Timestamp currentTime = new Timestamp(System.currentTimeMillis());

    ArrayList<Timestamp> showingTimes = clientModel.getShowingTimesByHallNoAndDate(getHallNo(), timestamp);

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
      Showing showing = new Showing(selectedMovie, timestamp, getHallByNumber());
      clientModel.addShowing(showing);
    }
    else {
      throw new IllegalArgumentException("Invalid input - Timestamp is before current time.");
    }
  }

  public Hall getHallByNumber() throws SQLException, RemoteException
  {

    return clientModel.getHallByNumber(this.hallNo.getValue());
  }


  public String getHallNo()
  {
    return hallNo.get();
  }

  public StringProperty hallNoProperty()
  {
    return hallNo;
  }

  public void setSelectedMovie(Movie selectedMovie)
  {
    this.selectedMovie = selectedMovie;
  }
}
