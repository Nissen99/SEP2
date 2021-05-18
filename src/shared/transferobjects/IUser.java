package shared.transferobjects;

import java.io.Serializable;

public interface IUser extends Serializable
{
  public String getUserName();
  public int getUserID();
  public String getEmail();
  public String getPassword();
}
