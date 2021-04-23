package client.core;

import client.view.viewModel.*;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewModelFactory
{

  private static ViewModelFactory viewModelFactory;
  private ViewModelMovieList movieList;
  private ViewModelShowingList showingList;
  private ViewModelBooking booking;
  private ViewModelSeat seatVM;
  private ViewModelEditMovie editMovie;
  private ViewModelAddShowing addShowing;
  private ViewModelEditShowing editShowing;

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

  public ViewModelSeat getSeatVM() throws SQLException, RemoteException
  {
    seatVM = new ViewModelSeat(ModelFactory.getInstance().getModel(),
        showingList.getSelectedShowing());

    return seatVM;

  }

  public ViewModelEditMovie getEditMovie(){
    if (editMovie == null){
      editMovie = new ViewModelEditMovie(ModelFactory.getInstance().getModel());
    }
    return editMovie;
  }

  public ViewModelAddShowing getAddShowing(){

      addShowing = new ViewModelAddShowing( ViewModelFactory.getInstance()
          .getEditMovie().getSelectedMovie());

    return addShowing;
  }

  public ViewModelEditShowing getEditShowing(){

    editShowing = new ViewModelEditShowing(ViewModelFactory.getInstance().editMovie.getSelectedMovie());
    return editShowing;
  }

}
