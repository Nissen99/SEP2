package shared.transferobjects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class User implements Serializable
{
  private String name;
  private int userID;
  private String email;

  public User(int userID, String name, String email)
  {
    this.name = name;
    this.userID = userID;
    this.email = email;
  }

  public String getName()
  {
    return name;
  }


  public int getUserID()
  {
    return userID;
  }

  public String getEmail()
  {
    return email;
  }
}
