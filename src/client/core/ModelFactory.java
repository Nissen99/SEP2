package client.core;

import client.model.*;

public class ModelFactory
{

  private static ModelFactory modelFactory;
  private ClientModel clientModel;
  private ClientModelShowing clientModelShowing;
  private ClientModelBooking clientModelBooking;
  private ClientModelMovie clientModelMovie;
  private ClientFactory clientFactory;
  private ClientModelLogin clientModelLogin;
  private ClientModelCreateUser clientModelCreateUser;

  private ModelFactory(ClientFactory clientFactory)
  {
    this.clientFactory = clientFactory;
  }

  public static ModelFactory getInstance()
  {
    if (modelFactory == null)
    {
      modelFactory = new ModelFactory(ClientFactory.getInstance());
    }
    return modelFactory;
  }

  public ClientModel getModel()
  {
    if (clientModel == null)
    {
      clientModel = new ClientModelManager(
          ClientFactory.getInstance().getClient());
    }
    return clientModel;
  }

  public ClientModelShowing getModelShowing()
  {
    if (clientModelShowing == null)
    {
      clientModelShowing = (ClientModelShowing) new ClientModelManager(
          ClientFactory.getInstance().getClient());
    }
    return clientModelShowing;
  }

  public ClientModelBooking getModelBooking()
  {
    if (clientModelBooking == null)
    {
      clientModelBooking = (ClientModelBooking) new ClientModelManager(
          ClientFactory.getInstance().getClient());
    }
    return clientModelBooking;
  }

  public ClientModelMovie getModelMovie()
  {
    if (clientModelMovie == null)
    {
      clientModelMovie = (ClientModelMovie) new ClientModelManager(
          ClientFactory.getInstance().getClient());
    }
    return clientModelMovie;
  }

  public ClientModelLogin getModelLogin()
  {
    if (clientModelLogin == null)
    {
      clientModelLogin = new ClientModelManager(
          ClientFactory.getInstance().getClient());
    }
    return clientModelLogin;
  }

  public ClientModelCreateUser getModelCreateUser()
  {
    if (clientModelCreateUser == null)
    {
      clientModelCreateUser = new ClientModelManager(
          ClientFactory.getInstance().getClient());
    }
    return clientModelCreateUser;
  }

}
