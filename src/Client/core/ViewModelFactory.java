package Client.core;

import Client.view.viewModel.ViewModelMovieList;
import Client.view.viewModel.ViewModelShowingList;

public class ViewModelFactory
{

  private static ViewModelFactory viewModelFactory;
  private ViewModelMovieList viewModelMovieList;
  private ViewModelShowingList viewModelShowingList;

  private ViewModelFactory(){}

  public static ViewModelFactory getInstance(){
    if (viewModelFactory == null){
      viewModelFactory = new ViewModelFactory();
    }
    return viewModelFactory;
  }

  public ViewModelMovieList getMovieListViewModel(){
    if (viewModelMovieList == null){
      viewModelMovieList = new ViewModelMovieList(ModelFactory.getInstance().getModel());
    }
    return viewModelMovieList;
  }

  public ViewModelShowingList getViewModelShowingList(){
    if (viewModelShowingList == null){
      viewModelShowingList = new ViewModelShowingList(ModelFactory.getInstance().getModel());
    }
    return viewModelShowingList;
  }

}
