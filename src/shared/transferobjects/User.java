package shared.transferobjects;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class User
{
  private String name;
  private int userID;

  public User(int userID, String name)
  {
    this.name = name;
    this.userID = userID;
  }

  public String getName()
  {
    return name;
  }

  public int getUserID()
  {
    return userID;
  }
}
