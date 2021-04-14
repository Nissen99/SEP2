package Client.view.viewModel;

import Client.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.Showing;

import java.sql.SQLException;

public class ViewModelShowingList
  {

  private Model modelManger;
  private ObservableList<Showing> showings = FXCollections.observableArrayList();


  public ViewModelShowingList(Model model)
  {
    this.modelManger = model;
  }




    public ObservableList<Showing> getAllShowings() throws SQLException
    {

      showings.removeAll(showings);

      showings.addAll(modelManger.getShowingList());

      return showings;
    }

    public String getMovie(){
    return modelManger.getSelectedMovie().getMovieTitle();
    }

    public void setSelectedShowing(Showing showing){
    modelManger.setSelectedShowing(showing);
    }

}
