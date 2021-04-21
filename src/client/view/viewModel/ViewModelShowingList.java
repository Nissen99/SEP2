package client.view.viewModel;

import client.model.ClientModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.Movie;
import shared.transferobjects.Showing;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewModelShowingList
  {

    private Movie movie;
    private ClientModel clientModelManger;
    private ObservableList<Showing> showings = FXCollections.observableArrayList();
    private Showing selectedShowing;

    public ViewModelShowingList(ClientModel clientModel, Movie movie)
  {
    this.clientModelManger = clientModel;
    this.movie = movie;
  }




    public ObservableList<Showing> getAllShowings()
        throws SQLException, RemoteException
    {

      showings.removeAll(showings);

      showings.addAll(clientModelManger.getShowingList(movie));

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
