package Client.view;

import Client.core.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class CinemaBookingApp extends Application
{

  @Override public void start(Stage stage) throws Exception
  {

    ViewHandler.getInstance().start(stage);
  }
}
