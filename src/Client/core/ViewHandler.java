package Client.core;

import Client.view.movieList.MovieListController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{

  private static ViewHandler viewHandler;
  private ViewModelFactory viewModelFactory;
  private Stage stage;

  private ViewHandler(ViewModelFactory instance){
    this.viewModelFactory = instance;
  }

  public void setStage(Stage stage)
  {
    this.stage = stage;
  }



  public static ViewHandler getInstance(){
    if (viewHandler == null){
      viewHandler = new ViewHandler(ViewModelFactory.getInstance());
    }
    return viewHandler;
  }


  public void start(Stage stage) throws Exception{
    setStage(stage);
    openView("../view/movieList/movieListView.fxml");
  }

  public void openView(String viewToOpen) throws IOException
  {
    Scene scene = null;
    FXMLLoader loader = new FXMLLoader();
    Parent root = null;

    loader.setLocation(getClass().getResource(viewToOpen));
    root = loader.load();


      MovieListController controller = loader.getController();
      controller.init();
      stage.setTitle("Movie List");



    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}

