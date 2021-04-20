package Client.core;

import Client.view.viewModel.ViewModelBooking;
import Client.view.viewModel.ViewModelMovieList;
import Client.view.viewModel.ViewModelSeat;
import Client.view.viewModel.ViewModelShowingList;

import java.sql.SQLException;

public class ViewModelFactory
{

  private static ViewModelFactory viewModelFactory;
  private ViewModelMovieList movieList;
  private ViewModelShowingList showingList;
  private ViewModelBooking booking;
  private ViewModelSeat seatVM;

  private ViewModelFactory()
  {
  }

  public static ViewModelFactory getInstance()
  {
    if (viewModelFactory == null)
    {
      viewModelFactory = new ViewModelFactory();
    }
    return viewModelFactory;
  }

  public ViewModelMovieList getMovieListViewModel()
  {
    if (movieList == null)
    {
      movieList = new ViewModelMovieList(ModelFactory.getInstance().getModel());
    }
    return movieList;
  }

  public ViewModelShowingList getShowingList()
  {

    //Vores showinList skal vide hvilke film der er valgt, den information
    // skal den have hver gang, så vi har ikke gjort den lazy

    showingList = new ViewModelShowingList(
        ModelFactory.getInstance().getModel(), movieList.getSelectedMovie());

    return showingList;
  }

  public ViewModelBooking getBooking()
  {

    booking = new ViewModelBooking(showingList.getSelectedShowing(),
        seatVM.getSelectedSeat());
    return booking;
  }

  public ViewModelSeat getSeatVM() throws SQLException
  {
    seatVM = new ViewModelSeat(ModelFactory.getInstance().getModel(),
        showingList.getSelectedShowing());

    return seatVM;

  }

}
