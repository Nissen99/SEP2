package Client.core;

import Client.model.Model;
import Client.model.ModelManager;

public class ModelFactory
{

  private static ModelFactory modelFactory;
  private Model model;

  private ModelFactory(){}

  public static ModelFactory getInstance(){
    if (modelFactory == null){
      modelFactory = new ModelFactory();
    }
    return modelFactory;
  }

  public Model getModel(){
    if (model == null){
      model = new ModelManager();
    }
    return model;
  }
}
