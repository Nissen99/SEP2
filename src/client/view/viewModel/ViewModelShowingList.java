package client.view.viewModel;

import client.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.Movie;
import shared.Showing;

import java.sql.SQLException;

public class ViewModelShowingList
  {

    private Movie movie;
    private Model modelManger;
    private ObservableList<Showing> showings = FXCollections.observableArrayList();
    private Showing selectedShowing;

    public ViewModelShowingList(Model model, Movie movie)
  {
    this.modelManger = model;
    this.movie = movie;
  }




    public ObservableList<Showing> getAllShowings() throws SQLException
    {

      showings.removeAll(showings);

      showings.addAll(modelManger.getShowingList(movie));

      return showings;
    }

    public String getMovie(){
    return movie.getMovieTitle();
    }

    public void setSelectedShowing(Showing showing){
    this.selectedShowing = showing;
    }

    public Showing getSelectedShowing()
    {
      return selectedShowing;
    }
  }
