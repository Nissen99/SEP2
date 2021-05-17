package client.core;

import client.model.*;

/**
 * Singleton
 */
public class ModelFactory
{

  private static ModelFactory modelFactory;
  private ClientModel clientModel;
  private ClientModelShowing clientModelShowing;
  private ClientModelBooking clientModelBooking;
  private ClientModelMovie clientModelMovie;
  private ClientModelLogin clientModelLogin;
  private ClientModelCreateUser clientModelCreateUser;
  private ClientModelShowingList clientModelShowingList;

  private ModelFactory()
  {
  }

  public static ModelFactory getInstance()
  {
    if (modelFactory == null)
    {
      modelFactory = new ModelFactory();
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
      clientModelShowing = new ClientModelShowingManager(
          ClientFactory.getInstance().getClient());
    }
    return clientModelShowing;
  }

  public ClientModelBooking getModelBooking()
  {
    if (clientModelBooking == null)
    {
      clientModelBooking = new ClientModelBookingManager(
          ClientFactory.getInstance().getClient());
    }
    return clientModelBooking;
  }

  public ClientModelMovie getModelMovie()
  {
    if (clientModelMovie == null)
    {
      clientModelMovie = new ClientModelMovieManager(
          ClientFactory.getInstance().getClient());
    }
    return clientModelMovie;
  }

  public ClientModelLogin getModelLogin()
  {
    if (clientModelLogin == null)
    {
      clientModelLogin = new ClientModelLoginManager(
          ClientFactory.getInstance().getClient());
    }
    return clientModelLogin;
  }

  public ClientModelCreateUser getModelCreateUser()
  {
    if (clientModelCreateUser == null)
    {
      clientModelCreateUser = new ClientModelCreateUserManager(
          ClientFactory.getInstance().getClient());
    }
    return clientModelCreateUser;
  }

  public ClientModelShowingList getShowingList(){

    if (clientModelShowingList == null){
      clientModelShowingList = new ClientModelShowingListManager(ClientFactory.getInstance()
          .getClient());
    }
    return clientModelShowingList;
  }

}
