package client.core;

import client.network.RMIClient;

/**
 * Factory, Singleton og starter client
 * Lazy instantiation
 */

public class ClientFactory
{

  private static ClientFactory clientFactory;
  private RMIClient client;

  private ClientFactory(){}

  public static ClientFactory getInstance(){
    if (clientFactory == null){
      clientFactory = new ClientFactory();
    }
    return clientFactory;
  }

  public RMIClient getClient()
  {
    if (client == null){
      client = new RMIClient();
      client.startClient();
    }
    return client;
  }
}
