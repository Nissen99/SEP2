package client.core;

import client.view.viewModel.*;
import shared.exception.ServerException;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewModelFactory
{

  private static ViewModelFactory viewModelFactory;
  private ViewModelMovieList movieList;
  private ViewModelShowingList showingList;
  private ViewModelSeat seatVM;
  private ViewModelEditMovie editMovie;
  private ViewModelAddShowing addShowing;
  private ViewModelEditShowing editShowing;
  private ViewModelLogin login;
  private ViewModelCreateUser createUser;
  private ViewModelEditBooking editBooking;

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
      movieList = new ViewModelMovieList();
    }
    return movieList;
  }

  public ViewModelShowingList getShowingList()
  {

if (showingList == null)
{
  showingList = new ViewModelShowingList();
}
    //Vores showinList skal vide hvilke film der er valgt, den information
    // skal den have hver gang også hvis den ikke er null
    showingList.setSelectedMovie(movieList.getSelectedMovie());

    return showingList;
  }


  public ViewModelSeat getSeatVM()
  {

    /**
     * Denne ViewModel kan ikke være lazy da den skal add sig selv som listener
     * og sætte den selectedShowing
     */

    seatVM = new ViewModelSeat(showingList.getSelectedShowing());

    return seatVM;

  }

  public ViewModelEditMovie getEditMovie(){
    if (editMovie == null){
      editMovie = new ViewModelEditMovie();
    }
    return editMovie;
  }

  public ViewModelAddShowing getAddShowing(){

    if (addShowing == null){
      addShowing = new ViewModelAddShowing();
    }

addShowing.setSelectedMovie(ViewModelFactory.getInstance()
    .getEditMovie().getSelectedMovie());

    return addShowing;
  }

  public ViewModelEditShowing getEditShowing(){

    if (editShowing == null){
      editShowing = new ViewModelEditShowing();
    }
    editShowing.setSelectedMovie(ViewModelFactory.getInstance().editMovie.getSelectedMovie());

    return editShowing;
  }

  public ViewModelEditBooking getEditBooking(){

    if (editBooking == null){
      editBooking = new ViewModelEditBooking();
    }
    return editBooking;
  }


  public ViewModelLogin getlogin(){

    if (login == null){
      login = new ViewModelLogin();
    }

        return login;
  }

  public ViewModelCreateUser getCreateUser()
  {
    if (createUser == null){
      createUser = new ViewModelCreateUser();
    }

    return createUser;
  }

}
