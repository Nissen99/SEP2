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
import java.util.Date;

public class ViewModelAddShowing
{
  private StringProperty hallNo = new SimpleStringProperty("");

  private Movie selectedMovie;
  private Timestamp timestamp = new Timestamp(new Date().getTime());
  private ClientModel clientModel = ModelFactory.getInstance().getModel();




  public void addShowing(Timestamp timestamp) throws SQLException, RemoteException
  {
    Showing showing = new Showing(selectedMovie, timestamp, getHallByNumber());
    clientModel.addShowing(showing);
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
