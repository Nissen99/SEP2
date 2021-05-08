package shared.transferobjects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class User implements Serializable
{
  private String userName;
  private int userID;
  private String email;
  private String password;

  public User(int userID, String userName, String email, String password)
  {
    this.userName = userName;
    this.userID = userID;
    this.email = email;
    this.password = password;
  }

  public String getUserName()
  {
    return userName;
  }


  public int getUserID()
  {
    return userID;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPassword()
  {
    return password;
  }


}
