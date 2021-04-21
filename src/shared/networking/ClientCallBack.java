package shared.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallBack extends Remote
{
  void update() throws RemoteException;
}
