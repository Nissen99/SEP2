package client.view.viewModel;

import client.core.ModelFactory;
import client.model.ClientModelShowing;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.exception.ServerException;
import shared.transferobjects.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * ViewModel for AddShowingView, her håndteres den læste data fra controlleren,
 * dette sker gennem bindings på forskellige Properties.
 */

public class ViewModelAddShowing
{
  private IMovie selectedMovie;
  private final ClientModelShowing clientModel;
  private ObservableList<String> observableList = FXCollections.observableArrayList();
  private Property<LocalDate> localDateProperty = new SimpleObjectProperty<>();
  private Property<LocalTime> localTimeProperty = new SimpleObjectProperty<>();
  private StringProperty hallNo = new SimpleStringProperty();

public ViewModelAddShowing(){
  this.clientModel = ModelFactory.getInstance().getModelShowing();
}

  public StringProperty hallNoProperty()
  {
    return hallNo;
  }

  public Property<LocalDate> localDatePropertyProperty()
  {
    return localDateProperty;
  }


  public Property<LocalTime> localTimePropertyProperty()
  {
    return localTimeProperty;
  }

  /**
   * Der laves nogle checks som checker om man er ved at oprette en valid Showing
   *
   * Der tjekkes om showing tidspunktet er valid i {@link #checkIfTimeOverlaps}
   *
   * Vi mener også at showings der forgår i fortiden ikke giver mening, derfor skal
   * det være efter currentTime
   *
   * @throws IllegalArgumentException kaster IllegalArgument hvis der der et overlap i tiden
   * @throws ServerException Connetion til server og/eller database fejler
   */
  public void addShowing() throws ServerException, IllegalArgumentException
  {
    Timestamp inputTimestamp = getTimestamp();

    checkIfTimeOverlaps(inputTimestamp);

    Timestamp currentTime = new Timestamp(System.currentTimeMillis());

    if (0 < inputTimestamp.compareTo(currentTime))
    {
      IShowing showing = new Showing(selectedMovie, inputTimestamp, getHallByNumber(hallNo.get()));
      clientModel.addShowing(showing);
    }else {
      throw new IllegalArgumentException("Invalid input - Timestamp is before current time.");
    }
  }

  /**
   * Tager listen af alle showings på den givene dag, og matcher den op mod hvad
   * man er ved at oprette
   *
   * Vi har besluttet at der skal være 3 timer mellem hver film, så den kan vises
   * og der kan gøres rent.
   *
   *
   * @param inputTimestamp Det nye timestamp vi vil tjekke
   */
  public void checkIfTimeOverlaps(Timestamp inputTimestamp)
  {
    try
    {
      clientModel.checkIfTimeOverlaps(hallNoProperty().get(), inputTimestamp);
    }
    catch (ServerException e)
    {
      throw new IllegalArgumentException("Invalid input - A showing is scheduled at this time");
    }
  }

  private Timestamp getTimestamp()
  {

    LocalDate date = localDateProperty.getValue();
    LocalTime time = localTimeProperty.getValue();
    LocalDateTime localDateTime = date.atTime(time);
    return Timestamp.valueOf(localDateTime);
  }

  public IHall getHallByNumber(String hallNo)
      throws ServerException
  {

    return clientModel.getHallByNumber(hallNo);
  }

  public void setSelectedMovie(IMovie selectedMovie)
  {
    this.selectedMovie = selectedMovie;
  }

  public ObservableList<String> getChoiceList()
      throws ServerException
  {
    observableList.clear();
    observableList.addAll(clientModel.getHallNumbers());
    return observableList;
  }
}
