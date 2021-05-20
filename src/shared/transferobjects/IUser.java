package shared.transferobjects;

import java.io.Serializable;

public interface IUser extends Serializable
{
  String getUserName();
  int getUserID();
  String getEmail();
  String getPassword();
  boolean equals(Object obj);
}
