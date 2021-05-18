package shared.util;


import shared.exception.ServerException;

import java.beans.PropertyChangeListener;

public interface PropertyChangeSubject
{
  void addPropertyChangeListener(PropertyChangeListener listener) throws
      ServerException;
  void removePropertyChangeListener(PropertyChangeListener listener) throws ServerException;
}
