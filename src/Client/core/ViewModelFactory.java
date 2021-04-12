package Client.core;

import Client.view.viewModel.ViewModelBooking;
import Client.view.viewModel.ViewModelMovieList;
import Client.view.viewModel.ViewModelShowingList;

public class ViewModelFactory
{

  private static ViewModelFactory viewModelFactory;
  private ViewModelMovieList movieList;
  private ViewModelShowingList ShowingList;
  private ViewModelBooking booking;

  private ViewModelFactory(){}

  public static ViewModelFactory getInstance(){
    if (viewModelFactory == null){
      viewModelFactory = new ViewModelFactory();
    }
    return viewModelFactory;
  }

  public ViewModelMovieList getMovieListViewModel(){
    if (movieList == null){
      movieList = new ViewModelMovieList(ModelFactory.getInstance().getModel());
    }
    return movieList;
  }

  public ViewModelShowingList getShowingList(){
    if (ShowingList == null){
      ShowingList = new ViewModelShowingList(ModelFactory.getInstance().getModel());
    }
    return ShowingList;
  }

  public ViewModelBooking getBooking(){
    if (booking == null){
      booking = new ViewModelBooking();
    }
    return booking;
  }

}
