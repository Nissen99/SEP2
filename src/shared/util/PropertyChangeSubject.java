package shared.util;


import shared.exception.ServerException;
import java.beans.PropertyChangeListener;

/**
 * Bruges til Observer pattern
 */

public interface PropertyChangeSubject
{
  void addPropertyChangeListener(PropertyChangeListener listener) throws
      ServerException;
  void removePropertyChangeListener(PropertyChangeListener listener) throws ServerException;
}
