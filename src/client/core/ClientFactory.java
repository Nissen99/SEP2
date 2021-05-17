package client.core;

import client.network.RMIClient;

/**
 * Singleton og starter client
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
