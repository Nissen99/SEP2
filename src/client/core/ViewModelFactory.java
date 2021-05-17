package client.core;

import client.view.viewModel.*;


/**
 * Singleton
 */
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



  //Vores showingList, editShowing og addShowing skal vide hvilke film der er valgt,
  //den information skal de have hver gang også hvis den ikke er null
  public ViewModelShowingList getShowingList()
  {
    if (showingList == null)
    {
      showingList = new ViewModelShowingList();
    }
    showingList.setSelectedMovie(movieList.getSelectedMovie());

    return showingList;
  }

  public ViewModelAddShowing getAddShowing()
  {

    if (addShowing == null)
    {
      addShowing = new ViewModelAddShowing();
    }

    addShowing.setSelectedMovie(editMovie.getSelectedMovie());

    return addShowing;
  }

  public ViewModelEditShowing getEditShowing()
  {

    if (editShowing == null)
    {
      editShowing = new ViewModelEditShowing();
    }
    editShowing.setSelectedMovie(editMovie.getSelectedMovie());

    return editShowing;
  }



  //Denne ViewModel kan ikke være lazy da den skal tilføje sig selv som listener
  //og sætte den selectedShowing
  public ViewModelSeat getSeatVM()
  {
    seatVM = new ViewModelSeat(showingList.getSelectedShowing());

    return seatVM;

  }



  public ViewModelEditMovie getEditMovie()
  {
    if (editMovie == null)
    {
      editMovie = new ViewModelEditMovie();
    }
    return editMovie;
  }

  public ViewModelEditBooking getEditBooking()
  {

    if (editBooking == null)
    {
      editBooking = new ViewModelEditBooking();
    }
    return editBooking;
  }

  public ViewModelLogin getlogin()
  {

    if (login == null)
    {
      login = new ViewModelLogin();
    }
    return login;
  }

  public ViewModelCreateUser getCreateUser()
  {
    if (createUser == null)
    {
      createUser = new ViewModelCreateUser();
    }
    return createUser;
  }

}
