package Client.core;

import Client.view.viewModel.MovieListViewModel;

public class ViewModelFactory
{

  private static ViewModelFactory viewModelFactory;
  private MovieListViewModel movieListViewModel;

  private ViewModelFactory(){}

  public static ViewModelFactory getInstance(){
    if (viewModelFactory == null){
      viewModelFactory = new ViewModelFactory();
    }
    return viewModelFactory;
  }

  public MovieListViewModel getMovieListViewModel(){
    if (movieListViewModel == null){
      movieListViewModel = new MovieListViewModel();
    }
    return movieListViewModel;
  }
}
