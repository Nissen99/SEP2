package shared.networking;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallBack extends Remote
{
  void update(PropertyChangeEvent evt) throws RemoteException;
}
