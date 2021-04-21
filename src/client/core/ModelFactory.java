package client.core;

import client.model.ClientModel;
import client.model.ClientModelManager;

public class ModelFactory
{

  private static ModelFactory modelFactory;
  private ClientModel clientModel;
  private ClientFactory clientFactory;

  private ModelFactory(ClientFactory clientFactory){
    this.clientFactory = clientFactory;
  }

  public static ModelFactory getInstance(){
    if (modelFactory == null){
      modelFactory = new ModelFactory(ClientFactory.getInstance());
    }
    return modelFactory;
  }

  public ClientModel getModel(){
    if (clientModel == null){
      clientModel = new ClientModelManager(ClientFactory.getInstance().getClient());
    }
    return clientModel;
  }
}
